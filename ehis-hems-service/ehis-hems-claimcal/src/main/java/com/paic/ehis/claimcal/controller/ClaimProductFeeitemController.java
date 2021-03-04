package com.paic.ehis.claimcal.controller;


import com.paic.ehis.claimcal.domain.ClaimProductFeeitem;
import com.paic.ehis.claimcal.service.IClaimProductFeeitemService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.api.domain.ClaimCasePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 费用项信息 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/productFeeitem")
public class ClaimProductFeeitemController extends BaseController
{
    @Autowired
    private IClaimProductFeeitemService claimProductFeeitemService;

    /**
     * 查询费用项信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:feeitem:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimProductFeeitem claimProductFeeitem)
    {
        startPage();
        List<ClaimProductFeeitem> list = claimProductFeeitemService.selectClaimProductFeeitemList(claimProductFeeitem);
        return getDataTable(list);
    }

    /**
     * 导出费用项信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:feeitem:export')")
    @Log(title = "费用项信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimProductFeeitem claimProductFeeitem) throws IOException
    {
        List<ClaimProductFeeitem> list = claimProductFeeitemService.selectClaimProductFeeitemList(claimProductFeeitem);
        ExcelUtil<ClaimProductFeeitem> util = new ExcelUtil<ClaimProductFeeitem>(ClaimProductFeeitem.class);
        util.exportExcel(response, list, "feeitem");
    }

    /**
     * 获取费用项信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:feeitem:query')")
    @GetMapping(value = "/{feeitemCode}")
    public AjaxResult getInfo(@PathVariable("feeitemCode") String feeitemCode)
    {
        return AjaxResult.success(claimProductFeeitemService.selectClaimProductFeeitemById(feeitemCode));
    }

    /**
     * 新增费用项信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:feeitem:add')")
    @Log(title = "费用项信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimProductFeeitem claimProductFeeitem)
    {
        return toAjax(claimProductFeeitemService.insertClaimProductFeeitem(claimProductFeeitem));
    }

    /**
     * 修改费用项信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:feeitem:edit')")
    @Log(title = "费用项信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimProductFeeitem claimProductFeeitem)
    {
        return toAjax(claimProductFeeitemService.updateClaimProductFeeitem(claimProductFeeitem));
    }

    /**
     * 删除费用项信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:feeitem:remove')")
    @Log(title = "费用项信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feeitemCodes}")
    public AjaxResult remove(@PathVariable String[] feeitemCodes)
    {
        return toAjax(claimProductFeeitemService.deleteClaimProductFeeitemByIds(feeitemCodes));
    }

    /**
     * 根据保单号获得费用项编码、费用项名称
     *
     * @param policys
     * @return
     */
    @PostMapping("/policys")
    public List<ClaimProductFeeitem> selectFeeitemByPolicys(@RequestBody List<ClaimCasePolicy> policys){
        List<ClaimProductFeeitem> claimProductFeeitems = claimProductFeeitemService.selectFeeitemByPolicys(policys);
        return claimProductFeeitemService.selectFeeitemByPolicys(policys);
    }
}
