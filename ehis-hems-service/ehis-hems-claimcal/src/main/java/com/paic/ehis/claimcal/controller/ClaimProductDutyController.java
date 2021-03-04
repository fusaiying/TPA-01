package com.paic.ehis.claimcal.controller;


import com.paic.ehis.claimcal.domain.ClaimProductDuty;
import com.paic.ehis.claimcal.service.IClaimProductDutyService;
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
 * 责任信息 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/duty")
public class ClaimProductDutyController extends BaseController
{
    @Autowired
    private IClaimProductDutyService claimProductDutyService;

    /**
     * 查询责任信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:duty:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimProductDuty claimProductDuty)
    {
        startPage();
        List<ClaimProductDuty> list = claimProductDutyService.selectClaimProductDutyList(claimProductDuty);
        return getDataTable(list);
    }

    /**
     * 导出责任信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:duty:export')")
    @Log(title = "责任信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimProductDuty claimProductDuty) throws IOException
    {
        List<ClaimProductDuty> list = claimProductDutyService.selectClaimProductDutyList(claimProductDuty);
        ExcelUtil<ClaimProductDuty> util = new ExcelUtil<ClaimProductDuty>(ClaimProductDuty.class);
        util.exportExcel(response, list, "duty");
    }

    /**
     * 获取责任信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:duty:query')")
    @GetMapping(value = "/{dutyCode}")
    public AjaxResult getInfo(@PathVariable("dutyCode") String dutyCode)
    {
        return AjaxResult.success(claimProductDutyService.selectClaimProductDutyById(dutyCode));
    }

    /**
     * 新增责任信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:duty:add')")
    @Log(title = "责任信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimProductDuty claimProductDuty)
    {
        return toAjax(claimProductDutyService.insertClaimProductDuty(claimProductDuty));
    }

    /**
     * 修改责任信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:duty:edit')")
    @Log(title = "责任信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimProductDuty claimProductDuty)
    {
        return toAjax(claimProductDutyService.updateClaimProductDuty(claimProductDuty));
    }

    /**
     * 删除责任信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:duty:remove')")
    @Log(title = "责任信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dutyCodes}")
    public AjaxResult remove(@PathVariable String[] dutyCodes)
    {
        return toAjax(claimProductDutyService.deleteClaimProductDutyByIds(dutyCodes));
    }
}
