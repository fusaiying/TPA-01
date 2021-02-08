package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseCalItem;
import com.paic.ehis.claimflow.domain.ClaimProductDutyDetail;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.service.IClaimCaseCalItemService;
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
 * 案件赔付费用项明细Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/item")
public class ClaimCaseCalItemController extends BaseController
{
    @Autowired
    private IClaimCaseCalItemService claimCaseCalItemService;

    /**
     * 查询案件赔付费用项明细列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseCalItem claimCaseCalItem)
    {
        startPage();
        List<ClaimCaseCalItem> list = claimCaseCalItemService.selectClaimCaseCalItemList(claimCaseCalItem);
        return getDataTable(list);
    }

    /**
     * 理算审核 下拉 查询案件赔付费用项明细列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:list')")
    @GetMapping("/itemList")
    public TableDataInfo itemList(ClaimCaseCalItem claimCaseCalItem)
    {
        List<CaseCalBillItemVo> caseCalBillItemVos = claimCaseCalItemService.selectClaimCaseCalFeeItemList(claimCaseCalItem);
        return getDataTable(caseCalBillItemVos);
    }

    /**
     * 理算审核 查询保单险种关联列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:list')")
    @PostMapping("/InsurancePolicyList")
    public TableDataInfo insurancePolicyList(@RequestBody ClaimCaseCalItem claimCaseCalItem)
    {
        List<PolicyRiskRelation> policyRiskRelations = claimCaseCalItemService.insurancePolicyList(claimCaseCalItem);
        return getDataTable(policyRiskRelations);
    }

    /**
     * 理算审核 查询费用项关联责任明细列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:list')")
    @GetMapping("/responsibilityDetailsList")
    public TableDataInfo responsibilityDetailsList(ClaimCaseCalItem claimCaseCalItem)
    {
        List<ClaimProductDutyDetail> claimProductDutyDetails = claimCaseCalItemService.responsibilityDetailsList(claimCaseCalItem);
        return getDataTable(claimProductDutyDetails);
    }



    /**
     * 导出案件赔付费用项明细列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:export')")
    @Log(title = "案件赔付费用项明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseCalItem claimCaseCalItem) throws IOException
    {
        List<ClaimCaseCalItem> list = claimCaseCalItemService.selectClaimCaseCalItemList(claimCaseCalItem);
        ExcelUtil<ClaimCaseCalItem> util = new ExcelUtil<ClaimCaseCalItem>(ClaimCaseCalItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取案件赔付费用项明细详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{calItemId}")
    public AjaxResult getInfo(@PathVariable("calItemId") Long calItemId)
    {
        return AjaxResult.success(claimCaseCalItemService.selectClaimCaseCalItemById(calItemId));
    }

    /**
     * 新增案件赔付费用项明细
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:add')")
    @Log(title = "案件赔付费用项明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseCalItem claimCaseCalItem)
    {
        return toAjax(claimCaseCalItemService.insertClaimCaseCalItem(claimCaseCalItem));
    }

    /**
     * 修改案件赔付费用项明细
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:edit')")
    @Log(title = "案件赔付费用项明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseCalItem claimCaseCalItem)
    {
        return toAjax(claimCaseCalItemService.updateClaimCaseCalItem(claimCaseCalItem));
    }

    /**
     * 删除案件赔付费用项明细
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:item:remove')")
    @Log(title = "案件赔付费用项明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{calItemIds}")
    public AjaxResult remove(@PathVariable Long[] calItemIds)
    {
        return toAjax(claimCaseCalItemService.deleteClaimCaseCalItemByIds(calItemIds));
    }
}
