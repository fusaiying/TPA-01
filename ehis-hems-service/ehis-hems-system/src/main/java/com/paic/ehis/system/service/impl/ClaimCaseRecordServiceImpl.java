package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseRecord;
import com.paic.ehis.system.mapper.ClaimCaseRecordMapper;
import com.paic.ehis.system.service.IClaimCaseRecordService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件操作记录 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseRecordServiceImpl implements IClaimCaseRecordService
{
    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    /**
     * 查询案件操作记录 
     * 
     * @param recordId 案件操作记录 ID
     * @return 案件操作记录 
     */
    @Override
    public ClaimCaseRecord selectClaimCaseRecordById(Long recordId)
    {
        return claimCaseRecordMapper.selectClaimCaseRecordById(recordId);
    }

    /**
     * 查询案件操作记录 列表
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 案件操作记录 
     */
    @Override
    public List<ClaimCaseRecord> selectClaimCaseRecordList(ClaimCaseRecord claimCaseRecord)
    {
        return claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
    }

    /**
     * 新增案件操作记录 
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 结果
     */
    @Override
    public int insertClaimCaseRecord(ClaimCaseRecord claimCaseRecord)
    {
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        return claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
    }

    /**
     * 修改案件操作记录 
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 结果
     */
    @Override
    public int updateClaimCaseRecord(ClaimCaseRecord claimCaseRecord)
    {
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        return claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord);
    }

    /**
     * 批量删除案件操作记录 
     * 
     * @param recordIds 需要删除的案件操作记录 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRecordByIds(Long[] recordIds)
    {
        return claimCaseRecordMapper.deleteClaimCaseRecordByIds(recordIds);
    }

    /**
     * 删除案件操作记录 信息
     * 
     * @param recordId 案件操作记录 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRecordById(Long recordId)
    {
        return claimCaseRecordMapper.deleteClaimCaseRecordById(recordId);
    }
}
