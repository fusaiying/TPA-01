package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimBatchRecord;

/**
 * 理赔批次流程记录 Service接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface IClaimBatchRecordService 
{
    /**
     * 查询理赔批次流程记录 
     * 
     * @param recordId 理赔批次流程记录 ID
     * @return 理赔批次流程记录 
     */
    public ClaimBatchRecord selectClaimBatchRecordById(Long recordId);

    /**
     * 查询理赔批次流程记录 列表
     * 
     * @param claimBatchRecord 理赔批次流程记录 
     * @return 理赔批次流程记录 集合
     */
    public List<ClaimBatchRecord> selectClaimBatchRecordList(ClaimBatchRecord claimBatchRecord);

    /**
     * 新增理赔批次流程记录 
     * 
     * @param claimBatchRecord 理赔批次流程记录 
     * @return 结果
     */
    public int insertClaimBatchRecord(ClaimBatchRecord claimBatchRecord);

    /**
     * 修改理赔批次流程记录 
     * 
     * @param claimBatchRecord 理赔批次流程记录 
     * @return 结果
     */
    public int updateClaimBatchRecord(ClaimBatchRecord claimBatchRecord);

    /**
     * 交单复核个人池获取案件
     *
     * @param batchnoes 理赔批次流程id数组
     * @return 结果
     */
    public int updateClaimBatchByReview(String[] batchnoes);

    /**
     * 批量删除理赔批次流程记录 
     * 
     * @param recordIds 需要删除的理赔批次流程记录 ID
     * @return 结果
     */
    public int deleteClaimBatchRecordByIds(Long[] recordIds);

    /**
     * 删除理赔批次流程记录 信息
     * 
     * @param recordId 理赔批次流程记录 ID
     * @return 结果
     */
    public int deleteClaimBatchRecordById(Long recordId);

    /**********************************************/
    /**
     *
     * @param batchno'批次号'
     * @return
     */
    public List<ClaimBatchRecord> selectSysClaimBatchRecordListByBatchno(String batchno);//
}
