package com.paic.ehis.claimcal.controller;


import com.paic.ehis.claimcal.domain.ClaimRuleConstraint;
import com.paic.ehis.claimcal.service.IClaimRuleConstraintService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 规则条件 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/constraint")
public class ClaimRuleConstraintController extends BaseController
{
    @Autowired
    private IClaimRuleConstraintService claimRuleConstraintService;

    /**
     * 查询规则条件 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:constraint:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimRuleConstraint claimRuleConstraint)
    {
        startPage();
        List<ClaimRuleConstraint> list = claimRuleConstraintService.selectClaimRuleConstraintList(claimRuleConstraint);
        return getDataTable(list);
    }

    /**
     * 导出规则条件 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:constraint:export')")
    @Log(title = "规则条件 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimRuleConstraint claimRuleConstraint) throws IOException
    {
        List<ClaimRuleConstraint> list = claimRuleConstraintService.selectClaimRuleConstraintList(claimRuleConstraint);
        ExcelUtil<ClaimRuleConstraint> util = new ExcelUtil<ClaimRuleConstraint>(ClaimRuleConstraint.class);
        util.exportExcel(response, list, "constraint");
    }

    /**
     * 获取规则条件 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:constraint:query')")
    @GetMapping(value = "/{constraintNo}")
    public AjaxResult getInfo(@PathVariable("constraintNo") String constraintNo)
    {
        return AjaxResult.success(claimRuleConstraintService.selectClaimRuleConstraintById(constraintNo));
    }

    /**
     * 新增规则条件 
     */
//    @PreAuthorize("@ss.hasPermi('system:constraint:add')")
    @Log(title = "规则条件 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimRuleConstraint claimRuleConstraint)
    {
        return toAjax(claimRuleConstraintService.insertClaimRuleConstraint(claimRuleConstraint));
    }

    /**
     * 修改规则条件 
     */
//    @PreAuthorize("@ss.hasPermi('system:constraint:edit')")
    @Log(title = "规则条件 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimRuleConstraint claimRuleConstraint)
    {
        return toAjax(claimRuleConstraintService.updateClaimRuleConstraint(claimRuleConstraint));
    }

    /**
     * 删除规则条件 
     */
//    @PreAuthorize("@ss.hasPermi('system:constraint:remove')")
    @Log(title = "规则条件 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{constraintNos}")
    public AjaxResult remove(@PathVariable String[] constraintNos)
    {
        return toAjax(claimRuleConstraintService.deleteClaimRuleConstraintByIds(constraintNos));
    }
}
