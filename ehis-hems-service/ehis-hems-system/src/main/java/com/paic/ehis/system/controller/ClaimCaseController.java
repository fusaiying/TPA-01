package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.system.domain.dto.CaseDispatchDTO;
import com.paic.ehis.system.domain.dto.ClaimCaseDTO;
import com.paic.ehis.system.domain.dto.DispatchDTO;
import com.paic.ehis.system.domain.vo.ClaimAndBatchVo;
import com.paic.ehis.system.domain.vo.ProcessingCaseVo;
import com.paic.ehis.common.core.web.page.PageDomain;
import com.paic.ehis.common.core.web.page.TableSupport;
import com.paic.ehis.system.domain.vo.DispatchVo;
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
import com.paic.ehis.system.service.IClaimCaseService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件信息 Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/case")
public class ClaimCaseController extends BaseController
{
    @Autowired
    private IClaimCaseService claimCaseService;

    /**
     * 查询案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCase claimCase)
    {
        startPage();
        List<ClaimCase> list = claimCaseService.selectClaimCaseList(claimCase);
        return getDataTable(list);
    }

    /**
     * 查询处理中受理案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/processingList")
    public TableDataInfo processingList(ClaimCaseDTO claimCaseDTO)
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("rpt_no");
        startSortPage();
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectProcessingClaimCaseList(claimCaseDTO);
        return getDataTable(processingCaseVos);
    }
    /**
     * 查询已处理受理案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/processedList")
    public TableDataInfo processedList(ClaimCaseDTO claimCaseDTO)
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("rpt_no");
        startSortPage();
        List<ClaimCase> list = claimCaseService.selectProcessedClaimCaseList(claimCaseDTO);
        return getDataTable(list);
    }
    /**
     * 查询悬挂中受理案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/suspensionList")
    public TableDataInfo suspensionList(ClaimCaseDTO claimCaseDTO)
    {
        TableSupport.setSort("desc");
        TableSupport.setOrderByColumn("rpt_no");
        startSortPage();
        List<ProcessingCaseVo> list = claimCaseService.selectSuspensionClaimCaseList(claimCaseDTO);
        return getDataTable(list);
    }


    /**
     * 导出案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCase claimCase) throws IOException
    {
        List<ClaimCase> list = claimCaseService.selectClaimCaseList(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, list, "case");
    }
    /**
     * 导出处理中受理案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcessingList")
    public void exportProcessingList(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException
    {
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectProcessingClaimCaseList(claimCaseDTO);
        ExcelUtil<ProcessingCaseVo> util = new ExcelUtil<ProcessingCaseVo>(ProcessingCaseVo.class);
        util.exportExcel(response, processingCaseVos, "处理中受理案件");
    }
    /**
     * 导出已处理受理案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "已处理受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcessedList")
    public void exportProcessedList(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setIsAsc("desc");
        pageDomain.setOrderByColumn("rpt_no");
        startPage();
        List<ClaimCase> list = claimCaseService.selectProcessedClaimCaseList(claimCaseDTO);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, list, "已处理受理案件");
    }
    /**
     * 导出悬挂中受理案件信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "悬挂中受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportSuspensionList")
    public void exportSuspensionList(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException
    {
        List<ProcessingCaseVo> list = claimCaseService.selectSuspensionClaimCaseList(claimCaseDTO);
        ExcelUtil<ProcessingCaseVo> util = new ExcelUtil<ProcessingCaseVo>(ProcessingCaseVo.class);
        util.exportExcel(response, list, "悬挂中受理案件");
    }

    /**
     * 获取案件信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseService.selectClaimCaseById(rptNo));
    }

    /**
     * 新增案件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:case:add')")
    @Log(title = "案件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.insertClaimCase(claimCase));
    }

    /**
     * 修改案件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.updateClaimCase(claimCase));
    }

    /**
     * 删除案件信息 
     */
    @PreAuthorize("@ss.hasPermi('system:case:remove')")
    @Log(title = "案件信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseService.deleteClaimCaseByIds(rptNos));
    }

