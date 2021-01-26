package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseSupplierReceip;
import com.paic.ehis.base.service.IBaseSupplierReceipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_supplier_receipt(供应商开票信息)Controller
 * 
 * @author sino
 * @date 2020-12-25
 */
@RestController
@RequestMapping("/receip")
public class BaseSupplierReceipController extends BaseController
{
    @Autowired
    private IBaseSupplierReceipService baseSupplierReceipService;

    /**
     * 查询base_supplier_receipt(供应商开票信息)列表
     */
    @PreAuthorize("@ss.hasPermi('system:receip:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseSupplierReceip baseSupplierReceip)
    {
        startPage();
        List<BaseSupplierReceip> list = baseSupplierReceipService.selectBaseSupplierReceipList(baseSupplierReceip);
        return getDataTable(list);
    }

    /**
     * 导出base_supplier_receipt(供应商开票信息)列表
     */
    @PreAuthorize("@ss.hasPermi('system:receip:export')")
    @Log(title = "base_supplier_receipt(供应商开票信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseSupplierReceip baseSupplierReceip) throws IOException
    {
        List<BaseSupplierReceip> list = baseSupplierReceipService.selectBaseSupplierReceipList(baseSupplierReceip);
        ExcelUtil<BaseSupplierReceip> util = new ExcelUtil<BaseSupplierReceip>(BaseSupplierReceip.class);
        util.exportExcel(response, list, "receip");
    }

    /**
     * 获取base_supplier_receipt(供应商开票信息)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:receip:query')")
    @GetMapping(value = "/{suppliercode}")
    public AjaxResult getInfo(@PathVariable("suppliercode") String suppliercode)
    {
        return AjaxResult.success(baseSupplierReceipService.selectBaseSupplierReceipById(suppliercode));
    }

    /**
     * 新增base_supplier_receipt(供应商开票信息)
     */
    @PreAuthorize("@ss.hasPermi('system:receip:add')")
    @Log(title = "base_supplier_receipt(供应商开票信息)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSupplierReceip baseSupplierReceip)
    {
        return toAjax(baseSupplierReceipService.insertBaseSupplierReceip(baseSupplierReceip));
    }

    /**
     * 修改base_supplier_receipt(供应商开票信息)
     */
    @PreAuthorize("@ss.hasPermi('system:receip:edit')")
    @Log(title = "base_supplier_receipt(供应商开票信息)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierReceip baseSupplierReceip)
    {
        return toAjax(baseSupplierReceipService.updateBaseSupplierReceip(baseSupplierReceip));
    }

    /**
     * 删除base_supplier_receipt(供应商开票信息)
     */
    @PreAuthorize("@ss.hasPermi('system:receip:remove')")
    @Log(title = "base_supplier_receipt(供应商开票信息)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{suppliercodes}")
    public AjaxResult remove(@PathVariable String[] suppliercodes)
    {
        return toAjax(baseSupplierReceipService.deleteBaseSupplierReceipByIds(suppliercodes));
    }
}
