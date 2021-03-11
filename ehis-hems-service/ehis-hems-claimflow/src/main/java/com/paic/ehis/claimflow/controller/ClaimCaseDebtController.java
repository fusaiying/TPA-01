package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimCaseDebt;
import com.paic.ehis.claimflow.domain.dto.DebtInfoDTO;
import com.paic.ehis.claimflow.domain.vo.BillProcessingVo;
import com.paic.ehis.claimflow.domain.vo.DebtInfoVO;
//import com.paic.ehis.claimflow.service.IClaimCaseDebtReceiptService;
import com.paic.ehis.claimflow.service.IClaimCaseDebtService;
import com.paic.ehis.claimflow.utils.ObjectNullUtil;
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
 * 案件追讨信息Controller
 *
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/debt")
public class ClaimCaseDebtController extends BaseController
{
    @Autowired
    private IClaimCaseDebtService claimCaseDebtService;
//    @Autowired
//    private IClaimCaseDebtReceiptService claimCaseDebtReceiptService;

    /**
     * 查询案件追讨信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseDebt claimCaseDebt)
    {
        startPage();
        List<ClaimCaseDebt> list = claimCaseDebtService.selectClaimCaseDebtList(claimCaseDebt);
        return getDataTable(list);
    }

    /**
     * 导出案件追讨信息列表
     */
    @Log(title = "案件追讨信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DebtInfoDTO debtInfoDTO) throws IOException
    {
        if ("0".equals(debtInfoDTO.getFlag())){
            List<DebtInfoVO> list = claimCaseDebtService.selectDebtInitList();
            ExcelUtil<DebtInfoVO> util = new ExcelUtil<DebtInfoVO>(DebtInfoVO.class);
            util.exportExcel(response, list, "debt");
        } else {
            if (null != debtInfoDTO.getInsuredName() && !"".equals(debtInfoDTO.getInsuredName())) {
                debtInfoDTO.setInsuredName(new String(debtInfoDTO.getInsuredName().getBytes(),"UTF-8"));
            }
            List<DebtInfoVO> list = claimCaseDebtService.selectDebtList(debtInfoDTO);
            ExcelUtil<DebtInfoVO> util = new ExcelUtil<DebtInfoVO>(DebtInfoVO.class);
            util.exportExcel(response, list, "debt");
        }
    }

    /**
     * 获取案件追讨信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:debt:query')")
    @GetMapping(value = "/{debtId}")
    public AjaxResult getInfo(@PathVariable("debtId") Long debtId)
    {
        return AjaxResult.success(claimCaseDebtService.selectClaimCaseDebtById(debtId));
    }

    /**
     * 新增案件追讨信息
     */
//    @PreAuthorize("@ss.hasPermi('system:debt:add')")
    @Log(title = "案件追讨信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseDebt claimCaseDebt)
    {
        return toAjax(claimCaseDebtService.insertClaimCaseDebt(claimCaseDebt));
    }

    /**
     * 修改案件追讨信息
     */
//    @PreAuthorize("@ss.hasPermi('system:debt:edit')")
    @Log(title = "案件追讨信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseDebt claimCaseDebt)
    {
        return toAjax(claimCaseDebtService.updateClaimCaseDebt(claimCaseDebt));
    }

    /**
     * 删除案件追讨信息
     */
//    @PreAuthorize("@ss.hasPermi('system:debt:remove')")
    @Log(title = "案件追讨信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{debtIds}")
    public AjaxResult remove(@PathVariable Long[] debtIds)
    {
        return toAjax(claimCaseDebtService.deleteClaimCaseDebtByIds(debtIds));
    }

    /**
     * 查询追讨工作池 - 初始化
     * @return
     */
    @PostMapping("/initDebt")
    public TableDataInfo initDebtList(@RequestBody DebtInfoDTO debtInfoDTO){
        startPage(debtInfoDTO);
        List<DebtInfoVO> list = new ArrayList<>();
        // 初始化或者查询条件为空
        if ("0".equals(debtInfoDTO.getFlag()) || ("1".equals(debtInfoDTO.getFlag()) && null==debtInfoDTO.getRptNo() && null==debtInfoDTO.getPolicyNo() && null==debtInfoDTO.getHospitalCode() && null==debtInfoDTO.getInsuredName()
                && null==debtInfoDTO.getIdNo() && null==debtInfoDTO.getStartDate() && null==debtInfoDTO.getWhiteStatus() && null==debtInfoDTO.getPolicyItemNo())){
            list = claimCaseDebtService.selectDebtInitList();
        } else {
            list = claimCaseDebtService.selectDebtList(debtInfoDTO);
        }
        return getDataTable(list);
    }

}
