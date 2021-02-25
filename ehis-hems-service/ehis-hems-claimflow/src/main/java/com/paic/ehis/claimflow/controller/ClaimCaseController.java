package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.*;
import com.paic.ehis.claimflow.domain.interfaceclass.BatchNoRptNoDTO;
import com.paic.ehis.claimflow.domain.interfaceclass.BatchNoRptNoVO;
import com.paic.ehis.claimflow.domain.interfaceclass.InsuredNoAndName;
import com.paic.ehis.claimflow.domain.interfaceclass.RptNoAndFilingNo;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.claimflow.service.*;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.PageDomain;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.core.web.page.TableSupport;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 案件信息 Controller
 *
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/case")
public class ClaimCaseController extends BaseController {
    @Autowired
    private IClaimCaseService claimCaseService;

    @Autowired
    private IClaimCaseProblemService claimCaseProblemService;

    @Autowired
    private IClaimCaseRecordService claimCaseRecordService;

    @Autowired
    private IClaimBatchService claimBatchService;

    @Autowired
    private IPolicyInfoService policyInfoService;
    /**
     * 理算审核调查 保存按钮
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:add')")
    @PostMapping("/saveInvestigation")
    public AjaxResult saveInvestigation(@RequestBody ClaimCaseInvestigation caseInvestigation) {
        return AjaxResult.success(claimCaseService.surveyInformationPreservation(caseInvestigation));
    }

    /**
     * 理算审核提调 按钮
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:edit')")
    @GetMapping("/raiseCase")
    public AjaxResult raiseClaimCase(ClaimCaseInvestigation caseInvestigation) {
        return toAjax(claimCaseService.raiseClaimCase(caseInvestigation));
    }

    /**
     * 查询案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCase claimCase) {
        startPage();
        List<ClaimCase> list = claimCaseService.selectClaimCaseList(claimCase);
        return getDataTable(list);
    }

    /**
     * 查询处理中受理案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:list')")
    @GetMapping("/processingList")
    public TableDataInfo processingList(ClaimCaseDTO claimCaseDTO) {
        if (claimCaseDTO.getOrderByColumn() != null && !claimCaseDTO.getOrderByColumn().equals("")) {
            claimCaseDTO.setOrderByColumn(StringUtils.humpToLine(claimCaseDTO.getOrderByColumn()));
        } else {
            claimCaseDTO.setOrderByColumn("rpt_no");
            claimCaseDTO.setIsAsc("desc");
        }

        startPage(claimCaseDTO);
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectProcessingClaimCaseList(claimCaseDTO);
        return getDataTable(processingCaseVos);
    }

    /**
     * 查询已处理受理案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:list')")
    @GetMapping("/processedList")
    public TableDataInfo processedList(ClaimCaseDTO claimCaseDTO) {
        if (claimCaseDTO.getOrderByColumn() != null && !claimCaseDTO.getOrderByColumn().equals("")) {
            claimCaseDTO.setOrderByColumn(StringUtils.humpToLine(claimCaseDTO.getOrderByColumn()));
        } else {
            claimCaseDTO.setOrderByColumn("rpt_no");
            claimCaseDTO.setIsAsc("desc");
        }

        startPage(claimCaseDTO);
        List<ClaimCase> list = claimCaseService.selectProcessedClaimCaseList(claimCaseDTO);
        return getDataTable(list);
    }

    /**
     * 查询悬挂中受理案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:list')")
    @GetMapping("/suspensionList")
    public TableDataInfo suspensionList(ClaimCaseDTO claimCaseDTO) {
        if (claimCaseDTO.getOrderByColumn() != null && !claimCaseDTO.getOrderByColumn().equals("")) {
            claimCaseDTO.setOrderByColumn(StringUtils.humpToLine(claimCaseDTO.getOrderByColumn()));
        } else {
            claimCaseDTO.setOrderByColumn("rpt_no");
            claimCaseDTO.setIsAsc("desc");
        }

        startPage(claimCaseDTO);
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectSuspensionClaimCaseList(claimCaseDTO);
        return getDataTable(processingCaseVos);
    }


    /**
     * 导出案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:export')")
    @Log(title = "案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCase claimCase) throws IOException {
        List<ClaimCase> list = claimCaseService.selectClaimCaseList(claimCase);
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, list, "case");
    }

    /**
     * 导出处理中受理案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:export')")
    @Log(title = "处理中受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcessingList")
    public void exportProcessingList(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException {
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectProcessingClaimCaseList(claimCaseDTO);
        ExcelUtil<ProcessingCaseVo> util = new ExcelUtil<ProcessingCaseVo>(ProcessingCaseVo.class);
        util.exportExcel(response, processingCaseVos, "处理中受理案件");
    }

    /**
     * 导出已处理受理案件信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:export')")
    @Log(title = "已处理受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportProcessedList")
    public void exportProcessedList(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException {
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
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:export')")
    @Log(title = "悬挂中受理案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportSuspensionList")
    public void exportSuspensionList(HttpServletResponse response, ClaimCaseDTO claimCaseDTO) throws IOException {
        List<ProcessingCaseVo> list = claimCaseService.selectSuspensionClaimCaseList(claimCaseDTO);
        ExcelUtil<ProcessingCaseVo> util = new ExcelUtil<ProcessingCaseVo>(ProcessingCaseVo.class);
        util.exportExcel(response, list, "悬挂中受理案件");
    }

    /**
     * 获取案件信息 详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo) {
        return AjaxResult.success(claimCaseService.selectClaimCaseById(rptNo));
    }

    /**
     * 根据批次号获取案件信息 详细信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:query')")
    @GetMapping("/caseListByBatchNo")
    public TableDataInfo getCaseListByBatchNo(ClaimCase claimCase) {
        return getDataTable(claimCaseService.selectClaimCaseByBatchNo(claimCase));
    }
    /**
     * 根据报案号查看当前案件是否存在借款
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:query')")
    @PostMapping("/borrowByRptNo")
    public AjaxResult getCaseBorrowByRptNo(@RequestBody String rptNo) {
        return AjaxResult.success(claimCaseService.selectCaseBorrowByRptNo(rptNo));
    }

    /**
     * 新增案件信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:add')")
    @Log(title = "案件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCase claimCase) {
        return toAjax(claimCaseService.insertClaimCase(claimCase));
    }

    /**
     * 修改案件信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCase claimCase) {
        return toAjax(claimCaseService.updateClaimCase(claimCase));
    }

    @PutMapping("/updatecaseStatus")
    public AjaxResult edit(@RequestBody ClaimCaseProblem claimCaseProblem) {
  /*      String repNo = claimCaseProblem.getRptNo();
        claimCaseProblemService.updateClaimCaseProblem(claimCaseProblem);// // //处理意见
        claimCaseService.updatecaseStatus(repNo);  // update to    case_status  31
        int result = claimCaseRecordService.updatehistoryFlag(repNo);// history_flag = 'Y'
        return toAjax(result);*/
        return toAjax(claimCaseProblemService.updateClaimCaseProblem(claimCaseProblem));
    }

    /**
     * 删除案件信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:remove')")
    @Log(title = "案件信息 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos) {
        return toAjax(claimCaseService.deleteClaimCaseByIds(rptNos));
    }

    /**
     * 受理处理页面查询报案号对应的批次信息和交单机构
     * 获取案件信息 详细信息（多表关联查询batch和Claim表）
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:query')")
    @GetMapping("/getInfoClaimAndBatch")
    public AjaxResult getInfoClaimAndBatch(String rptNo) {
        ClaimAndBatchVo claimAndBatchVo = claimCaseService.selectClaimClaimAndBatchById(rptNo);
        return AjaxResult.success(claimAndBatchVo);
    }

    /**
     * 问题件按钮（子任务添加功能为完成）
     * 修改案件信息
     *
     * @param claimCaseProblemDTO
     * @return
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseInfoSuspend")
    public AjaxResult editCaseInfoSuspend(@RequestBody ClaimCaseProblemDTO claimCaseProblemDTO) {
        return toAjax(claimCaseService.updateCaseInfoSuspend(claimCaseProblemDTO));
    }

    /**
     * 受理确认按钮功能
     * 修改案件信息
     *
     * @param claimCaseShuntClass
     * @return
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseAndRecordInfoSuspend")
    public AjaxResult editCaseAndRecordInfoSuspend(@RequestBody ClaimCaseShuntClass claimCaseShuntClass) {
        return AjaxResult.success(claimCaseService.updateCaseAndRecordInfoSuspend(claimCaseShuntClass));
    }


    /**
     * 撤件弹框确认按钮
     * 修改案件信息
     *
     * @param claimCase
     * @return
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseAndRecordInfoCancel")
    public AjaxResult editCaseAndRecordInfoCancel(@RequestBody ClaimCase claimCase) {
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
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:case:list')")
    @GetMapping("/selectCaseDispatchList")
    public TableDataInfo selectCaseDispatchList(DispatchDTO dispatchDTO) {
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
    public AjaxResult Dispatch(@RequestBody CaseDispatchDTO caseDispatchDTO) {
        claimCaseService.updateClaimCase(caseDispatchDTO);
        return AjaxResult.success();
    }

    /**
     * 导出案件调度
     */
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "案件调度 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportDispatchList")
    public void exportDispatchList(HttpServletResponse response, DispatchDTO dispatchDTO) throws IOException {
        List<CaseDispatchVO> claimCases = claimCaseService.selectCaseDispatchList(dispatchDTO);
        ExcelUtil<CaseDispatchVO> util = new ExcelUtil<CaseDispatchVO>(CaseDispatchVO.class);
        util.exportExcel(response, claimCases, "案件调度");
    }

    /*******************************************/
    //审核工作池接口-导出处理中清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentUnder")
    public void exportConditionsForTheAdjustmentUnder(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException {
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentUnder(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "处理中理算案件");
    }

    //审核工作池接口-个人池处理中
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentUnder")
    public TableDataInfo listConditionsForTheAdjustmentUnder(AuditWorkPoolDTO auditWorkPoolDTO) {
        if (StringUtils.isNotEmpty(auditWorkPoolDTO.getOrderByColumn())) {
            auditWorkPoolDTO.setOrderByColumn(StringUtils.humpToLine(auditWorkPoolDTO.getOrderByColumn()));
        }
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentUnder(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVoS);
    }

    //审核工作池接口-导出已处理清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentOver")
    public void exportConditionsForTheAdjustmentOver(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException {
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "已处理理算案件");
    }

    //审核工作池接口-个人池已处理
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentOver")
    public TableDataInfo listConditionsForTheAdjustmentOver(AuditWorkPoolDTO auditWorkPoolDTO) {
        if (StringUtils.isNotEmpty(auditWorkPoolDTO.getOrderByColumn())) {
            auditWorkPoolDTO.setOrderByColumn(StringUtils.humpToLine(auditWorkPoolDTO.getOrderByColumn()));
        }
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVoS);
    }

    //审核工作池接口-导出悬挂中清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportConditionsForTheAdjustmentHang")
    public void exportConditionsForTheAdjustmentHang(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException {
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentHang(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "悬挂中理算案件");
    }

    //审核工作池接口-个人池悬挂中
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/listConditionsForTheAdjustmentHang")
    public TableDataInfo listConditionsForTheAdjustmentHang(AuditWorkPoolDTO auditWorkPoolDTO) {
        if (StringUtils.isNotEmpty(auditWorkPoolDTO.getOrderByColumn())) {
            auditWorkPoolDTO.setOrderByColumn(StringUtils.humpToLine(auditWorkPoolDTO.getOrderByColumn()));
        }
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = claimCaseService.selectConditionsForTheAdjustmentHang(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVoS);
    }
/********************************************/

    /**
     * 抽检完毕按钮
     * 修改案件状态
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseCheck")
    public AjaxResult editCaseCheck(@RequestBody ClaimCase claimCase) {
        claimCaseService.editCaseCheck(claimCase);
        return AjaxResult.success();
    }

    /**
     * 退回审核按钮
     * 退回后流转至该案件理算审核环节操作人处理中工作池
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "案件信息 ", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseCheckBack")
    public AjaxResult editCaseCheckBack(@RequestBody ClaimCase claimCase) {
        claimCaseService.editCaseCheckBack(claimCase);
        return AjaxResult.success();
    }

    /**
     * 抽检工作池-处理中
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/caseCheck")
    public TableDataInfo caseCheck(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO) {
        auditWorkPoolDTO.setOrderByColumn(StringUtils.humpToLine(auditWorkPoolDTO.getOrderByColumn()));
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentUnderCase(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVOS);
    }


    //抽检工作池接口-导出处理中清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportcaseCheck")
    public void exportcaseCheck(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException {
        String utf8Name = new String(auditWorkPoolDTO.getName().getBytes("UTF-8"));
        auditWorkPoolDTO.setName(utf8Name);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentUnderCase(auditWorkPoolDTO);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVOS, "处理中审核案件");
    }

    //抽检工作池接口-个人池已处理
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/listConditionsForTheAdjustmentOverNew")
    public TableDataInfo listConditionsForTheAdjustmentOverNew(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO) {
        auditWorkPoolDTO.setOrderByColumn(StringUtils.humpToLine(auditWorkPoolDTO.getOrderByColumn()));
        startPage(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseService.SelectConditionsForTheAdjustmentOverNew(auditWorkPoolDTO);
        return getDataTable(conditionsForTheAdjustmentVOS);
    }

    //抽检工作池接口-导出已处理清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportcaseCheckOver")
    public void exportcaseCheckOver(HttpServletResponse response, AuditWorkPoolDTO auditWorkPoolDTO) throws IOException {
        String utf8Name = new String(auditWorkPoolDTO.getName().getBytes("UTF-8"));
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
    public AjaxResult backToClaimCase(ClaimCase claimCase) {
        return toAjax(claimCaseService.backToClaimCase(claimCase));
    }

    /**
     * 理算审核案件审核完毕验证产品是否重设
     */
    @GetMapping("/verifyReset")
    public AjaxResult verifyReset(ReviewCompletedDTO reviewCompletedDTO) {
        return claimCaseService.verifyReset(reviewCompletedDTO);
    }

    /**
     * 理算审核案件审核完毕账单币种是否一致
     */
    @GetMapping("/verifyBillCurrency")
    public AjaxResult verifyBillCurrency(ReviewCompletedDTO reviewCompletedDTO) {
        return claimCaseService.verifyBillCurrency(reviewCompletedDTO);
    }

    /**
     * 理算审核案件审核完毕 按钮
     */
//    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @GetMapping("/ReviewCompleted")
    public AjaxResult reviewCompletedClaimCase(ClaimCaseCal claimCaseCal) {

        ClaimCaseCheckDTO claimCaseCheckDTO = new ClaimCaseCheckDTO();
        claimCaseCheckDTO.setRptNo(claimCaseCal.getRptNo());
        claimCaseCheckDTO.setPayAmount(claimCaseCal.getPayAmount());
        claimCaseCheckDTO.setPayConclusion(claimCaseCal.getPayConclusion());
        claimCaseCheckDTO.setCaseType(claimCaseCal.getIsAppeal());
        claimCaseCheckDTO.setAmount(claimCaseCal.getRefusedAmount());
        //判断案件是否符合流程抽检岗规则
        //判断后  结案
        return toAjax(claimCaseService.judgeClaimCaseCheckRule(claimCaseCheckDTO));
    }

    /**
     * 理赔信息查询
     */
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/selectClaimInformation")
    public TableDataInfo selectClaimInformation(ClaimInformationDTO claimInformationDTO) {
        startPage();
        List<ClaimInformationVo> list = claimCaseService.selectClaimInformation(claimInformationDTO);
        return getDataTable(list);
    }

    //理赔信息查询-导出清单
//    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "处理中理算案件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportClaimInformation")
    public void exportClaimInformation(HttpServletResponse response, ClaimInformationDTO claimInformationDTO) throws IOException {
        List<ClaimInformationVo> caseList = claimCaseService.selectClaimInformation(claimInformationDTO);
        ExcelUtil<ClaimInformationVo> util = new ExcelUtil<ClaimInformationVo>(ClaimInformationVo.class);
        util.exportExcel(response, caseList, "悬挂中理算案件");
    }

    //事后案件台账
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/postHocAccountingOfCases")
    public TableDataInfo postHocAccountingOfCases(ClaimCase claimCase) {
        String batchNo = claimCase.getBatchNo();
        List<ClaimCaseStanding> claimCaseStandings = claimCaseService.postHocAccountingOfCases(batchNo);
        return getDataTable(claimCaseStandings);
    }

    //base_code_mapping
    //BaseCodeMappingNew

    /**
     * @param baseCodeMappingNew
     * @return
     */
    @GetMapping("/getInfoBaseCodeMappingNew")
    public AjaxResult getInfoBaseCodeMappingNew(BaseCodeMappingNew baseCodeMappingNew) {
        return AjaxResult.success(claimCaseService.selectBaseCodeMappingNew(baseCodeMappingNew));
    }

    /**
     * PBW在线理赔请求接口
     *
     * @param batchNoRptNoDTO
     * @return
     */
    @PostMapping("/getBatchAndNoRptNo")
    public AjaxResult getBatchNoRptNo(BatchNoRptNoDTO batchNoRptNoDTO) {
        int caseload = batchNoRptNoDTO.getCasenum();
        if (caseload > 0) {
            BatchNoRptNoVO batchNoRptNoVO = new BatchNoRptNoVO();
            List<RptNoAndFilingNo> RptNoAndFilingNoList = new ArrayList<>();
            RptNoAndFilingNo rptNoAndFilingNo = new RptNoAndFilingNo();
            for (int i = 0; i <= caseload; i++) {
                //报案号
                String bahtime = "96" + "JGH0X" + PubFun.createMySqlMaxNoUseCache("RPTCODE", 10, 10);
                rptNoAndFilingNo.setRptNo(bahtime);

                //归档号
                String claimCaseNumber1 = "JGHDQQW" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 10);
                rptNoAndFilingNo.setFilingNo(claimCaseNumber1);

                RptNoAndFilingNoList.add(rptNoAndFilingNo);
            }

            //批次号
            String str1 = "JGH" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);
            batchNoRptNoVO.setBatchNo(str1);
            batchNoRptNoVO.setRptNoAndFilingNoList(RptNoAndFilingNoList);
            Date nowDate = DateUtils.getNowDate();
            batchNoRptNoVO.setCreateBatchTime(nowDate);

            ClaimBatch claimBatch = new ClaimBatch();
            claimBatch.setBatchno(str1);
            claimBatch.setSource(batchNoRptNoDTO.getSource());
            claimBatch.setHospitalcode(batchNoRptNoDTO.getHospitalCode());
            claimBatch.setClaimtype(batchNoRptNoDTO.getClaimType());
            claimBatch.setSubmitdate(batchNoRptNoDTO.getReceiveDate());
            claimBatch.setCasenum(batchNoRptNoDTO.getCasenum());
            claimBatch.setBatchtotal(batchNoRptNoDTO.getBatchTotal());
            claimBatch.setOrgancode(batchNoRptNoDTO.getHospitalCode());
            claimBatch.setCurrency(batchNoRptNoDTO.getCurrency());
            claimBatch.setConttype(batchNoRptNoDTO.getClaimType());
            claimBatch.setBillrecevieflag(batchNoRptNoDTO.getBillRecevieFlag());
            claimBatch.setPrireason(batchNoRptNoDTO.getPriReason());
            claimBatch.setRemark(batchNoRptNoDTO.getRemark());
            claimBatch.setBatchstatus(ClaimStatus.BATCHFINISH.getCode());//03
            claimBatch.setExpressnumber(batchNoRptNoDTO.getExpressNumber());
            claimBatch.setReceivedate(batchNoRptNoDTO.getReceiveDate());
            claimBatch.setSendby(batchNoRptNoDTO.getSendBy());
            claimBatch.setSpeccasetype(batchNoRptNoDTO.getClaimType());
            claimBatch.setIssuingunit(batchNoRptNoDTO.getIssuingUnit());
            claimBatch.setContno(batchNoRptNoDTO.getContNo());
            claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y
            //claimBatch.setCreateBy(SecurityUtils.getUsername());
            claimBatch.setCreateTime(nowDate);
            //claimBatch.setUpdateBy(SecurityUtils.getUsername());
            //claimBatch.setUpdateTime(nowDate);
            claimBatchService.insertClaimBatch(claimBatch);

            return AjaxResult.success(batchNoRptNoVO);
        } else {
            return AjaxResult.success("案件数不能小于等于0！");
        }
    }


    /**
     * PBW在线理赔/E结算接口
     *
     * @param
     * @return
     */
    /*
    @PostMapping("/giveBatchNoRptNoMessage")
    public AjaxResult giveBatchNoRptNoMessage(Class classnimber) {

        //查询TPA保单-根据被保人客户号和被保人姓名
        //policy_info
        InsuredNoAndName insuredNoAndName = new InsuredNoAndName();
        List<PolicyInfo> listA = policyInfoService.selectPolicyInfoListByinsuredNo(insuredNoAndName);

        //查询核心健康险保单-？
        ArrayList<Object> listB = new ArrayList<>();

        if (listA.size()!=0){//若存在TPA保单-走TPA
            if (?){

            }else if (?){

            }
        }else if (listB.size() != 0 && listA.size() == 0) {//若只存在核心健康险保单-走核心

        } else {//若都没有-提示：请撤件

        }
        return AjaxResult.success();
    }
    */
}
