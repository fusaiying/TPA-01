package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseRecord;
import com.paic.ehis.claimflow.domain.dto.*;
import com.paic.ehis.claimflow.domain.vo.*;

import java.util.List;

/**
 * 案件信息 Mapper接口
 *
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseMapper
{
    /**
     * 查询案件信息 
     *
     * @param rptNo 案件信息 ID
     * @return 案件信息
     */
    public ClaimCase selectClaimCaseById(String rptNo);

    /**
     * 查询案件信息 列表
     *
     * @param claimCase 案件信息
     * @return 案件信息 集合
     */
    public List<ClaimCase> selectClaimCaseList(ClaimCase claimCase);

    /**
     * 查询处理中案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    public List<ProcessingCaseVo> selectProcessingClaimCaseList(ClaimCaseDTO claimCaseDTO);

    /**
     * 查询已处理受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    public List<ClaimCase> selectClaimCaseProcessedList(ClaimCaseDTO claimCaseDTO);

    /**
     * 新增案件信息 
     *
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int insertClaimCase(ClaimCase claimCase);

    /**
     * 修改案件信息 
     *
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int updateClaimCase(ClaimCase claimCase);

    /**
     *
     * @param batchNo
     * @return
     */
    public int updateClaimCaseVo(String batchNo);

    /**
     *
     * @param repNo
     * @return
     */
    public int updatecaseStatus(String repNo);



    /**
     * 删除案件信息 
     *
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseById(String rptNo);

    /**
     * 批量删除案件信息 
     *
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseByIds(String[] rptNos);


    public ClaimAndBatchVo selectClaimClaimAndBatchById(String rptNo);

    /**
     * 案件调度查询
     * @param dispatchDTO
     * @return
     */
    List<CaseDispatchVO> selectCaseDispatchList(DispatchDTO dispatchDTO);

    /**
     * 修改案件调度流程节点操作人
     * @param claimCase
     * @return
     */
    int  updateCaseDispatchList(ClaimCase claimCase);

    // List<DispatchPolicyVo>  selectPolicyDispatchList(String policyNo);


    //条件理算审核

    /**
     *查询待处理
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentUnder(AuditWorkPoolDTO auditWorkPoolDTO);//待处理

    /**
     *默认查询一个月的
     *
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentOver(AuditWorkPoolDTO auditWorkPoolDTO);//已处理

    /**按条件查询
     *
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentUnderTwo(AuditWorkPoolDTO auditWorkPoolDTO);//已处理

    /**
     *查询悬挂
     *
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentHang(AuditWorkPoolDTO auditWorkPoolDTO);//悬挂

    /**
     * 查询录入工作池处理中的案件信息，包括批次号、报案号、案件状态、被保人姓名、停留时长、提交用户
     *
     * @param claimCaseDTO
     * @return
     */
    public List<BillProcessingVo> selectBillProcessing(ClaimCaseDTO claimCaseDTO);

    /** 抽检处理中停留时间查询 */
    public ClaimCase selectClaimCaseRecordByrptNoCase(String rptNo);

    /**
     * 查询录入工作池已处理的案件信息，包括批次号、报案号、案件状态、被保人姓名、提交时长
     *
     * @param claimCaseDTO
     * @return
     */
    public List<BillAccomplishVo> selectBillAccomplish(ClaimCaseDTO claimCaseDTO);

    /**
     * 理赔信息查询
     */
    public List<ClaimInformationVo>  selectClaimInformation(ClaimInformationDTO claimInformationDTO);

    /** 查询案件归档信息 */
    public String selectCaseClaimCaseFilingList(ClaimCaseFilingListVO claimCaseFilingListVO);

    /**
     *
     * @param batchNo
     * @return
     */
    public List<String>  selepostHocAccountingOfCases(String batchNo);

    int updateClaimCaseCheck(ClaimCaseCheckDTO claimCaseCheckDTO);

    //抽检待处理
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentUnderNew(AuditWorkPoolDTO auditWorkPoolDTO);//待处理
    //抽检已处理
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentUnderTwoNew(AuditWorkPoolDTO auditWorkPoolDTO);//已处理
    //抽检已处理
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentOverNew(AuditWorkPoolDTO auditWorkPoolDTO);//已处理

    /**
     * 计算案子数量+
     */
    ClaimCaseDistVo selectCaseDistNumber(String batchNo);
    ClaimCaseDistVo selectCaseDistNumberOne(String rptNo,String caseStatus);
    /** 见未被使用-flint-List<ClaimCase> */
    public List<ProcessingCaseVo> selectCaseOne(ClaimCase claimCase);
}
