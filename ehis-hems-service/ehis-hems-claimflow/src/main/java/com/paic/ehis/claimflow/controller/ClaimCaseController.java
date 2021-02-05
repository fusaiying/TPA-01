package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseProblem;
import com.paic.ehis.claimflow.domain.ClaimCaseStanding;
import com.paic.ehis.claimflow.domain.dto.*;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.claimflow.service.IClaimCaseProblemService;
import com.paic.ehis.claimflow.service.IClaimCaseRecordService;
import com.paic.ehis.claimflow.service.IClaimCaseService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.PageDomain;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.core.web.page.TableSupport;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @Autowired
    private IClaimCaseProblemService claimCaseProblemService;

    @Autowired
    private IClaimCaseRecordService claimCaseRecordService;


    /**
     * 理算审核提调 按钮
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @GetMapping("/raiseCase")
    public AjaxResult raiseClaimCase(ClaimCase claimCase)
    {
        return toAjax(claimCaseService.raiseClaimCase(claimCase));
    }

    /**
     * 查询案件信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/processingList")
    public TableDataInfo processingList(ClaimCaseDTO claimCaseDTO)
    {
        claimCaseDTO.setIsAsc("desc");
        claimCaseDTO.setOrderByColumn("rpt_no");
        startPage(claimCaseDTO);
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectProcessingClaimCaseList(claimCaseDTO);
        return getDataTable(processingCaseVos);
    }
    /**
     * 查询已处理受理案件信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:export')")export
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
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseService.selectClaimCaseById(rptNo));
    }

    /**
     * 新增案件信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:case:add')")
    @Log(title = "案件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.insertClaimCase(claimCase));
    }

    /**
     * 修改案件信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCase claimCase)
    {
        return toAjax(claimCaseService.updateClaimCase(claimCase));
    }

    @PutMapping("/updatecaseStatus")
    public AjaxResult edit(@RequestBody ClaimCaseProblem claimCaseProblem)
    {
        String repNo = claimCaseProblem.getRptNo();
        claimCaseProblemService.updateClaimCaseProblem(claimCaseProblem) ;// // //处理意见
        claimCaseService.updatecaseStatus(repNo);  // update to    case_status  31
        int result = claimCaseRecordService.updatehistoryFlag(repNo);// history_flag = 'Y'
        return toAjax(result);
    }

    /**
     * 删除案件信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:case:remove')")
    @Log(title = "案件信息 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseService.deleteClaimCaseByIds(rptNos));
    }

    /**受理处理页面查询报案号对应的批次信息和交单机构
     * 获取案件信息 详细信息（多表关联查询batch和Claim表）
     */
