package com.paic.ehis.system.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.domain.ClaimProductDutyDetail;
import com.paic.ehis.system.domain.ClaimProductDutyDetailInfo;
import com.paic.ehis.system.service.IClaimProductDutyDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 责任明细信息 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/detail")
public class ClaimProductDutyDetailController extends BaseController
{
    @Autowired
    private IClaimProductDutyDetailService claimProductDutyDetailService;

    /**
     * 查询责任明细信息 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimProductDutyDetail claimProductDutyDetail)
    {
        startPage();
        List<ClaimProductDutyDetailInfo> list = claimProductDutyDetailService.selectClaimProductDutyDetailInfoList(claimProductDutyDetail);
        return getDataTable(list);
    }

    /**
     * 导出责任明细信息 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "责任明细信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimProductDutyDetail claimProductDutyDetail) throws IOException
    {
        List<ClaimProductDutyDetail> list = claimProductDutyDetailService.selectClaimProductDutyDetailList(claimProductDutyDetail);
        ExcelUtil<ClaimProductDutyDetail> util = new ExcelUtil<ClaimProductDutyDetail>(ClaimProductDutyDetail.class);
        util.exportExcel(response, list, "detail");
    }

    /**
     * 获取责任明细信息 详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{dutyDetailCode}")
    public AjaxResult getInfo(@PathVariable("dutyDetailCode") String dutyDetailCode)
    {
        return AjaxResult.success(claimProductDutyDetailService.selectClaimProductDutyDetailById(dutyDetailCode));
    }

    /**
     * 新增责任明细信息 
     */
    //@PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "责任明细信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimProductDutyDetail claimProductDutyDetail)
    {
        return toAjax(claimProductDutyDetailService.insertClaimProductDutyDetail(claimProductDutyDetail));
    }

    /**
     * 修改责任明细信息 
     */
    //@PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "责任明细信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimProductDutyDetail claimProductDutyDetail)
    {
        return toAjax(claimProductDutyDetailService.updateClaimProductDutyDetail(claimProductDutyDetail));
    }

    /**
     * 删除责任明细信息 
     */
    //@PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "责任明细信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dutyDetailCodes}")
    public AjaxResult remove(@PathVariable String[] dutyDetailCodes)
    {
        return toAjax(claimProductDutyDetailService.deleteClaimProductDutyDetailByIds(dutyDetailCodes));
    }
}