    /**
     * 获取案件信息 详细信息（多表关联查询batch和Claim表）
     */
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/getInfoClaimAndBatch")
    public AjaxResult getInfoClaimAndBatch(String rptNo)
    {
        ClaimAndBatchVo claimAndBatchVo = claimCaseService.selectClaimClaimAndBatchById(rptNo);
        return AjaxResult.success(claimAndBatchVo);
    }

    /**
     * 问题件按钮（子任务添加功能为完成）
     * 修改案件信息
     * @param claimCase
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseInfoSuspend")
    public AjaxResult editCaseInfoSuspend(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.updateCaseInfoSuspend(claimCase));
    }

    /**
     *受理确认按钮功能
     * 修改案件信息
     * @param claimCase
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseAndRecordInfoSuspend")
    public AjaxResult editCaseAndRecordInfoSuspend(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.updateCaseAndRecordInfoSuspend(claimCase));
    }

    /**
     * 撤件弹框确认按钮
     * 修改案件信息
     * @param claimCase
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseAndRecordInfoCancel")
    public AjaxResult editCaseAndRecordInfoCancel (@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.updateCaseAndRecordInfoCancel(claimCase));
    }


    /**
     * 查询案件调度工作池
     */
    /*@PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/selectCaseDispatchList")
    public TableDataInfo selectCaseDispatchList(ClaimCase claimCase)
    {
        startPage();
        List<ClaimCase> list = claimCaseService.selectCaseDispatchList(claimCase);
        return getDataTable(list);
    }*/

    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/selectCaseDispatchList")
    public AjaxResult selectCaseDispatchList(DispatchDTO dispatchDTO)
    {
        startPage();
        List<DispatchVo> list = claimCaseService.selectCaseDispatchList(dispatchDTO);
        return AjaxResult.success(list);
    }

    /**
     * 案件调度修改操作人
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/Dispatch")
    public AjaxResult Dispatch(@RequestBody CaseDispatchDTO caseDispatchDTO)
    {
        claimCaseService.updateClaimCase(caseDispatchDTO);
        return AjaxResult.success();
    }

    /**
     * 导出案件调度
     */
    /*@PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportDispatchList")
    public void exportDispatchList(HttpServletResponse response, ClaimCase claimCase) throws IOException
    {
        List<ClaimCase> claimCases = claimCaseService.selectCaseDispatchList(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, claimCases, "处理中受理案件");
    }*/


    //审核工作池接口-导出处理中清单
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentUnder")
    public void exportConditionsForTheAdjustmentUnder(HttpServletResponse response, ClaimCase claimCase) throws IOException
    {
        List<ClaimCase> claimCases = claimCaseService.SelectConditionsForTheAdjustmentUnder(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, claimCases, "处理中理算案件");
    }
    //审核工作池接口-个人池处理中
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentUnder")
    public TableDataInfo listConditionsForTheAdjustmentUnder(ClaimCase claimCase)
    {
        startPage();
        List<ClaimCase> list = claimCaseService.SelectConditionsForTheAdjustmentUnder(claimCase);
        return getDataTable(list);
    }

    //审核工作池接口-导出已处理清单
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentOver")
    public void exportConditionsForTheAdjustmentOver(HttpServletResponse response, ClaimCase claimCase) throws IOException
    {
        List<ClaimCase> claimCases = claimCaseService.SelectConditionsForTheAdjustmentOver(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, claimCases, "处理中理算案件");
    }
    //审核工作池接口-个人池已处理
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentOver")
    public TableDataInfo listConditionsForTheAdjustmentOver(ClaimCase claimCase)
    {
        startPage();
        List<ClaimCase> list = claimCaseService.SelectConditionsForTheAdjustmentOver(claimCase);
        return getDataTable(list);
    }

    //审核工作池接口-导出悬挂中清单
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentHang")
    public void exportConditionsForTheAdjustmentHang(HttpServletResponse response, ClaimCase claimCase) throws IOException
    {
        List<ClaimCase> claimCases = claimCaseService.SelectConditionsForTheAdjustmentHang(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, claimCases, "处理中理算案件");
    }
    //审核工作池接口-个人池悬挂中
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentHang")
    public TableDataInfo listConditionsForTheAdjustmentHang(ClaimCase claimCase)
    {
        startPage();
        List<ClaimCase> list = claimCaseService.SelectConditionsForTheAdjustmentHang(claimCase);
        return getDataTable(list);
    }
}
