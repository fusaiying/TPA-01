package com.paic.ehis.claimflow.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.paic.ehis.claimapt.domain.DTO.ClaimBatchDTO;
import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.*;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.IClaimCaseCheckRuleService;
import com.paic.ehis.claimflow.service.IClaimCaseService;
//import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
//import com.paic.ehis.claimmgt.mapper.ClaimCaseDistMapper;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.PolicyAndRiskService;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 案件信息 Service业务层处理
 *
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseServiceImpl implements IClaimCaseService {
    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    @Autowired
    private ClaimCaseAcceptMapper claimCaseAcceptMapper;

    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    @Autowired
    private PolicyInfoMapper policyInfoMapper;

    @Autowired
    private ClaimCaseInvestigationMapper claimCaseInvestigationMapper;

    @Autowired
    private ClaimProductTaskLogMapper claimProductTaskLogMapper;

    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    @Autowired
    private ClaimCaseCalMapper claimCaseCalMapper;

    @Autowired
    private ClaimCaseStandingMapper claimCaseStandingMapper;

    @Autowired
    private ClaimCaseInsuredMapper claimCaseInsuredMapper;

    @Autowired
    PolicyAndRiskService policyAndRiskService;

//    @Autowired
//    private IClaimBatchService iClaimBatchService;

    @Autowired
    private IClaimCaseCheckRuleService claimCaseCheckRuleService;

    @Autowired
    private ClaimCaseProblemMapper claimCaseProblemMapper;


    /**
     * 查询案件信息
     *
     * @param rptNo 案件信息 ID
     * @return 案件信息
     */
    @Override
    public ClaimCase selectClaimCaseById(String rptNo) {
        return claimCaseMapper.selectClaimCaseById(rptNo);
    }

    /**
     * 查询案件信息 列表
     *
     * @param claimCase 案件信息
     * @return 案件信息
     */
    @Override
    public List<ClaimCase> selectClaimCaseList(ClaimCase claimCase) {
        return claimCaseMapper.selectClaimCaseList(claimCase);
    }

    /**
     * 查询处理中案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    @Override
    public List<ProcessingCaseVo> selectProcessingClaimCaseList(ClaimCaseDTO claimCaseDTO) {
        if (StringUtils.isEmpty(claimCaseDTO.getBatchNo()) && StringUtils.isEmpty(claimCaseDTO.getName()) && StringUtils.isEmpty(claimCaseDTO.getRptNo())) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) - 30);
            claimCaseDTO.setUpdateTime(DateUtils.parseDate(calendar.getTime()));
        }
        claimCaseDTO.setCaseStatus("05");
        claimCaseDTO.setStatus("Y");
        claimCaseDTO.setUpdateBy(SecurityUtils.getUsername());
        /*          实现多少天多少小时多少分的计算实现
        ArrayList<ProcessingCaseVo> processingCaseVos = new ArrayList<>();
        for (ClaimCase claimCase : claimCases) {
            ProcessingCaseVo processingCaseVo = new ProcessingCaseVo();
            BeanUtils.copyProperties(claimCase,processingCaseVo);
            long times = now.getTime()-claimCase.getUpdateTime().getTime();
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
            StringBuilder sbTime = new StringBuilder();
            if (day>0){
                sbTime.append(day).append("天");
            }
            if (hour > 0) {
                sbTime.append(hour).append("小时");
            }
            if (min > 0) {
                sbTime.append(min).append("分钟");
            }
            processingCaseVo.setStayTime(sbTime.toString());
            processingCaseVos.add(processingCaseVo);
        }*/
        return claimCaseMapper.selectProcessingClaimCaseList(claimCaseDTO);
    }

    /**
     * 查询已处理受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    @Override
    public List<ClaimCase> selectProcessedClaimCaseList(ClaimCaseDTO claimCaseDTO) {
        claimCaseDTO.setOperation("05");
        claimCaseDTO.setIsHistory("Y");
        claimCaseDTO.setCaseStatus("'05','30'");
        claimCaseDTO.setOperator(SecurityUtils.getUsername());
        claimCaseDTO.setStatus("Y");
        return claimCaseMapper.selectClaimCaseProcessedList(claimCaseDTO);
    }

    /**
     * 查询悬挂中受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    @Override
    public List<ProcessingCaseVo> selectSuspensionClaimCaseList(ClaimCaseDTO claimCaseDTO) {
        claimCaseDTO.setCaseStatus("30");
        claimCaseDTO.setStatus("Y");
        claimCaseDTO.setOperator(SecurityUtils.getUsername());
        claimCaseDTO.setIsHistory("Y");
        claimCaseDTO.setOperation("05");
        return claimCaseMapper.selectProcessingClaimCaseList(claimCaseDTO);
    }

    /**
     * 新增案件信息
     *
     * @param claimCase 案件信息
     * @return 结果
     */
    @Override
    public int insertClaimCase(ClaimCase claimCase) {
        claimCase.setCreateTime(DateUtils.getNowDate());
        return claimCaseMapper.insertClaimCase(claimCase);
    }

    /**
     * 修改案件信息
     *
     * @param claimCase 案件信息
     * @return 结果
     */
    @Override
    public int updateClaimCase(ClaimCase claimCase) {
        claimCase.setUpdateTime(DateUtils.getNowDate());
        return claimCaseMapper.updateClaimCase(claimCase);
    }

    @Override
    public int updatecaseStatus(String repNo) {
        return claimCaseMapper.updatecaseStatus(repNo);
    }

    /**
     * 批量删除案件信息
     *
     * @param rptNos 需要删除的案件信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseByIds(String[] rptNos) {
        return claimCaseMapper.deleteClaimCaseByIds(rptNos);
    }

    /**
     * 删除案件信息 信息
     *
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseById(String rptNo) {
        return claimCaseMapper.deleteClaimCaseById(rptNo);
    }

    /**
     * 获取案件信息 详细信息（多表关联查询batch和Claim表）
     *
     * @param rptNo 报案号
     * @return 结果
     */
    @Override
    public ClaimAndBatchVo selectClaimClaimAndBatchById(String rptNo) {
        return claimCaseMapper.selectClaimClaimAndBatchById(rptNo);
    }

    /**
     * 问题件按钮（子任务添加功能未完成）
     * 修改案件信息
     *
     * @param claimCaseProblemDTO
     * @return
     */
    @Override
    public int updateCaseInfoSuspend(ClaimCaseProblemDTO claimCaseProblemDTO) {
        ClaimCase claimCase = new ClaimCase();
        claimCase.setRptNo(claimCaseProblemDTO.getRptNo());
        claimCase.setCaseStatus("30");//问题件
        claimCase.setStatus("Y");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());

        //将之前的案件流程表变为历史节点
        List<ClaimCaseRecord> claimCaseRecordsList = claimCaseRecordMapper.selectClaimCaseRecordProblemShipment(claimCaseProblemDTO.getRptNo());
        if (claimCaseRecordsList.size() != 0) {
            for (ClaimCaseRecord claimCaseRecords : claimCaseRecordsList) {
                ClaimCaseRecord claimCaseRecord1 = new ClaimCaseRecord();
                claimCaseRecord1.setHistoryFlag("Y");
                claimCaseRecord1.setRecordId(claimCaseRecords.getRecordId());
                claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord1);
            }
        }
        //生成一条案件流程信息
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCaseProblemDTO.getRptNo());//报案号
        claimCaseRecord.setHistoryFlag("N");//是否历史节点
        claimCaseRecord.setOperation("30");//流程节点-问题件
        claimCaseRecord.setOperator(SecurityUtils.getUsername());//流程节点操作人
        claimCaseRecord.setStatus("Y");//数据状态
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecordSecond(claimCaseRecord);


        List<ClaimCaseProblem> claimCaseProblems = claimCaseProblemMapper.selectClaimCaseProblemByRptNo(claimCaseProblemDTO.getRptNo());
        if (claimCaseProblems.size() != 0) {
            for (ClaimCaseProblem claimCaseProblemsOne : claimCaseProblems) {
                ClaimCaseProblem claimCaseProblem1 = new ClaimCaseProblem();
                claimCaseProblem1.setIsHistory("Y");
                claimCaseProblem1.setProblemId(claimCaseProblemsOne.getProblemId());
                claimCaseProblemMapper.updateClaimCaseProblem(claimCaseProblem1);
            }
        }
        //生成一条案件问题件表信息
        ClaimCaseProblem claimCaseProblem = new ClaimCaseProblem();
        claimCaseProblem.setRptNo(claimCaseProblemDTO.getRptNo());//报案号
        claimCaseProblem.setProblemType(claimCaseProblemDTO.getProblemType());//问题件类型
        claimCaseProblem.setProblemView(claimCaseProblemDTO.getProblemView());//转出意见
        claimCaseProblem.setIsHistory("N");
        claimCaseProblem.setStatus("Y");
        claimCaseProblem.setCreateBy(SecurityUtils.getUsername());
        claimCaseProblem.setCreateTime(DateUtils.getNowDate());
        claimCaseProblem.setUpdateBy(SecurityUtils.getUsername());
        claimCaseProblem.setUpdateTime(DateUtils.getNowDate());
        claimCaseProblemMapper.insertClaimCaseProblem(claimCaseProblem);

        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 受理确认按钮功能
     * 修改案件信息
     *
     * @param claimCase
     * @return
     */
    @Override
    public int updateCaseAndRecordInfoSuspend(ClaimCase claimCase) {

        //判断是否为审核岗退回受理
        // 通过查询报案号为本报案号,数据状态为"Y",是否为历史节点："N",流程节点为："07"审核的上一流程节点ID；
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCase.getRptNo());
        claimCaseRecord.setStatus("Y");
        //claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setOperation("06");
        List<ClaimCaseRecord> claimCaseRecords = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
        ClaimCaseRecord claimCaseRecord1 = new ClaimCaseRecord();
        if (null == claimCaseRecords || claimCaseRecords.size() == 0) {
            //为空的情况
            //第一次处理-案件状态05->06
            claimCase.setCaseStatus("06");//案件信息-录入
            claimCaseRecord1.setOperation("06");//案件操作记录-录入

            //将原有的
        } else {
            //不为空的情况
            //第二次处理-案件状态05->07
            claimCase.setCaseStatus("07");//案件信息-审核
            claimCaseRecord1.setOperation("07");//案件操作记录-审核
        }
        claimCase.setStatus("Y");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCaseMapper.updateClaimCase(claimCase);//完成案件信息改变

        //修改受理信息的材料齐全日期：
        ClaimCaseAccept claimCaseAccept = new ClaimCaseAccept();
        claimCaseAccept.setRptNo(claimCase.getRptNo());
        claimCaseAccept.setMaterialCompleteDate(DateUtils.getNowDate());
        claimCaseAcceptMapper.updateClaimCaseAccept(claimCaseAccept);

        //将之前的案件流程表变为历史节点
        List<ClaimCaseRecord> claimCaseRecordsList = claimCaseRecordMapper.selectClaimCaseRecordProblemShipment(claimCase.getRptNo());
        if (claimCaseRecordsList.size() != 0) {
            for (ClaimCaseRecord claimCaseRecords2 : claimCaseRecordsList) {
                ClaimCaseRecord claimCaseRecord3 = new ClaimCaseRecord();
                claimCaseRecord3.setHistoryFlag("Y");
                claimCaseRecord3.setRecordId(claimCaseRecords2.getRecordId());
                claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord3);
            }
        }
        //完成案件操作记录表的记录
        claimCaseRecord1.setRptNo(claimCase.getRptNo());
        claimCaseRecord1.setOperator(SecurityUtils.getUsername());
        claimCaseRecord1.setHistoryFlag("N");
        claimCaseRecord1.setStatus("Y");
        claimCaseRecord1.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord1.setCreateTime(DateUtils.getNowDate());

        Long s = claimCaseRecordMapper.selectClaimCaseRecordSecondTwo(claimCase.getRptNo());

        ClaimCaseRecord claimCaseRecord2 = new ClaimCaseRecord();
        claimCaseRecord2.setRptNo(claimCase.getRptNo());
        claimCaseRecord2.setHistoryFlag("Y");
        claimCaseRecord2.setRecordId(s);
        claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord2);

        claimCaseRecord1.setOrgRecordId(s);
        return claimCaseRecordMapper.insertClaimCaseRecordSecond(claimCaseRecord1);
    }

    /**
     * 撤件弹框确认按钮
     * 修改案件信息
     *
     * @param claimCase
     * @return
     */
    @Override
    public int updateCaseAndRecordInfoCancel(ClaimCase claimCase) {
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();

        String pulloutType = claimCase.getPulloutType();
        if (pulloutType.equals("01")) {//撤件97
            claimCase.setCaseStatus("97");
            claimCaseRecord.setOperation("97");
        } else if (pulloutType.equals("02")) {//撤件可申诉98
            claimCase.setCaseStatus("98");
            claimCaseRecord.setOperation("98");
        }
        claimCase.setStatus("Y");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());

        //将之前的案件流程表变为历史节点
        List<ClaimCaseRecord> claimCaseRecordsList = claimCaseRecordMapper.selectClaimCaseRecordRevocation(claimCase.getRptNo());
        if (claimCaseRecordsList.size() != 0) {
            for (ClaimCaseRecord claimCaseRecords2 : claimCaseRecordsList) {
                ClaimCaseRecord claimCaseRecord3 = new ClaimCaseRecord();
                claimCaseRecord3.setHistoryFlag("Y");
                claimCaseRecord3.setRecordId(claimCaseRecords2.getRecordId());
                claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord3);
            }
        }

        //将之前的案件流程表变为历史节点
        List<ClaimCaseRecord> claimCaseRecords = claimCaseRecordMapper.selectClaimCaseRecordProblemShipment(claimCase.getRptNo());
        if (claimCaseRecords.size() != 0) {
            for (ClaimCaseRecord claimCaseRecords2 : claimCaseRecords) {
                ClaimCaseRecord claimCaseRecord3 = new ClaimCaseRecord();
                claimCaseRecord3.setHistoryFlag("Y");
                claimCaseRecord3.setRecordId(claimCaseRecords2.getRecordId());
                claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord3);
            }
        }

        //生成案件操作记录表
        claimCaseRecord.setRptNo(claimCase.getRptNo());
        claimCaseRecord.setOperator(SecurityUtils.getUsername());
        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecordSecond(claimCaseRecord);

        return claimCaseMapper.updateClaimCase(claimCase);
    }


    /**
     * 查询案件调度工作池
     *
     * @param
     * @returnDispatchDTO dispatchDTO
     */

    @Override
    public List<CaseDispatchVO> selectCaseDispatchList(DispatchDTO dispatchDTO) {

        dispatchDTO.setUpdateBy(SecurityUtils.getUsername());
        dispatchDTO.setStatus("Y");
        // List<CaseDispatchVO> caseList=claimCaseMapper.selectCaseDispatchList(dispatchDTO);
        //获取   rpt_no  case_statuus  id_no operator
       /* for (CaseDispatchVO caseInfo : caseList){
            // 监控时效
           // caseInfo.setMonitoringTime(claimCaseRecordMapper.selectMinAcceptTime(caseInfo.getRptNo()));
            // 出单公司
            List<ClaimCasePolicy> claimCasePolicyList = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(caseInfo.getRptNo());
            for (ClaimCasePolicy policy : claimCasePolicyList) {
                caseInfo.setCompanyCode(policy.getCompanyCode());
                caseInfo.setName(policy.getName());
            }
        }*/
        return claimCaseMapper.selectCaseDispatchList(dispatchDTO);
    }

    /**
     * 案件调度
     *
     * @param caseDispatchDTO
     * @return
     */
    @Override
    public void updateClaimCase(CaseDispatchDTO caseDispatchDTO) {
       //获取前端传入报案号
        List<String> rptNo1=caseDispatchDTO.getRptNo();
        ClaimCaseRecord claimCaseRecord=new ClaimCaseRecord();
        //循环遍历  进行案件调度修改操作人
        for (String rpt:rptNo1){
            claimCaseRecord.setRptNo(rpt);
            claimCaseRecord.setOperator(caseDispatchDTO.getOperator());
            claimCaseRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimCaseRecordMapper.updateCaseDispatchList(claimCaseRecord);
        }
       // return claimCaseRecordMapper.updateCaseDispatchList();
    }

    //条件理算审核

    /**
     * 待处理
     *
     * @param auditWorkPoolDTO
     * @return
     */
    @Override
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentUnder(AuditWorkPoolDTO auditWorkPoolDTO) {
        List<ConditionsForTheAdjustmentVO> ConditionsForTheAdjustmentVOLList = new ArrayList<>();
        auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());

        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.selectConditionsForTheAdjustmentUnder(auditWorkPoolDTO);//查询出处理中的所有的数据
        if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
            for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                //已经拥有：批次号、报案号、批次状态、提交用户、被保人姓名
                //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                //查询案件保单关联表：claim_case_policy，rpt_no
                List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                    List<String> organCodeList = new ArrayList<>();//承保机构
                    List<String> companyNameList = new ArrayList<>();//出单公司
                    for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                        //去重出单公司名称拼接
                        companyNameList.add(claimCasePoliciesOne.getCompanyName());
                        //去重承保机构名称拼接
                        organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    Set set = new HashSet<>(organCodeList);
                    List newOrganCodeList = new ArrayList<>(set);
                    if (!newOrganCodeList.isEmpty()) {
                        stringBuilder.append(newOrganCodeList.get(0));
                        for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                            stringBuilder.append("|").append(newOrganCodeList.get(i));
                        }
                    }
                    conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                    StringBuilder stringBuilder2 = new StringBuilder();
                    Set set1 = new HashSet<>(companyNameList);
                    List newCompanyNameList = new ArrayList<>(set1);
                    if (!newCompanyNameList.isEmpty()) {
                        stringBuilder2.append(newCompanyNameList.get(0));
                        for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                            stringBuilder2.append("|").append(newCompanyNameList.get(i));
                        }
                    }
                    conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                }

                //是否调查
                ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                claimCaseInvestigation.setIsHistory("N");
                claimCaseInvestigation.setStatus("Y");
                List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                    conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                } else {
                    conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                }

                //停留时长
                ClaimCaseRecord claimCaseRecord = claimCaseRecordMapper.selectClaimCaseRecordByrptNoOne(conditionsForTheAdjustmentVOSLost.getRptNo());
                if (null != claimCaseRecord) {
                    Date begin = claimCaseRecord.getCreateTime();
                    Date end = new Date();

                    long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒

                    long day1 = between / (24 * 3600);

                    long hour1 = between % (24 * 3600) / 3600;

                    long minute1 = between % 3600 / 60;

                    String time = "" + day1 + "天" + hour1 + "小时" + minute1 + "分";

                    conditionsForTheAdjustmentVOSLost.setStayTime(time);
                }
                //监控时效
                ClaimCaseRecord claimCaseRecord1 = claimCaseRecordMapper.selectClaimCaseRecordByrptNoTwo(conditionsForTheAdjustmentVOSLost.getRptNo());
                if (null != claimCaseRecord1) {
                    Date begin1 = claimCaseRecord1.getCreateTime();
                    Date end1 = new Date();

                    long between1 = (end1.getTime() - begin1.getTime()) / 1000;//除以1000是为了转换成秒

                    long day2 = between1 / (24 * 3600);

                    long hour2 = between1 % (24 * 3600) / 3600;

                    long minute2 = between1 % 3600 / 60;

                    String time2 = "" + day2 + "天" + hour2 + "小时" + minute2 + "分";

                    conditionsForTheAdjustmentVOSLost.setMonitoringTime(time2);
                }
                ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
            }
        }
        return ConditionsForTheAdjustmentVOLList;
    }//待处理

    /**
     * 已处理
     *
     * @param auditWorkPoolDTO
     * @return
     */
    @Override
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentOver(AuditWorkPoolDTO auditWorkPoolDTO) {
        List<ConditionsForTheAdjustmentVO> ConditionsForTheAdjustmentVOLList = new ArrayList<>();
        String batchNo = auditWorkPoolDTO.getBatchNo();
        String rptNo = auditWorkPoolDTO.getRptNo();
        String name = auditWorkPoolDTO.getName();
        if (batchNo == null || "".equals(batchNo)) {
            if (rptNo == null || "".equals(rptNo)) {
                if (name == null || "".equals(name)) {
                    //默认查询一个月的
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                    auditWorkPoolDTO.setUpdateStartTime(calendar.getTime());
                    auditWorkPoolDTO.setUpdateEndTime(DateUtils.parseDate(DateUtils.getTime()));
                    auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());
                    List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.selectConditionsForTheAdjustmentOver(auditWorkPoolDTO);
                    if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
                        for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                            //已经拥有：批次号、报案号、批次状态、被保人姓名、提交用户
                            //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                            //查询案件保单关联表：claim_case_policy，rpt_no
                            List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                            if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                                List<String> organCodeList = new ArrayList<>();//承保机构
                                List<String> companyNameList = new ArrayList<>();//出单公司
                                for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                                    //去重出单公司名称拼接
                                    companyNameList.add(claimCasePoliciesOne.getCompanyName());
                                    //去重承保机构名称拼接
                                    organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                                }

                                StringBuilder stringBuilder = new StringBuilder();
                                Set set = new HashSet<>(organCodeList);
                                List newOrganCodeList = new ArrayList<>(set);
                                if (!newOrganCodeList.isEmpty()) {
                                    stringBuilder.append(newOrganCodeList.get(0));
                                    for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                                        stringBuilder.append("|").append(newOrganCodeList.get(i));
                                    }
                                }
                                conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                                StringBuilder stringBuilder2 = new StringBuilder();
                                Set set1 = new HashSet<>(companyNameList);
                                List newCompanyNameList = new ArrayList<>(set1);
                                if (!newCompanyNameList.isEmpty()) {
                                    stringBuilder2.append(newCompanyNameList.get(0));
                                    for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                                        stringBuilder2.append("|").append(newCompanyNameList.get(i));
                                    }
                                }
                                conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                            }

                            //是否调查
                            ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                            claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                            claimCaseInvestigation.setIsHistory("N");
                            claimCaseInvestigation.setStatus("Y");
                            List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                            if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                                conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                            } else {
                                conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                            }
                            ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
                        }
                    }
                }
            }
        } else {
            //按条件查询
            auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());
            List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.selectConditionsForTheAdjustmentUnderTwo(auditWorkPoolDTO);//查询出处理中的所有的数据
            if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
                for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                    //已经拥有：批次号、报案号、批次状态、被保人姓名、提交用户
                    //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                    //查询案件保单关联表：claim_case_policy，rpt_no
                    List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                    if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                        List<String> organCodeList = new ArrayList<>();//承保机构
                        List<String> companyNameList = new ArrayList<>();//出单公司
                        for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                            //去重出单公司名称拼接
                            companyNameList.add(claimCasePoliciesOne.getCompanyName());
                            //去重承保机构名称拼接
                            organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                        }

                        StringBuilder stringBuilder = new StringBuilder();
                        Set set = new HashSet<>(organCodeList);
                        List newOrganCodeList = new ArrayList<>(set);
                        if (!newOrganCodeList.isEmpty()) {
                            stringBuilder.append(newOrganCodeList.get(0));
                            for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                                stringBuilder.append("|").append(newOrganCodeList.get(i));
                            }
                        }
                        conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                        StringBuilder stringBuilder2 = new StringBuilder();
                        Set set1 = new HashSet<>(companyNameList);
                        List newCompanyNameList = new ArrayList<>(set1);
                        if (!newCompanyNameList.isEmpty()) {
                            stringBuilder2.append(newCompanyNameList.get(0));
                            for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                                stringBuilder2.append("|").append(newCompanyNameList.get(i));
                            }
                        }
                        conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                    }

                    //是否调查
                    ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                    claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                    claimCaseInvestigation.setIsHistory("N");
                    claimCaseInvestigation.setStatus("Y");
                    List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                    if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                        conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                    } else {
                        conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                    }
                    ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
                }
            }
        }
        return ConditionsForTheAdjustmentVOLList;
    }//已处理

    /**
     * 悬挂
     *
     * @param auditWorkPoolDTO
     * @return
     */
    @Override
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentHang(AuditWorkPoolDTO auditWorkPoolDTO) {
        List<ConditionsForTheAdjustmentVO> ConditionsForTheAdjustmentVOLList = new ArrayList<>();
        auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());
        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.selectConditionsForTheAdjustmentHang(auditWorkPoolDTO);//查询出处理中的所有的数据
        if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
            for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                //已经拥有：批次号、报案号、批次状态、被保人姓名、提交用户
                //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                //查询案件保单关联表：claim_case_policy，rpt_no
                List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                    List<String> organCodeList = new ArrayList<>();//承保机构
                    List<String> companyNameList = new ArrayList<>();//出单公司
                    for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                        //去重出单公司名称拼接
                        companyNameList.add(claimCasePoliciesOne.getCompanyName());
                        //去重承保机构名称拼接
                        organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    Set set = new HashSet<>(organCodeList);
                    List newOrganCodeList = new ArrayList<>(set);
                    if (!newOrganCodeList.isEmpty()) {
                        stringBuilder.append(newOrganCodeList.get(0));
                        for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                            stringBuilder.append("|").append(newOrganCodeList.get(i));
                        }
                    }
                    conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                    StringBuilder stringBuilder2 = new StringBuilder();
                    Set set1 = new HashSet<>(companyNameList);
                    List newCompanyNameList = new ArrayList<>(set1);
                    if (!newCompanyNameList.isEmpty()) {
                        stringBuilder2.append(newCompanyNameList.get(0));
                        for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                            stringBuilder2.append("|").append(newCompanyNameList.get(i));
                        }
                    }
                    conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                }

                //是否调查
                ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                claimCaseInvestigation.setIsHistory("N");
                claimCaseInvestigation.setStatus("Y");
                List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                    conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                } else {
                    conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                }
                ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
            }
        }
        return ConditionsForTheAdjustmentVOLList;
    }//悬挂

    /**
     * 审核退回
     *
     * @param claimCase 案件信息
     * @return 结果
     */
    @Transactional
    @Override
    public int backToClaimCase(ClaimCase claimCase) {
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCase.getRptNo());
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setStatus(ClaimStatus.DATAYES.getCode());
        claimCaseRecord.setOperation(ClaimStatus.CASEACCEPTED.getCode());
        ClaimCaseRecord caseRecord = claimCaseRecordMapper.selectRecentClaimCaseRecord(claimCaseRecord);
        /*List<ClaimCaseRecord> claimCaseRecords = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
        if (claimCaseRecords.size() > 0) {
            claimCase.setUpdateBy(claimCaseRecords.get(0).getCreateBy());
        }*/
        claimCase.setUpdateBy(caseRecord.getOperator());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCase.setCaseStatus(ClaimStatus.CASEACCEPTED.getCode());

        ClaimCaseRecord record = claimCaseRecordMapper.selectClaimCaseRecordByRptNoOperation(claimCase.getRptNo());
        record.setHistoryFlag("Y");
        record.setOperator(SecurityUtils.getUsername());
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecord(record);

        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setOrgRecordId(record.getRecordId());
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 审核完毕前验证重设与否
     *
     * @param reviewCompletedDTO 案件信息
     * @return 结果
     */
    @Override
    public AjaxResult verifyReset(ReviewCompletedDTO reviewCompletedDTO) {
        //若账单理算匹配的保单下的产品存在保障福利重设（重设中或已重设完毕）非阻断弹框提示：“xxx产品已对保障福利进行重设操作。”
            /*
            需要根据报案号获取保单信息中的险种编码
                再根据当前状态及轨迹判断是否重设过
                    直接判断轨迹 “risk_status=02” 的数据大于两条
            */
        PolicyAndRiskRelation policyAndRiskRelation = new PolicyAndRiskRelation();
        ClaimProductTaskLog claimProductTaskLog = new ClaimProductTaskLog();
        ArrayList<PolicyRiskRelation> policyRiskRelations = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        PolicyRiskRelation policyRiskRelation;
        List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(reviewCompletedDTO.getRptNo());
        for (ClaimCasePolicy claimCasePolicy : claimCasePolicies) {
            policyAndRiskRelation.setPolicyNo(claimCasePolicy.getPolicyNo());
            TableDataInfo relationList = policyAndRiskService.getRelationList(policyAndRiskRelation);
            if (relationList.getTotal() > 0) {
                if (StringUtils.isNotNull(relationList.getRows())) {
                    for (Object row : relationList.getRows()) {
                        policyRiskRelation = objectMapper.convertValue(row, PolicyRiskRelation.class);
                        policyRiskRelations.add(policyRiskRelation);
                    }
                }
                for (PolicyRiskRelation riskRelation : policyRiskRelations) {
                    claimProductTaskLog.setRiskStatus("04");
                    claimProductTaskLog.setRiskCode(riskRelation.getRiskCode());
                    List<ClaimProductTaskLog> claimProductTaskLogs = claimProductTaskLogMapper.selectClaimProductTaskLogList(claimProductTaskLog);
                    if (1 < claimProductTaskLogs.size()) {
//                        claimProductTaskLogs.get(0).getRiskCode()
                        return AjaxResult.success("xxx产品已对保障福利进行重设操作");//非阻断弹框提示：“xxx产品已对保障福利进行重设操作。
                    }
                }
            }

        }
        return AjaxResult.success();
    }

    /**
     * 审核完毕前验证帐单币种
     *
     * @param reviewCompletedDTO 案件信息
     * @return 结果
     */
    @Override
    public AjaxResult verifyBillCurrency(ReviewCompletedDTO reviewCompletedDTO) {
        //判断赔付结论中的账单币种（支付币种）与机构交单的账单币种是否一致，若不一致，则非阻断提示：“请通知该批次的所有案件均调整成正确币种”
        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(reviewCompletedDTO.getBatchNo());

        if (!reviewCompletedDTO.getBillCurrency().equals(claimBatch.getCurrency())) {
            return AjaxResult.success("请通知该批次的所有案件均调整成正确币种");
        }
        return AjaxResult.success();
    }

    /**
     * 审核完毕
     *
     * @param claimCaseCal 案件信息
     * @return 结果
     */
    @Transactional
    @Override
    public int reviewCompletedClaimCase(ClaimCaseCal claimCaseCal) {
        //暂未实现判断被保人当前匹配到的赔付责任所属的保单是否存在在途保全（个别保全项：）对理赔进行挂起操作，待保全处理完成后理赔继续并进行重新理算
        //——寿险还未对接接口，暂缓处理

        ClaimCaseCheckDTO claimCaseCheckDTO = new ClaimCaseCheckDTO();
        claimCaseCheckDTO.setRptNo(claimCaseCal.getRptNo());
        claimCaseCheckDTO.setPayAmount(claimCaseCal.getPayAmount());
        claimCaseCheckDTO.setPayConclusion(claimCaseCal.getPayConclusion());
        claimCaseCheckDTO.setCaseType(claimCaseCal.getIsAppeal());
        claimCaseCheckDTO.setAmount(claimCaseCal.getRefusedAmount());
        //判断案件是否符合流程抽检岗规则
        //判断后  结案
        ClaimCaseServiceImpl claimCaseService = new ClaimCaseServiceImpl();
        return claimCaseService.judgeClaimCaseCheckRule(claimCaseCheckDTO);

    }


    /**
     * 提调
     *
     * @param caseInvestigation 案件信息
     * @return 结果
     */
    @Transactional
    @Override
    public int raiseClaimCase(ClaimCaseInvestigation caseInvestigation) {
        ClaimCaseRecord claimCaseRecord = claimCaseRecordMapper.selectClaimCaseRecordByrptNoOne(caseInvestigation.getRptNo());
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setOperator(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord);
        ClaimCaseRecord caseRecord = new ClaimCaseRecord();
        caseRecord.setRptNo(caseInvestigation.getRptNo());
        caseRecord.setOperation("32");
        caseRecord.setStatus(ClaimStatus.DATAYES.getCode());
        caseRecord.setHistoryFlag("N");
        caseRecord.setOrgRecordId(claimCaseRecord.getRecordId());
        caseRecord.setCreateBy(SecurityUtils.getUsername());
        caseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(caseRecord);

        ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
        claimCaseInvestigation.setIsHistory("N");
        claimCaseInvestigation.setInvNo("ZWQR"+ PubFun.createMySqlMaxNoUseCache("Investigation",10,7));
        claimCaseInvestigation.setCreateBy(SecurityUtils.getUsername());
        claimCaseInvestigation.setCreateTime(DateUtils.getNowDate());
        claimCaseInvestigation.setUpdateBy(SecurityUtils.getUsername());
        claimCaseInvestigation.setUpdateTime(DateUtils.getNowDate());
        claimCaseInvestigationMapper.insertClaimCaseInvestigation(claimCaseInvestigation);

        ClaimCase claimCase = new ClaimCase();
        claimCase.setCaseStatus("32");
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 抽检完毕，修改案件状态
     */
    @Override
    public int editCaseCheck(ClaimCase claimCase) {
        claimCase.setCaseStatus("99");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 退回后流转至该案件理算审核环节操作人处理中工作池
     */
    @Override
    public int editCaseCheckBack(ClaimCase claimCase) {
//        claimCase.setCaseStatus("07");
        //查询理算审核的历史操作记录
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCase.getRptNo());
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setOperation("07");
        List<ClaimCaseRecord> claimCaseRecords = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
        //判断是否查出，如果查出为case表的操作人进行赋值
        if (claimCaseRecords.size() > 0) {
            claimCase.setUpdateBy(claimCaseRecords.get(0).getOperator());
        }
        //更改case表的更新人和案件状态
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCase.setCaseStatus("07");

        //根据报案号查询出案件抽检的操作记录并进行更新
        ClaimCaseRecord record = claimCaseRecordMapper.selectClaimCaseRecordByrptNoOneNew(claimCase.getRptNo());
        record.setOperator(SecurityUtils.getUsername());
        record.setHistoryFlag("Y");
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecord(record);

        //增加一条新的、不是历史状态的理算审核操作记录
        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setOperation("07");
        claimCaseRecord.setOperator(claimCase.getCreateBy());
        claimCaseRecord.setOrgRecordId(record.getRecordId());
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
        return claimCaseMapper.updateClaimCase(claimCase);

    }

    /**
     * 抽检工作池-处理中
     */
    @Override
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentUnderCase(AuditWorkPoolDTO auditWorkPoolDTO) {
        List<ConditionsForTheAdjustmentVO> ConditionsForTheAdjustmentVOLList = new ArrayList<>();
        auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());

        List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.SelectConditionsForTheAdjustmentUnderNew(auditWorkPoolDTO);//查询出处理中的所有的数据
        if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
            for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                //已经拥有：批次号、报案号、批次状态、提交用户、被保人姓名
                //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                //查询案件保单关联表：claim_case_policy，rpt_no
                List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                    List<String> organCodeList = new ArrayList<>();//承保机构
                    List<String> companyNameList = new ArrayList<>();//出单公司
                    for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                        //去重出单公司名称拼接
                        companyNameList.add(claimCasePoliciesOne.getCompanyName());
                        //去重承保机构名称拼接
                        organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    Set set = new HashSet<>(organCodeList);
                    List newOrganCodeList = new ArrayList<>(set);
                    if (!newOrganCodeList.isEmpty()) {
                        stringBuilder.append(newOrganCodeList.get(0));
                        for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                            stringBuilder.append("|").append(newOrganCodeList.get(i));
                        }
                    }
                    conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                    StringBuilder stringBuilder2 = new StringBuilder();
                    Set set1 = new HashSet<>(companyNameList);
                    List newCompanyNameList = new ArrayList<>(set1);
                    if (!newCompanyNameList.isEmpty()) {
                        stringBuilder2.append(newCompanyNameList.get(0));
                        for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                            stringBuilder2.append("|").append(newCompanyNameList.get(i));
                        }
                    }
                    conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                }

                //是否调查
                ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                claimCaseInvestigation.setIsHistory("N");
                claimCaseInvestigation.setStatus("Y");
                List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                    conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                } else {
                    conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                }

                //停留时长
                ClaimCaseRecord claimCaseRecord = claimCaseRecordMapper.selectClaimCaseRecordByrptNoCase(conditionsForTheAdjustmentVOSLost.getRptNo());
                Date begin;
                Date end = new Date();
                if (null != claimCaseRecord && claimCaseRecord.getStatus() != "") {
                    begin = claimCaseRecord.getCreateTime();
                } else {
                    ClaimCaseRecord claimCaseRecord1 = claimCaseRecordMapper.selectClaimCaseRecordByrptNoOne(conditionsForTheAdjustmentVOSLost.getRptNo());
                    begin = claimCaseRecord1.getUpdateTime();
                }
                long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒

                long day1 = between / (24 * 3600);

                long hour1 = between % (24 * 3600) / 3600;

                long minute1 = between % 3600 / 60;

                String time = "" + day1 + "天" + hour1 + "小时" + minute1 + "分";

                conditionsForTheAdjustmentVOSLost.setStayTime(time);
                //监控时效
                ClaimCaseRecord claimCaseRecord1 = claimCaseRecordMapper.selectClaimCaseRecordByrptNoTwo(conditionsForTheAdjustmentVOSLost.getRptNo());
                if (null != claimCaseRecord1) {
                    Date begin1 = claimCaseRecord1.getCreateTime();
                    Date end1 = new Date();

                    long between1 = (end1.getTime() - begin1.getTime()) / 1000;//除以1000是为了转换成秒

                    long day2 = between1 / (24 * 3600);

                    long hour2 = between1 % (24 * 3600) / 3600;

                    long minute2 = between1 % 3600 / 60;

                    String time2 = "" + day2 + "天" + hour2 + "小时" + minute2 + "分";

                    conditionsForTheAdjustmentVOSLost.setMonitoringTime(time2);
                }
                ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
            }
        }
        return ConditionsForTheAdjustmentVOLList;
    }

    /**
     * 理赔信息查询
     */
    @Override
    public List<ClaimInformationVo> selectClaimInformation(ClaimInformationDTO claimInformationDTO) {
        //99 结案状态
        //claimInformationDTO.setCaseStatus("99");
        claimInformationDTO.setStatus("Y");
//获取批次号，报案号，按键状态，被保人，证件号  就诊日期  赔付结论
        //还差是否调查  分单号     给付金额  审核人
        List<ClaimInformationVo> caseList = claimCaseMapper.selectClaimInformation(claimInformationDTO);
        for (ClaimInformationVo caseInfo : caseList
        ) { // 是否调查
            ClaimCaseInvestigation claimCaseInvestigation = claimCaseInvestigationMapper.selectClaimCaseInvestigationByIdOne(caseInfo.getRptNo());
            if (null == claimCaseInvestigation) {
                caseInfo.setInvestigation("02");
            } else {
                caseInfo.setInvestigation("01");
            }
            //获取就诊日期
            List<ClaimCaseAccept> claimCaseAccepts = claimCaseAcceptMapper.selectClaimCaseAcceptByIdOne(caseInfo.getRptNo());
            for (ClaimCaseAccept c : claimCaseAccepts
            ) {
                caseInfo.setAccDate(c.getAccDate());
            }
            //获取给i付金额   即账单币种  账单金额
            List<ClaimCaseBill> claimCaseBill = claimCaseBillMapper.selectClaimCaseBillByIdOne(caseInfo.getRptNo());
            for (ClaimCaseBill c : claimCaseBill
            ) {
                caseInfo.setPaymentAmount(c.getPaymentAmount());
            }
            //获取赔付结论
            List<ClaimCaseCal> claimCaseCal = claimCaseCalMapper.selectClaimCaseCalByIdOne(caseInfo.getRptNo());
            for (ClaimCaseCal c : claimCaseCal
            ) {
                caseInfo.setPayConclusion(c.getPayConclusion());
            }
            //获取审核人
          /*  List<ClaimCaseRecord> claimCaseRecords = claimCaseRecordMapper.selectClaimCaseRecordSecondThree(caseInfo.getRptNo());
            for (ClaimCaseRecord c : claimCaseRecords
            ) {
                caseInfo.setUpdateBy(c.getUpdateBy());
            }*/
        }
        return caseList;
    }

    @Override
    public List<ClaimCaseStanding> postHocAccountingOfCases(String batchNo) {
        List<ClaimCaseStanding> ClaimCaseStandingList = new ArrayList<>();
        //根据批次号去查报案号
        List<String> strings = claimCaseMapper.selepostHocAccountingOfCases(batchNo);
        if (strings.size() != 0) {
            for (String rptno : strings) {
                //根据报案号去查台账信息
                ClaimCaseStanding claimCaseStanding = claimCaseStandingMapper.selectClaimCaseStandingByIdOne(rptno);
                if (claimCaseStanding != null) {
                    ClaimCaseStandingList.add(claimCaseStanding);
                }
            }
        }
        return ClaimCaseStandingList;
    }

    /**
     * 判断案件是否符合流程抽检岗规则
     */
    @Override
    public int judgeClaimCaseCheckRule(ClaimCaseCheckDTO claimCaseCheckDTO) {

        ClaimCaseCheckDTO claimCaseCheckDTO1 = claimCaseCheckRuleService.judgeClaimCaseCheckRule(claimCaseCheckDTO);

        claimCaseCheckDTO1.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCheckDTO1.setUpdateTime(DateUtils.getNowDate());

        //更新历史记录,并添加新的历史记录
        ClaimCaseRecord claimCaseRecord = claimCaseRecordMapper.selectClaimCaseRecordByRptNoOperation(claimCaseCheckDTO.getRptNo());
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setOperator(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord);
        ClaimCaseRecord caseRecord = new ClaimCaseRecord();
        caseRecord.setRptNo(claimCaseCheckDTO.getRptNo());
        if (claimCaseCheckDTO1.getCaseStatus().equals("08")){
            caseRecord.setOperation("08");
        }else if (claimCaseCheckDTO1.getCaseStatus().equals("99")){
            caseRecord.setOperation("99");
        }
        caseRecord.setStatus("Y");
        caseRecord.setHistoryFlag("N");
        caseRecord.setOrgRecordId(claimCaseRecord.getRecordId());
        caseRecord.setCreateBy(SecurityUtils.getUsername());
        caseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(caseRecord);

        return claimCaseMapper.updateClaimCaseCheck(claimCaseCheckDTO1);
    }

    //案件抽检已处理
    @Override
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentOverNew(AuditWorkPoolDTO auditWorkPoolDTO) {
        List<ConditionsForTheAdjustmentVO> ConditionsForTheAdjustmentVOLList = new ArrayList<>();
        String batchNo = auditWorkPoolDTO.getBatchNo();
        String rptNo = auditWorkPoolDTO.getRptNo();
        String name = auditWorkPoolDTO.getName();
        if (batchNo == null || "".equals(batchNo)) {
            if (rptNo == null || "".equals(rptNo)) {
                if (name == null || "".equals(name)) {
                    //默认查询一个月的
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                    auditWorkPoolDTO.setUpdateStartTime(calendar.getTime());
                    auditWorkPoolDTO.setUpdateEndTime(DateUtils.parseDate(DateUtils.getTime()));
                    auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());
                    List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.SelectConditionsForTheAdjustmentOverNew(auditWorkPoolDTO);
                    if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
                        for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                            //已经拥有：批次号、报案号、批次状态、被保人姓名、提交用户
                            //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                            //查询案件保单关联表：claim_case_policy，rpt_no
                            List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                            if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                                List<String> organCodeList = new ArrayList<>();//承保机构
                                List<String> companyNameList = new ArrayList<>();//出单公司
                                for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                                    //去重出单公司名称拼接
                                    companyNameList.add(claimCasePoliciesOne.getCompanyName());
                                    //去重承保机构名称拼接
                                    organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                                }

                                StringBuilder stringBuilder = new StringBuilder();
                                Set set = new HashSet<>(organCodeList);
                                List newOrganCodeList = new ArrayList<>(set);
                                if (!newOrganCodeList.isEmpty()) {
                                    stringBuilder.append(newOrganCodeList.get(0));
                                    for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                                        stringBuilder.append("|").append(newOrganCodeList.get(i));
                                    }
                                }
                                conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                                StringBuilder stringBuilder2 = new StringBuilder();
                                Set set1 = new HashSet<>(companyNameList);
                                List newCompanyNameList = new ArrayList<>(set1);
                                if (!newCompanyNameList.isEmpty()) {
                                    stringBuilder2.append(newCompanyNameList.get(0));
                                    for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                                        stringBuilder2.append("|").append(newCompanyNameList.get(i));
                                    }
                                }
                                conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                            }

                            //是否调查
                            ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                            claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                            claimCaseInvestigation.setIsHistory("N");
                            claimCaseInvestigation.setStatus("Y");
                            List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                            if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                                conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                            } else {
                                conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                            }
                            ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
                        }
                    }
                }
            }
        } else {
            //按条件查询
            auditWorkPoolDTO.setOperator(SecurityUtils.getUsername());
            List<ConditionsForTheAdjustmentVO> conditionsForTheAdjustmentVOS = claimCaseMapper.SelectConditionsForTheAdjustmentUnderTwoNew(auditWorkPoolDTO);//查询出处理中的所有的数据
            if (conditionsForTheAdjustmentVOS != null || conditionsForTheAdjustmentVOS.size() != 0) {
                for (ConditionsForTheAdjustmentVO conditionsForTheAdjustmentVOSLost : conditionsForTheAdjustmentVOS) {
                    //已经拥有：批次号、报案号、批次状态、被保人姓名、提交用户
                    //还差：出单公司、承保机构、停留时长、监控时效、是否调查
                    //查询案件保单关联表：claim_case_policy，rpt_no
                    List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                    if (claimCasePolicies != null || claimCasePolicies.size() != 0) {
                        List<String> organCodeList = new ArrayList<>();//承保机构
                        List<String> companyNameList = new ArrayList<>();//出单公司
                        for (ClaimCasePolicy claimCasePoliciesOne : claimCasePolicies) {
                            //去重出单公司名称拼接
                            companyNameList.add(claimCasePoliciesOne.getCompanyName());
                            //去重承保机构名称拼接
                            organCodeList.add(claimCasePoliciesOne.getPolicyManageCom());
                        }

                        StringBuilder stringBuilder = new StringBuilder();
                        Set set = new HashSet<>(organCodeList);
                        List newOrganCodeList = new ArrayList<>(set);
                        if (!newOrganCodeList.isEmpty()) {
                            stringBuilder.append(newOrganCodeList.get(0));
                            for (int i = 1, n = newOrganCodeList.size(); i < n; i++) {
                                stringBuilder.append("|").append(newOrganCodeList.get(i));
                            }
                        }
                        conditionsForTheAdjustmentVOSLost.setOrganCode(stringBuilder.toString());//承保机构-by批次号=organ_code-拼接形式：A｜B

                        StringBuilder stringBuilder2 = new StringBuilder();
                        Set set1 = new HashSet<>(companyNameList);
                        List newCompanyNameList = new ArrayList<>(set1);
                        if (!newCompanyNameList.isEmpty()) {
                            stringBuilder2.append(newCompanyNameList.get(0));
                            for (int i = 1, n = newCompanyNameList.size(); i < n; i++) {
                                stringBuilder2.append("|").append(newCompanyNameList.get(i));
                            }
                        }
                        conditionsForTheAdjustmentVOSLost.setCompanyName(stringBuilder2.toString());  //出单公司companyName-拼接形式：A｜B
                    }

                    //是否调查
                    ClaimCaseInvestigation claimCaseInvestigation = new ClaimCaseInvestigation();
                    claimCaseInvestigation.setRptNo(conditionsForTheAdjustmentVOSLost.getRptNo());
                    claimCaseInvestigation.setIsHistory("N");
                    claimCaseInvestigation.setStatus("Y");
                    List<ClaimCaseInvestigation> claimCaseInvestigations = claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
                    if (claimCaseInvestigations != null && !claimCaseInvestigations.isEmpty()) {//不为空01-是 02-为否
                        conditionsForTheAdjustmentVOSLost.setSurveyCode("01");
                    } else {
                        conditionsForTheAdjustmentVOSLost.setSurveyCode("02");
                    }
                    ConditionsForTheAdjustmentVOLList.add(conditionsForTheAdjustmentVOSLost);
                }
            }
        }
        return ConditionsForTheAdjustmentVOLList;
    }

    @Override
    public BaseCodeMappingNew selectBaseCodeMappingNew(BaseCodeMappingNew baseCodeMappingNew) {
        BaseCodeMappingNew baseCodeMappingNew1 = claimCaseInvestigationMapper.selectBaseCodeMappingNew(baseCodeMappingNew);
        return baseCodeMappingNew1;
    }

    /**
     * 案件交单完成进入受理阶段分配操作人
     */
    /*@Override
    public int updateCaseDist(ClaimBatchDTO claimBatchDTO) {
        ClaimCase claimCase=new ClaimCase();
        //List<ClaimCase> claimCases=claimCaseMapper.selectClaimCaseList(claimCase);
        ClaimCaseDistVo claimCaseDistVo=claimCaseMapper.selectCaseDistNumber(claimBatchDTO.getClaimBatch().getBatchno());
        int i=claimCaseDistVo.getQuantity();//当前批次案子数量
        ClaimCaseDist claimCaseDist=new ClaimCaseDist();
        ClaimCaseDist caseDists=claimCaseDistMapper.selectClaimCaseDistListOne(claimCaseDist);
        double j=caseDists.getRate().doubleValue()/100;//获取当前操作人分配比例
        SysUser sysUser = sysUserMapper.selectUserById(claimCaseDist.getUserId());//根据用户id得到用户名
        for (int x=0;x<=i*j;x++) {
            //c.setUpdateBy(claimCaseDist.getUserId().toString());
            claimCase.setUpdateBy(sysUser.getUserName());
        }
        return claimCaseMapper.updateClaimCase(claimCase);
    }
    @Override
    public int updateOtherCaseDist(String rptNo) {
        ClaimCase claimCase=new ClaimCase();
        ClaimCaseDist claimCaseDist=new ClaimCaseDist();
        ClaimCaseDist caseDists=claimCaseDistMapper.selectClaimCaseDistListOne(claimCaseDist);
        double j=caseDists.getRate().doubleValue()/100;//获取当前操作人分配比例
        ClaimCaseDistVo claimCaseDistVo=claimCaseMapper.selectCaseDistNumberOne(rptNo,claimCase.getCaseStatus());
        int i=claimCaseDistVo.getQuantity();//当前批次案子数量
        SysUser sysUser = sysUserMapper.selectUserById(claimCaseDist.getUserId());//根据用户id得到用户名
        if(j<j*(i+1)){
            claimCase.setUpdateBy(sysUser.getUserName());
        }
        return  claimCaseMapper.updateClaimCase(claimCase);
    }*/
}