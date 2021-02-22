package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimBatch;
import com.paic.ehis.claimflow.domain.dto.BatchDTO;
import com.paic.ehis.claimflow.domain.dto.BatchRecordDTO;
import com.paic.ehis.claimflow.domain.vo.BatchVo;

import java.util.List;
import java.util.Map;

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
     * 查询已退回理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    public List<BatchVo> selectBackToBatchList(BatchDTO batchDTO);

    /**
     * 查询已处理理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    public List<BatchVo> selectDealWithBatchList(BatchDTO batchDTO);

    /**
     * 查询交单复核理赔批次 列表
     *
     * @param batchRecordDTO 理赔批次
     * @return 理赔批次 集合
     */
    public List<BatchVo> selectStraightAndReview(BatchRecordDTO batchRecordDTO);


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
    public int updateClaimBatchInvoice(String BatchNo);

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

    /**
     * 案件调度查询
     */
    List<ClaimBatch> selectClaimBatchByIdOne(String rptNo);

}
