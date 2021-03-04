package com.paic.ehis.claimflow.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.claimflow.domain.ClaimCaseInvestigateDetail;
import com.paic.ehis.claimflow.service.IClaimCaseInvestigateDetailService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
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
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件调查明细Controller
 * 
 * @author sino
 * @date 2021-03-04
 */
@RestController
@RequestMapping("/detail")
public class ClaimCaseInvestigateDetailController extends BaseController
{
    @Autowired
    private IClaimCaseInvestigateDetailService claimCaseInvestigateDetailService;

    /**
     * 查询案件调查明细列表
     */
    //@PreAuthorize(hasPermi = "system:detail:list")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseInvestigateDetail claimCaseInvestigateDetail)
    {
        startPage();
        List<ClaimCaseInvestigateDetail> list = claimCaseInvestigateDetailService.selectClaimCaseInvestigateDetailList(claimCaseInvestigateDetail);
        return getDataTable(list);
    }

    /**
     * 导出案件调查明细列表
     */
    //@PreAuthorize(hasPermi = "system:detail:export")
    @Log(title = "案件调查明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseInvestigateDetail claimCaseInvestigateDetail) throws IOException
    {
        List<ClaimCaseInvestigateDetail> list = claimCaseInvestigateDetailService.selectClaimCaseInvestigateDetailList(claimCaseInvestigateDetail);
        ExcelUtil<ClaimCaseInvestigateDetail> util = new ExcelUtil<ClaimCaseInvestigateDetail>(ClaimCaseInvestigateDetail.class);
        util.exportExcel(response, list, "detail");
    }

    /**
     * 获取案件调查明细详细信息
     */
    //@PreAuthorize(hasPermi = "system:detail:query")
    @GetMapping(value = "/{investigateDetailNo}")
    public AjaxResult getInfo(@PathVariable("investigateDetailNo") Long investigateDetailNo)
    {
        return AjaxResult.success(claimCaseInvestigateDetailService.selectClaimCaseInvestigateDetailById(investigateDetailNo));
    }

    /**
     * 新增案件调查明细
     */
    //@PreAuthorize(hasPermi = "system:detail:add")
    @Log(title = "案件调查明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseInvestigateDetail claimCaseInvestigateDetail)
    {
        return toAjax(claimCaseInvestigateDetailService.insertClaimCaseInvestigateDetail(claimCaseInvestigateDetail));
    }

    /**
     * 修改案件调查明细
     */
    //@PreAuthorize(hasPermi = "system:detail:edit")
    @Log(title = "案件调查明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseInvestigateDetail claimCaseInvestigateDetail)
    {
        return toAjax(claimCaseInvestigateDetailService.updateClaimCaseInvestigateDetail(claimCaseInvestigateDetail));
    }

    /**
     * 删除案件调查明细
     */
    //@PreAuthorize(hasPermi = "system:detail:remove")
    @Log(title = "案件调查明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{investigateDetailNos}")
    public AjaxResult remove(@PathVariable Long[] investigateDetailNos)
    {
        return toAjax(claimCaseInvestigateDetailService.deleteClaimCaseInvestigateDetailByIds(investigateDetailNos));
    }

    /**
     * 根据报案号查案件调差历史记录
     */
    @GetMapping("/listByRptNo")
    public TableDataInfo listByRptNo(String rptNo)
    {
        startPage();
        List<ClaimCaseInvestigateDetail> list = claimCaseInvestigateDetailService.selectClaimCaseInvestigateDetailListByRptNo(rptNo);
        return getDataTable(list);
    }
}
