package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCasePolicy;
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
import com.paic.ehis.system.service.IClaimCasePolicyService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件保单关联Controller
 * 
 * @author sino
 * @date 2021-01-14
 */
@RestController
@RequestMapping("/policy")
public class ClaimCasePolicyController extends BaseController
{
    @Autowired
    private IClaimCasePolicyService claimCasePolicyService;

    /**
     * 查询案件保单关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:policy:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCasePolicy claimCasePolicy)
    {
        startPage();
        List<ClaimCasePolicy> list = claimCasePolicyService.selectClaimCasePolicyList(claimCasePolicy);
        return getDataTable(list);
    }

    /**
     * 导出案件保单关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:policy:export')")
    @Log(title = "案件保单关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCasePolicy claimCasePolicy) throws IOException
    {
        List<ClaimCasePolicy> list = claimCasePolicyService.selectClaimCasePolicyList(claimCasePolicy);
        ExcelUtil<ClaimCasePolicy> util = new ExcelUtil<ClaimCasePolicy>(ClaimCasePolicy.class);
        util.exportExcel(response, list, "policy");
    }

    /**
     * 获取案件保单关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:policy:query')")
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId)
    {
        return AjaxResult.success(claimCasePolicyService.selectClaimCasePolicyById(relationId));
    }

    /**
     * 新增案件保单关联
     */
    @PreAuthorize("@ss.hasPermi('system:policy:add')")
    @Log(title = "案件保单关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCasePolicy claimCasePolicy)
    {
        return toAjax(claimCasePolicyService.insertClaimCasePolicy(claimCasePolicy));
    }

    /**
     * 修改案件保单关联
     */
    @PreAuthorize("@ss.hasPermi('system:policy:edit')")
    @Log(title = "案件保单关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCasePolicy claimCasePolicy)
    {
        return toAjax(claimCasePolicyService.updateClaimCasePolicy(claimCasePolicy));
    }

    /**
     * 删除案件保单关联
     */
    @PreAuthorize("@ss.hasPermi('system:policy:remove')")
    @Log(title = "案件保单关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds)
    {
        return toAjax(claimCasePolicyService.deleteClaimCasePolicyByIds(relationIds));
    }
}
