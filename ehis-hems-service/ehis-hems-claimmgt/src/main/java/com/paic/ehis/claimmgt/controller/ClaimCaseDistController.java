package com.paic.ehis.claimmgt.controller;

import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
import com.paic.ehis.claimmgt.service.IClaimCaseDistService;
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
 * 案件分配规则Controller
 *
 * @author sino
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/dist")
public class ClaimCaseDistController extends BaseController
{
    @Autowired
    private IClaimCaseDistService claimCaseDistService;

    /**
     * 查询案件分配规则列表
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseDist claimCaseDist)
    {
        startPage();
        List<ClaimCaseDist> list = claimCaseDistService.selectClaimCaseDistList(claimCaseDist);
        return getDataTable(list);
    }

    /**
     * 导出案件分配规则列表
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:export')")
    @Log(title = "案件分配规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseDist claimCaseDist) throws IOException
    {
        List<ClaimCaseDist> list = claimCaseDistService.selectClaimCaseDistList(claimCaseDist);
        ExcelUtil<ClaimCaseDist> util = new ExcelUtil<ClaimCaseDist>(ClaimCaseDist.class);
        util.exportExcel(response, list, "dist");
    }

    /**
     * 获取案件分配规则详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:query')")
    @GetMapping(value = "/{distId}")
    public AjaxResult getInfo(@PathVariable("distId") Long distId)
    {
        return AjaxResult.success(claimCaseDistService.selectClaimCaseDistById(distId));
    }

    /**
     * 新增案件分配规则
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:add')")
    @Log(title = "案件分配规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseDist claimCaseDist)
    {
        return toAjax(claimCaseDistService.insertClaimCaseDist(claimCaseDist));
    }

    /**
     * 修改案件分配规则
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:edit')")
    @Log(title = "案件分配规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseDist claimCaseDist)
    {
        return toAjax(claimCaseDistService.updateClaimCaseDist(claimCaseDist));
    }

    /**
     * 删除案件分配规则
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:remove')")
    @Log(title = "案件分配规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{distIds}")
    public AjaxResult remove(@PathVariable Long[] distIds)
    {
        return toAjax(claimCaseDistService.deleteClaimCaseDistByIds(distIds));
    }

    /**
     * 一件均分
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:edit')")
    @Log(title = "案件分配规则", businessType = BusinessType.UPDATE)
    @PutMapping("updateClaimCaseAverage")
    public AjaxResult updateClaimCaseAverage(@RequestBody ClaimCaseDist claimCaseDist)
    {
        return toAjax(claimCaseDistService.updateClaimCaseAverage(claimCaseDist));
    }

    /**
     * 案件分配规则编辑超链接
     * @param claimCaseDist
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:edit')")
    @Log(title = "案件分配规则", businessType = BusinessType.UPDATE)
    @PutMapping("updateClaimCaseDistOne")
    public AjaxResult updateClaimCaseDistOne (@RequestBody ClaimCaseDist claimCaseDist)
    {
        return toAjax(claimCaseDistService.updateClaimCaseDistOne(claimCaseDist));
    }

    /**
     * 查询案件分配规则列表
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:list')")
    @GetMapping("/selectClaimCaseDist")
    public TableDataInfo selectClaimCaseDist(ClaimCaseDist claimCaseDist)
    {
        startPage();
        List<ClaimCaseDist> list = claimCaseDistService.selectClaimCaseDist(claimCaseDist);
        return getDataTable(list);
    }
}
