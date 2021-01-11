package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.ClaimBatchMapper;
import com.paic.ehis.system.domain.ClaimBatch;
import com.paic.ehis.system.service.IClaimBatchService;

/**
 * 理赔批次 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimBatchServiceImpl implements IClaimBatchService 
{
    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    /**
     * 查询理赔批次 
     * 
     * @param batchNo 理赔批次 ID
     * @return 理赔批次 
     */
    @Override
    public ClaimBatch selectClaimBatchById(String batchNo)
    {
        return claimBatchMapper.selectClaimBatchById(batchNo);
    }

    /**
     * 查询理赔批次 列表
     * 
     * @param claimBatch 理赔批次 
     * @return 理赔批次 
     */
    @Override
    public List<ClaimBatch> selectClaimBatchList(ClaimBatch claimBatch)
    {
        return claimBatchMapper.selectClaimBatchList(claimBatch);
    }

    /**
     * 新增理赔批次 
     * 
     * @param claimBatch 理赔批次 
     * @return 结果
     */
    @Override
    public int insertClaimBatch(ClaimBatch claimBatch)
    {
        claimBatch.setCreateTime(DateUtils.getNowDate());
        return claimBatchMapper.insertClaimBatch(claimBatch);
    }

    /**
     * 修改理赔批次 
     * 
     * @param claimBatch 理赔批次 
     * @return 结果
     */
    @Override
    public int updateClaimBatch(ClaimBatch claimBatch)
    {
        claimBatch.setUpdateTime(DateUtils.getNowDate());
        return claimBatchMapper.updateClaimBatch(claimBatch);
    }

    /**
     * 批量删除理赔批次 
     * 
     * @param batchNos 需要删除的理赔批次 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchByIds(String[] batchNos)
    {
        return claimBatchMapper.deleteClaimBatchByIds(batchNos);
    }

    /**
     * 删除理赔批次 信息
     * 
     * @param batchNo 理赔批次 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchById(String batchNo)
    {
        return claimBatchMapper.deleteClaimBatchById(batchNo);
    }
}
