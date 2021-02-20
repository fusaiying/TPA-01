package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseRemark;
import com.paic.ehis.claimflow.domain.vo.RemarkVO;
import com.paic.ehis.claimflow.service.IClaimCaseRemarkService;
import com.paic.ehis.common.core.utils.SecurityUtils;
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
 * 案件备注Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/remark")
public class ClaimCaseRemarkController extends BaseController
{
    @Autowired
    private IClaimCaseRemarkService claimCaseRemarkService;

    /**
     * 查询案件备注列表
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseRemark claimCaseRemark)
    {
        startPage();
        List<ClaimCaseRemark> list = claimCaseRemarkService.selectClaimCaseRemarkList(claimCaseRemark);
        return getDataTable(list);
    }

    /**
     * 理算页面查询案件备注列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:remark:list')")
    @GetMapping("/adjustRemarkList")
    public AjaxResult adjustRemarkList(ClaimCaseRemark claimCaseRemark)
    {
        startPage();
        List<ClaimCaseRemark> list = claimCaseRemarkService.selectAdjustClaimCaseRemarkList(claimCaseRemark);
        RemarkVO remarkVO = new RemarkVO();
        remarkVO.setRemarkList(list);
        remarkVO.setUserName(SecurityUtils.getUsername());
        return AjaxResult.success(remarkVO);
    }

    /**
     * 导出案件备注列表
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:export')")
    @Log(title = "案件备注", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseRemark claimCaseRemark) throws IOException
    {
        List<ClaimCaseRemark> list = claimCaseRemarkService.selectClaimCaseRemarkList(claimCaseRemark);
        ExcelUtil<ClaimCaseRemark> util = new ExcelUtil<ClaimCaseRemark>(ClaimCaseRemark.class);
        util.exportExcel(response, list, "remark");
    }

    /**
     * 获取案件备注详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:query')")
    @GetMapping(value = "/{remarkId}")
    public AjaxResult getInfo(@PathVariable("remarkId") Long remarkId)
    {
        return AjaxResult.success(claimCaseRemarkService.selectClaimCaseRemarkById(remarkId));
    }

    /**唯一新增
     * 新增案件备注
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:add')")
    @Log(title = "案件备注", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseRemark claimCaseRemark)
    {
        return toAjax(claimCaseRemarkService.insertClaimCaseRemark(claimCaseRemark));
    }

    /**
     * 修改案件备注
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:edit')")
    @Log(title = "案件备注", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseRemark claimCaseRemark)
    {
        return toAjax(claimCaseRemarkService.updateClaimCaseRemark(claimCaseRemark));
    }

    /**
     * 删除案件备注
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:remove')")
    @Log(title = "案件备注", businessType = BusinessType.DELETE)
	@DeleteMapping("/{remarkIds}")
    public AjaxResult remove(@PathVariable Long[] remarkIds)
    {
        return AjaxResult.success(claimCaseRemarkService.deleteClaimCaseRemarkByIds(remarkIds));
    }

    /**唯一 修改
     * 获取案件备注详细信息BY rptNo
     */
//    @PreAuthorize("@ss.hasPermi('system:remark:query')")
    @GetMapping(value = "/s/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        List<ClaimCaseRemark> claimCaseRemarks = claimCaseRemarkService.selectClaimCaseRemarkByRptNo(rptNo);
        RemarkVO remarkVO = new RemarkVO();
        remarkVO.setRemarkList(claimCaseRemarks);
        remarkVO.setUserName(SecurityUtils.getUsername());
        return AjaxResult.success(remarkVO);

    }
}
