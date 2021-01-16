package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseStanding;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.service.IClaimCaseStandingService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 报案台账信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/standing")
public class ClaimCaseStandingController extends BaseController
{
    @Autowired
    private IClaimCaseStandingService claimCaseStandingService;

    /**
     * 查询报案台账信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:standing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseStanding claimCaseStanding)
    {
        startPage();
        List<ClaimCaseStanding> list = claimCaseStandingService.selectClaimCaseStandingList(claimCaseStanding);
        return getDataTable(list);
    }

    /**
     * 导出报案台账信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:standing:export')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseStanding claimCaseStanding) throws IOException
    {
        List<ClaimCaseStanding> list = claimCaseStandingService.selectClaimCaseStandingList(claimCaseStanding);
        ExcelUtil<ClaimCaseStanding> util = new ExcelUtil<ClaimCaseStanding>(ClaimCaseStanding.class);
        util.exportExcel(response, list, "standing");
    }

    /**
     * 获取报案台账信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:standing:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseStandingService.selectClaimCaseStandingById(rptNo));
    }

    /**
     * 新增报案台账信息 
     */
    @PreAuthorize("@ss.hasPermi('system:standing:add')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseStanding claimCaseStanding)
    {
        return toAjax(claimCaseStandingService.insertClaimCaseStanding(claimCaseStanding));
    }

    /**
     * 修改报案台账信息 
     */
    @PreAuthorize("@ss.hasPermi('system:standing:edit')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseStanding claimCaseStanding)
    {
        return toAjax(claimCaseStandingService.updateClaimCaseStanding(claimCaseStanding));
    }

    /**
     * 删除报案台账信息 
     */
    @PreAuthorize("@ss.hasPermi('system:standing:remove')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseStandingService.deleteClaimCaseStandingByIds(rptNos));
    }
}
