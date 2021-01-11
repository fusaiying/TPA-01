package com.paic.ehis.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.domain.vo.AddressInfo;
import com.paic.ehis.base.domain.vo.AddressVO;
import com.paic.ehis.base.domain.vo.BaseBankVo;
import com.paic.ehis.base.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

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


    /**
     * 查询base_provider_info(服务商基本信息)列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getList")
    public TableDataInfo list(BaseProviderInfo baseProviderInfo)
    {
        startPage();
        List<BaseProviderInfo> list = baseProviderInfoService.selectBaseProviderInfoList(baseProviderInfo);
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
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getprovince")
    public List<AddressInfo> getprovince()
    {
        List<AddressInfo> list = baseProviderInfoService.getprovince();
        return list;
    }


    /**
     * 获取市或者区的的信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getcity")
    public List<AddressInfo> getplace(String placecode)
    {
        List<AddressInfo> list = baseProviderInfoService.getplace(placecode);
        return list;
    }



    /**
     * 导出base_provider_info(服务商基本信息)列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
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
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getInfo")
    public AjaxResult getInfo(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return AjaxResult.success(baseProviderInfoService.selectBaseProviderInfo(baseProviderInfo));
    }


    /**
     * 获取base_provider_info(服务商基本信息)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/checkfield")
    public AjaxResult checkfield(@RequestBody String providercode)
    {
        return AjaxResult.success(baseProviderInfoService.selectBaseProviderInfoById(providercode));
    }

    /**
     * 新增base_provider_info(服务商基本信息)
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("addInfo")
    public AjaxResult addinfo(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return AjaxResult.success(baseProviderInfoService.insertBaseProviderInfo(baseProviderInfo));

    }

    /**
     * 修改base_provider_info(服务商基本信息)
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "base_provider_info(服务商基本信息)", businessType = BusinessType.UPDATE)
    @PostMapping("updateInfo")
    public AjaxResult edit(@RequestBody BaseProviderInfo baseProviderInfo)
    {
        return toAjax(baseProviderInfoService.updateBaseProviderInfo(baseProviderInfo));
    }

    /**
     * 删除base_provider_info(服务商基本信息)
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "base_provider_info(服务商基本信息)", businessType = BusinessType.DELETE)
    @DeleteMapping("/{providercodes}")
    public AjaxResult remove(@PathVariable String[] providercodes)
    {
        return toAjax(baseProviderInfoService.deleteBaseProviderInfoByIds(providercodes));
    }

    /**
     * 获取base_bank（银行信息）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bank:query')")
    @PostMapping("getbankInfo")
    public AjaxResult getBankInfo(@RequestBody String providercode)
    {
        return AjaxResult.success(baseBankService.selectBaseBankByCode(providercode));
    }

    /**
     * 获取base_contacts（联系人信息）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:query')")
    @PostMapping("getcontactsInfo")
    public AjaxResult getcontactsInfo(@RequestBody String suppliercode)
    {
        return AjaxResult.success(baseContactsService.selectBaseContactsByCode(suppliercode));
    }

    /**
     * 获取base_provider_apply(服务商预约信息)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:apply:query')")
    @PostMapping("getapplyInfo")
    public AjaxResult getapplyInfo(@RequestBody String providercode)
    {
        return AjaxResult.success(baseProviderApplyService.selectBaseProviderApplyById(providercode));
    }

    /**
     * 获取base_provider_dept(服务商科室)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dep:query')")
    @PostMapping("getdepInfo")
    public AjaxResult getdepInfo(@RequestBody String providercode)
    {
        return AjaxResult.success(baseProviderDepService.selectBaseProviderDepByCode(providercode));
    }

    /**
     * 获取base_provider_service(服务商服务信息)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:service:query')")
    @PostMapping("getserviceInfo")
    public AjaxResult getserviceInfo(@RequestBody String providercode)
    {
        return AjaxResult.success(baseProviderServiceService.selectBaseProviderServiceById(providercode));
    }

    /**
     * 获取base_provider_worktime(服务商服务时间信息)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:worktime:query')")
    @PostMapping("getworktimeInfo")
    public AjaxResult getworktimeInfo(@RequestBody String providercode)
    {
        return AjaxResult.success(baseProviderWorktimeService.selectBaseProviderWorktimeById(providercode));
    }

    /**
     * 新增base_bank（银行信息）
     */
    @PreAuthorize("@ss.hasPermi('system:bank:add')")
    @Log(title = "base_bank（银行信息）", businessType = BusinessType.INSERT)
    @PostMapping("addbankInfo")
    public AjaxResult addbank(@RequestBody BaseBankVo baseBankVo)
    {
        return toAjax(baseBankService.insertBaseBank(baseBankVo));
    }

    /**
     * 新增base_contacts（联系人信息）
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:add')")
    @Log(title = "base_contacts（联系人信息）", businessType = BusinessType.INSERT)
    @PostMapping("addcontactsInfo")
    public AjaxResult addcontacts(@RequestBody List<BaseContacts> baseContactsVo)
    {
        return toAjax(baseContactsService.insertBaseContacts(baseContactsVo));
    }

    /**
     * 新增base_provider_apply(服务商预约信息)
     */
    @PreAuthorize("@ss.hasPermi('system:apply:add')")
    @Log(title = "base_provider_apply(服务商预约信息)", businessType = BusinessType.INSERT)
    @PostMapping("addapplyInfo")
    public AjaxResult addapply(@RequestBody BaseProviderApply baseProviderApply)
    {
        return toAjax(baseProviderApplyService.insertBaseProviderApply(baseProviderApply));
    }

    /**
     * 新增base_provider_dept(服务商科室)
     */
    @PreAuthorize("@ss.hasPermi('system:dep:add')")
    @Log(title = "base_provider_dept(服务商科室)", businessType = BusinessType.INSERT)
    @PostMapping("adddepInfo")
    public AjaxResult adddep(@RequestBody List<BaseProviderDep> baseProviderDep)
    {
        return toAjax(baseProviderDepService.insertBaseProviderDep(baseProviderDep));
    }

    /**
     * 新增base_provider_service(服务商服务信息)
     */
    @PreAuthorize("@ss.hasPermi('system:service:add')")
    @Log(title = "base_provider_service(服务商服务信息)", businessType = BusinessType.INSERT)
    @PostMapping("addserviceInfo")
    public AjaxResult addservice(@RequestBody BaseProviderService baseProviderService)
    {
        return toAjax(baseProviderServiceService.insertBaseProviderService(baseProviderService));
    }

    /**
     * 新增base_provider_worktime(服务商服务时间信息)
     */
    @PreAuthorize("@ss.hasPermi('system:worktime:add')")
    @Log(title = "base_provider_worktime(服务商服务时间信息)", businessType = BusinessType.INSERT)
    @PostMapping("addworktimeInfo")
    public AjaxResult addworktime(@RequestBody BaseProviderWorktime baseProviderWorktime)
    {
        return toAjax(baseProviderWorktimeService.insertBaseProviderWorktime(baseProviderWorktime));
    }

    /**
     * 修改base_provider_apply(服务商预约信息)
     */
    @PreAuthorize("@ss.hasPermi('system:apply:edit')")
    @Log(title = "base_provider_apply(服务商预约信息)", businessType = BusinessType.UPDATE)
    @PostMapping("updateapplyInfo")
    public AjaxResult updateapplyInfo(@RequestBody BaseProviderApply baseProviderApply)
    {
        return toAjax(baseProviderApplyService.updateBaseProviderApply(baseProviderApply));
    }


    /**
     * 修改base_provider_service(服务商服务信息)
     */
    @PreAuthorize("@ss.hasPermi('system:service:edit')")
    @Log(title = "base_provider_service(服务商服务信息)", businessType = BusinessType.UPDATE)
    @PostMapping("updateserviceInfo")
    public AjaxResult updateserviceInfo(@RequestBody BaseProviderService baseProviderService)
    {
        return toAjax(baseProviderServiceService.updateBaseProviderService(baseProviderService));
    }


    /**
     * 查询base_provider_newtworktypet(医疗网络类型)列表
     */
    @PreAuthorize("@ss.hasPermi('system:newtworktype:list')")
    @PostMapping("/getNewtworktypeList")
    public TableDataInfo getNewtworktypeList(BaseProviderNewtworktype baseProviderNewtworktype)
    {
        startPage();
        List<BaseProviderNewtworktype> list = baseProviderNewtworktypeService.selectBaseProviderNewtworktypeList(baseProviderNewtworktype);
        return getDataTable(list);
    }


    /**
     * 修改base_provider_newtworktypet(医疗网络类型)
     */
    @PreAuthorize("@ss.hasPermi('system:newtworktype:edit')")
    @Log(title = "base_provider_newtworktypet(医疗网络类型)", businessType = BusinessType.UPDATE)
    @PostMapping("/updateNewtworktypeList")
    public AjaxResult updateNewtworktypeList(@RequestBody List<BaseProviderNewtworktype> baseProviderNewtworktype)
    {
        return toAjax(baseProviderNewtworktypeService.updateBaseProviderNewtworktype(baseProviderNewtworktype));
    }



    /**
     * 查询base_provider_newtworktype_log(医疗网络类型记录)列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @PostMapping("/getNewtworktypeLogList")
    public TableDataInfo getNewtworktypeLogList(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog)
    {
        startPage();
        List<BaseProviderNewtworktypeLog> list = baseProviderNewtworktypeLogService.selectBaseProviderNewtworktypeLogList(baseProviderNewtworktypeLog);
        return getDataTable(list);
    }

    /**
     * 查询base_provider_newtworktypet_risk(医疗网络险种信息)列表
     */
    @PreAuthorize("@ss.hasPermi('system:risk:list')")
    @PostMapping("/getNewtworktypetRisk")
    public TableDataInfo getNewtworktypetRisk(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk)
    {
        startPage();
        List<BaseProviderNewtworktypetRisk> list = baseProviderNewtworktypetRiskService.selectBaseProviderNewtworktypetRiskList(baseProviderNewtworktypetRisk);
        return getDataTable(list);
    }


    /**
     * 新增base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    @PreAuthorize("@ss.hasPermi('system:risk:add')")
    @Log(title = "base_provider_newtworktypet_risk(医疗网络险种信息)", businessType = BusinessType.INSERT)
    @PostMapping("/addNewtworktypetRisk")
    public AjaxResult addNewtworktypetRisk(@RequestBody List<BaseProviderNewtworktypetRisk> baseProviderNewtworktypetRisk)
    {
        return toAjax(baseProviderNewtworktypetRiskService.insertBaseProviderNewtworktypetRisk(baseProviderNewtworktypetRisk));
    }


    /**
     * 删除base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    @PreAuthorize("@ss.hasPermi('system:risk:remove')")
    @Log(title = "base_provider_newtworktypet_risk(医疗网络险种信息)", businessType = BusinessType.DELETE)
    @PostMapping("/removeNewtworktypetRisk")
    public AjaxResult removeNewtworktypetRisk(@RequestBody String serNo)
    {
        return toAjax(baseProviderNewtworktypetRiskService.deleteBaseProviderNewtworktypetRiskById(serNo));
    }


    /**
     * 查询base_provider_newtworktype_risklog(医疗网络类型险种记录)列表
     */
    @PreAuthorize("@ss.hasPermi('system:risklog:list')")
    @GetMapping("/getNewtworktypeRisklog")
    public TableDataInfo getNewtworktypeRisklog(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog)
    {
        startPage();
        List<BaseProviderNewtworktypeRisklog> list = baseProviderNewtworktypeRisklogService.selectBaseProviderNewtworktypeRisklogList(baseProviderNewtworktypeRisklog);
        return getDataTable(list);
    }

}
