package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimBatchRecord;

/**
 * 理赔批次流程记录 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface ClaimBatchRecordMapper 
{
    /**
     * 查询理赔批次流程记录 
     * 
     * @param recordid 理赔批次流程记录 ID
     * @return 理赔批次流程记录 
     */
    public ClaimBatchRecord selectClaimBatchRecordById(Long recordid);

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
     * @param claimBatchRecord 理赔批次流程id数组
     * @return 结果
     */
    public int updateClaimBatchRecordByReview(ClaimBatchRecord claimBatchRecord);


    /**
     * 删除理赔批次流程记录 
     * 
     * @param recordId 理赔批次流程记录 ID
     * @return 结果
     */
    public int deleteClaimBatchRecordById(Long recordId);

    /**
     * 批量删除理赔批次流程记录 
     * 
     * @param recordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimBatchRecordByIds(Long[] recordIds);

    /******************************************/
    /**
     *
     * @param batchno'批次号'
     * @return
     */
    public List<ClaimBatchRecord> selectSysClaimBatchRecordListByBatchno(String batchno);
}
