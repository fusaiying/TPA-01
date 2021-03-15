package com.paic.ehis.claimmgt.service.impl;

import java.util.List;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealRecord;
import com.paic.ehis.claimmgt.mapper.ClaimCaseAppealRecordMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseAppealRecordService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件申诉任务记录Service业务层处理
 * 
 * @author sino
 * @date 2021-03-15
 */
@Service
public class ClaimCaseAppealRecordServiceImpl implements IClaimCaseAppealRecordService
{
    @Autowired
    private ClaimCaseAppealRecordMapper claimCaseAppealRecordMapper;

    /**
     * 查询案件申诉任务记录
     * 
     * @param recordId 案件申诉任务记录ID
     * @return 案件申诉任务记录
     */
    @Override
    public ClaimCaseAppealRecord selectClaimCaseAppealRecordById(Long recordId)
    {
        return claimCaseAppealRecordMapper.selectClaimCaseAppealRecordById(recordId);
    }

    /**
     * 查询案件申诉任务记录列表
     * 
     * @param claimCaseAppealRecord 案件申诉任务记录
     * @return 案件申诉任务记录
     */
    @Override
    public List<ClaimCaseAppealRecord> selectClaimCaseAppealRecordList(ClaimCaseAppealRecord claimCaseAppealRecord)
    {
        return claimCaseAppealRecordMapper.selectClaimCaseAppealRecordList(claimCaseAppealRecord);
    }

    /**
     * 新增案件申诉任务记录
     * 
     * @param claimCaseAppealRecord 案件申诉任务记录
     * @return 结果
     */
    @Override
    public int insertClaimCaseAppealRecord(ClaimCaseAppealRecord claimCaseAppealRecord)
    {
        claimCaseAppealRecord.setCreateTime(DateUtils.getNowDate());
        return claimCaseAppealRecordMapper.insertClaimCaseAppealRecord(claimCaseAppealRecord);
    }

    /**
     * 修改案件申诉任务记录
     * 
     * @param claimCaseAppealRecord 案件申诉任务记录
     * @return 结果
     */
    @Override
    public int updateClaimCaseAppealRecord(ClaimCaseAppealRecord claimCaseAppealRecord)
    {
        claimCaseAppealRecord.setUpdateTime(DateUtils.getNowDate());
        return claimCaseAppealRecordMapper.updateClaimCaseAppealRecord(claimCaseAppealRecord);
    }

    /**
     * 批量删除案件申诉任务记录
     * 
     * @param recordIds 需要删除的案件申诉任务记录ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAppealRecordByIds(Long[] recordIds)
    {
        return claimCaseAppealRecordMapper.deleteClaimCaseAppealRecordByIds(recordIds);
    }

    /**
     * 删除案件申诉任务记录信息
     * 
     * @param recordId 案件申诉任务记录ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAppealRecordById(Long recordId)
    {
        return claimCaseAppealRecordMapper.deleteClaimCaseAppealRecordById(recordId);
    }
}
