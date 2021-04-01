package com.paic.ehis.common.core.utils.poi;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.annotation.Excels;
import com.paic.ehis.common.core.text.Convert;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.reflect.ReflectUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Excel相关处理
 *
 * @author admin
 */
public class ExcelUtils<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * Excel sheet最大行数，默认65536
     */
    public static final int sheetSize = 65536;

    /**
     * 工作表名称
     */
    private String sheetName;

    /**
     * 导出类型（EXPORT:导出数据；IMPORT：导入模板）
     */
    private Excel.Type type;

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 导入导出数据列表
     */
    private List<T> list;

    /**
     * 注解列表
     */
    private List<Object[]> fields;

    /**
     * 统计列表
     */
    private Map<Integer, Double> statistics = new HashMap<Integer, Double>();

    /**
     * 数字格式
     */
    private static final DecimalFormat DOUBLE_FORMAT = new DecimalFormat("######0.00");

    /**
     * 实体对象
     */
    public Class<T> clazz;

    public ExcelUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ExcelUtils() {

    }

    public void init(List<T> list, String sheetName, Excel.Type type) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.list = list;
        this.sheetName = sheetName;
        this.type = type;
        createExcelField();
        createWorkbook();
    }

    /**
     * 对excel表单默认第一个索引名转换成list
     *
     * @param sheet 输入流
     * @return 转换后集合
     */
    public List<T> importExcel(Sheet sheet) throws Exception {
        return importExcel(StringUtils.EMPTY, sheet);
    }

    /**
     * 对excel表单指定表格索引名转换成list
     *
     * @param sheetName 表格索引名
     * @param sheet        输入流
     * @return 转换后集合
     */
    public List<T> importExcel(String sheetName, Sheet sheet) throws Exception {
        this.type = Excel.Type.IMPORT;
//        this.wb = WorkbookFactory.create(is);
        List<T> list = new ArrayList<T>();
//        Sheet sheet = null;
//        if (StringUtils.isNotEmpty(sheetName)) {
//            // 如果指定sheet名,则取指定sheet中的内容.
//            sheet = wb.getSheet(sheetName);
//        } else {
//            // 如果传入的sheet名不存在则默认指向第1个sheet
//            sheet = wb.getSheetAt(0);
//        }

        if (sheet == null) {
            throw new IOException("文件sheet不存在");
        }

        int rows = sheet.getPhysicalNumberOfRows();

        if (rows > 0) {
            // 定义一个map用于存放excel列的序号和field.
            Map<String, Integer> cellMap = new HashMap<String, Integer>();
            // 获取表头
            Row heard = sheet.getRow(0);
            for (int i = 0; i < heard.getPhysicalNumberOfCells(); i++) {
                Cell cell = heard.getCell(i);
                if (StringUtils.isNotNull(cell)) {
                    String value = this.getCellValue(heard, i).toString();
                    cellMap.put(value, i);
                } else {
                    cellMap.put(null, i);
                }
            }
            // 有数据时才处理 得到类的所有field.
            Field[] allFields = clazz.getDeclaredFields();
            // 定义一个map用于存放列的序号和field.
            Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
            for (int col = 0; col < allFields.length; col++) {
                Field field = allFields[col];
                Excel attr = field.getAnnotation(Excel.class);
                if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == type)) {
                    // 设置类的私有字段属性可访问.
                    field.setAccessible(true);
                    Integer column = cellMap.get(attr.name());
                    if (column != null) {
                        fieldsMap.put(column, field);
                    }
                }
            }
            for (int i = 1; i < rows; i++) {
                // 从第2行开始取数据,默认第一行是表头.
                Row row = sheet.getRow(i);
                T entity = null;
                for (Map.Entry<Integer, Field> entry : fieldsMap.entrySet()) {
                    Object val = this.getCellValue(row, entry.getKey());

                    // 如果不存在实例则新建.
                    entity = (entity == null ? clazz.newInstance() : entity);
                    // 从map中得到对应列的field.
                    Field field = fieldsMap.get(entry.getKey());
                    // 取得类型,并根据对象类型设置值.
                    Class<?> fieldType = field.getType();
                    if (String.class == fieldType) {
                        String s = Convert.toStr(val);
                        if (StringUtils.endsWith(s, ".0")) {
                            val = StringUtils.substringBefore(s, ".0");
                        } else {
                            val = Convert.toStr(val);
                        }
                    } else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                        val = Convert.toInt(val);
                    } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                        val = Convert.toLong(val);
                    } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                        val = Convert.toDouble(val);
                    } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                        val = Convert.toFloat(val);
                    } else if (BigDecimal.class == fieldType) {
                        val = Convert.toBigDecimal(val);
                    } else if (Date.class == fieldType) {
                        if (val instanceof String) {
                            val = DateUtils.parseDate(val);
                        } else if (val instanceof Double) {
                            val = DateUtil.getJavaDate((Double) val);
                        }
                    }
                    if (StringUtils.isNotNull(fieldType)) {
                        Excel attr = field.getAnnotation(Excel.class);
                        String propertyName = field.getName();
                        if (StringUtils.isNotEmpty(attr.targetAttr())) {
                            propertyName = field.getName() + "." + attr.targetAttr();
                        } else if (StringUtils.isNotEmpty(attr.readConverterExp())) {
                            val = reverseByExp(Convert.toStr(val), attr.readConverterExp(), attr.separator());
                        }
                        ReflectUtils.invokeSetter(entity, propertyName, val);
                    }
                }
                list.add(entity);
            }
        }
        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param response  返回数据
     * @param
     * @param sheetTitle 工作表的名称
     * @return 结果
     * @throws IOException
     */
    public void exportExcel(HttpServletResponse response, Workbook workbook,
                            String[] sheetTitle, List<String[]> headers, List<List<String>> result1,List<List<String>> result2) throws IOException {
        int[] sheetNum=new int[]{0,1};
        OutputStream outputStream = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            outputStream = response.getOutputStream();

            exportExcel(workbook,sheetNum[0],sheetTitle[0],headers.get(0),result1);
            exportExcel(workbook,sheetNum[1],sheetTitle[1],headers.get(1),result2);
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            outputStream.close();
        }

    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public void importTemplateExcel(HttpServletResponse response, String sheetName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        this.init(null, sheetName, Excel.Type.IMPORT);
//        exportExcel(response.getOutputStream());
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @return 结果
     */
    public void exportExcel(Workbook workbook, int sheetNum, String sheetTitle, String[] headers, List<List<String>> result1) throws IOException {

        // 生成一个表格
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.protectSheet("");
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        Row row = sheet.createRow(0);
        for (int i=0;i<headers.length;i++){
            Cell cell = row.createCell((short) i);

            cell.setCellStyle(style);
            RichTextString text = new HSSFRichTextString(headers[i]);
            /*String text;
            if (headers[i] == null) {
                text= new String("");
            } else {
                text = new String(headers[i]);
            }*/
            cell.setCellValue(text.toString());
        }
        if (result1 != null) {
            int index = 1;
            for (List<String> m : result1) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    Cell cell = row.createCell((short) cellIndex);
                    cell.setCellValue(str);
                    cellIndex++;
                }
                index++;
            }
        }
    }

    //根据03还是07版本的Excel创建不同wb对象
    public static Workbook getWorkbook(InputStream inStr, String filePath) throws Exception {
        Workbook wb = null;
        String fileType = filePath.substring(filePath.lastIndexOf("."));
        System.out.println(fileType);
        if (".xls".equals(fileType)) {
            wb = new HSSFWorkbook(inStr); // 2003-
        } else if (".xlsx".equals(fileType)) {
            wb = new XSSFWorkbook(inStr); // 2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    // 读取多个sheet页面方法
    private static Map<String, List<List<String>>> moreSheetImport(MultipartFile file) throws Exception {
        Map<String, List<List<String>>> tempMap = new HashMap<>();
        InputStream is = file.getInputStream();
        Workbook wb = getWorkbook(is,file.getName());
        int sheetNum = wb.getNumberOfSheets();
        for (int j = 0; j < sheetNum; j++) {
            List<List<String>> lists = new ArrayList<List<String>>();
            Sheet sheet = wb.getSheetAt(j);
            int rowstart = sheet.getFirstRowNum();
            int rowEnd = sheet.getLastRowNum();
            for (int i = rowstart; i <= rowEnd; i++) {// 从第一行表头开始读
                Row xssRow = sheet.getRow(i);
                if (null == xssRow)
                    continue;
                int cellStart = xssRow.getFirstCellNum();
                if (cellStart > -1) {
                    int cellEnd = xssRow.getLastCellNum() - 1;//因为总大1,减去
                    List<String> listmini = new ArrayList<String>();
                    for (int k = cellStart; k <= cellEnd; k++) {
                        Cell cell = xssRow.getCell(k);
//                        String value = getCellStringValue(cell);
//                        listmini.add(value);
                    }
                    if (listmini.size() != 0) {
                        lists.add(listmini);
                    }
                }
            }
            tempMap.put(String.valueOf(j), lists);
        }
        System.out.println(tempMap);
        return tempMap;
    }

    /**
     * 填充excel数据
     *
     * @param index 序号
     * @param row   单元格行
     */
    public void fillExcelData(int index, Row row) {
        int startNo = index * sheetSize;
        int endNo = Math.min(startNo + sheetSize, list.size());
        for (int i = startNo; i < endNo; i++) {
            row = sheet.createRow(i + 1 - startNo);
            // 得到导出对象.
            T vo = (T) list.get(i);
            int column = 0;
            for (Object[] os : fields) {
                Field field = (Field) os[0];
                Excel excel = (Excel) os[1];
                // 设置实体类私有属性可访问
                field.setAccessible(true);
                this.addCell(excel, row, vo, field, column++);
            }
        }
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font totalFont = wb.createFont();
        totalFont.setFontName("Arial");
        totalFont.setFontHeightInPoints((short) 10);
        style.setFont(totalFont);
        styles.put("total", style);

        return styles;
    }

    /**
     * 创建单元格
     */
    public Cell createCell(Excel attr, Row row, int column) {
        // 创建列
        Cell cell = row.createCell(column);
        // 写入列信息
        cell.setCellValue(attr.name());
        setDataValidation(attr, row, column);
        cell.setCellStyle(styles.get("header"));
        return cell;
    }

    /**
     * 设置单元格信息
     *
     * @param value 单元格值
     * @param attr  注解相关
     * @param cell  单元格信息
     */
    public void setCellVo(Object value, Excel attr, Cell cell) {
        if (Excel.ColumnType.STRING == attr.cellType()) {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(StringUtils.isNull(value) ? attr.defaultValue() : value + attr.suffix());
        } else if (Excel.ColumnType.NUMERIC == attr.cellType()) {
            cell.setCellType(CellType.NUMERIC);
            cell.setCellValue(Integer.parseInt(value + ""));
        }
    }

    /**
     * 创建表格样式
     */
    public void setDataValidation(Excel attr, Row row, int column) {
        if (attr.name().indexOf("注：") >= 0) {
            sheet.setColumnWidth(column, 6000);
        } else {
            // 设置列宽
            sheet.setColumnWidth(column, (int) ((attr.width() + 0.72) * 256));
            row.setHeight((short) (attr.height() * 20));
        }
        // 如果设置了提示信息则鼠标放上去提示.
        if (StringUtils.isNotEmpty(attr.prompt())) {
            // 这里默认设了2-101列提示.
            setXSSFPrompt(sheet, "", attr.prompt(), 1, 100, column, column);
        }
        // 如果设置了combo属性则本列只能选择不能输入
        if (attr.combo().length > 0) {
            // 这里默认设了2-101列只能选择不能输入.
            setXSSFValidation(sheet, attr.combo(), 1, 100, column, column);
        }
    }

    /**
     * 添加单元格
     */
    public Cell addCell(Excel attr, Row row, T vo, Field field, int column) {
        Cell cell = null;
        try {
            // 设置行高
            row.setHeight((short) (attr.height() * 20));
            // 根据Excel中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
            if (attr.isExport()) {
                // 创建cell
                cell = row.createCell(column);
                cell.setCellStyle(styles.get("data"));

                // 用于读取对象中的属性
                Object value = getTargetValue(vo, field, attr);
                String dateFormat = attr.dateFormat();
                String readConverterExp = attr.readConverterExp();
                String separator = attr.separator();
                if (StringUtils.isNotEmpty(dateFormat) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(DateUtils.parseDateToStr(dateFormat, (Date) value));
                } else if (StringUtils.isNotEmpty(readConverterExp) && StringUtils.isNotNull(value)) {
                    cell.setCellValue(convertByExp(Convert.toStr(value), readConverterExp, separator));
                } else if (value instanceof BigDecimal && -1 != attr.scale()) {
                    cell.setCellValue((((BigDecimal) value).setScale(attr.scale(), attr.roundingMode())).toString());
                } else {
                    // 设置列类型
                    setCellVo(value, attr, cell);
                }
                addStatisticsData(column, Convert.toStr(value), attr);
            }
        } catch (Exception e) {
            log.error("导出Excel失败{}", e);
        }
        return cell;
    }

    /**
     * 设置 POI XSSFSheet 单元格提示
     *
     * @param sheet         表单
     * @param promptTitle   提示标题
     * @param promptContent 提示内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     */
    public void setXSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow,
                              int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createCustomConstraint("DD1");
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        dataValidation.createPromptBox(promptTitle, promptContent);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public void setXSSFValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String convertByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[0].equals(value)) {
                        propertyString.append(itemArray[1] + separator);
                        break;
                    }
                }
            } else {
                if (itemArray[0].equals(propertyValue)) {
                    return itemArray[1];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 反向解析值 男=0,女=1,未知=2
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String reverseByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(",");
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtils.containsAny(separator, propertyValue)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[1].equals(value)) {
                        propertyString.append(itemArray[0] + separator);
                        break;
                    }
                }
            } else {
                if (itemArray[1].equals(propertyValue)) {
                    return itemArray[0];
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 合计统计信息
     */
    private void addStatisticsData(Integer index, String text, Excel entity) {
        if (entity != null && entity.isStatistics()) {
            Double temp = 0D;
            if (!statistics.containsKey(index)) {
                statistics.put(index, temp);
            }
            try {
                temp = Double.valueOf(text);
            } catch (NumberFormatException e) {
            }
            statistics.put(index, statistics.get(index) + temp);
        }
    }

    /**
     * 创建统计行
     */
    public void addStatisticsRow() {
        if (statistics.size() > 0) {
            Cell cell = null;
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            Set<Integer> keys = statistics.keySet();
            cell = row.createCell(0);
            cell.setCellStyle(styles.get("total"));
            cell.setCellValue("合计");

            for (Integer key : keys) {
                cell = row.createCell(key);
                cell.setCellStyle(styles.get("total"));
                cell.setCellValue(DOUBLE_FORMAT.format(statistics.get(key)));
            }
            statistics.clear();
        }
    }

    /**
     * 获取bean中的属性值
     *
     * @param vo    实体对象
     * @param field 字段
     * @param excel 注解
     * @return 最终的属性值
     * @throws Exception
     */
    private Object getTargetValue(T vo, Field field, Excel excel) throws Exception {
        Object o = field.get(vo);
        if (StringUtils.isNotEmpty(excel.targetAttr())) {
            String target = excel.targetAttr();
            if (target.indexOf(".") > -1) {
                String[] targets = target.split("[.]");
                for (String name : targets) {
                    o = getValue(o, name);
                }
            } else {
                o = getValue(o, target);
            }
        }
        return o;
    }

    /**
     * 以类的属性的get方法方法形式获取值
     *
     * @param o
     * @param name
     * @return value
     * @throws Exception
     */
    private Object getValue(Object o, String name) throws Exception {
        if (StringUtils.isNotEmpty(name)) {
            Class<?> clazz = o.getClass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            o = field.get(o);
        }
        return o;
    }

    /**
     * 得到所有定义字段
     */
    private void createExcelField() {
        this.fields = new ArrayList<Object[]>();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempFields) {
            // 单注解
            if (field.isAnnotationPresent(Excel.class)) {
                putToField(field, field.getAnnotation(Excel.class));
            }

            // 多注解
            if (field.isAnnotationPresent(Excels.class)) {
                Excels attrs = field.getAnnotation(Excels.class);
                Excel[] excels = attrs.value();
                for (Excel excel : excels) {
                    putToField(field, excel);
                }
            }
        }
        this.fields = this.fields.stream().sorted(Comparator.comparing(objects -> ((Excel) objects[1]).sort())).collect(Collectors.toList());
    }

    /**
     * 放到字段集合中
     */
    private void putToField(Field field, Excel attr) {
        if (attr != null && (attr.type() == Excel.Type.ALL || attr.type() == type)) {
            this.fields.add(new Object[]{field, attr});
        }
    }

    /**
     * 创建一个工作簿
     */
    public void createWorkbook() {
        this.wb = new SXSSFWorkbook(500);
    }

    /**
     * 创建工作表
     *
     * @param sheetNo sheet数量
     * @param index   序号
     */
    public void createSheet(double sheetNo, int index) {
        this.sheet = wb.createSheet();
        this.styles = createStyles(wb);
        // 设置工作表的名称.
        if (sheetNo == 0) {
            wb.setSheetName(index, sheetName);
        } else {
            wb.setSheetName(index, sheetName + index);
        }
    }

    /**
     * 获取单元格值
     *
     * @param row    获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        if (row == null) {
            return row;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (StringUtils.isNotNull(cell)) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val); // POI Excel 日期格式转换
                    } else {
                        if ((Double) val % 1 > 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellTypeEnum() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }

            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }


    /**
     * 得到Workbook对象
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkBook(MultipartFile file) throws IOException{
        //这样写  excel 能兼容03和07
        InputStream is = file.getInputStream();
        Workbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (Exception ex) {
            is =file.getInputStream();
            hssfWorkbook = new XSSFWorkbook(is);
        }
        return hssfWorkbook;
    }

    /**
     * 得到错误信息
     * @param sb
     * @param list
     * @param i
     * @param obj
     * @param name  用哪个属性名去表明不和规定的数据
     * @param msg
     * @throws Exception
     */
    public static void getWrongInfo(StringBuilder sb,List list,int i,Object obj,String name,String msg) throws Exception{
        Class clazz=obj.getClass();
        Object str=null;
        //得到属性名数组
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields){
            if(f.getName().equals(name)){
                //用来得到属性的get和set方法
//                PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
//                //得到get方法
//                Method getMethod=pd.getReadMethod();
//                str = getMethod.invoke(obj);
            }
        }
        if(i==0)
            sb.append(msg+str+";");
        else if(i==(list.size()-1))
            sb.append(str+"</br>");
        else
            sb.append(str+";");
    }


   /* public Object excelImport(MultipartFile file) throws IOException {
        //根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页
        Workbook hssfWorkbook = ExcelUtils.getWorkBook(file);
        StringBuilder sb=new StringBuilder();
        try {
            ImportParams params = new ImportParams();
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                //表头在第几行
                params.setTitleRows(0);
                //距离表头中间有几行不要的数据
                //params.setStartRows(1);
                //第几个sheet页
                params.setStartSheetIndex(numSheet);
                //验证数据
                params.setNeedVerfiy(true);

                ExcelImportResult<Object> result=null;
                if(numSheet==0){
                    result = ExcelImportUtil.importExcelMore(file.getInputStream(),
                            Entity1.class, params);
                    //插入验证合格的数据
                    //CallServiceUtil.callDataService("", "", new Object[] { result.getList() },
                    //new Class[] { List.class });
                }else if(numSheet==1){
                    result = ExcelImportUtil.importExcelMore(file.getInputStream(),
                            Entity2.class, params);
                    //插入验证合格的数据
                }//............

                List list=null;
                //如果有些数据验证出来有误   为true
                if(result.isVerfiyFail()){
                    //不合规定的数据
                    list=result.getFailList();
                    //拼凑错误信息,自定义
                    for(int i=0;i<list.size();i++){
                        if(list.get(i) instanceof Entity1)
                            ExcelUtil.getWrongInfo(sb, list, i, list.get(i), "realName", "Entity1信息不符");
                        else if(list.get(i) instanceof Entity2)
                            ExcelUtil.getWrongInfo(sb, list, i, list.get(i), "age", "Entity2信息不符");
                        //....
                    }
                }
            }
            if(sb.length()!=0){
                return renderError(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("导入失败！请检查导入文档的格式是否正确");
        }
        return renderSuccess("导入成功！");
    }*/
}