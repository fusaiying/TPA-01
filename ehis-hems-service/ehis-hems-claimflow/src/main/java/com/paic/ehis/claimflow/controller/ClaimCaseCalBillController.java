package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimCaseCalBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCalItem;
import com.paic.ehis.claimflow.domain.dto.BillDetailDTO;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillVo;
import com.paic.ehis.claimflow.service.IClaimCaseCalBillService;
import com.paic.ehis.claimflow.service.IClaimCaseCalItemService;
import com.paic.ehis.common.core.utils.StringUtils;
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
import java.util.List;

/**
 * 案件赔付账单明细Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/calBill")
public class ClaimCaseCalBillController extends BaseController
{
    @Autowired
    private IClaimCaseCalBillService claimCaseCalBillService;

    @Autowired
    private IClaimCaseCalItemService claimCaseCalItemService;

    /**
     * 查询案件赔付账单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:calBill:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseCalBill claimCaseCalBill)
    {
        startPage();
        List<ClaimCaseCalBill> list = claimCaseCalBillService.selectClaimCaseCalBillList(claimCaseCalBill);
        return getDataTable(list);
    }

    /**
     * 理算审核默认查询案件赔付账单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:calBill:list')")
    @GetMapping("/infoList")
    public TableDataInfo informationList(ClaimCaseCalBill claimCaseCalBill)
    {
        List<CaseCalBillVo> list = claimCaseCalBillService.selectCaseCalInformationList(claimCaseCalBill);
        return getDataTable(list);
    }

    /**
     * 理算审核 一键展开查询案件赔付账单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:calBill:list')")
    @GetMapping("/detailsList")
    public TableDataInfo detailsList(ClaimCaseCalBill claimCaseCalBill)
    {
        List<CaseCalBillVo> caseCalBillVos = claimCaseCalBillService.selectCaseCalInformationList(claimCaseCalBill);
        ClaimCaseCalItem claimCaseCalItem = new ClaimCaseCalItem();
        for (CaseCalBillVo caseCalBillVo : caseCalBillVos) {
            claimCaseCalItem.setBillId(caseCalBillVo.getBillId());
            claimCaseCalItem.setRptNo(caseCalBillVo.getRptNo());
            List<CaseCalBillItemVo> caseCalBillItemVos = claimCaseCalItemService.selectClaimCaseCalFeeItemList(claimCaseCalItem);
            if(StringUtils.isNotNull(caseCalBillItemVos)) {
                caseCalBillVo.setMinData(caseCalBillItemVos);
            }
        }
        return getDataTable(caseCalBillVos);
    }

    /**
     * 导出案件赔付账单明细列表
     */
//    @PreAuthorize("@ss.hasPermi('system:bill:export')")
    @Log(title = "案件赔付账单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseCalBill claimCaseCalBill) throws IOException
    {
        List<ClaimCaseCalBill> list = claimCaseCalBillService.selectClaimCaseCalBillList(claimCaseCalBill);
        ExcelUtil<ClaimCaseCalBill> util = new ExcelUtil<ClaimCaseCalBill>(ClaimCaseCalBill.class);
        util.exportExcel(response, list, "bill");
    }

    /**
     * 获取案件赔付账单明细详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:bill:query')")
    @GetMapping(value = "/{calBillId}")
    public AjaxResult getInfo(@PathVariable("calBillId") Long calBillId)
    {
        return AjaxResult.success(claimCaseCalBillService.selectClaimCaseCalBillById(calBillId));
    }

    /**
     * 新增案件赔付账单明细
     */
//    @PreAuthorize("@ss.hasPermi('system:bill:add')")
    @Log(title = "案件赔付账单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseCalBill claimCaseCalBill)
    {
        return toAjax(claimCaseCalBillService.insertClaimCaseCalBill(claimCaseCalBill));
    }

    /**
     * 修改案件赔付账单明细
    @PreAuthorize("@ss.hasPermi('system:bill:edit')")
    @Log(title = "案件赔付账单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseCalBill claimCaseCalBill)
    {
        return toAjax(claimCaseCalBillService.updateClaimCaseCalBill(claimCaseCalBill));
    }*/

    /**
     * 理算审核  账单明细保存  按钮
     */
//    @PreAuthorize("@ss.hasPermi('system:bill:edit')")
    @Log(title = "案件赔付账单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult billDetailsSave (@RequestBody BillDetailDTO billDetailDTO)
    {
        return toAjax(claimCaseCalBillService.billDetailsSave(billDetailDTO));
    }

    /**
     * 删除案件赔付账单明细
     */
//    @PreAuthorize("@ss.hasPermi('system:bill:remove')")
    @Log(title = "案件赔付账单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{calBillIds}")
    public AjaxResult remove(@PathVariable Long[] calBillIds)
    {
        return toAjax(claimCaseCalBillService.deleteClaimCaseCalBillByIds(calBillIds));
    }
}
