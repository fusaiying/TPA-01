package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimapt.domain.DTO.ClaimBatchDTO;
import com.paic.ehis.claimflow.domain.BaseCodeMappingNew;
import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseStanding;
import com.paic.ehis.claimflow.domain.dto.*;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.common.core.web.domain.AjaxResult;

import java.util.List;

/**
 * 案件信息 Service接口
 *
 * @author sino
 * @date 2021-01-09
 */
public interface IClaimCaseService
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
    public List<ClaimCase> selectProcessedClaimCaseList(ClaimCaseDTO claimCaseDTO);
    /**
     * 查询悬挂中受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    public List<ProcessingCaseVo> selectSuspensionClaimCaseList(ClaimCaseDTO claimCaseDTO);

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

    public int updatecaseStatus(String repNo);

    /**
     * 批量删除案件信息 
     *
     * @param rptNos 需要删除的案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseByIds(String[] rptNos);

    /**
     * 删除案件信息 信息
     *
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseById(String rptNo);

    /**
     * 获取案件信息 详细信息（多表关联查询batch和Claim表）
     * @param rptNo
     * @return 结果
     */
    public ClaimAndBatchVo selectClaimClaimAndBatchById(String rptNo);

    /**
     * 问题件按钮（子任务添加功能为完成）
     *  修改案件信息
     * @param claimCaseProblemDTO
     * @return
     */
    public int updateCaseInfoSuspend(ClaimCaseProblemDTO claimCaseProblemDTO);

    /**
     * 受理确认按钮功能
     * 修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseAndRecordInfoSuspend(ClaimCase claimCase);

    /**
     * 撤件弹框确认按钮
     * 修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseAndRecordInfoCancel(ClaimCase claimCase);

    /**
     * 查询案件调度工作池
     * @param
     * @return
     */
    //List<ClaimCase> selectCaseDispatchList(ClaimCase claimCase);
    List<CaseDispatchVO> selectCaseDispatchList(DispatchDTO dispatchDTO);

    /**
     *
     * @param rptNo
     * @return
     */
    public int updateClaimCase(String rptNo);


    /**条件理算审核
     * 待处理
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentUnder(AuditWorkPoolDTO auditWorkPoolDTO);//待处理

    /**条件理算审核
     * 已处理
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentOver(AuditWorkPoolDTO auditWorkPoolDTO);//已处理

    /**条件理算审核
     * 悬挂
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> selectConditionsForTheAdjustmentHang(AuditWorkPoolDTO auditWorkPoolDTO);//悬挂

    /** 抽检完毕，修改案件状态 */
    int editCaseCheck(ClaimCase claimCase);

    /** 退回后流转至该案件理算审核环节操作人处理中工作池 */
    int editCaseCheckBack(ClaimCase claimCase);

    /** 抽检工作池处理中 */
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentUnderCase(AuditWorkPoolDTO auditWorkPoolDTO);//待处理
    /**
     * 审核退回
     *
     * @param claimCase 案件信息
     * @return 结果
     */
    public int backToClaimCase(ClaimCase claimCase);

    /**
     * 审核完毕前验证重设与否
     *
     * @param reviewCompletedDTO 案件信息
     * @return 结果
     */
    public AjaxResult verifyReset(ReviewCompletedDTO reviewCompletedDTO);

    /**
     * 审核完毕前验证帐单币种
     *
     * @param reviewCompletedDTO 案件信息
     * @return 结果
     */
    public AjaxResult verifyBillCurrency(ReviewCompletedDTO reviewCompletedDTO);

    /**
     * 审核完毕
     *
     * @param claimCase 案件信息
     * @return 结果
     */
    public int reviewCompletedClaimCase(ClaimCase claimCase );

    /**
     * 提调
     *
     * @param claimCase 案件信息
     * @return 结果
     */
    public int raiseClaimCase(ClaimCase claimCase);

    /**
     * 理赔信息查询
     * @param claimInformationDTO
     * @return
     */
    List<ClaimInformationVo> selectClaimInformation(ClaimInformationDTO claimInformationDTO);

    /**
     *
     * @param batchNo
     * @return
     */
    public List<ClaimCaseStanding> postHocAccountingOfCases(String batchNo);

    /** 判断案件是否符合流程抽检岗规则 */
    int judgeClaimCaseCheckRule(ClaimCaseCheckDTO claimCaseCheckDTO);

    /**
     *
     * @param auditWorkPoolDTO
     * @return
     */
    public List<ConditionsForTheAdjustmentVO> SelectConditionsForTheAdjustmentOverNew(AuditWorkPoolDTO auditWorkPoolDTO);//已处理

    /**
     *用户调度更改操作人
     */
    //public int updateClaimCaseVo(ClaimCaseDistVo claimCaseDistVo);

    public BaseCodeMappingNew selectBaseCodeMappingNew(BaseCodeMappingNew baseCodeMappingNew);

    /**
     * 案件交单完成进入受理阶段分配操作人
     */
    //public  int updateCaseDist(ClaimBatchDTO claimBatchDTO);
    /**
     除受理阶段其余阶段分配操作人
     */
   // public int updateOtherCaseDist(String rptNo);
}
