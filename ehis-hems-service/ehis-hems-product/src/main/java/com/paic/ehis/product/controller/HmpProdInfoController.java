package com.paic.ehis.product.controller;

import com.paic.ehis.product.domain.*;
import com.paic.ehis.product.service.*;
import com.paic.ehis.common.core.utils.PathUtil;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.file.FileUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品定义Controller
 *
 * @author sino
 * @date 2020-09-19
 */
@RestController
@RequestMapping("/definition")
public class HmpProdInfoController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(HmpProdInfoController.class);

    @Autowired
    private IHmpProdInfoService hmpProdInfoService;

    @Autowired
    private IHmpProdServComService hmpProdServComService;

    @Autowired
    private IHmpFileInfoService hmpFileInfoService;

    @Autowired
    private IHmpAuditTrackService hmpAuditTrackService;

    @Autowired
    private IHmpServProjectService hmpServProjectService;

    /**
     * 查询产品定义列表
     */
    @PreAuthorize("@ss.hasPermi('system:definition:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpProdInfo hmpProdInfo)
    {
        String allowedtocancel = hmpProdInfo.getAllowedtocancel();
        log.debug("查询所有产品信息");
        startPage();
        List<HmpProdInfo> list = hmpProdInfoService.selectHmpProdInfoList(hmpProdInfo);
        return getDataTable(list);
    }

    /**
     * 查询服务类型
     */
    @GetMapping("/getServiceType")
    public AjaxResult getServiceType(HmpProdInfo hmpProdInfo)
    {
        return AjaxResult.success(hmpProdInfoService.selectServiceType(hmpProdInfo));
    }

    /**
     * 查询产品类别
     */
    @GetMapping("/getProductClass")
    public AjaxResult getProductClass(HmpProdInfo hmpProdInfo)
    {
        return AjaxResult.success(hmpProdInfoService.selectProductClass(hmpProdInfo));
    }

    /**
     * 查询产品细类
     */
    @GetMapping("/getProductSubClass")
    public AjaxResult getProductSubClass(HmpProdInfo hmpProdInfo)
    {
        return AjaxResult.success(hmpProdInfoService.selectProductSubClass(hmpProdInfo));
    }

    /**
     * 查询服务机构名称
     */
    @GetMapping("/getServiceComNo")
    public AjaxResult getServiceComNo(HmpProdServCom hmpProdServCom)
    {
        return AjaxResult.success(hmpProdServComService.selectSerivceComNo(hmpProdServCom));
    }

    /**
     * 查询服务机构网点
     */
    @GetMapping("/getServiceNetWorkList")
    public TableDataInfo getServiceNetWorkList(HmpServOpera hmpServOpera)
    {
        startPage();
        HmpProdServCom hmpProdServCom = new HmpProdServCom();
        hmpProdServCom.setSerivcecomno(hmpServOpera.getServcomno());
        List<HmpComWebSite> list = hmpProdServComService.selectServiceNetWorkList(hmpProdServCom);
        return getDataTable(list);
    }

    /**
     * 查询所有省份
     */
    @GetMapping("/getProvinceAll")
    public AjaxResult getProvinceAll(HmpProdSalCom hmpProdSalCom)
    {
        return AjaxResult.success(hmpProdInfoService.selectProvinceAll(hmpProdSalCom));
    }

    /**
     * 查询城市
     */
    @GetMapping("/getCityByProvince")
    public AjaxResult getCityByProvince(HmpProdSalCom hmpProdSalCom)
    {
        return AjaxResult.success(hmpProdInfoService.selectCityByProvince(hmpProdSalCom));
    }

    /**
     * 查询地区
     */
    @GetMapping("/getDistrictByCity")
    public AjaxResult getDistrictByCity(HmpProdSalCom hmpProdSalCom)
    {
        return AjaxResult.success(hmpProdInfoService.selectDistrictByCity(hmpProdSalCom));
    }

    /**
     * 导出产品定义列表
     */
    @PreAuthorize("@ss.hasPermi('system:definition:export')")
    @Log(title = "产品定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,@RequestBody HmpProdInfo hmpProdInfo) throws IOException
    {
        List<HmpProdInfo> list = hmpProdInfoService.selectHmpProdInfoList(hmpProdInfo);
        ExcelUtil<HmpProdInfo> util = new ExcelUtil<HmpProdInfo>(HmpProdInfo.class);
        util.exportExcel(response, list, "产品信息");
    }

    /**
     * 获取产品定义详细信息
     */
    @PostMapping(value = "/getProductInfoById")
    public AjaxResult getInfo(@RequestBody String productno)
    {
        return AjaxResult.success(hmpProdInfoService.selectHmpProdInfoById(productno));
    }

    /**
     * 新增产品定义
     */
    @PreAuthorize("@ss.hasPermi('system:definition:addProduct')")
    @Log(title = "产品定义", businessType = BusinessType.INSERT)
    @PostMapping("/addProduct")
    public AjaxResult addProduct(@RequestBody ProductInfo product)
    {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();

        //转化请求中的数据为实体类
        HmpProdInfo productInfo = product.getProdInfo();

        if(StringUtils.isEmpty(productInfo.getProductname())){
            return AjaxResult.error("服务产品名称为空，请确认！");
        }else {
            if("".equals(productInfo.getProductno()) ||  "null".equals(productInfo.getProductno()) || productInfo.getProductno() == null){
                HmpProdInfo queryProductInfo = new HmpProdInfo();
                queryProductInfo.setProductname(productInfo.getProductname());
                List<HmpProdInfo> dbProdInfos = hmpProdInfoService.selectHmpProdInfoList(queryProductInfo);
                if(dbProdInfos.size() > 0){
                    return AjaxResult.error("当前新增的[产品名称]在系统中已存在，请再次确认后重新录入!");
                }
            }else {
                HmpProdInfo queryProductInfo = new HmpProdInfo();
                queryProductInfo.setProductname(productInfo.getProductname());
                List<HmpProdInfo> dbProdInfos = hmpProdInfoService.selectHmpProdByName(queryProductInfo);
                if(dbProdInfos.size() > 0){
                    for (int i = 0; i < dbProdInfos.size(); i++) {
                        if(!dbProdInfos.get(i).getProductno().equals(productInfo.getProductno())){
                            return AjaxResult.error("当前修改的[产品名称]在系统中已存在，请再次确认后重新录入!");
                        }
                    }
                }
            }
        }
        String tAgeMin = productInfo.getAgemin();//适用年龄
        String tAgeMax = productInfo.getAgemax();//适用年龄
        if (!("".equals(tAgeMin) || "null".equals(tAgeMin) || null == tAgeMin) && !("".equals(tAgeMax) || "null".equals(tAgeMax) || null == tAgeMax)) {
            int ageMin = Integer.parseInt(tAgeMin);
            int AgeMax = Integer.parseInt(tAgeMax);
            if (ageMin > AgeMax) {
                return AjaxResult.error("录入的[适用年龄]区间错误，请确认！");
            }
        }
//        Date tSalStartDate = productInfo.getSalstartdate();
//        Date tSalEndDate = productInfo.getSalenddate();
//        if (!(null == tSalStartDate) && !(null == tSalEndDate) && tSalStartDate.compareTo(tSalEndDate) > 0) {
//            return AjaxResult.error("录入的[销售起期]大于[销售止期]，请确认！");
//        }

        int result;
        if(StringUtils.isEmpty(productInfo.getProductno())){

            productInfo.setProductno("HP"+PubFun.createMySqlMaxNoUseCache("ProdInfo",10,8));
            //为不为空数据赋值
            productInfo.setProductstate("01");
            productInfo.setOperator(username);
            productInfo.setMakedate(new Date());
            productInfo.setMaketime(PubFun.getCurrentTime());
            productInfo.setModifyoperator(username);
            productInfo.setModifydate(new Date());
            productInfo.setModifytime(PubFun.getCurrentTime());

            //入库
            result = hmpProdInfoService.insertHmpProdInfo(productInfo);
        }else{

            HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productInfo.getProductno());
            //修改数据赋值
            productInfo.setProductno(productInfoDB.getProductno());
            productInfo.setProductstate(productInfoDB.getProductstate());
            productInfo.setModifyoperator(username);
            productInfo.setModifydate(new Date());
            productInfo.setModifytime(PubFun.getCurrentTime());

            //修改入库
            hmpProdInfoService.updateHmpProdInfo(productInfo);
            result = 0;
        }

        //获取入库的产品编码，数据库自动生成的编码可以通过实体类直接获取。
        String productNo = productInfo.getProductno();

        //关联服务机构处理入库
        if(result > 0){
            //服务项目
            List<HmpServProject> servProjects = product.getServProjects();
            //供应商
            List<HmpServOpera> servOperas = product.getServOperas();
            //服务网点
            List<HmpComWebSite> comWebSites = product.getComWebSites();

            HmpProdServCom hmpProdServCom = new HmpProdServCom();
            //存入项目编码
            if (servProjects!=null && servProjects.size()==1){
                hmpProdServCom.setAlternatefield1(servProjects.get(0).getProjectcode());
            }
            //存入供应商、网点
            if(servOperas!=null && servOperas.size()==1){
                HmpServOpera hmpServOpera = servOperas.get(0);
                hmpProdServCom.setSerivcecomno(hmpServOpera.getServcomno());
                hmpProdServCom.setSerivcecomtype(hmpServOpera.getServcomtype());

                if(comWebSites!=null && comWebSites.size() > 0){
                    for (HmpComWebSite comWebSite : comWebSites) {
                        hmpProdServCom.setContactcode(comWebSite.getWebsitecode());
                    }
                }else{
                    hmpProdServCom.setContactcode("000000");
                }

//                if(StringUtils.isEmpty(hmpProdServCom.getCurrency())){
//                    //默认币种为CNY-人民币
//                    hmpProdServCom.setCurrency("CNY");
//                }

                hmpProdServCom.setProductno(productNo);

                hmpProdServCom.setOperator(username);
                hmpProdServCom.setMakedate(new Date());
                hmpProdServCom.setMaketime(PubFun.getCurrentTime());
                hmpProdServCom.setModifydate(new Date());
                hmpProdServCom.setModifytime(PubFun.getCurrentTime());

                hmpProdServComService.deleteHmpProdServComById(productNo);
                hmpProdServComService.insertHmpProdServCom(hmpProdServCom);
            }
        }
        return AjaxResult.success(productInfo);
    }

    /**
     * 查询服务机构和网点
     */
    @PostMapping("/queryServiceOrgAndNetWork")
    public AjaxResult queryServiceOrgAndNetWork(@RequestBody String productNo)
    {
        AjaxResult ajax = AjaxResult.success();
        if (!StringUtils.isEmpty(productNo))
        {
            HmpServProject servProject = hmpServProjectService.selectHmpServProjectByProductNo(productNo);
            List<HmpProdServCom> productServiceComSelects = hmpProdServComService.queryServiceOrg(productNo);
            if (productServiceComSelects!=null && productServiceComSelects.size()==1){
                String serivcecomno = productServiceComSelects.get(0).getSerivcecomno();
                HmpServOpera servOpera = hmpServProjectService.selectHmpServOperaById(serivcecomno);
                ArrayList<HmpServOpera> hmpServOperaList = new ArrayList<>();
                hmpServOperaList.add(servOpera);
                ajax.put("serviceComs",hmpServOperaList);
            }
            List<HmpComWebSite> comWebSites = hmpProdServComService.queryServiceNetWork(productNo);
            ajax.put("serviceNetWorks", comWebSites);

            ArrayList<HmpServProject> hmpServProjectList = new ArrayList<>();
            hmpServProjectList.add(servProject);
            ajax.put("serviceProject",hmpServProjectList);
        }
        System.out.println(ajax);
        return ajax;
    }

    /**
     * 删除产品定义
     */
    @PreAuthorize("@ss.hasPermi('system:definition:remove')")
    @Log(title = "产品定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productnos}")
    public AjaxResult remove(@PathVariable String[] productnos)
    {
        int result = hmpProdInfoService.deleteHmpProdInfoByIds(productnos);
//        String[] productnos = new String[productnos.length];
//        for (int i = 0; i < productids.length; i++) {
//            HmpProdInfo infor = hmpProdInfoService.selectHmpProdInfoById(productids[i]);
//            productnos[i] = infor.getProductno();
//        }
        if(result > 0 && productnos.length > 0 ){
            result = hmpProdServComService.deleteHmpProdServComByIds(productnos);
        }
        return toAjax(result);
    }

    /**
     * 添加保存服务地区
     */
    @PreAuthorize("@ss.hasPermi('system:definition:saveServiceCity')")
    @Log(title = "产品定义添加服务地区", businessType = BusinessType.INSERT)
    @PostMapping("/saveServiceCity")
    public AjaxResult saveServiceCity(@RequestBody ProductInfo product)
    {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();

        //转化请求中的数据为实体类
        //Object info = paramMap.get("productInfo");
        HmpProdInfo productInfo = product.getProdInfo();

        HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productInfo.getProductno());

        //获取库中的产品编码，因为编码不能改变，这里防止页面改编码
        String productNo = productInfoDB.getProductno();

        int result = 0;
        //关联服务地区处理入库
        if(!StringUtils.isEmpty(productNo)){

            List<HmpProdSalCom> productSalComs = product.getProdSalComs();

            for (int i = 0; i < productSalComs.size(); i++) {
                HmpProdSalCom salCom = productSalComs.get(i);
                salCom.setProductno(productNo);
                if(StringUtils.isEmpty(salCom.getSalcity())){
                    salCom.setSalcity("000");
                }
                if(StringUtils.isEmpty(salCom.getSalarea())){
                    salCom.setSalarea("000");
                }
                salCom.setOperator(username);
                salCom.setMakedate(new Date());
                salCom.setMaketime(PubFun.getCurrentTime());
                salCom.setModifydate(new Date());
                salCom.setModifytime(PubFun.getCurrentTime());
            }
            hmpProdInfoService.deleteHmpProdSalComByProductNo(productNo);
            result = hmpProdInfoService.betchInsertHmpProdSalCom(productSalComs);

        }
        if(result > 0){
            return AjaxResult.success();
        }else {
            return AjaxResult.error("添加服务地区失败！");
        }
    }

    /**
     * 查询服务地区
     */
    @PostMapping("/queryServiceCity")
    public AjaxResult queryServiceCity(@RequestBody String productNo)
    {
        AjaxResult ajax = AjaxResult.success();
        if (!StringUtils.isEmpty(productNo))
        {
            List<HmpProdSalCom> productSalComSelects = hmpProdInfoService.queryServiceCity(productNo);
            ajax.put("salComs", productSalComSelects);
        }
        return ajax;
    }

    /**
     * 产品提交审核
     */
    @PreAuthorize("@ss.hasPermi('system:definition:productAudit')")
    @Log(title = "产品定义提交审核", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/productAudit")
    public AjaxResult productAudit(@RequestBody HmpProdInfo productInfo)
    {

        HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productInfo.getProductno());
        if("02".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已提交审核，不可再次操作，请确认！");
        }
        productInfoDB.setProductstate("02");
        int i = hmpProdInfoService.updateHmpProdInfo(productInfoDB);
        if(i > 0){
            return AjaxResult.success(productInfoDB);
        }else{
            return AjaxResult.error("产品提交审核失败！");
        }
    }

    @Log(title = "产品服务条款导入", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:definition:uploadFile')")
    @PostMapping("/uploadFile")
    public AjaxResult importData(MultipartFile file, @RequestParam String productNo, @RequestParam String fileDesc) throws Exception
    {

        // 获取登录用户名称
        String username = SecurityUtils.getUsername();

        if(file.isEmpty()){
            return AjaxResult.error("上传文件为空！");
        }

        //获取产品信息
        HmpProdInfo productInfo = hmpProdInfoService.selectHmpProdInfoById(productNo);

        HmpFileInfo queryFileInput = new HmpFileInfo();
        queryFileInput.setFilesource(productInfo.getProductno());
        queryFileInput.setFilename(file.getOriginalFilename());
        List<HmpFileInfo> fileInputDownloads = hmpFileInfoService.selectHmpFileInfoList(queryFileInput);
        if(fileInputDownloads.size() > 0){
            return AjaxResult.error("不能上传相同文件！");
        }

        if(!StringUtils.isEmpty(productInfo.getProductno())){

            //文件保存地址
            String url = PathUtil.projectPath.substring(6,PathUtil.projectPath.length())+"sino_file/sino_product/";
            //保存文件
            boolean flag = FileUtils.saveFileByMultipartFile(file, url);
            if(!flag){
                return AjaxResult.error("上传文件失败！");
            }

            //文件信息入库
            HmpFileInfo fileInput = new HmpFileInfo();
            fileInput.setFilecode("F"+PubFun.createMySqlMaxNoUseCache("FileInfo",10,9));
            fileInput.setFilename(file.getOriginalFilename());
            fileInput.setFilesource(productInfo.getProductno());
            fileInput.setAlternatefield1(fileDesc);
            fileInput.setFilepath(url + file.getOriginalFilename());
            fileInput.setOperator(username);
            fileInput.setMakedate(new Date());
            fileInput.setMaketime(PubFun.getCurrentTime());
            fileInput.setModifydate(new Date());
            fileInput.setModifytime(PubFun.getCurrentTime());
            int i = hmpFileInfoService.insertHmpFileInfo(fileInput);
            if (i>0){
                return AjaxResult.success("上传文件成功！");
            }else {
                return AjaxResult.error("上传文件入库失败！");
            }
        }
        return AjaxResult.error("产品信息不能为空！");
    }

    /**
     * 查询产品的审核轨迹
     */
    @GetMapping("/queryAuditTrack")
    public TableDataInfo queryAuditTrack(HmpAuditTrack hmpAuditTrack)
    {
        startPage();
        List<HmpAuditTrack> list = hmpAuditTrackService.selectHmpAuditTrackList(hmpAuditTrack);
        return getDataTable(list);
    }

    /**
     * 产品审核完毕
     */
    @PreAuthorize("@ss.hasPermi('system:definition:productAudit2')")
    @Log(title = "产品定义提交审核", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/productAudit2")
    public AjaxResult productAudit2(@RequestBody ProductInfo productInfo)
    {
        //产品信息检查
        String productNo = productInfo.getProdInfo().getProductno();
        if(StringUtils.isEmpty(productNo)){
            return AjaxResult.error("产品编码不能为空！");
        }
        String productName = productInfo.getProdInfo().getProductname();
        if(StringUtils.isEmpty(productName) || "null".equals(productName)){
            return AjaxResult.error("没有得到产品的[产品名称]，请确认！");
        }
        HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productNo);
        if(!"02".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已审核完毕，不可再次操作，请确认！");
        }
        if("04".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已经是上线状态，不允许审核，请确认！");
        }
        if("01".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品还在录入中，不允许审核，请确认！");
        }

        // 获取登录用户名称
        String username = SecurityUtils.getUsername();

        //审核信息入库
        HmpAuditTrack auditTrack = productInfo.getAudit();
        auditTrack.setAuditno("AT"+PubFun.createMySqlMaxNoUseCache("AuditTrack",10,8));
        auditTrack.setProductno(productInfoDB.getProductno());
        auditTrack.setProductname(productInfoDB.getProductname());
        auditTrack.setOperator(username);
        auditTrack.setMakedate(new Date());
        auditTrack.setMaketime(PubFun.getCurrentTime());
        int i = hmpAuditTrackService.insertHmpAuditTrack(auditTrack);
        if(i > 0){
            if("01".equals(auditTrack.getAuditresult())){
                productInfoDB.setProductstate("03");
            }else if("02".equals(auditTrack.getAuditresult())){
                productInfoDB.setProductstate("01");
            }
            return toAjax(hmpProdInfoService.updateHmpProdInfo(productInfoDB));
        }else{
            return AjaxResult.error("产品审核信息入库失败！");
        }
    }

    /**
     * 财务复核审核完毕
     */
    @PreAuthorize("@ss.hasPermi('system:definition:productAudit3')")
    @Log(title = "产品定义提交审核", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/productAudit3")
    public AjaxResult productAudit3(@RequestBody ProductInfo productInfo)
    {
        //产品信息检查
        String productNo = productInfo.getProdInfo().getProductno();
        if(StringUtils.isEmpty(productNo)){
            return AjaxResult.error("产品编码不能为空！");
        }
        HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productNo);
        if("06".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品财务已审核完毕，不可再次操作，请确认！");
        }

        // 获取登录用户名称
        String username = SecurityUtils.getUsername();

        //审核信息入库
        HmpAuditTrack auditTrack = productInfo.getAudit();
        auditTrack.setAuditno("AT"+PubFun.createMySqlMaxNoUseCache("AuditTrack",10,8));
        auditTrack.setProductno(productInfoDB.getProductno());
        auditTrack.setProductname(productInfoDB.getProductname());
        auditTrack.setOperator(username);
        auditTrack.setMakedate(new Date());
        auditTrack.setMaketime(PubFun.getCurrentTime());
        int i = hmpAuditTrackService.insertHmpAuditTrack(auditTrack);
        if(i > 0){
            if("01".equals(auditTrack.getAuditresult())){
                productInfoDB.setProductstate("06");

                //修改产品服务机构
                HmpProdServCom prodServCom = hmpProdServComService.selectHmpProdServComById(productNo);
                prodServCom.setOperator(username);
                prodServCom.setMakedate(new Date());
                prodServCom.setMaketime(PubFun.getCurrentTime());
                prodServCom.setModifydate(new Date());
                prodServCom.setModifytime(PubFun.getCurrentTime());
                hmpProdServComService.updateHmpProdServCom(prodServCom);

//                //修改产品销售区域
//                List<HmpProdSalCom> dbProductSalComSelects = hmpProdInfoService.queryServiceCity(productNo);
//                List<HmpProdSalCom> productSalComSelects = new ArrayList<>();
//                for (int j = 0; j < dbProductSalComSelects.size(); j++) {
//                    HmpProdSalCom salCom = dbProductSalComSelects.get(j);
//                    salCom.setOperator(username);
//                    salCom.setMakedate(new Date());
//                    salCom.setMaketime(PubFun.getCurrentTime());
//                    salCom.setModifydate(new Date());
//                    salCom.setModifytime(PubFun.getCurrentTime());
//                    productSalComSelects.add(salCom);
//                }
//                hmpProdInfoService.betchInsertHmpProdSalCom(productSalComSelects);

            }else if("02".equals(auditTrack.getAuditresult())){
                productInfoDB.setProductstate("01");
            }
            return toAjax(hmpProdInfoService.updateHmpProdInfo(productInfoDB));
        }else{
            return AjaxResult.error("产品审核信息入库失败！");
        }
    }

    /**
     * 查询产品审核结果
     */
    @PostMapping("/getProductExamineResult")
    public AjaxResult getProductExamineResult(@RequestBody String productNo){
        AjaxResult ajax = AjaxResult.success();
        if (!StringUtils.isEmpty(productNo))
        {
            HmpAuditTrack productExamineResult = hmpAuditTrackService.getProductExamineResult(productNo);
            ajax.put("examineresult", productExamineResult);
        }
        return ajax;
    }

    /**
     * 产品上线
     */
    @PreAuthorize("@ss.hasPermi('product:definition:online')")
    @PostMapping("/productPublish")
    public AjaxResult productPublish(@RequestBody HmpProdInfo productInfo){
        String productNo = productInfo.getProductno();
        if(StringUtils.isEmpty(productNo)){
            return AjaxResult.error("产品编码不能为空！");
        }
        String productName = productInfo.getProductname();
        if(StringUtils.isEmpty(productName) || "null".equals(productName)){
            return AjaxResult.error("没有得到产品的[产品名称]，请确认！");
        }
        HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productNo);
        if("02".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已重新审核，不可再次操作，请确认！");
        }
        if("04".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已上线，不可再次操作，请确认！");
        }

        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        //更改产品状态、操作员
        productInfoDB.setProductstate("04");
        productInfoDB.setModifyoperator(username);
        productInfoDB.setMakedate(new Date());
        productInfoDB.setMaketime(PubFun.getCurrentTime());
        return toAjax(hmpProdInfoService.updateHmpProdInfo(productInfoDB));
    }

    /**
     * 重新审核
     */
    @PreAuthorize("@ss.hasAnyPermi('product:definition:reaudit')")
    @PostMapping("/productReAuditTrack")
    public AjaxResult productReAuditTrack(@RequestBody HmpProdInfo productInfo){
        String productNo = productInfo.getProductno();
        if(StringUtils.isEmpty(productNo)){
            return AjaxResult.error("产品编码不能为空！");
        }
        String productName = productInfo.getProductname();
        if(StringUtils.isEmpty(productName) || "null".equals(productName)){
            return AjaxResult.error("没有得到产品的[产品名称]，请确认！");
        }
        HmpProdInfo productInfoDB = hmpProdInfoService.selectHmpProdInfoById(productNo);
        if("02".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已重新审核，不可再次操作，请确认！");
        }
        if("04".equals(productInfoDB.getProductstate())){
            return AjaxResult.error("该产品已上线，不允许审核，请确认！");
        }

        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        //更改产品状态、操作员
        productInfoDB.setProductstate("02");
        productInfoDB.setModifyoperator(username);
        productInfoDB.setMakedate(new Date());
        productInfoDB.setMaketime(PubFun.getCurrentTime());
        return toAjax(hmpProdInfoService.updateHmpProdInfo(productInfoDB));
    }

    /**
     * 产品上下线转换
     */
    @PutMapping("/changeOnLineStatus")
    public AjaxResult changeOnLineStatus(@RequestBody HmpProdInfo productInfo){
        if(productInfo.getProductstate().equals("下线")){
            productInfo.setProductstate("05");
        }
        if (productInfo.getProductstate().equals("上线")){
            productInfo.setProductstate("04");
        }
        return toAjax(hmpProdInfoService.updateHmpProdInfo(productInfo));
    }

    /**
     * 更新产品信息、服务机构、服务网点
     */
    @PreAuthorize("@ss.hasAnyPermi('product:definition:productupdate')")
    @PostMapping("/productUpdate")
    public AjaxResult productUpdate(@RequestBody ProductInfo product){
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();

        //转化请求中的数据为实体类
        HmpProdInfo productInfo = product.getProdInfo();

        //更新产品基本信息
        productInfo.setModifyoperator(username);
        productInfo.setModifydate(new Date());
        productInfo.setModifytime(PubFun.getCurrentTime());
        hmpProdInfoService.updateHmpProdInfo(productInfo);

        String productNo = productInfo.getProductno();
        //服务项目
        List<HmpServProject> servProjects = product.getServProjects();
        //服务机构
        List<HmpServOpera> servOperas = product.getServOperas();
        //服务网点
        List<HmpComWebSite> comWebSites = product.getComWebSites();

        if(servProjects!=null && servProjects.size()==1) {
            HmpProdServCom serviceComSelect = new HmpProdServCom();
            serviceComSelect.setAlternatefield1(servProjects.get(0).getProjectcode());
            serviceComSelect.setSerivcecomno(servOperas.get(0).getServcomno());
            serviceComSelect.setSerivcecomtype(servOperas.get(0).getServcomtype());

            if (comWebSites != null && comWebSites.size() > 0) {
                for (HmpComWebSite comWebSite : comWebSites) {
                    // 最后的网点编码存入
                    serviceComSelect.setContactcode(comWebSite.getWebsitecode());
                }
            } else {
                serviceComSelect.setContactcode("000000");
            }
            serviceComSelect.setOperator(username);
            serviceComSelect.setMakedate(new Date());
            serviceComSelect.setMaketime(PubFun.getCurrentTime());
            serviceComSelect.setProductno(productNo);
            serviceComSelect.setModifydate(new Date());
            serviceComSelect.setModifytime(PubFun.getCurrentTime());

            hmpProdServComService.deleteHmpProdServComById(productNo);
            hmpProdServComService.insertHmpProdServCom(serviceComSelect);
        }
        return AjaxResult.success(productInfo);
    }

    /**
     * 更新产品的服务项目、服务机构、服务网点
     */
    @PostMapping("/updateProject")
    public AjaxResult updateProject(@RequestBody ProductInfo product){
        //获取登录用户名
        String username = SecurityUtils.getUsername();

        HmpProdInfo prodInfo = product.getProdInfo();
        String productNo = prodInfo.getProductno();
        //服务项目
        List<HmpServProject> servProjects = product.getServProjects();
        //服务机构
        List<HmpServOpera> servOperas = product.getServOperas();
        //服务网点
        List<HmpComWebSite> comWebSites = product.getComWebSites();

        HmpProdServCom hmpProdServCom = new HmpProdServCom();
        //存入项目编码
        if (servProjects!=null && servProjects.size()==1){
            hmpProdServCom.setAlternatefield1(servProjects.get(0).getProjectcode());
        }
        //存入供应商、网点
        if(servOperas!=null && servOperas.size()==1){
            HmpServOpera hmpServOpera = servOperas.get(0);
            hmpProdServCom.setSerivcecomno(hmpServOpera.getServcomno());
            hmpProdServCom.setSerivcecomtype(hmpServOpera.getServcomtype());

            if(comWebSites!=null && comWebSites.size() > 0){
                for (HmpComWebSite comWebSite : comWebSites) {
                    hmpProdServCom.setContactcode(comWebSite.getWebsitecode());
                }
            }else{
                hmpProdServCom.setContactcode("000000");
            }

            hmpProdServCom.setProductno(productNo);

            hmpProdServCom.setOperator(username);
            hmpProdServCom.setMakedate(new Date());
            hmpProdServCom.setMaketime(PubFun.getCurrentTime());
            hmpProdServCom.setModifydate(new Date());
            hmpProdServCom.setModifytime(PubFun.getCurrentTime());

            hmpProdServComService.deleteHmpProdServComById(productNo);
            hmpProdServComService.insertHmpProdServCom(hmpProdServCom);
        }
        return AjaxResult.success("保存成功！");
    }


    /**
     * 客户权益管理-分配规则配置-分配产品字典下拉
     * @return
     */
    @GetMapping("/selectproductdict")
    public AjaxResult selectProductDict()
    {
        return AjaxResult.success(hmpProdInfoService.selectProductDict());
    }


}
