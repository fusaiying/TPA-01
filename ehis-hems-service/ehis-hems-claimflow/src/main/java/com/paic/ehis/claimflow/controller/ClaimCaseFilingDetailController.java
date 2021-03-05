package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimCaseFilingDetail;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.claimflow.service.IClaimCaseFilingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件归档明细Controller
 * 
 * @author sino
 * @date 2021-03-05
 */
@RestController
@RequestMapping("/FilingDetail")
public class ClaimCaseFilingDetailController extends BaseController
{
    @Autowired
    private IClaimCaseFilingDetailService claimCaseFilingDetailService;

    /**
     * 查询案件归档明细列表
     */
    //@PreAuthorize(hasPermi = "system:detail:list")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        startPage();
        List<ClaimCaseFilingDetail> list = claimCaseFilingDetailService.selectClaimCaseFilingDetailList(claimCaseFilingDetail);
        return getDataTable(list);
    }

    /**
     * 导出案件归档明细列表
     */
    //@PreAuthorize(hasPermi = "system:detail:export")
    @Log(title = "案件归档明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseFilingDetail claimCaseFilingDetail) throws IOException
    {
        List<ClaimCaseFilingDetail> list = claimCaseFilingDetailService.selectClaimCaseFilingDetailList(claimCaseFilingDetail);
        ExcelUtil<ClaimCaseFilingDetail> util = new ExcelUtil<ClaimCaseFilingDetail>(ClaimCaseFilingDetail.class);
        util.exportExcel(response, list, "detail");
    }

    /**
     * 获取案件归档明细详细信息
     */
    //@PreAuthorize(hasPermi = "system:detail:query")
    @GetMapping(value = "/{filingDetailId}")
    public AjaxResult getInfo(@PathVariable("filingDetailId") Long filingDetailId)
    {
        return AjaxResult.success(claimCaseFilingDetailService.selectClaimCaseFilingDetailById(filingDetailId));
    }

    /**
     * 新增案件归档明细
     */
    //@PreAuthorize(hasPermi = "system:detail:add")
    @Log(title = "案件归档明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        return toAjax(claimCaseFilingDetailService.insertClaimCaseFilingDetail(claimCaseFilingDetail));
    }

    /**
     * 修改案件归档明细
     */
    //@PreAuthorize(hasPermi = "system:detail:edit")
    @Log(title = "案件归档明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        return toAjax(claimCaseFilingDetailService.updateClaimCaseFilingDetail(claimCaseFilingDetail));
    }

    /**
     * 删除案件归档明细
     */
    //@PreAuthorize(hasPermi = "system:detail:remove")
    @Log(title = "案件归档明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{filingDetailIds}")
    public AjaxResult remove(@PathVariable Long[] filingDetailIds)
    {
        return toAjax(claimCaseFilingDetailService.deleteClaimCaseFilingDetailByIds(filingDetailIds));
    }
}
