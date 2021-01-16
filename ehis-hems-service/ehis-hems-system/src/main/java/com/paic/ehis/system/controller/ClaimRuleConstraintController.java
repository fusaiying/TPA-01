package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimRuleConstraint;
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
import com.paic.ehis.system.service.IClaimRuleConstraintService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

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
    @PreAuthorize("@ss.hasPermi('system:constraint:list')")
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
    @PreAuthorize("@ss.hasPermi('system:constraint:export')")
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
    @PreAuthorize("@ss.hasPermi('system:constraint:query')")
    @GetMapping(value = "/{constraintNo}")
    public AjaxResult getInfo(@PathVariable("constraintNo") String constraintNo)
    {
        return AjaxResult.success(claimRuleConstraintService.selectClaimRuleConstraintById(constraintNo));
    }

    /**
     * 新增规则条件 
     */
    @PreAuthorize("@ss.hasPermi('system:constraint:add')")
    @Log(title = "规则条件 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimRuleConstraint claimRuleConstraint)
    {
        return toAjax(claimRuleConstraintService.insertClaimRuleConstraint(claimRuleConstraint));
    }

    /**
     * 修改规则条件 
     */
    @PreAuthorize("@ss.hasPermi('system:constraint:edit')")
    @Log(title = "规则条件 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimRuleConstraint claimRuleConstraint)
    {
        return toAjax(claimRuleConstraintService.updateClaimRuleConstraint(claimRuleConstraint));
    }

    /**
     * 删除规则条件 
     */
    @PreAuthorize("@ss.hasPermi('system:constraint:remove')")
    @Log(title = "规则条件 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{constraintNos}")
    public AjaxResult remove(@PathVariable String[] constraintNos)
    {
        return toAjax(claimRuleConstraintService.deleteClaimRuleConstraintByIds(constraintNos));
    }
}
