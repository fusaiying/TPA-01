package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseProblem;
import com.paic.ehis.claimflow.service.IClaimCaseProblemService;
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
 * 案件问题件Controller
 *
 * @author sino
 * @date 2021-01-11
 */
@RestController
@RequestMapping("/problem")
public class ClaimCaseProblemController extends BaseController
{
    @Autowired
    private IClaimCaseProblemService claimCaseProblemService;

    /**
     * 查询案件问题件列表
     */
 //   @PreAuthorize("@ss.hasPermi('system:problem:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseProblem claimCaseProblem)
    {
        startPage();
        List<ClaimCaseProblem> list = claimCaseProblemService.selectClaimCaseProblemList(claimCaseProblem);
        return getDataTable(list);
    }

    @GetMapping("/list1")
    public TableDataInfo list1(ClaimCaseProblem claimCaseProblem)
    {
        startPage();
        List<ClaimCaseProblem> list = claimCaseProblemService.selectClaimCaseProblemList1(claimCaseProblem);
        return getDataTable(list);
    }

    @GetMapping("/list2")
    public TableDataInfo list2(ClaimCaseProblem claimCaseProblem) throws Exception {
        startPage();
        List<ClaimCaseProblem> list = claimCaseProblemService.selectClaimCaseProblemList2(claimCaseProblem);
        return getDataTable(list);
    }

    /**
     * 导出案件问题件列表
     */
//    @PreAuthorize("@ss.hasPermi('system:problem:export')")
    @Log(title = "案件问题件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseProblem claimCaseProblem) throws IOException
    {
        List<ClaimCaseProblem> list = claimCaseProblemService.selectClaimCaseProblemList(claimCaseProblem);
        ExcelUtil<ClaimCaseProblem> util = new ExcelUtil<ClaimCaseProblem>(ClaimCaseProblem.class);
        util.exportExcel(response, list, "problem");
    }

    /**
     * 获取案件问题件详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:problem:query')")
    @GetMapping(value = "/{problemId}")
    public AjaxResult getInfo(@PathVariable("problemId") Long problemId)
    {
        return AjaxResult.success(claimCaseProblemService.selectClaimCaseProblemById(problemId));
    }

    /**
     * 新增案件问题件
     */
//    @PreAuthorize("@ss.hasPermi('system:problem:add')")
    @Log(title = "案件问题件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseProblem claimCaseProblem)
    {
        return toAjax(claimCaseProblemService.insertClaimCaseProblem(claimCaseProblem));
    }

    /**
     * 修改案件问题件
     */
//    @PreAuthorize("@ss.hasPermi('system:problem:edit')")
    @Log(title = "案件问题件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseProblem claimCaseProblem)
    {
        return toAjax(claimCaseProblemService.updateClaimCaseProblem(claimCaseProblem));
    }

    /**
     * 删除案件问题件
     */
//    @PreAuthorize("@ss.hasPermi('system:problem:remove')")
    @Log(title = "案件问题件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{problemIds}")
    public AjaxResult remove(@PathVariable Long[] problemIds)
    {
        return toAjax(claimCaseProblemService.deleteClaimCaseProblemByIds(problemIds));
    }

    /**
     * 查找历史问题件
     */
    /**
     * 查询案件问题件列表
     */
 //   @PreAuthorize("@ss.hasPermi('system:problem:list')")
    @GetMapping("/selectHistoricalProblem")
    public TableDataInfo selectHistoricalProblem(ClaimCaseProblem claimCaseProblem)
    {
        startPage();
        List<ClaimCaseProblem> list = claimCaseProblemService.selectHistoricalProblem(claimCaseProblem);
        return getDataTable(list);
    }

    /**
     * 获取案件问题件详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:problem:query')")
    @GetMapping(value = "/s/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseProblemService.selectClaimCaseProblemByRptNo(rptNo));
    }

}