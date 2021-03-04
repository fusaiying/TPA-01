package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseSupplierInfo;
import com.paic.ehis.base.service.IBaseSupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSupplierInfo baseSupplierInfo) throws Exception {
        startPage(baseSupplierInfo);
        List<BaseSupplierInfo> list = baseSupplierInfoService.selectBaseSupplierInfoList(baseSupplierInfo);
        return getDataTable(list);
    }

    @PostMapping("/list1")
    public TableDataInfo list1(@RequestBody BaseSupplierInfo baseSupplierInfo) {
        startPage(baseSupplierInfo);
        List<BaseSupplierInfo> list = baseSupplierInfoService.selectBaseSupplierInfoList1(baseSupplierInfo);
        return getDataTable(list);
    }

    @PostMapping("/list2")
    public TableDataInfo list2(@RequestBody BaseSupplierInfo baseSupplierInfo) {
        startPage(baseSupplierInfo);
        List<BaseSupplierInfo> list = baseSupplierInfoService.selectBaseSupplierInfoList2(baseSupplierInfo);
        return getDataTable(list);
    }

    /**
     * 导出base_supplier_info（供应商基础信息）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
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
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{servcomNo}")
    public AjaxResult getInfo(@PathVariable("servcomNo") String servcomNo)
    {
        return AjaxResult.success(baseSupplierInfoService.selectBaseSupplierInfoById(servcomNo));
    }

    /**
     * 新增base_supplier_info（供应商基础信息）
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BaseSupplierInfo baseSupplierInfo)
    {
        return AjaxResult.success(baseSupplierInfoService.insertBaseSupplierInfo(baseSupplierInfo));
    }

    /**
     * 修改base_supplier_info（供应商基础信息）
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierInfo baseSupplierInfo)
    {
        return toAjax(baseSupplierInfoService.updateBaseSupplierInfo(baseSupplierInfo));
    }

    /**
     * 删除base_supplier_info（供应商基础信息）
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "base_supplier_info（供应商基础信息）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servcomNos}")
    public AjaxResult remove(@PathVariable String[] servcomNos)
    {
        return toAjax(baseSupplierInfoService.deleteBaseSupplierInfoByIds(servcomNos));
    }

    /**
     * 查询base_supplier_info（供应商基础信息）所有列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/allList")
    public AjaxResult getAllBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo) {

        List<BaseSupplierInfo> list = baseSupplierInfoService.getAllBaseSupplierInfo(baseSupplierInfo);

        return AjaxResult.success(list);
    }
}
