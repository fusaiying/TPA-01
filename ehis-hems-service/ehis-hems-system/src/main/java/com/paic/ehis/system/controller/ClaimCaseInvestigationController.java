package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseInvestigation;
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
import com.paic.ehis.system.service.IClaimCaseInvestigationService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件调查任务Controller
 * 
 * @author sino
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/investigation")
public class ClaimCaseInvestigationController extends BaseController
{
    @Autowired
    private IClaimCaseInvestigationService claimCaseInvestigationService;

    /**
     * 查询案件调查任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:investigation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseInvestigation claimCaseInvestigation)
    {
        startPage();
        List<ClaimCaseInvestigation> list = claimCaseInvestigationService.selectClaimCaseInvestigationList(claimCaseInvestigation);
        return getDataTable(list);
    }

    /**
     * 导出案件调查任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:investigation:export')")
    @Log(title = "案件调查任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseInvestigation claimCaseInvestigation) throws IOException
    {
        List<ClaimCaseInvestigation> list = claimCaseInvestigationService.selectClaimCaseInvestigationList(claimCaseInvestigation);
        ExcelUtil<ClaimCaseInvestigation> util = new ExcelUtil<ClaimCaseInvestigation>(ClaimCaseInvestigation.class);
        util.exportExcel(response, list, "investigation");
    }

    /**
     * 获取案件调查任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:investigation:query')")
    @GetMapping(value = "/{invNo}")
    public AjaxResult getInfo(@PathVariable("invNo") Long invNo)
    {
        return AjaxResult.success(claimCaseInvestigationService.selectClaimCaseInvestigationById(invNo));
    }

    /**
     * 新增案件调查任务
     */
    @PreAuthorize("@ss.hasPermi('system:investigation:add')")
    @Log(title = "案件调查任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseInvestigation claimCaseInvestigation)
    {
        return toAjax(claimCaseInvestigationService.insertClaimCaseInvestigation(claimCaseInvestigation));
    }

    /**
     * 修改案件调查任务
     */
    @PreAuthorize("@ss.hasPermi('system:investigation:edit')")
    @Log(title = "案件调查任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseInvestigation claimCaseInvestigation)
    {
        return toAjax(claimCaseInvestigationService.updateClaimCaseInvestigation(claimCaseInvestigation));
    }

    /**
     * 删除案件调查任务
     */
    @PreAuthorize("@ss.hasPermi('system:investigation:remove')")
    @Log(title = "案件调查任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{invNos}")
    public AjaxResult remove(@PathVariable Long[] invNos)
    {
        return toAjax(claimCaseInvestigationService.deleteClaimCaseInvestigationByIds(invNos));
    }
}
