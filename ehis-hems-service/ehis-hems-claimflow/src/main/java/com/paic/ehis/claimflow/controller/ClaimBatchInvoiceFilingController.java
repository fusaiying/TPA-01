package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimBatchInvoiceFiling;
import com.paic.ehis.claimflow.domain.vo.InvoiceFileVo;
import com.paic.ehis.claimflow.service.IClaimBatchInvoiceFilingService;
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
 * 发票归档Controller
 *
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/filing")
public class ClaimBatchInvoiceFilingController extends BaseController
{
    @Autowired
    private IClaimBatchInvoiceFilingService claimBatchInvoiceFilingService;

    /**
     * 查询发票归档列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimBatchInvoiceFiling claimBatchInvoiceFiling)
    {
        startPage();
        List<ClaimBatchInvoiceFiling> list = claimBatchInvoiceFilingService.selectClaimBatchInvoiceFilingList(claimBatchInvoiceFiling);
        return getDataTable(list);
    }

    /**
     * 导出发票归档列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:export')")
    @Log(title = "发票归档", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimBatchInvoiceFiling claimBatchInvoiceFiling) throws IOException
    {
        List<ClaimBatchInvoiceFiling> list = claimBatchInvoiceFilingService.selectClaimBatchInvoiceFilingList(claimBatchInvoiceFiling);
        ExcelUtil<ClaimBatchInvoiceFiling> util = new ExcelUtil<ClaimBatchInvoiceFiling>(ClaimBatchInvoiceFiling.class);
        util.exportExcel(response, list, "filing");
    }

    /**
     * 获取发票归档详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:query')")
    @GetMapping(value = "/{batchNo}")
    public AjaxResult getInfo(@PathVariable("batchNo") String batchNo)
    {
        return AjaxResult.success(claimBatchInvoiceFilingService.selectClaimBatchInvoiceFilingById(batchNo));
    }

    /**
     * 新增发票归档
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:add')")
    @Log(title = "发票归档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimBatchInvoiceFiling claimBatchInvoiceFiling)
    {
        return toAjax(claimBatchInvoiceFilingService.insertClaimBatchInvoiceFiling(claimBatchInvoiceFiling));
    }

    /**
     * 修改发票归档
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:edit')")
    @Log(title = "发票归档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimBatchInvoiceFiling claimBatchInvoiceFiling)
    {
        return toAjax(claimBatchInvoiceFilingService.updateClaimBatchInvoiceFiling(claimBatchInvoiceFiling));
    }

    /**
     * 删除发票归档
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:remove')")
    @Log(title = "发票归档", businessType = BusinessType.DELETE)
    @DeleteMapping("/{batchNos}")
    public AjaxResult remove(@PathVariable String[] batchNos)
    {
        return toAjax(claimBatchInvoiceFilingService.deleteClaimBatchInvoiceFilingByIds(batchNos));
    }

    /**
     * 查询发票归档工作池
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:list')")
    @GetMapping("/selectInvoiceFile")
    public TableDataInfo selectInvoiceFile(InvoiceFileVo invoiceFileVo)
    {
        startPage();
        List<InvoiceFileVo> list = claimBatchInvoiceFilingService.selectInvoiceFile(invoiceFileVo);
        return getDataTable(list);
    }


    /**
     * 发票归档编辑超链接
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:filing:edit')")
    @Log(title = "发票归档", businessType = BusinessType.UPDATE)
    @PutMapping("updateInvoiceFile")
    public AjaxResult updateInvoiceFile(@RequestBody InvoiceFileVo invoiceFileVo)
    {
        return toAjax((claimBatchInvoiceFilingService.updateInvoiceFile(invoiceFileVo)));
    }

}
