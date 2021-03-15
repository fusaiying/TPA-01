package com.paic.ehis.claimmgt.service;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealRecord;

import java.util.List;

/**
 * 案件申诉任务记录Service接口
 * 
 * @author sino
 * @date 2021-03-15
 */
public interface IClaimCaseAppealRecordService 
{
    /**
     * 查询案件申诉任务记录
     * 
     * @param recordId 案件申诉任务记录ID
     * @return 案件申诉任务记录
     */
    public ClaimCaseAppealRecord selectClaimCaseAppealRecordById(Long recordId);

    /**
     * 查询案件申诉任务记录列表
     * 
     * @param claimCaseAppealRecord 案件申诉任务记录
     * @return 案件申诉任务记录集合
     */
    public List<ClaimCaseAppealRecord> selectClaimCaseAppealRecordList(ClaimCaseAppealRecord claimCaseAppealRecord);

    /**
     * 新增案件申诉任务记录
     * 
     * @param claimCaseAppealRecord 案件申诉任务记录
     * @return 结果
     */
    public int insertClaimCaseAppealRecord(ClaimCaseAppealRecord claimCaseAppealRecord);

    /**
     * 修改案件申诉任务记录
     * 
     * @param claimCaseAppealRecord 案件申诉任务记录
     * @return 结果
     */
    public int updateClaimCaseAppealRecord(ClaimCaseAppealRecord claimCaseAppealRecord);

    /**
     * 批量删除案件申诉任务记录
     * 
     * @param recordIds 需要删除的案件申诉任务记录ID
     * @return 结果
     */
    public int deleteClaimCaseAppealRecordByIds(Long[] recordIds);

    /**
     * 删除案件申诉任务记录信息
     * 
     * @param recordId 案件申诉任务记录ID
     * @return 结果
     */
    public int deleteClaimCaseAppealRecordById(Long recordId);
}
