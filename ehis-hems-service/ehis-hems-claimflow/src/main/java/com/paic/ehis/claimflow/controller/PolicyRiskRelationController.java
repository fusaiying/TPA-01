package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.claimflow.domain.PolicyAndRiskRelation;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import com.paic.ehis.claimflow.service.IPolicyRiskRelationService;
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
 * 保单险种关联Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/policyRelation")
public class PolicyRiskRelationController extends BaseController
{
    @Autowired
    private IPolicyRiskRelationService policyRiskRelationService;

    /**
     * 查询保单险种关联列表
     */
   /* @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(PolicyRiskRelation policyRiskRelation)
    {
        startPage();
        List<PolicyRiskRelation> list = policyRiskRelationService.selectPolicyRiskRelationList(policyRiskRelation);
        return getDataTable(list);
    }*/

    /**
     * 跨服务查询 保单险种关联列表
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:list')")
    @PostMapping("/relationList")
    public TableDataInfo relationList(@RequestBody PolicyAndRiskRelation policyAndRiskRelation)
    {
        List<PolicyRiskRelation> list = policyRiskRelationService.selectPolicyAndRiskRelation(policyAndRiskRelation);
        return getDataTable(list);
    }

    /**
     * 跨服务查询 保单险种关联列表       PolicyAndRiskRelation
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:list')")
    @PostMapping("/companyPolicyList")
    public TableDataInfo companyPolicyList(@RequestBody PolicyAndRiskRelation PolicyAndRiskRelation)
    {
        List<CompanyRiskPolicyInfo> list = policyRiskRelationService.selectPolicyByCompanyRisk(PolicyAndRiskRelation);
        return getDataTable(list);
    }

    /**
     * 跨服务查询 查询出单公司险种保单 字段列表
     *
     * @param policyAndRiskRelation  出单公司编码、险种编码
     * @return 结果
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:list')")
    @PostMapping(value = "validStartDateList")
    public TableDataInfo getValidStartDateList(@RequestBody PolicyAndRiskRelation policyAndRiskRelation)
    {
        List<PolicyRiskRelation> policyRiskRelations = policyRiskRelationService.selectValidStartDateList(policyAndRiskRelation);
        return getDataTable(policyRiskRelations);
    }

    /*
     *
     * 导出保单险种关联列表
     * */
   // @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "保单险种关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PolicyRiskRelation policyRiskRelation) throws IOException
    {
        List<PolicyRiskRelation> list = policyRiskRelationService.selectPolicyRiskRelationList(policyRiskRelation);
        ExcelUtil<PolicyRiskRelation> util = new ExcelUtil<PolicyRiskRelation>(PolicyRiskRelation.class);
        util.exportExcel(response, list, "relation");
    }

    /**
     * 获取保单险种关联详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId)
    {
        return AjaxResult.success(policyRiskRelationService.selectPolicyRiskRelationById(relationId));
    }

    /**
     * 新增保单险种关联
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "保单险种关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PolicyRiskRelation policyRiskRelation)
    {
        return toAjax(policyRiskRelationService.insertPolicyRiskRelation(policyRiskRelation));
    }

    /**
     * 修改保单险种关联
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "保单险种关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PolicyRiskRelation policyRiskRelation)
    {
        return toAjax(policyRiskRelationService.updatePolicyRiskRelation(policyRiskRelation));
    }

    /**
     * 删除保单险种关联
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "保单险种关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds)
    {
        return toAjax(policyRiskRelationService.deletePolicyRiskRelationByIds(relationIds));
    }
}
