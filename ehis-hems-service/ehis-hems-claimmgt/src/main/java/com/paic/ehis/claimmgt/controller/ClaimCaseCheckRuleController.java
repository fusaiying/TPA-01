package com.paic.ehis.claimmgt.controller;


import com.paic.ehis.claimmgt.domain.ClaimCaseCheckRule;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseCheckRuleDTO;
import com.paic.ehis.claimmgt.service.IClaimCaseCheckRuleService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件抽检规则Controller
 * 
 * @author sino
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/checkrule")
public class ClaimCaseCheckRuleController extends BaseController
{
    @Autowired
    private IClaimCaseCheckRuleService claimCaseCheckRuleService;

    /**
     * 查询案件抽检规则列表
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseCheckRule claimCaseCheckRule)
    {
        startPage();
        List<ClaimCaseCheckRule> list = claimCaseCheckRuleService.selectClaimCaseCheckRuleList(claimCaseCheckRule);
        return getDataTable(list);
    }

    /**
     * 导出案件抽检规则列表
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:export')")
    @Log(title = "案件抽检规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseCheckRule claimCaseCheckRule) throws IOException
    {
        List<ClaimCaseCheckRule> list = claimCaseCheckRuleService.selectClaimCaseCheckRuleList(claimCaseCheckRule);
        ExcelUtil<ClaimCaseCheckRule> util = new ExcelUtil<ClaimCaseCheckRule>(ClaimCaseCheckRule.class);
        util.exportExcel(response, list, "rule");
    }

    /**
     * 查询案件抽检规则
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:list')")
    @PostMapping("/selectClaimCaseCheckRule")
    public TableDataInfo selectClaimCaseCheckRule(@RequestBody ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO)
    {
        startPage(claimCaseCheckRuleDTO);
        List<ClaimCaseCheckRuleDTO> list = claimCaseCheckRuleService.selectClaimCaseCheckRule(claimCaseCheckRuleDTO);
        return getDataTable(list);
    }

    /**
     * 获取案件抽检规则详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:query')")
    @GetMapping(value = "/{checkRuleNo}")
    public AjaxResult getInfo(@PathVariable("checkRuleNo") String checkRuleNo)
    {
        return AjaxResult.success(claimCaseCheckRuleService.selectClaimCaseCheckRuleById(checkRuleNo));
    }

    /**
     * 新增案件抽检规则
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:add')")
    @Log(title = "案件抽检规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseCheckRule claimCaseCheckRule)
    {
        return toAjax(claimCaseCheckRuleService.insertClaimCaseCheckRule(claimCaseCheckRule));
    }

    /**
     * 新增
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:add')")
    @Log(title = "案件抽检规则", businessType = BusinessType.INSERT)
    @PostMapping("/addRule")
    public AjaxResult addRule(@RequestBody ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO)
    {
        claimCaseCheckRuleService.insertClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);
        return AjaxResult.success();
    }

    /**
     * 修改案件抽检规则
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:edit')")
    @Log(title = "案件抽检规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseCheckRule claimCaseCheckRule)
    {
        return toAjax(claimCaseCheckRuleService.updateClaimCaseCheckRule(claimCaseCheckRule));
    }

    /**
     * 修改
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:edit')")
    @Log(title = "案件抽检规则", businessType = BusinessType.UPDATE)
    @PostMapping("/editRule")
    public AjaxResult editRule(@RequestBody ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO)
    {
        claimCaseCheckRuleService.updateClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);
        return AjaxResult.success();
    }

    /**
     * 删除案件抽检规则
     */
    //@PreAuthorize("@ss.hasPermi('system:checkrule:remove')")
    @Log(title = "案件抽检规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{checkRuleNos}")
    public AjaxResult remove(@PathVariable String[] checkRuleNos)
    {
        return toAjax(claimCaseCheckRuleService.deleteClaimCaseCheckRuleByIds(checkRuleNos));
    }
}
