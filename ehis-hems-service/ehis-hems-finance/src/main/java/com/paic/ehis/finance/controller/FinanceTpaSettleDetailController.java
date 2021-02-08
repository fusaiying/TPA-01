package com.paic.ehis.finance.controller;


import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import com.paic.ehis.finance.domain.FinanceTpaSettleDetail;
import com.paic.ehis.finance.service.IFinanceTpaSettleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TPA服务费结算明细Controller
 * 
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/tpaDetail")
public class FinanceTpaSettleDetailController extends BaseController
{
    @Autowired
    private IFinanceTpaSettleDetailService financeTpaSettleDetailService;

    /**
     * 查询TPA服务费结算明细列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinanceTpaSettleDetail financeTpaSettleDetail)
    {
        startPage();
        List<FinanceTpaSettleDetail> list = financeTpaSettleDetailService.selectFinanceTpaSettleDetailList(financeTpaSettleDetail);
        return getDataTable(list);
    }

    /**
     * 导出TPA服务费结算明细列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:detail:export')")
    @Log(title = "TPA服务费结算明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinanceTpaSettleDetail financeTpaSettleDetail) throws IOException
    {
        List<FinanceTpaSettleDetail> list = financeTpaSettleDetailService.selectFinanceTpaSettleDetailList(financeTpaSettleDetail);
        ExcelUtil<FinanceTpaSettleDetail> util = new ExcelUtil<FinanceTpaSettleDetail>(FinanceTpaSettleDetail.class);
        util.exportExcel(response, list, "detail");
    }

    /**
     * 获取TPA服务费结算明细详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return AjaxResult.success(financeTpaSettleDetailService.selectFinanceTpaSettleDetailById(detailId));
    }

    /**
     * 新增TPA服务费结算明细
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:detail:add')")
    @Log(title = "TPA服务费结算明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinanceTpaSettleDetail financeTpaSettleDetail)
    {
        return toAjax(financeTpaSettleDetailService.insertFinanceTpaSettleDetail(financeTpaSettleDetail));
    }

    /**
     * 修改TPA服务费结算明细
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:detail:edit')")
    @Log(title = "TPA服务费结算明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinanceTpaSettleDetail financeTpaSettleDetail)
    {
        return toAjax(financeTpaSettleDetailService.updateFinanceTpaSettleDetail(financeTpaSettleDetail));
    }

    /**
     * 删除TPA服务费结算明细
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:detail:remove')")
    @Log(title = "TPA服务费结算明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(financeTpaSettleDetailService.deleteFinanceTpaSettleDetailByIds(detailIds));
    }
}
