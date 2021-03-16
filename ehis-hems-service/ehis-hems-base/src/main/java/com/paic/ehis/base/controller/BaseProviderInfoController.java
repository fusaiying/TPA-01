package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.domain.vo.*;
import com.paic.ehis.base.service.*;
import com.paic.ehis.system.api.domain.BaseServiceInfo;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import com.paic.ehis.system.api.domain.FirstDeptInfoVo;
import com.paic.ehis.system.api.domain.HospitalInfoVo;
import com.paic.ehis.system.api.domain.SecondDeptInfoVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商合约Controller
 *
 * @author sino
 * @date 2020-09-23
 */
@RestController
@RequestMapping("/org")
public class BaseProviderInfoController extends BaseController {

    @Autowired
    private IBaseProviderInfoService baseProviderInfoService;

    @Autowired
    private IBaseBankService baseBankService;

    @Autowired
    private IBaseContactsService baseContactsService;

    @Autowired
    private IBaseProviderApplyService baseProviderApplyService;

    @Autowired
    private IBaseProviderDepService baseProviderDepService;

    @Autowired
    private IBaseProviderServiceService baseProviderServiceService;

    @Autowired
    private IBaseProviderWorktimeService baseProviderWorktimeService;

    @Autowired
    private IBaseProviderNewtworktypeService baseProviderNewtworktypeService;

    @Autowired
    private IBaseProviderNewtworktypeLogService baseProviderNewtworktypeLogService;

    @Autowired
    private IBaseProviderNewtworktypetRiskService baseProviderNewtworktypetRiskService;

    @Autowired
    private IBaseProviderNewtworktypeRisklogService baseProviderNewtworktypeRisklogService;

    @Autowired
    private IBaseProviderSettleService baseProviderSettleService;


    /**updateNewtworktypeList
     * 查询base_provider_info(服务商基本信息)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getList")
    public TableDataInfo list(@RequestBody BaseProviderInfo baseProviderInfo) throws Exception
    {
        startPage(baseProviderInfo);
        List<BaseProviderInfo> list = baseProviderInfoService.selectBaseProviderInfoList(baseProviderInfo);
        return getDataTable(list);
    }

    @PostMapping("/getListNew")
    public TableDataInfo listNew(@RequestBody BaseProviderInfo baseProviderInfo) throws Exception
    {
        startPage(baseProviderInfo);
        List<BaseProviderInfo> list = baseProviderInfoService.selectBaseProviderInfoListNew(baseProviderInfo);
        return getDataTable(list);
    }

    @PostMapping("/getlist1")
    public TableDataInfo providerInfoList(@RequestBody BaseProviderInfo baseProviderInfo) throws Exception{
        startPage(baseProviderInfo);
        List<BaseProviderInfo> list =baseProviderInfoService.selectProvideInfoList(baseProviderInfo);
        return getDataTable(list);
    }



    @PostMapping("/getAddress")
    public  ArrayList<AddressVO> getAddress(){
        ArrayList<AddressVO> addressList = baseProviderInfoService.getAddress();
        return addressList;
    }

    /**
     * 获取省的信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getprovince")
    public List<AddressInfo> getprovince()
    {
        List<AddressInfo> list = baseProviderInfoService.getprovince();
        return list;
    }


    /**
     * 获取市或者区的的信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getcity")
    public List<AddressInfo> getplace(String placecode)
    {
        List<AddressInfo> list = baseProviderInfoService.getplace(placecode);
        return list;
    }



    /**
     * 导出base_provider_info(服务商基本信息)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "base_provider_info(服务商基本信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseProviderInfo baseProviderInfo) throws IOException
    {
        List<BaseProviderInfo> list = baseProviderInfoService.selectBaseProviderInfoList(baseProviderInfo);
        ExcelUtil<BaseProviderInfo> util = new ExcelUtil<BaseProviderInfo>(BaseProviderInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取base_provider_info(服务商基本信息)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return AjaxResult.success(baseProviderInfoService.selectBaseProviderInfo(baseProviderInfo));
    }


    /**
     * 获取base_provider_info(服务商基本信息)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/checkfield")
    public AjaxResult checkfield(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return AjaxResult.success(baseProviderInfoService.selectBaseProviderInfoByNames(baseProviderInfo));
    }


    /**
     * 获取base_provider_info(服务商基本信息)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/checkfieldNew")
    public AjaxResult checkfieldNew(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return AjaxResult.success(baseProviderInfoService.selectBaseProviderInfoByNamesNew(baseProviderInfo));
    }

    /**
     * 新增base_provider_info(服务商基本信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("addInfo")
    public AjaxResult addinfo(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return AjaxResult.success(baseProviderInfoService.insertBaseProviderInfo(baseProviderInfo));

    }

    /**
     * 修改base_provider_info(服务商基本信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "base_provider_info(服务商基本信息)", businessType = BusinessType.UPDATE)
    @PostMapping("updateInfo")
    public AjaxResult edit(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return toAjax(baseProviderInfoService.updateBaseProviderInfo(baseProviderInfo));
    }



    /**
     * 获取base_bank（银行信息）详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:bank:query')")
    @PostMapping("getbankInfo")
    public AjaxResult getBankInfo(@RequestBody BaseBankVo baseBankinfo)
    {
        return AjaxResult.success(baseBankService.selectBaseBankByCode(baseBankinfo));
    }

    /**
     * 获取base_contacts（联系人信息）详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:contacts:query')")
    @PostMapping("getcontactsInfo")
    public AjaxResult getcontactsInfo(@RequestBody BaseContacts baseContacts)
    {
        return AjaxResult.success(baseContactsService.selectBaseContactsByCode(baseContacts));
    }



    /**
     * 获取base_provider_apply(服务商预约信息)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:apply:query')")
    @PostMapping("getapplyInfo")
    public AjaxResult getapplyInfo(@RequestBody BaseProviderApply baseProviderApply)
    {
        return AjaxResult.success(baseProviderApplyService.selectBaseProviderApplyById(baseProviderApply));
    }

    /**
     * 获取base_provider_dept(服务商科室)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:dep:query')")
    @PostMapping("getdepInfo")
    public AjaxResult getdepInfo(@RequestBody BaseProviderDep baseProviderDep)
    {
        return AjaxResult.success(baseProviderDepService.selectBaseProviderDepByCode(baseProviderDep));
    }

    /**
     * 获取base_provider_service(服务商服务信息)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:service:query')")
    @PostMapping("getserviceInfo")
    public AjaxResult getserviceInfo(@RequestBody BaseProviderService baseProviderService)
    {
        return AjaxResult.success(baseProviderServiceService.selectBaseProviderServiceById(baseProviderService));
    }

    /**
     * 获取base_provider_worktime(服务商服务时间信息)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:worktime:query')")
    @PostMapping("getworktimeInfo")
    public AjaxResult getworktimeInfo(@RequestBody String providercode)
    {
        return AjaxResult.success(baseProviderWorktimeService.selectBaseProviderWorktimeById(providercode));
    }

    /**
     * 新增base_bank（银行信息）
     */
    //@PreAuthorize("@ss.hasPermi('system:bank:add')")
    @Log(title = "base_bank（银行信息）", businessType = BusinessType.INSERT)
    @PostMapping("addbankInfo")
    public AjaxResult addbank(@RequestBody BaseBankVo baseBankVo)
    {
        return toAjax(baseBankService.insertBaseBank(baseBankVo));
    }

