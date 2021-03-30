package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.CodeDictTemporary;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictUploadVo;
import com.paic.ehis.cs.domain.vo.CodeDictVo;
import com.paic.ehis.cs.service.ICodeDictService;
import com.paic.ehis.cs.service.ICodeDictTemporaryService;
import com.paic.ehis.cs.utils.CodeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务码 Controller
 * 
 * @author sino
 * @date 2021-02-27
 */
@RestController
@RequestMapping("/dict/internal")
public class CodeDictController extends BaseController
{
    @Autowired
    private ICodeDictService codeDictService;

    @Autowired
    private ICodeDictTemporaryService codeDictTemporaryService;


    /**
     * 查询二级投诉 列表
     */
    @GetMapping("/selectClassifyLevel2")
    public AjaxResult selectClassifyLevel2(CodeDict codeDict){
        return AjaxResult.success(codeDictService.selectClassifyLevel2(codeDict));
    }

    /**
     * 查询二级投诉原因 列表
     */
    @GetMapping("/selectReasonLevel2")
    public AjaxResult selectReasonLevel2(CodeDict codeDict){
        return AjaxResult.success(codeDictService.selectReasonLevel2(codeDict));
    }


    /**
     * 查询三级投诉原因 列表
     */
    @GetMapping("/selectReasonLevel3")
    public AjaxResult selectReasonLevel3(CodeDict codeDict){
        return AjaxResult.success(codeDictService.selectReasonLevel3(codeDict));
    }




    /**
     * 查询业务码 列表
     */
    @GetMapping("/listWithParent")
    public TableDataInfo listWithParent(CodeDictDTO codeDictDTO)
    {
        startPage();
        codeDictDTO.setInsuranceSourceType(CodeTypeEnum.CS_INSURANCE_SOURCE.getCodeType());
        codeDictDTO.setComplaintBusinessType(CodeTypeEnum.CS_COMPLAINT_BUSINESS_ITEM.getCodeType());
        List<CodeDictVo> list = codeDictService.selectCodeDictVoList(codeDictDTO);
        return getDataTable(list);
    }

    /**
     * 查询业务码 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(CodeDict codeDict)
    {
        startPage();
        List<CodeDict> list = codeDictService.selectCodeDictList(codeDict);
        return getDataTable(list);
    }

    /**
     * 导出业务码 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CodeDict codeDict) throws IOException
    {
        List<CodeDict> list = codeDictService.selectCodeDictList(codeDict);
        ExcelUtil<CodeDict> util = new ExcelUtil<CodeDict>(CodeDict.class);
        util.exportExcel(response, list, "dict");
    }


    /**
     * 导出投诉分类 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:enum:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportNew")
    public void export(HttpServletResponse response, CodeDictDTO codeDictDTO) throws IOException
    {
        List<CodeDictVo> list = codeDictService.selectCodeEnumVo(codeDictDTO);
        ExcelUtil<CodeDictVo> util = new ExcelUtil<CodeDictVo>(CodeDictVo.class);
        util.exportExcel(response, list, "投诉业务类别");
    }

    /**
     * 导出模板
     */
//    @PreAuthorize("@ss.hasPermi('system:enum:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadTemplant")
    public void downloadTemplant(HttpServletResponse response, CodeDictDTO codeDictDTO) throws IOException
    {
        List<CodeDictVo> list = new ArrayList<>();
        ExcelUtil<CodeDictVo> util = new ExcelUtil<CodeDictVo>(CodeDictVo.class);
        util.exportExcel(response, list, "投诉业务类别");
    }


    @GetMapping("/updateCodeEnumVo")
    public AjaxResult updateCodeEnumVo(List<CodeDictVo> list){
        return AjaxResult.success(codeDictService.updateCodeDictVo(list));
    }


    /**
     * 获取业务码 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{codeType}")
    public AjaxResult getInfo(@PathVariable("codeType") String codeType)
    {
        return AjaxResult.success(codeDictService.selectCodeDictById(codeType));
    }

    /**
     * 新增业务码 
     */
//    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "业务码 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CodeDict codeDict)
    {
        return toAjax(codeDictService.insertCodeDict(codeDict));
    }

    /**
     * 修改业务码 
     */
//    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "业务码 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CodeDict codeDict)
    {
        return toAjax(codeDictService.updateCodeDict(codeDict));
    }

