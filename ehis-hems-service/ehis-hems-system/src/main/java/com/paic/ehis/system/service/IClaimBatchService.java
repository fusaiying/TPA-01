package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimBatch;
import com.paic.ehis.system.domain.StandingAndBatck;
import com.paic.ehis.system.domain.dto.BatchDTO;
import com.paic.ehis.system.domain.dto.BatchRecordDTO;
import com.paic.ehis.system.domain.dto.ClaimBatchDTO;
import com.paic.ehis.system.domain.vo.BatchVo;

/**
 * 理赔批次 Service接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface IClaimBatchService 
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
     * 查询直结复核理赔批次公共池 列表
     *
     * @param batchRecordDTO 理赔批次
     * @return 交单复核理赔批次 集合
     */
    public List<BatchVo> selectReviewPublicList(BatchRecordDTO batchRecordDTO);
    /**
     * 查询直结复核理赔批次待处理个人池 列表
     *
     * @param
     * @return 交单复核理赔批次 集合
     */
    public List<BatchVo> selectUntreatedPersonalList();
    /**
     * 查询直结复核理赔批次已处理个人池 列表
     *
     * @param
     * @return 交单复核理赔批次 集合
     */
    public List<BatchVo> selectProcessedPersonalList();

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
     * @param claimBatchDTO 理赔批次
     * @return 结果
     */
    public int updateClaimBatch(ClaimBatchDTO claimBatchDTO);//

    public ClaimBatch updateClaimBatchTwo(ClaimBatch claimBatch);//

    /**
     * 修改理赔批次
     *
     * @param batchno 理赔批次
     * @return 结果
     */
    public int updateClaimBatchstatus (String batchno);

    /**
     * 批量删除理赔批次 
     * 
     * @param batchNos 需要删除的理赔批次 ID
     * @return 结果
     */
    public int deleteClaimBatchByIds(String[] batchNos);

    /**
     * 删除理赔批次 信息
     * 
     * @param batchNo 理赔批次 ID
     * @return 结果
     */
    public int deleteClaimBatchById(String batchNo);

    /********************************************************/
    /**事后
     * 新增理赔批次
     *
     * @param  standingAndBatck 理赔批次
     * @return 结果
     */
    public StandingAndBatck insertSysClaimBatch(StandingAndBatck standingAndBatck);//

    /**直结
     * 新增理赔批次
     *
     * @param claimBatch 理赔批次
     * @return 结果
     */
    public ClaimBatch insertSysClaimBatchTwo(ClaimBatch claimBatch);//

    /**
     * 修改理赔批次
     *
     * @param standingAndBatck 理赔批次
     * @return 结果
     */
    public StandingAndBatck updateSysClaimBatch(StandingAndBatck standingAndBatck);//

    public StandingAndBatck updateSysClaimBatchPresent(StandingAndBatck standingAndBatck);//
}
