package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.paic.ehis.system.domain.BaseFeeitem;
import com.paic.ehis.system.service.IBaseFeeitemService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 费用项信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/feeitem")
public class BaseFeeitemController extends BaseController
{
    @Autowired
    private IBaseFeeitemService baseFeeitemService;

    /**
     * 查询费用项信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeitem:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseFeeitem baseFeeitem)
    {
        startPage();
        List<BaseFeeitem> list = baseFeeitemService.selectBaseFeeitemList(baseFeeitem);
        return getDataTable(list);
    }

    /**
     * 导出费用项信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:feeitem:export')")
    @Log(title = "费用项信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseFeeitem baseFeeitem) throws IOException
    {
        List<BaseFeeitem> list = baseFeeitemService.selectBaseFeeitemList(baseFeeitem);
        ExcelUtil<BaseFeeitem> util = new ExcelUtil<BaseFeeitem>(BaseFeeitem.class);
        util.exportExcel(response, list, "feeitem");
    }

    /**
     * 获取费用项信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:feeitem:query')")
    @GetMapping(value = "/{feeitemCode}")
    public AjaxResult getInfo(@PathVariable("feeitemCode") String feeitemCode)
    {
        return AjaxResult.success(baseFeeitemService.selectBaseFeeitemById(feeitemCode));
    }

    /**
     * 新增费用项信息 
     */
    @PreAuthorize("@ss.hasPermi('system:feeitem:add')")
    @Log(title = "费用项信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseFeeitem baseFeeitem)
    {
        return toAjax(baseFeeitemService.insertBaseFeeitem(baseFeeitem));
    }

    /**
     * 修改费用项信息 
     */
    @PreAuthorize("@ss.hasPermi('system:feeitem:edit')")
    @Log(title = "费用项信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseFeeitem baseFeeitem)
    {
        return toAjax(baseFeeitemService.updateBaseFeeitem(baseFeeitem));
    }

    /**
     * 删除费用项信息 
     */
    @PreAuthorize("@ss.hasPermi('system:feeitem:remove')")
    @Log(title = "费用项信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feeitemCodes}")
    public AjaxResult remove(@PathVariable String[] feeitemCodes)
    {
        return toAjax(baseFeeitemService.deleteBaseFeeitemByIds(feeitemCodes));
    }
}
