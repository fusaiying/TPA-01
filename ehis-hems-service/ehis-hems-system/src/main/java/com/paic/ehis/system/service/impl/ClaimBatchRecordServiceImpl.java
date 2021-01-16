package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimBatchRecord;
import com.paic.ehis.system.mapper.ClaimBatchRecordMapper;
import com.paic.ehis.system.service.IClaimBatchRecordService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 理赔批次流程记录 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimBatchRecordServiceImpl implements IClaimBatchRecordService
{
    @Autowired
    private ClaimBatchRecordMapper claimBatchRecordMapper;

    /**
     * 查询理赔批次流程记录 
     * 
     * @param recordId 理赔批次流程记录 ID
     * @return 理赔批次流程记录 
     */
    @Override
    public ClaimBatchRecord selectClaimBatchRecordById(Long recordId)
    {
        return claimBatchRecordMapper.selectClaimBatchRecordById(recordId);
    }

    /**
     * 查询理赔批次流程记录 列表
     * 
     * @param claimBatchRecord 理赔批次流程记录 
     * @return 理赔批次流程记录 
     */
    @Override
    public List<ClaimBatchRecord> selectClaimBatchRecordList(ClaimBatchRecord claimBatchRecord)
    {
        return claimBatchRecordMapper.selectClaimBatchRecordList(claimBatchRecord);
    }

    /**
     * 新增理赔批次流程记录 
     * 
     * @param claimBatchRecord 理赔批次流程记录 
     * @return 结果
     */
    @Override
    public int insertClaimBatchRecord(ClaimBatchRecord claimBatchRecord)
    {
        claimBatchRecord.setCreateTime(DateUtils.getNowDate());
        return claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
    }

    /**
     * 修改理赔批次流程记录 
     * 
     * @param claimBatchRecord 理赔批次流程记录 
     * @return 结果
     */
    @Override
    public int updateClaimBatchRecord(ClaimBatchRecord claimBatchRecord)
    {
        claimBatchRecord.setUpdateTime(DateUtils.getNowDate());
        return claimBatchRecordMapper.updateClaimBatchRecord(claimBatchRecord);
    }

    /**
     * 交单复核个人池获取案件
     *
     * @param batchnoes 理赔批次流程id数组
     * @return 结果
     */
    @Override
    @Transactional
    public int updateClaimBatchByReview(String[] batchnoes) {
        int i = 0;
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        claimBatchRecord.setOperation("04");
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.getNowDate());
        for (String batchno : batchnoes) {
            claimBatchRecord.setBatchno(batchno);
            i = claimBatchRecordMapper.updateClaimBatchRecordByReview(claimBatchRecord);
        }
        return i;
    }

    /**
     * 批量删除理赔批次流程记录 
     * 
     * @param recordIds 需要删除的理赔批次流程记录 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchRecordByIds(Long[] recordIds)
    {
        return claimBatchRecordMapper.deleteClaimBatchRecordByIds(recordIds);
    }

    /**
     * 删除理赔批次流程记录 信息
     * 
     * @param recordId 理赔批次流程记录 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchRecordById(Long recordId)
    {
        return claimBatchRecordMapper.deleteClaimBatchRecordById(recordId);
    }

    /************************************************/
    /**
     *
     * @param batchno'批次号'
     * @return
     */
    @Override
    public List<ClaimBatchRecord> selectSysClaimBatchRecordListByBatchno(String batchno) {
        return claimBatchRecordMapper.selectSysClaimBatchRecordListByBatchno(batchno);
    }
}
