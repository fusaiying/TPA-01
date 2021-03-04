package com.paic.ehis.claimapt.mapper;


import com.paic.ehis.claimapt.domain.ClaimBatch;
import com.paic.ehis.claimapt.domain.DTO.BatchDTO;
import com.paic.ehis.claimapt.domain.DTO.BatchRecordDTO;
import com.paic.ehis.claimapt.domain.DTO.ClaimCasePayDTO;
import com.paic.ehis.claimapt.domain.Vo.BatchVo;

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
    public List<BatchVo> selectDirectQueryList(BatchDTO batchDTO);

    /**
     * 查询已退回理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    public List<BatchVo> selectDirectQueryListTwo(BatchDTO batchDTO);


    /**
     * 查询已退回理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    public List<BatchVo> selectReturnedBatchList(BatchDTO batchDTO);

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

    /**
     * 对公支付首页初始化查询
     *
     * @param deptId
     * @return
     */
    public List<Map<String,Object>> selectPayBatchInit(String deptId);

    /**
     * 查询对公支付的批次信息
     *
     * @param claimCasePayDTO
     * @return
     */
    public List<Map<String,Object>> selectPayBatchList(ClaimCasePayDTO claimCasePayDTO);

    /**
     * 部门ID下币种不为人民币、支付状态不全为已支付状态的
     * @param deptId
     * @return
     */
    public List<Map<String,Object>> selectPayForeignBatchInit(String deptId);

    /**
     * 对公外币支付-查询
     *
     * @param claimCasePayDTO
     * @return
     */
    public List<Map<String,Object>> selectPayForeignBatchList(ClaimCasePayDTO claimCasePayDTO);

    /**
     * 查询理赔批次
     *
     * @param batchNo 理赔批次 ID
     * @return 理赔批次
     */
    public ClaimBatch selectClaimBatchByBatchNo(String batchNo);

}
