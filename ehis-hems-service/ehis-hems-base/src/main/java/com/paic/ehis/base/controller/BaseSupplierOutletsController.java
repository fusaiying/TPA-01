package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseProviderInfo;
import com.paic.ehis.base.domain.BaseSupplierOutlets;
import com.paic.ehis.base.domain.vo.BaseSupplierOutletsDTO;
import com.paic.ehis.base.service.IBaseSupplierOutletsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_supplier_outlets（服务网点配置）Controller
 * 
 * @author sino
 * @date 2020-12-27
 */
@RestController
@RequestMapping("/outlets")
public class BaseSupplierOutletsController extends BaseController
{
    @Autowired
    private IBaseSupplierOutletsService baseSupplierOutletsService;
    @GetMapping("/list1")
    public TableDataInfo selectOutletsList(BaseSupplierOutlets baseSupplierOutlets)
    {
        startPage();
        List<BaseSupplierOutlets> list = baseSupplierOutletsService.selectOutletsList(baseSupplierOutlets);
        return getDataTable(list);
    }

    /**
     * 查询base_supplier_outlets（服务网点配置）列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BaseSupplierOutlets baseSupplierOutlets)
    {
        startPage();
        List<BaseSupplierOutlets> list = baseSupplierOutletsService.selectBaseSupplierOutletsList(baseSupplierOutlets);
        return getDataTable(list);
    }

    /**
     * 导出base_supplier_outlets（服务网点配置）列表
     */
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseSupplierOutlets baseSupplierOutlets) throws IOException
    {
        List<BaseSupplierOutlets> list = baseSupplierOutletsService.selectBaseSupplierOutletsList(baseSupplierOutlets);
        ExcelUtil<BaseSupplierOutlets> util = new ExcelUtil<BaseSupplierOutlets>(BaseSupplierOutlets.class);
        util.exportExcel(response, list, "outlets");
    }

    /**
     * 获取base_supplier_outlets（服务网点配置）详细信息
     */
    @GetMapping(value = "/{servcomno}")
    public AjaxResult getInfo(@PathVariable("servcomno") String servcomno)
    {
        return AjaxResult.success(baseSupplierOutletsService.selectBaseSupplierOutletsById(servcomno));
    }

    /**
     * 新增base_supplier_outlets（服务网点配置）
     */
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSupplierOutletsDTO so)
    {
        BaseSupplierOutlets baseSupplierOutlets = new BaseSupplierOutlets();
        List<BaseProviderInfo> baseProviderInfoList = so.getBaseProviderInfoList();
        int count=0;
        for (BaseProviderInfo baseProviderInfo:baseProviderInfoList){
            baseSupplierOutlets.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno", 10, 9));
            baseSupplierOutlets.setStatus("Y");
            baseSupplierOutlets.setSupplierCode(so.getSerialNo());
            baseSupplierOutlets.setAddressdetail(baseProviderInfo.getAddressdetail());
            baseSupplierOutlets.setWebsitecType(baseProviderInfo.getOrgFlag());
            baseSupplierOutlets.setWebsitecName(baseProviderInfo.getChname1());
            baseSupplierOutlets.setWebsiteCode(baseProviderInfo.getProviderCode());
            baseSupplierOutletsService.insertBaseSupplierOutlets(baseSupplierOutlets);
            count ++;
        }

        return toAjax(count);
    }

    /**
     * 修改base_supplier_outlets（服务网点配置）
     */
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierOutlets baseSupplierOutlets)
    {
        return toAjax(baseSupplierOutletsService.updateBaseSupplierOutlets(baseSupplierOutlets));
    }

    @PutMapping("/updatestatus")
    public AjaxResult updateBaseSupplierOutletsStatus(@RequestBody BaseSupplierOutlets baseSupplierOutlets){
        return toAjax(baseSupplierOutletsService.updateBaseSupplierOutletsStatus(baseSupplierOutlets));
    }

    /**
     * 删除base_supplier_outlets（服务网点配置）
     */
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servcomnos}")
    public AjaxResult remove(@PathVariable String[] servcomnos)
    {
        return toAjax(baseSupplierOutletsService.deleteBaseSupplierOutletsByIds(servcomnos));
    }
}
