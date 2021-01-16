package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseBillDetail;
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
import com.paic.ehis.system.service.IClaimCaseBillDetailService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件账单费用项明细Controller
 * 
 * @author sino
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/billDetail")
public class ClaimCaseBillDetailController extends BaseController
{
    @Autowired
    private IClaimCaseBillDetailService claimCaseBillDetailService;

    /**
     * 查询案件账单费用项明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseBillDetail claimCaseBillDetail)
    {
        startPage();
        List<ClaimCaseBillDetail> list = claimCaseBillDetailService.selectClaimCaseBillDetailList(claimCaseBillDetail);
        return getDataTable(list);
    }

    /**
     * 导出案件账单费用项明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "案件账单费用项明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseBillDetail claimCaseBillDetail) throws IOException
    {
        List<ClaimCaseBillDetail> list = claimCaseBillDetailService.selectClaimCaseBillDetailList(claimCaseBillDetail);
        ExcelUtil<ClaimCaseBillDetail> util = new ExcelUtil<ClaimCaseBillDetail>(ClaimCaseBillDetail.class);
        util.exportExcel(response, list, "detail");
    }

    /**
     * 获取案件账单费用项明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return AjaxResult.success(claimCaseBillDetailService.selectClaimCaseBillDetailById(detailId));
    }

    /**
     * 新增案件账单费用项明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "案件账单费用项明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseBillDetail claimCaseBillDetail)
    {
        return toAjax(claimCaseBillDetailService.insertClaimCaseBillDetail(claimCaseBillDetail));
    }

    /**
     * 修改案件账单费用项明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "案件账单费用项明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseBillDetail claimCaseBillDetail)
    {
        return toAjax(claimCaseBillDetailService.updateClaimCaseBillDetail(claimCaseBillDetail));
    }

    /**
     * 删除案件账单费用项明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "案件账单费用项明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(claimCaseBillDetailService.deleteClaimCaseBillDetailByIds(detailIds));
    }
}