    /**
     * 新增base_contacts（联系人信息）
     */
    //@PreAuthorize("@ss.hasPermi('system:contacts:add')")
    @Log(title = "base_contacts（联系人信息）", businessType = BusinessType.INSERT)
    @PostMapping("addcontactsInfo")
    public AjaxResult addcontacts(@RequestBody BaseContactsVo baseContactsVo)
    {
        return toAjax(baseContactsService.insertBaseContacts(baseContactsVo));
    }


    @PostMapping("deleteContactsInfo")
    public AjaxResult deleteContactsInfo(@RequestBody BaseContacts baseContacts)
    {
        return toAjax(baseContactsService.deleteBaseContacts(baseContacts));
    }

    /**
     * 新增base_provider_apply(服务商预约信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:apply:add')")
    @Log(title = "base_provider_apply(服务商预约信息)", businessType = BusinessType.INSERT)
    @PostMapping("addapplyInfo")
    public AjaxResult addapply(@RequestBody BaseProviderApply baseProviderApply)
    {
        return toAjax(baseProviderApplyService.insertBaseProviderApply(baseProviderApply));
    }

    /**
     * 新增base_provider_dept(服务商科室)
     */
    //@PreAuthorize("@ss.hasPermi('system:dep:add')")
    @Log(title = "base_provider_dept(服务商科室)", businessType = BusinessType.INSERT)
    @PostMapping("adddepInfo")
    public AjaxResult adddep(@RequestBody BaseProviderDepVo baseProviderDep)
    {
        return toAjax(baseProviderDepService.insertBaseProviderDep(baseProviderDep));
    }

