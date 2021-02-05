package com.paic.ehis.claimflow.controller;

import com.sino.common.core.utils.poi.ExcelUtil;
import com.sino.common.core.web.controller.BaseController;
import com.sino.common.core.web.domain.AjaxResult;
import com.sino.common.core.web.page.TableDataInfo;
import com.sino.common.log.annotation.Log;
import com.sino.common.log.enums.BusinessType;
import com.sino.system.domain.ClaimProductPlan;
import com.sino.system.service.IClaimProductPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 计划信息 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/plan")
public class ClaimProductPlanController extends BaseController
{
    @Autowired
    private IClaimProductPlanService claimProductPlanService;

    /**
     * 查询计划信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimProductPlan claimProductPlan)
    {
        startPage();
        List<ClaimProductPlan> list = claimProductPlanService.selectClaimProductPlanList(claimProductPlan);
        return getDataTable(list);
    }

    /**
     * 导出计划信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:export')")
    @Log(title = "计划信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimProductPlan claimProductPlan) throws IOException
    {
        List<ClaimProductPlan> list = claimProductPlanService.selectClaimProductPlanList(claimProductPlan);
        ExcelUtil<ClaimProductPlan> util = new ExcelUtil<ClaimProductPlan>(ClaimProductPlan.class);
        util.exportExcel(response, list, "plan");
    }

    /**
     * 获取计划信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:plan:query')")
    @GetMapping(value = "/{planCode}")
    public AjaxResult getInfo(@PathVariable("planCode") String planCode)
    {
        return AjaxResult.success(claimProductPlanService.selectClaimProductPlanById(planCode));
    }

    /**
     * 新增计划信息 
     */
    @PreAuthorize("@ss.hasPermi('system:plan:add')")
    @Log(title = "计划信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimProductPlan claimProductPlan)
    {
        return toAjax(claimProductPlanService.insertClaimProductPlan(claimProductPlan));
    }

    /**
     * 修改计划信息 
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @Log(title = "计划信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimProductPlan claimProductPlan)
    {
        return toAjax(claimProductPlanService.updateClaimProductPlan(claimProductPlan));
    }

    /**
     * 删除计划信息 
     */
    @PreAuthorize("@ss.hasPermi('system:plan:remove')")
    @Log(title = "计划信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planCodes}")
    public AjaxResult remove(@PathVariable String[] planCodes)
    {
        return toAjax(claimProductPlanService.deleteClaimProductPlanByIds(planCodes));
    }
}
