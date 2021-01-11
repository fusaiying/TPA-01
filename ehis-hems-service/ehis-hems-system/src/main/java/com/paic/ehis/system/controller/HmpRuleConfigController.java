package com.paic.ehis.system.controller;

import com.paic.ehis.system.domain.HmpBenefitRule;
import com.paic.ehis.system.service.IHmpBenefitRuleService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 权益分配规则Controller
 *
 * @author sino
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/ruleconfig")
public class HmpRuleConfigController extends BaseController {


    @Autowired
    private IHmpBenefitRuleService hmpBenefitRuleService;


    /**
     * 查询权益分配规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpBenefitRule hmpBenefitRule)
    {
        startPage();
        List<HmpBenefitRule> list = hmpBenefitRuleService.selectHmpBenefitRuleList(hmpBenefitRule);
        return getDataTable(list);
    }

    /**
     * 导出权益分配规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:export')")
    @Log(title = "权益分配规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpBenefitRule hmpBenefitRule) throws IOException
    {
        List<HmpBenefitRule> list = hmpBenefitRuleService.selectHmpBenefitRuleList(hmpBenefitRule);
        ExcelUtil<HmpBenefitRule> util = new ExcelUtil<HmpBenefitRule>(HmpBenefitRule.class);
        util.exportExcel(response, list, "benefit");
    }

    /**
     * 获取权益分配规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:query')")
    @GetMapping(value = "/{rulecode}")
    public AjaxResult getInfo(@PathVariable("rulecode") String rulecode)
    {
        return AjaxResult.success(hmpBenefitRuleService.selectHmpBenefitRuleById(rulecode));
    }

    /**
     * 新增权益分配规则
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:add')")
    @Log(title = "权益分配规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpBenefitRule hmpBenefitRule)
    {
        return toAjax(hmpBenefitRuleService.insertHmpBenefitRule(hmpBenefitRule));
    }

    /**
     * 修改权益分配规则
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:edit')")
    @Log(title = "权益分配规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpBenefitRule hmpBenefitRule)
    {
        return toAjax(hmpBenefitRuleService.updateHmpBenefitRule(hmpBenefitRule));
    }

    /**
     * 删除权益分配规则
     */
    @PreAuthorize("@ss.hasPermi('system:benefit:remove')")
    @Log(title = "权益分配规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rulecodes}")
    public AjaxResult remove(@PathVariable String[] rulecodes)
    {
        return toAjax(hmpBenefitRuleService.deleteHmpBenefitRuleByIds(rulecodes));
    }

}
