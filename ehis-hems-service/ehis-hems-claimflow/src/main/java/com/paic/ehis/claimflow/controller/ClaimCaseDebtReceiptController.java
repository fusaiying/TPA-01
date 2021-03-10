package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseDebtReceipt;
import com.paic.ehis.claimflow.domain.dto.DebtInfoDTO;
import com.paic.ehis.claimflow.service.IClaimCaseDebtReceiptService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 案件追讨收款明细Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/receipt")
public class ClaimCaseDebtReceiptController extends BaseController
{
    @Autowired
    private IClaimCaseDebtReceiptService claimCaseDebtReceiptService;

    /**
     * 查询案件追讨收款明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:receipt:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseDebtReceipt claimCaseDebtReceipt)
    {
        startPage();
        List<ClaimCaseDebtReceipt> list = claimCaseDebtReceiptService.selectClaimCaseDebtReceiptList(claimCaseDebtReceipt);
        return getDataTable(list);
    }

    /**
     * 导出案件追讨收款明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:receipt:export')")
    @Log(title = "案件追讨收款明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseDebtReceipt claimCaseDebtReceipt) throws IOException
    {
        List<ClaimCaseDebtReceipt> list = claimCaseDebtReceiptService.selectClaimCaseDebtReceiptList(claimCaseDebtReceipt);
        ExcelUtil<ClaimCaseDebtReceipt> util = new ExcelUtil<ClaimCaseDebtReceipt>(ClaimCaseDebtReceipt.class);
        util.exportExcel(response, list, "receipt");
    }

    /**
     * 获取案件追讨收款明细详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:receipt:query')")
    @GetMapping(value = "/{receiptNo}")
    public AjaxResult getInfo(@PathVariable("receiptNo") String receiptNo)
    {
        return AjaxResult.success(claimCaseDebtReceiptService.selectClaimCaseDebtReceiptById(receiptNo));
    }

    /**
     * 新增案件追讨收款明细
     */
//    @PreAuthorize("@ss.hasPermi('system:receipt:add')")
    @Log(title = "案件追讨收款明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseDebtReceipt claimCaseDebtReceipt)
    {
        return toAjax(claimCaseDebtReceiptService.insertClaimCaseDebtReceipt(claimCaseDebtReceipt));
    }

    /**
     * 修改案件追讨收款明细
     */
//    @PreAuthorize("@ss.hasPermi('system:receipt:edit')")
    @Log(title = "案件追讨收款明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseDebtReceipt claimCaseDebtReceipt)
    {
        return toAjax(claimCaseDebtReceiptService.updateClaimCaseDebtReceipt(claimCaseDebtReceipt));
    }

    /**
     * 删除案件追讨收款明细
     */
//    @PreAuthorize("@ss.hasPermi('system:receipt:remove')")
    @Log(title = "案件追讨收款明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{receiptNos}")
    public AjaxResult remove(@PathVariable String[] receiptNos)
    {
        return toAjax(claimCaseDebtReceiptService.deleteClaimCaseDebtReceiptByIds(receiptNos));
    }

    /**
     * 查询收款明细
     *
     * @param debtInfoDTO
     * @return
     */
    @PostMapping("/initReceipt")
    public TableDataInfo initReceipt(@RequestBody DebtInfoDTO debtInfoDTO){
        startPage(debtInfoDTO);
        List<ClaimCaseDebtReceipt> receiptList = new ArrayList<>();
        if ("1".equals(debtInfoDTO.getFlag())){
            if ((!"".equals(debtInfoDTO.getInsuredName()) && debtInfoDTO.getInsuredName() != null) || debtInfoDTO.getStartDate() != null) {
                receiptList = claimCaseDebtReceiptService.selectClaimCaseDebtReceiptListByInsuredOrDate(debtInfoDTO);
            } else {
                receiptList = claimCaseDebtReceiptService.selectReceiptList();
            }
        } else {
            receiptList = claimCaseDebtReceiptService.selectClaimCaseDebtReceiptListNear();
        }
        return getDataTable(receiptList);
    }
}
