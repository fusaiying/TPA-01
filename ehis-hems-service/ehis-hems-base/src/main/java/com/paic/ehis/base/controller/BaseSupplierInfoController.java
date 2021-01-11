package com.paic.ehis.base.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.base.domain.BaseSupplierInfo;
import com.paic.ehis.base.service.IBaseSupplierInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 * base_supplier_info（供应商基础信息）Controller
 * 
 * @author sino
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/info")
public class BaseSupplierInfoController extends BaseController
{
    @Autowired
    private IBaseSupplierInfoService baseSupplierInfoService;

    /**
     * 查询base_supplier_info（供应商基础信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseSupplierInfo baseSupplierInfo) throws Exception {
        startPage();
        List<BaseSupplierInfo> list = baseSupplierInfoService.selectBaseSupplierInfoList(baseSupplierInfo);
        return getDataTable(list);
    }

    /**
     * 导出base_supplier_info（供应商基础信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseSupplierInfo baseSupplierInfo) throws Exception
    {
        List<BaseSupplierInfo> list = baseSupplierInfoService.selectBaseSupplierInfoList(baseSupplierInfo);
        ExcelUtil<BaseSupplierInfo> util = new ExcelUtil<BaseSupplierInfo>(BaseSupplierInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取base_supplier_info（供应商基础信息）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{servcomNo}")
    public AjaxResult getInfo(@PathVariable("servcomNo") String servcomNo)
    {
        return AjaxResult.success(baseSupplierInfoService.selectBaseSupplierInfoById(servcomNo));
    }

    /**
     * 新增base_supplier_info（供应商基础信息）
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSupplierInfo baseSupplierInfo)
    {
        return toAjax(baseSupplierInfoService.insertBaseSupplierInfo(baseSupplierInfo));
    }

    /**
     * 修改base_supplier_info（供应商基础信息）
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierInfo baseSupplierInfo)
    {
        return toAjax(baseSupplierInfoService.updateBaseSupplierInfo(baseSupplierInfo));
    }

    /**
     * 删除base_supplier_info（供应商基础信息）
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servcomNos}")
    public AjaxResult remove(@PathVariable String[] servcomNos)
    {
        return toAjax(baseSupplierInfoService.deleteBaseSupplierInfoByIds(servcomNos));
    }
}