    /**
     * 新增base_provider_service(服务商服务信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:service:add')")
    @Log(title = "base_provider_service(服务商服务信息)", businessType = BusinessType.INSERT)
    @PostMapping("addserviceInfo")
    public AjaxResult addservice(@RequestBody BaseProviderService baseProviderService)
    {
        return toAjax(baseProviderServiceService.insertBaseProviderService(baseProviderService));
    }

    /**
     * 新增base_provider_worktime(服务商服务时间信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:worktime:add')")
    @Log(title = "base_provider_worktime(服务商服务时间信息)", businessType = BusinessType.INSERT)
    @PostMapping("addworktimeInfo")
    public AjaxResult addworktime(@RequestBody BaseProviderWorktime baseProviderWorktime)
    {
        return toAjax(baseProviderWorktimeService.insertBaseProviderWorktime(baseProviderWorktime));
    }

    /**
     * 修改base_provider_apply(服务商预约信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:apply:edit')")
    @Log(title = "base_provider_apply(服务商预约信息)", businessType = BusinessType.UPDATE)
    @PostMapping("updateapplyInfo")
    public AjaxResult updateapplyInfo(@RequestBody BaseProviderApply baseProviderApply)
    {
        return toAjax(baseProviderApplyService.updateBaseProviderApply(baseProviderApply));
    }


    /**
     * 修改base_provider_service(服务商服务信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:service:edit')")
    @Log(title = "base_provider_service(服务商服务信息)", businessType = BusinessType.UPDATE)
    @PostMapping("updateserviceInfo")
    public AjaxResult updateserviceInfo(@RequestBody BaseProviderService baseProviderService)
    {
        return toAjax(baseProviderServiceService.updateBaseProviderService(baseProviderService));
    }




    /**
     * 查询base_provider_newtworktypet(医疗网络类型)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:newtworktype:list')")
    @GetMapping("/getNewtworktypeList")
    public TableDataInfo getNewtworktypeList(BaseProviderNewtworktype baseProviderNewtworktype)
    {
        startPage();
        List<BaseProviderNewtworktype> list = baseProviderNewtworktypeService.selectBaseProviderNewtworktypeList(baseProviderNewtworktype);
        return getDataTable(list);
    }


    /**
     * 修改base_provider_newtworktypet(医疗网络类型)
     */
    //@PreAuthorize("@ss.hasPermi('system:newtworktype:edit')")
    @Log(title = "base_provider_newtworktypet(医疗网络类型)", businessType = BusinessType.UPDATE)
    @PostMapping("/updateNewtworktypeList")
    public AjaxResult updateNewtworktypeList(@RequestBody BaseProviderNetworktypeVO baseProviderNetworktypeVO)
    {
        return toAjax(baseProviderNewtworktypeService.updateBaseProviderNewtworktype(baseProviderNetworktypeVO));
    }



    /**
     * 查询base_provider_newtworktype_log(医疗网络类型记录)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/getNewtworktypeLogList")
    public TableDataInfo getNewtworktypeLogList(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog)
    {
        startPage();
        List<BaseProviderNewtworktypeLog> list = baseProviderNewtworktypeLogService.selectBaseProviderNewtworktypeLogList(baseProviderNewtworktypeLog);
        return getDataTable(list);
    }

    /**
     * 查询base_provider_newtworktypet_risk(医疗网络险种信息)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:risk:list')")
    @GetMapping("/getNewtworktypetRisk")
    public TableDataInfo getNewtworktypetRisk(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk)
    {
        startPage();
        List<BaseProviderNewtworktypetRisk> list = baseProviderNewtworktypetRiskService.selectBaseProviderNewtworktypetRiskList(baseProviderNewtworktypetRisk);
        return getDataTable(list);
    }


    /**
     * 新增base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:risk:add')")
    @Log(title = "base_provider_newtworktypet_risk(医疗网络险种信息)", businessType = BusinessType.INSERT)
    @PostMapping("/addNewtworktypetRisk")
    public AjaxResult addNewtworktypetRisk(@RequestBody List<BaseProviderNewtworktypetRisk> baseProviderNewtworktypetRisk)
    {
        return toAjax(baseProviderNewtworktypetRiskService.insertBaseProviderNewtworktypetRisk(baseProviderNewtworktypetRisk));
    }


    /**
     * 删除base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    //@PreAuthorize("@ss.hasPermi('system:risk:remove')")
    @Log(title = "base_provider_newtworktypet_risk(医疗网络险种信息)", businessType = BusinessType.DELETE)
    @PostMapping("/removeNewtworktypetRisk")
    public AjaxResult removeNewtworktypetRisk(@RequestBody String serNo)
    {
        return toAjax(baseProviderNewtworktypetRiskService.deleteBaseProviderNewtworktypetRiskById(serNo));
    }


    /**
     * 查询base_provider_newtworktype_risklog(医疗网络类型险种记录)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @GetMapping("/getNewtworktypeRisklog")
    public TableDataInfo getNewtworktypeRisklog(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog)
    {
        startPage();
        List<BaseProviderNewtworktypeRisklog> list = baseProviderNewtworktypeRisklogService.selectBaseProviderNewtworktypeRisklogList(baseProviderNewtworktypeRisklog);
        return getDataTable(list);
    }


    /**
     * 提交到审核状态
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody ProviderInfoVo providerInfoVo)
    {
        return toAjax(baseProviderInfoService.insertCheckInfo(providerInfoVo));
    }


    /**
     * 查询待审核医院列表列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getCheckUpList")
    public TableDataInfo getCheckUpList(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        startPage(baseProviderInfo);
        List<BaseProviderInfo> list = baseProviderInfoService.selectBaseProviderInfoCheckList(baseProviderInfo);
        return getDataTable(list);
    }

    /**
     * 医院信息进行审核处理
     */
    //@PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @PostMapping("/insertCheckInfo")
    public AjaxResult insertCheckInfo(@RequestBody ProviderInfoVo providerInfoVo)
    {
        return toAjax(baseProviderInfoService.insertChecDatak(providerInfoVo));
    }

