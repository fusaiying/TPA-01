package com.paic.ehis.base.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.base.domain.BaseSupplierOutlets;
import com.paic.ehis.base.service.IBaseSupplierOutletsService;
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

    /**
     * 查询base_supplier_outlets（服务网点配置）列表
     */
    @PreAuthorize("@ss.hasPermi('system:outlets:list')")
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
    @PreAuthorize("@ss.hasPermi('system:outlets:export')")
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
    @PreAuthorize("@ss.hasPermi('system:outlets:query')")
    @GetMapping(value = "/{servcomno}")
    public AjaxResult getInfo(@PathVariable("servcomno") String servcomno)
    {
        return AjaxResult.success(baseSupplierOutletsService.selectBaseSupplierOutletsById(servcomno));
    }

    /**
     * 新增base_supplier_outlets（服务网点配置）
     */
    @PreAuthorize("@ss.hasPermi('system:outlets:add')")
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSupplierOutlets baseSupplierOutlets)
    {
        return toAjax(baseSupplierOutletsService.insertBaseSupplierOutlets(baseSupplierOutlets));
    }

    /**
     * 修改base_supplier_outlets（服务网点配置）
     */
    @PreAuthorize("@ss.hasPermi('system:outlets:edit')")
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierOutlets baseSupplierOutlets)
    {
        return toAjax(baseSupplierOutletsService.updateBaseSupplierOutlets(baseSupplierOutlets));
    }

    /**
     * 删除base_supplier_outlets（服务网点配置）
     */
    @PreAuthorize("@ss.hasPermi('system:outlets:remove')")
    @Log(title = "base_supplier_outlets（服务网点配置）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servcomnos}")
    public AjaxResult remove(@PathVariable String[] servcomnos)
    {
        return toAjax(baseSupplierOutletsService.deleteBaseSupplierOutletsByIds(servcomnos));
    }
}
