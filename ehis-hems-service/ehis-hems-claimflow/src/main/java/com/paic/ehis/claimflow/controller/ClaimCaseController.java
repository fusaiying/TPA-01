package com.paic.ehis.claimflow.controller;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.*;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.claimflow.service.*;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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
    private ClaimStandingRptNoService claimStandingRptNoService;

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
    @PostMapping("/processingList")
    public TableDataInfo processingList(@RequestBody ClaimCaseDTO claimCaseDTO) {
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
    @PostMapping("/processedList")
    public TableDataInfo processedList(@RequestBody ClaimCaseDTO claimCaseDTO) {
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
    @PostMapping("/suspensionList")
    public TableDataInfo suspensionList(@RequestBody ClaimCaseDTO claimCaseDTO) {
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
        ArrayList<ClaimCase> claimCases = new ArrayList<>();
        for (ProcessingCaseVo processingCaseVo : list) {
            ClaimCase claimCase = new ClaimCase();
            BeanUtils.copyProperties(processingCaseVo, claimCase);
            claimCases.add(claimCase);
        }
        ExcelUtil<ClaimCase> util = new ExcelUtil<ClaimCase>(ClaimCase.class);
        util.exportExcel(response, claimCases, "悬挂中受理案件");
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
    @PostMapping("/listConditionsForTheAdjustmentUnder")
    public TableDataInfo listConditionsForTheAdjustmentUnder(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO) {
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
        TableDataInfo tableDataInfo = claimCaseService.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVoS = JSON.parseArray( JSON.toJSONString(tableDataInfo.getRows()), ConditionsForTheAdjustmentVO.class);
        ExcelUtil<ConditionsForTheAdjustmentVO> util = new ExcelUtil<ConditionsForTheAdjustmentVO>(ConditionsForTheAdjustmentVO.class);
        util.exportExcel(response, conditionsForTheAdjustmentVoS, "已处理理算案件");
    }

    //审核工作池接口-个人池已处理
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/listConditionsForTheAdjustmentOver")
    public TableDataInfo listConditionsForTheAdjustmentOver(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO) {
        if (StringUtils.isNotEmpty(auditWorkPoolDTO.getOrderByColumn())) {
            auditWorkPoolDTO.setOrderByColumn(StringUtils.humpToLine(auditWorkPoolDTO.getOrderByColumn()));
        }
        else {
            auditWorkPoolDTO.setOrderByColumn("updateTime");
            auditWorkPoolDTO.setIsAsc("desc");
        }
       // startPage(auditWorkPoolDTO);
        return claimCaseService.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
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
    @PostMapping("/listConditionsForTheAdjustmentHang")
    public TableDataInfo listConditionsForTheAdjustmentHang(@RequestBody AuditWorkPoolDTO auditWorkPoolDTO) {
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
        startPage(claimInformationDTO);
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

    //受理完成匹配事后案件台账 插入报案号
//    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @PostMapping("/setRptNo")
    public AjaxResult setRptNo(@RequestBody ClaimStandingRptNoDTO claimStandingRptNoDTO) {
        return AjaxResult.success(claimStandingRptNoService.updateStandingRptNo(claimStandingRptNoDTO));
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
     * PBW在线理赔请求接口-完成机构交单动作
     *
     * @param batchNoRptNoDTO
     * @return batchNoRptNoVO
     */
    @PostMapping("/getBatchAndNoRptNo")
    public AjaxResult getBatchNoRptNo(BatchNoRptNoDTO batchNoRptNoDTO) {
        String batchCount = batchNoRptNoDTO.getBatchCount();
        int i1 = Integer.parseInt(batchCount);
        if (i1 > 0) {
            BatchNoRptNoVO batchNoRptNoVO = new BatchNoRptNoVO();
            List<String> rptNoList = new ArrayList<>();

            for (int i = 0; i <= i1; i++) {
                //报案号
                String bahtime = "96" + "JGH0X" + PubFun.createMySqlMaxNoUseCache("RPTCODE", 10, 10);
                rptNoList.add(bahtime);
            }

            //批次号
            String str1 = "JGH" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);

            batchNoRptNoVO.setBatchNo(str1);
            batchNoRptNoVO.setRptNoList(rptNoList);
            Date nowDate = DateUtils.getNowDate();
            batchNoRptNoVO.setCreateBatchTime(nowDate);

            ClaimBatch claimBatch = new ClaimBatch();
            claimBatch.setBatchno(str1);//批次号
            claimBatch.setSource(batchNoRptNoDTO.getSource());//交单来源
            claimBatch.setHospitalcode(batchNoRptNoDTO.getProvider());//医院编码
            claimBatch.setClaimtype(batchNoRptNoDTO.getClaimType());//理赔类型
            claimBatch.setSubmitdate(batchNoRptNoDTO.getReceiveDate());//收单日期
            claimBatch.setCasenum(i1);//案件数量
            BigDecimal bd = new BigDecimal(batchNoRptNoDTO.getBatchAmount());
            claimBatch.setBatchtotal(bd);//批次总金额
            claimBatch.setOrgancode(batchNoRptNoDTO.getBranchRegion());//交单机构编码
            claimBatch.setBatchstatus(ClaimStatus.BATCHFINISH.getCode());//03
            claimBatch.setReceivedate(batchNoRptNoDTO.getReceiveDate());//接单日期
            claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y
            claimBatch.setDirectReceiptSign(batchNoRptNoDTO.getDirectReceiptSign());//批次是否单张发票
            claimBatch.setCaseFlag(batchNoRptNoDTO.getCaseFlag());//案件第五位标识码
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
     * PBW在校交单撤件接口
     * updateOnlineClaimWithdrawal
     *
     * @param batchNo
     * @return
     */
    @PostMapping("/updateOnlineClaimWithdrawal")
    public AjaxResult updateOnlineClaimWithdrawal(String batchNo) {
        //查询是否已经进行影像件扫描
        ClaimCase claimCase = new ClaimCase();
        claimCase.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimCase.setBatchNo(batchNo);
        List<ProcessingCaseVo> processingCaseVos = claimCaseService.selectClaimCaseByBatchNo(claimCase);
        if (processingCaseVos.size()==0) {//已经进行影像件扫描
            return AjaxResult.error("该批次已经完成影像件扫描，不能撤件！");
        } else {//未进行影像件扫描-完成撤件
            return toAjax(claimBatchService.updateClaimBatchBybatchNo(batchNo));
        }

    }


}
