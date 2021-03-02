package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseIssuingcompanyRule;
import com.paic.ehis.base.domain.ClaimProduct;
import com.paic.ehis.base.domain.dto.IssuingcompanyRuleDTO;
import com.paic.ehis.base.service.IBaseIssuingcompanyRuleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出单公司规则 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/rule")
public class BaseIssuingcompanyRuleController extends BaseController
{
    @Autowired
    private IBaseIssuingcompanyRuleService baseIssuingcompanyRuleService;

    /**
     * 查询出单公司规则 列表
     *///    @GetMapping("/list")
    //@PreAuthorize("@ss.hasPermi('system:rule:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        startPage(baseIssuingcompanyRule);
        return baseIssuingcompanyRuleService.selectBaseIssuingcompanyRuleList(baseIssuingcompanyRule);
    }

    /**
     * 查询出单公司下属产品 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:rule:list')")
    @GetMapping("/riskList")
    public TableDataInfo riskList(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        List<ClaimProduct> claimProducts = baseIssuingcompanyRuleService.selectBaseIssuingCompanyRiskList(baseIssuingcompanyRule);
        return getDataTable(claimProducts);
    }

    /**
     * 导出出单公司规则 列表
     */
    /*//@PreAuthorize("@ss.hasPermi('system:rule:export')")
    @Log(title = "出单公司规则 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIssuingcompanyRule baseIssuingcompanyRule) throws IOException
    {
        List<IssuingcompanyRuleVO> list = baseIssuingcompanyRuleService.selectBaseIssuingcompanyRuleList(baseIssuingcompanyRule);
        ExcelUtil<IssuingcompanyRuleVO> util = new ExcelUtil<IssuingcompanyRuleVO>(IssuingcompanyRuleVO.class);
        util.exportExcel(response, list, "rule");
    }*/

    /**
     * 获取出单公司规则 详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:rule:query')")
    @GetMapping(value = "/{ruleNo}")
    public AjaxResult getInfo(@PathVariable("ruleNo") String ruleNo)
    {
        return AjaxResult.success(baseIssuingcompanyRuleService.selectBaseIssuingcompanyRuleById(ruleNo));
    }

    /**
     * 新增或修改出单公司规则
     */
    //@PreAuthorize("@ss.hasPermi('system:rule:query')")
    @PostMapping ("/query")
    public AjaxResult getInfo(IssuingcompanyRuleDTO issuingcompanyRuleDTO) {
        return toAjax( baseIssuingcompanyRuleService.addAndModifyBaseIssuingcompanyRule(issuingcompanyRuleDTO));
    }

    /**
     * 新增出单公司规则 
     */
    //@PreAuthorize("@ss.hasPermi('system:rule:add')")
    @Log(title = "出单公司规则 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        return toAjax(baseIssuingcompanyRuleService.insertBaseIssuingcompanyRule(baseIssuingcompanyRule));
    }

    /**
     * 修改出单公司规则 
     */
    //@PreAuthorize("@ss.hasPermi('system:rule:edit')")
    @Log(title = "出单公司规则 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        return toAjax(baseIssuingcompanyRuleService.updateBaseIssuingcompanyRule(baseIssuingcompanyRule));
    }

    /**
     * 删除出单公司规则 
     */
    //@PreAuthorize("@ss.hasPermi('system:rule:remove')")
    @Log(title = "出单公司规则 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ruleNos}")
    public AjaxResult remove(@PathVariable String[] ruleNos)
    {
        return toAjax(baseIssuingcompanyRuleService.deleteBaseIssuingcompanyRuleByIds(ruleNos));
    }
}