    /**
     * 删除业务码 
     */
//    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "业务码 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{codeTypes}")
    public AjaxResult remove(@PathVariable String[] codeTypes)
    {
        return toAjax(codeDictService.deleteCodeDictByIds(codeTypes));
    }

    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/uploadComplaintCategory")
    public AjaxResult uploadComplaintCategory(HttpServletResponse response, MultipartFile file) throws IOException
    {
        ExcelUtil<CodeDictVo> util = new ExcelUtil<CodeDictVo>(CodeDictVo.class);
        List<CodeDictVo> excelCaseList = null;
        try {
            excelCaseList = util.importExcel(file.getInputStream());
        }  catch (Exception ex) {
            return AjaxResult.error("表格解析失败，请检查数据，再进行上传！");
        }
        if (excelCaseList.size() == 0) {
            return AjaxResult.error("上传失败，Excel中数据为空！");
        }

        //获取导入批次流水号
        String tBatchNo = PubFun.createMySqlMaxNoUseCache("cs_upload_batchno",15,15);

        //定义投保业务类别LIST  防止重复插入
        List tREComplaint = new ArrayList();

        //数据存入临时表 投保来源
        for(int i=0;i<excelCaseList.size();i++){
            CodeDictVo tCodeDictVo = excelCaseList.get(i);
            CodeDictTemporary tCodeDictTemporary = new CodeDictTemporary();

            //投保来源
            tCodeDictTemporary.setCodeType("cs_insurance_source");
            tCodeDictTemporary.setBatchNo(tBatchNo);
            tCodeDictTemporary.setSerialNo(PubFun.createMySqlMaxNoUseCache("cs_upload_serialno",15,15));
            tCodeDictTemporary.setCode(tCodeDictVo.getInsuranceSourceCode());
            tCodeDictTemporary.setCodeName(tCodeDictVo.getInsuranceSourceName());
            tCodeDictTemporary.setStatus("0");//状态默认0暂存
            if(tCodeDictVo.getInsuranceSourceCode() != null && !"".equals(tCodeDictVo.getInsuranceSourceCode())){
                tCodeDictTemporary.setOrderNo(Long.parseLong(tCodeDictVo.getComplaintBusinessCode()));//顺序默认等于编号
            }
            tCodeDictTemporary.setRemarks("投保来源");
            tCodeDictTemporary.setReason("");
            tCodeDictTemporary.setParentCode(tCodeDictVo.getComplaintBusinessCode());
            tCodeDictTemporary.setCreatedBy(SecurityUtils.getUsername());
            tCodeDictTemporary.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            tCodeDictTemporary.setUpdatedBy(SecurityUtils.getUsername());
            tCodeDictTemporary.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            codeDictTemporaryService.insertCodeDictTemporary(tCodeDictTemporary);


            //存储投保业务类别 注意要去重
            tCodeDictTemporary = new CodeDictTemporary();
            tCodeDictTemporary.setCodeType("cs_complaint_business_item");
            tCodeDictTemporary.setBatchNo(tBatchNo);
            tCodeDictTemporary.setSerialNo(PubFun.createMySqlMaxNoUseCache("cs_upload_serialno",15,15));
            tCodeDictTemporary.setCode(tCodeDictVo.getComplaintBusinessCode());
            tCodeDictTemporary.setCodeName(tCodeDictVo.getComplaintBusinessName());
            tCodeDictTemporary.setStatus("0");//状态默认0暂存
            if(tCodeDictVo.getInsuranceSourceCode() != null && !"".equals(tCodeDictVo.getInsuranceSourceCode())){
                tCodeDictTemporary.setOrderNo(Long.parseLong(tCodeDictVo.getComplaintBusinessCode()));//顺序默认等于编号
            }
            tCodeDictTemporary.setRemarks("投诉业务类别");
            tCodeDictTemporary.setReason("");
            tCodeDictTemporary.setCreatedBy(SecurityUtils.getUsername());
            tCodeDictTemporary.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            tCodeDictTemporary.setUpdatedBy(SecurityUtils.getUsername());
            tCodeDictTemporary.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            if(i==0){//第一条数据投保来源一定要插入
                tREComplaint.add(tCodeDictTemporary.getCode()+tCodeDictTemporary.getCodeName());
                codeDictTemporaryService.insertCodeDictTemporary(tCodeDictTemporary);
            }

            Boolean tFlag = true;
            for(int a=0;a<tREComplaint.size();a++){
                if(tREComplaint.get(a).equals(tCodeDictTemporary.getCode()+tCodeDictTemporary.getCodeName())){
                    tFlag = false;//中间存在重复的 直接跳过 禁止重复插入
                    break;
                }
            }

            if(tFlag){
                tREComplaint.add(tCodeDictTemporary.getCode()+tCodeDictTemporary.getCodeName());
                codeDictTemporaryService.insertCodeDictTemporary(tCodeDictTemporary);
            }

        }

        //开始校验数据
        codeDictTemporaryService.updateInsuranceIDNotNUll(tBatchNo);///*投保来源ID不能为空*/
        codeDictTemporaryService.updateInsuranceNameNotNUll(tBatchNo);///*投保来源名称不能为空*/
        codeDictTemporaryService.updateBusinessIDNotNUll(tBatchNo);///*投保业务类别ID不能为空*/
        codeDictTemporaryService.updateBusinessNameNotNUll(tBatchNo);///*投保业务类别名称不能为空*/
        codeDictTemporaryService.updateInsuranceIDRE(tBatchNo);///*投保来源ID重复 ID相同*/
        codeDictTemporaryService.updateInsuranceIDRE2(tBatchNo);///*投保来源ID重复 ID相同 临时表主表数据对比*/
        codeDictTemporaryService.updateBusinessIDRE(tBatchNo);///*投保业务类别 ID相同  name不同 临时表数据对比
        codeDictTemporaryService.updateBusinessIDRE2(tBatchNo);///*投保业务类别 ID相同  name不同 临时表主表数据对比
        codeDictTemporaryService.updateBusinessToIns(tBatchNo);///*投保业务分类错误 则投保来源也错误*/
        codeDictTemporaryService.updateSuccess(tBatchNo);///*成功更新状态 变成01*/
        codeDictTemporaryService.updateFailure(tBatchNo);///*失败更新状态 变更09*/

        //校验完成后数据同步至主表
        //查询导入成功数据
        CodeDictTemporary tSelCodeDictTemporary = new CodeDictTemporary();
        tSelCodeDictTemporary.setBatchNo(tBatchNo);
        tSelCodeDictTemporary.setStatus("1");
        List<CodeDictTemporary> tSuccList = codeDictTemporaryService.selectCodeDictTemporaryList(tSelCodeDictTemporary);
        for(int i=0;i<tSuccList.size();i++){
            CodeDict tCodeDict = new CodeDict();
            //业务来源特殊处理  防止重复插入
            if("cs_complaint_business_item".equals(tSuccList.get(i).getCodeType())){
                //通过code + codetype查询是否存在数据
                //不存在则插入 防止重复插入
                tCodeDict.setCodeType("cs_complaint_business_item");
                tCodeDict.setCode(tSuccList.get(i).getCode());
                List<CodeDict> tOldCodeDictList = codeDictService.selectCodeDictList(tCodeDict);
                if(tOldCodeDictList == null || tOldCodeDictList.size()<=0){
                    tCodeDict.setCodeName(tSuccList.get(i).getCodeName());
                    tCodeDict.setStatus("1");
                    tCodeDict.setOrderNo(tSuccList.get(i).getOrderNo());
                    tCodeDict.setRemarks("投诉业务类别");
                    tCodeDict.setCreatedBy(SecurityUtils.getUsername());
                    tCodeDict.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                    tCodeDict.setUpdatedBy(SecurityUtils.getUsername());
                    tCodeDict.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                    codeDictService.insertCodeDict(tCodeDict);
                }
            }else{
                tCodeDict.setCodeType("cs_insurance_source");
                tCodeDict.setCode(tSuccList.get(i).getCode());
                tCodeDict.setCodeName(tSuccList.get(i).getCodeName());
                tCodeDict.setStatus("1");
                tCodeDict.setOrderNo(tSuccList.get(i).getOrderNo());
                tCodeDict.setRemarks("投保来源");
                tCodeDict.setParentCode(tSuccList.get(i).getParentCode());
                tCodeDict.setCreatedBy(SecurityUtils.getUsername());
                tCodeDict.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                tCodeDict.setUpdatedBy(SecurityUtils.getUsername());
                tCodeDict.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                codeDictService.insertCodeDict(tCodeDict);
            }

        }

        //查询导入失败数据 只查询cs_insurance_source 投保来源
        tSelCodeDictTemporary = new CodeDictTemporary();
        tSelCodeDictTemporary.setBatchNo(tBatchNo);
        tSelCodeDictTemporary.setStatus("9");
        tSelCodeDictTemporary.setCodeType("cs_insurance_source");
        List<CodeDictTemporary> tSBList = codeDictTemporaryService.selectCodeDictTemporaryList(tSelCodeDictTemporary);

        //查询导入成功数据 只查询cs_insurance_source 投保来源
        tSelCodeDictTemporary = new CodeDictTemporary();
        tSelCodeDictTemporary.setBatchNo(tBatchNo);
        tSelCodeDictTemporary.setStatus("1");
        tSelCodeDictTemporary.setCodeType("cs_insurance_source");
        List<CodeDictTemporary> tCGList = codeDictTemporaryService.selectCodeDictTemporaryList(tSelCodeDictTemporary);

        CodeDictUploadVo tCodeDictUploadVo= new CodeDictUploadVo();
        tCodeDictUploadVo.setCgNumber(tCGList.size()+"");
        tCodeDictUploadVo.setSbNumber(tSBList.size()+"");
        tCodeDictUploadVo.setBatchNo(tBatchNo);

        //先默认返回成功条数  优化了后期再改
        return AjaxResult.success(tCodeDictUploadVo);
    }
}
