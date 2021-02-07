package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseAccept;
import com.paic.ehis.claimflow.domain.dto.CaseRegisterAndAppleTypeDTO;
import com.paic.ehis.claimflow.service.IClaimCaseAcceptService;
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
 * 案件受理信息 Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/accept")
public class ClaimCaseAcceptController extends BaseController
{
    @Autowired
    private IClaimCaseAcceptService claimCaseAcceptService;

    /**
     * 查询案件受理信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseAccept claimCaseAccept)
    {
        startPage();
        List<ClaimCaseAccept> list = claimCaseAcceptService.selectClaimCaseAcceptList(claimCaseAccept);
        return getDataTable(list);
    }

    /**
     * 导出案件受理信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:export')")
    @Log(title = "案件受理信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseAccept claimCaseAccept) throws IOException
    {
        List<ClaimCaseAccept> list = claimCaseAcceptService.selectClaimCaseAcceptList(claimCaseAccept);
        ExcelUtil<ClaimCaseAccept> util = new ExcelUtil<ClaimCaseAccept>(ClaimCaseAccept.class);
        util.exportExcel(response, list, "accept");
    }

    /**受理
     * 获取案件受理信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseAcceptService.selectClaimCaseAcceptById(rptNo));
    }

    /**唯一受理
     * 新增案件受理信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:add')")
    @Log(title = "案件受理信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseRegisterAndAppleTypeDTO caseRegisterAndAppleTypeDTO)
    {
        return toAjax(claimCaseAcceptService.insertClaimCaseAccept(caseRegisterAndAppleTypeDTO));
    }

    /**
     * 修改案件受理信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:edit')")
    @Log(title = "案件受理信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseAccept claimCaseAccept)
    {
        return toAjax(claimCaseAcceptService.updateClaimCaseAccept(claimCaseAccept));
    }

    /**
     * 删除案件受理信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:accept:remove')")
    @Log(title = "案件受理信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseAcceptService.deleteClaimCaseAcceptByIds(rptNos));
    }
}
