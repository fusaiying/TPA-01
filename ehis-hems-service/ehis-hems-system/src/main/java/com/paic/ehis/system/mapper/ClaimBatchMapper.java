package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimBatch;

/**
 * 理赔批次 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface ClaimBatchMapper 
{
    /**
     * 查询理赔批次 
     * 
     * @param batchNo 理赔批次 ID
     * @return 理赔批次 
     */
    public ClaimBatch selectClaimBatchById(String batchNo);

    /**
     * 查询理赔批次 列表
     * 
     * @param claimBatch 理赔批次 
     * @return 理赔批次 集合
     */
    public List<ClaimBatch> selectClaimBatchList(ClaimBatch claimBatch);

    /**
     * 新增理赔批次 
     * 
     * @param claimBatch 理赔批次 
     * @return 结果
     */
    public int insertClaimBatch(ClaimBatch claimBatch);

    /**
     * 修改理赔批次 
     * 
     * @param claimBatch 理赔批次 
     * @return 结果
     */
    public int updateClaimBatch(ClaimBatch claimBatch);

    /**
     * 删除理赔批次 
     * 
     * @param batchNo 理赔批次 ID
     * @return 结果
     */
    public int deleteClaimBatchById(String batchNo);

    /**
     * 批量删除理赔批次 
     * 
     * @param batchNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimBatchByIds(String[] batchNos);
}
