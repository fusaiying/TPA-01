package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCal;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;
import com.paic.ehis.claimflow.service.IClaimCaseCalService;
import com.paic.ehis.common.core.utils.StringUtils;
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
 * 案件赔付信息Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/cal")
public class ClaimCaseCalController extends BaseController
{
    @Autowired
    private IClaimCaseCalService claimCaseCalService;

    /**
     * 查询案件赔付信息列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseCal claimCaseCal)
    {
        startPage();
        List<ClaimCaseCal> list = claimCaseCalService.selectClaimCaseCalList(claimCaseCal);
        return getDataTable(list);
    }

    /**
     * 导出案件赔付信息列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:export')")
    @Log(title = "案件赔付信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseCal claimCaseCal) throws IOException
    {
        List<ClaimCaseCal> list = claimCaseCalService.selectClaimCaseCalList(claimCaseCal);
        ExcelUtil<ClaimCaseCal> util = new ExcelUtil<ClaimCaseCal>(ClaimCaseCal.class);
        util.exportExcel(response, list, "cal");
    }

    /**
     * 理算审核 默认查询案件汇总信息
     *
     * @param claimCaseBill 案件赔付账单明细
     * @return 案件赔付账单明细集合
     */
    @GetMapping("/calSummary")
    public AjaxResult selectCaseCalBillSummary(ClaimCaseBill claimCaseBill)
    {
        return  AjaxResult.success(claimCaseCalService.selectCaseCalBillSummary(claimCaseBill));
    }

    /**
     * 根据rptNo获查询案件赔付结论信息及理算总值
     *   非系统生成，已改变：关联了账单数据
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseCalService.selectClaimCaseCalInformation(rptNo));
    }

    /**
     * 新增案件赔付信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:add')")
    @Log(title = "案件赔付信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseCal claimCaseCal)
    {
        return toAjax(claimCaseCalService.insertClaimCaseCal(claimCaseCal));
    }

    /**
     * 修改案件赔付信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:edit')")
    @Log(title = "案件赔付信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseCal claimCaseCal)
    {
        CalConclusionVo calConclusionVo = claimCaseCalService.updateClaimCaseCal(claimCaseCal);
        if (StringUtils.isNotNull(calConclusionVo)){
            return AjaxResult.success(calConclusionVo);
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 删除案件赔付信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:remove')")
    @Log(title = "案件赔付信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{calIds}")
    public AjaxResult remove(@PathVariable Long[] calIds)
    {
        return toAjax(claimCaseCalService.deleteClaimCaseCalByIds(calIds));
    }

    /***
     * 案件理算  保存后 更新 claim_case_cal 表 赔付结论 为 空
     * @param rptNo
     * @return
     * auth: hjw
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:edit')")
    @PutMapping(value = "/claimConclusionNull/{rptNo}")
    public AjaxResult claimConclusionNull(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseCalService.updateClaimConclusionNull(rptNo));
    }

    /**
     * 根据rptNo获查询案件赔付结论信息及理算总值
     *   非系统生成，已改变：关联了账单数据
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:cal:query')")
    @GetMapping("exchangeRate")
    public AjaxResult getInfo(ClaimCaseCal claimCaseCal)
    {
        return AjaxResult.success(claimCaseCalService.selectExchangeRateForeign(claimCaseCal));
    }

}
