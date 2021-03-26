package com.paic.ehis.claimmgt.mapper;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;

import java.util.List;

/**
 * 案件申诉任务Mapper接口
 * 
 * @author sino
 * @date 2021-03-15
 */
public interface ClaimCaseAppealTaskMapper 
{
    /**
     * 查询案件申诉任务
     * 
     * @param param 案件申诉任务I
     * @return 案件申诉任务
     */
    public ClaimCaseAppealTask selectClaimCaseAppealTaskByParam(ClaimCaseAppealTask param);

    /**
     * 查询案件申诉任务列表
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 案件申诉任务集合
     */
    public List<ClaimCaseAppealTask> selectClaimCaseAppealTaskList(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 新增案件申诉任务
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 结果
     */
    public int insertClaimCaseAppealTask(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 修改案件申诉任务
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 结果
     */
    public int updateClaimCaseAppealTask(ClaimCaseAppealTask claimCaseAppealTask);

    /**
     * 删除案件申诉任务
     * 
     * @param taskId 案件申诉任务ID
     * @return 结果
     */
    public int deleteClaimCaseAppealTaskById(Long taskId);

    /**
     * 批量删除案件申诉任务
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseAppealTaskByIds(Long[] taskIds);


    /**
     * 查询案件申诉任务列表
     *
     * @param vo 案件申诉任务
     * @return 案件申诉任务集合
     */
    public List<ClaimCaseAppealTaskVo> getAppealList(ClaimCaseAppealTaskVo vo);

    /**
     * 获取taskId
     * @auhtor: hjw
     * @time:2021-03-15
     * */
    public Long getTaskId();

    /**
     * 获取申诉成功后的报案号
     * @auhtor: hjw
     * @time:2021-03-15
     * */
    public String getAppealNewRptNo(String appealRptNo);

    /**
     * 清空案件修正新RPT_NO 数据
     * @auhtor: hjw
     * @time:2021-03-15
     * */
    public void clearClaimTableData(ClaimCaseAppealTask bean);

    /**
     * 初始化案件修正新RPT_NO 数据
     * @auhtor: hjw
     * @time:2021-03-15
     * 不让用存储过程。。。。。
     * */
   // public void insertClaimTableData(ClaimCaseAppealTask bean);

    /** claim_case */
    public void insertClaimCaseCp(ClaimCaseAppealTask bean);

    /** claim_case_accept */
    public void insertClaimCaseAcceptCp(ClaimCaseAppealTask bean);

    /** claim_case_apply_type */
    public void insertClaimCaseApplyTypeCp(ClaimCaseAppealTask bean);

    /** claim_case_bill */
    public void insertClaimCaseBillCp(ClaimCaseAppealTask bean);

    /** claim_case_cal_bill */
    public void insertClaimCaseCalBillCp(ClaimCaseAppealTask bean);

    /** claim_case_bill_detail */
    public void insertClaimCaseBillDetailCp(ClaimCaseAppealTask bean);

    /** claim_case_bill_diagnosis */
    public void insertClaimCaseBillDiagnosisCp(ClaimCaseAppealTask bean);

    /** claim_case_insured */
    public void insertClaimCaseInsuredCp(ClaimCaseAppealTask bean);

    /** claim_case_payee */
    public void insertClaimCasePayeeCp(ClaimCaseAppealTask bean);

    /** claim_case_register */
    public void insertClaimCaseRegisterCp(ClaimCaseAppealTask bean);

    /** claim_case_remark */
    public void insertClaimCasRemarkeCp(ClaimCaseAppealTask bean);

    /** claim_case_remark type == 02 */
    public void insertClaimCasTypeRemarkeCp(ClaimCaseAppealTask bean);

    /** claim_case_policy */
    public void insertClaimCasePolicyCp(ClaimCaseAppealTask bean);

    /** claim_case_record  00 04 04 32*/
    public void insertClaimCaseRecordCp(ClaimCaseAppealTask bean);

    /** claim_case_record  06 */
    public void insertClaimCaseC06RecordCp(ClaimCaseAppealTask bean);

    /** claim_case_record  07 */
    public void insertClaimCaseRecordCpAutid(ClaimCaseAppealTask bean);

    /** claim_case_investigate_detail */
    public void insertClaimCaseInvestigateDetailCp(ClaimCaseAppealTask bean);

    /** claim_case_investigation */
    public void insertclaimCaseInvestigationCp(ClaimCaseAppealTask bean);



}