//    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping("/getInfoClaimAndBatch")
    public AjaxResult getInfoClaimAndBatch(String rptNo)
    {
        ClaimAndBatchVo claimAndBatchVo = claimCaseService.selectClaimClaimAndBatchById(rptNo);
        return AjaxResult.success(claimAndBatchVo);
    }

    /**
     * 问题件按钮（子任务添加功能为完成）
     * 修改案件信息
     * @param claimCaseProblemDTO
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseInfoSuspend")
    public AjaxResult editCaseInfoSuspend(@RequestBody ClaimCaseProblemDTO claimCaseProblemDTO)
    {
        return toAjax(claimCaseService.updateCaseInfoSuspend(claimCaseProblemDTO));
    }

    /**
     *受理确认按钮功能
     * 修改案件信息
     * @param claimCase
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
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
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
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

//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/selectCaseDispatchList")
    public TableDataInfo selectCaseDispatchList(DispatchDTO dispatchDTO)
    {
        startPage();
        List<CaseDispatchVO> list = claimCaseService.selectCaseDispatchList(dispatchDTO);
        return getDataTable(list);
    }

    /**
     * 案件调度修改操作人
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/Dispatch")
    public AjaxResult Dispatch(@RequestBody String rptNo)
    {
        claimCaseService.updateClaimCase(rptNo);
        return AjaxResult.success();
    }

    /**
     * 导出案件调度
     */
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "案件调度 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportDispatchList")
    public void exportDispatchList(HttpServletResponse response, DispatchDTO dispatchDTO) throws IOException
    {
        List<CaseDispatchVO> claimCases = claimCaseService.selectCaseDispatchList(dispatchDTO);
        ExcelUtil<CaseDispatchVO> util = new ExcelUtil<CaseDispatchVO>(CaseDispatchVO.class);
        util.exportExcel(response, claimCases, "案件调度");
    }

    /*******************************************/
    //审核工作池接口-导出处理中清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentUnder")
    public void exportConditionsForTheAdjustmentUnder(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException
    {
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentUnder(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "处理中理算案件");
    }
    //审核工作池接口-个人池处理中
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentUnder")
    public TableDataInfo listConditionsForTheAdjustmentUnder(AuditWorkPoolDTO auditWorkPoolDTO)
    {
        startPage();
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentUnder(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVoS);
    }

    //审核工作池接口-导出已处理清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentOver")
    public void exportConditionsForTheAdjustmentOver(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException
    {
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "已处理理算案件");
    }
    //审核工作池接口-个人池已处理
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentOver")
    public TableDataInfo listConditionsForTheAdjustmentOver(AuditWorkPoolDTO auditWorkPoolDTO)
    {
        startPage();
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVoS);
    }

    //审核工作池接口-导出悬挂中清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentHang")
    public void exportConditionsForTheAdjustmentHang(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException
    {
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentHang(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "悬挂中理算案件");
    }
    //审核工作池接口-个人池悬挂中
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentHang")
    public TableDataInfo listConditionsForTheAdjustmentHang(AuditWorkPoolDTO auditWorkPoolDTO)
    {
        startPage();
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentHang(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVoS);
    }
/********************************************/

    /**
     *  抽检完毕按钮
     *  修改案件状态
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseCheck")
    public AjaxResult editCaseCheck(@RequestBody ClaimCase claimCase)
    {
        claimCaseService.editCaseCheck(claimCase);
        return AjaxResult.success();
    }

    /**
     *  退回审核按钮
     *  退回后流转至该案件理算审核环节操作人处理中工作池
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseCheckBack")
    public AjaxResult editCaseCheckBack(@RequestBody ClaimCase claimCase)
    {
        claimCaseService.editCaseCheckBack(claimCase);
        return AjaxResult.success();
    }

    /** 抽检工作池-处理中 */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/caseCheck")
    public TableDataInfo caseCheck(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO)
    {
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentUnderCase(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVOS);
    }


    //抽检工作池接口-导出处理中清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportcaseCheck")
    public void exportcaseCheck(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException
    {
        String utf8Name = new String(auditWorkPoolDTO.getName().getBytes( "UTF-8"));
        auditWorkPoolDTO.setName(utf8Name);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentUnderCase(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVOS, "处理中审核案件");
    }

    //审核工作池接口-个人池已处理
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/listConditionsForTheAdjustmentOverNew")
    public TableDataInfo listConditionsForTheAdjustmentOverNew(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO)
    {
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentOverNew(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVOS);
    }

    //抽检工作池接口-导出已处理清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportcaseCheckOver")
    public void exportcaseCheckOver(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException
    {
        String utf8Name = new String(auditWorkPoolDTO.getName().getBytes( "UTF-8"));
        auditWorkPoolDTO.setName(utf8Name);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentOverNew(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVOS, "已处理抽检案件");
    }


    /**
     * 理算审核案件退回 按钮
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @GetMapping("/backToCase")
    public AjaxResult backToClaimCase(ClaimCase claimCase)
    {
        return toAjax(claimCaseService.backToClaimCase(claimCase));
    }

    /**
     * 理算审核案件审核完毕验证产品是否重设
     */
    @GetMapping("/verifyReset")
    public AjaxResult verifyReset(ReviewCompletedDTO reviewCompletedDTO)
    {
        return claimCaseService.verifyReset(reviewCompletedDTO);
    }

    /**
     * 理算审核案件审核完毕账单币种是否一致
     */
    @GetMapping("/verifyBillCurrency")
    public AjaxResult verifyBillCurrency(ReviewCompletedDTO reviewCompletedDTO)
    {
        return claimCaseService.verifyBillCurrency(reviewCompletedDTO);
    }

    /**
     * 理算审核案件审核完毕 按钮
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @GetMapping("/ReviewCompleted")
    public AjaxResult reviewCompletedClaimCase(ClaimCase claimCase)
    {
        return toAjax(claimCaseService.reviewCompletedClaimCase(claimCase));
    }
    /**
     * 理赔信息查询
     */
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/selectClaimInformation")
    public TableDataInfo selectClaimInformation(ClaimInformationDTO claimInformationDTO)
    {
        startPage();
        List<ClaimInformationVo> list = claimCaseService.selectClaimInformation(claimInformationDTO);
        return getDataTable(list);
    }
    //理赔信息查询-导出清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportClaimInformation")
    public void exportClaimInformation(HttpServletResponse response, ClaimInformationDTO claimInformationDTO) throws IOException
    {
        List<ClaimInformationVo> caseList = claimCaseService.selectClaimInformation(claimInformationDTO);
        ExcelUtil<ClaimInformationVo> util = new ExcelUtil<ClaimInformationVo>(ClaimInformationVo.class);
        util.exportExcel(response, caseList, "悬挂中理算案件");
    }

    //事后案件台账
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/postHocAccountingOfCases")
    public TableDataInfo postHocAccountingOfCases(ClaimCase claimCase)
    {
        String batchNo = claimCase.getBatchNo();
        List<ClaimCaseStanding> claimCaseStandings = claimCaseService.postHocAccountingOfCases(batchNo);
        return getDataTable(claimCaseStandings);
    }
}