    /**
     * 医院信息进行审核信息查询
     */
    //@PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @PostMapping("/selectCheckInfo")
    public AjaxResult selectCheckInfo(@RequestBody String providerCode)
    {
        List<BaseCheckInfo> list = baseProviderInfoService.selectBaseProviderCheckList(providerCode);
        return AjaxResult.success(list);
    }

    /**
     * 查询base_provider_info(服务商基本信息)所有列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/allList")
    public AjaxResult allList(BaseProviderInfo baseProviderInfo)
    {
        return  AjaxResult.success(baseProviderInfoService.selectBaseProviderInfos(baseProviderInfo));
    }

    @PostMapping("/allLBackist")
    public AjaxResult allLBackist(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return  AjaxResult.success(baseProviderInfoService.selectBaseProviderBackInfos(baseProviderInfo));
    }

    //@PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @GetMapping("/selectsettleInfo")
    public TableDataInfo selectsettleInfo(BaseProviderSettle baseProviderSettle)
    {
        startPage();
        List<BaseProviderSettle> list = baseProviderSettleService.selectBaseProviderSettleList(baseProviderSettle);
        return getDataTable(list);
    }


    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:risklog:list')")
    @PostMapping("/selectHospitalInfo")
    public AjaxResult selectHospitalInfo(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        List<BaseProviderInfo> list = baseProviderInfoService.selectHospitalInfo(baseProviderInfo);
        return AjaxResult.success(list);
    }

    //@PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @PostMapping("/selectHospitalInfoNew")
    public TableDataInfo selectHospitalInfoNew(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        startPage(baseProviderInfo);
        List<BaseProviderInfo> list = baseProviderInfoService.selectHospitalInfo(baseProviderInfo);
        return getDataTable(list);
    }

    /**
     * 外部调用结算时使用的方法
     *
     * @param baseProviderSettle
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @PostMapping("/selectsettleInfoNew")
    public List<BaseProviderSettle> selectsettleInfoNew(@RequestBody BaseProviderSettle baseProviderSettle)
    {
        List<BaseProviderSettle> list = baseProviderSettleService.selectBaseProviderSettleList(baseProviderSettle);
        return list;
    }


    @PostMapping("/getServiceInfo")
    public List<BaseServiceInfo> getServiceInfo()
    {
        return baseProviderInfoService.getServiceInfo();
    }


    /**
     * 查询供应商信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getSupplierInfo")
    public AjaxResult getSupplierInfo()
    {
        return AjaxResult.success(baseProviderInfoService.getSupplierInfo());
    }


    //获取医院信息
    @PostMapping("/getHospitalInfo")
    public List<HospitalInfoVo> getHospitalInfo()
    {
        return baseProviderInfoService.getHospitalInfo();
    }

    ////获取一级科室
    @PostMapping("/getFirstDeptInfo")
    public List<FirstDeptInfoVo> getFirstDeptInfo()
    {
        return baseProviderInfoService.getFirstDeptInfo();
    }

    //获取二级科室
    @PostMapping("/getSecondDeptInfo")
    public List<SecondDeptInfoVo> getSecondDeptInfo()
    {
        return baseProviderInfoService.getSecondDeptInfo();
    }

    @PostMapping("/getHospitalInfo1")
    public List<HospitalInfoVo> getHospitalInfo1(@RequestBody com.paic.ehis.system.api.domain.AddressInfo addressInfo){
        return baseProviderInfoService.getHospitalInfo1(addressInfo);
    }

    /**
     * 供应商信息查询接口
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/selectSupplier")
    public AjaxResult selectSupplier(@RequestBody ProductInfo productInfo)
    {
        /*startPage();
        List<ProductSupplierInfo> list = productInfoService.selectSupplier(productInfo);
        return getDataTable(list);*/
        return AjaxResult.success(baseProviderInfoService.selectSupplier(productInfo));
    }


    /**
     * 配置供应商信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/insertSupplier")
    public AjaxResult insertSupplier(@RequestBody com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo)
    {
        return toAjax(baseProviderInfoService.insertSupplier(productSupplierInfoVo));
    }
}
