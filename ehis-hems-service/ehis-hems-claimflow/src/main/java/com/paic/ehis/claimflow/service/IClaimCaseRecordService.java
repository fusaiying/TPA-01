package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimflow.domain.ClaimCaseRecord;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseStandingVo;

import java.util.List;

/**
 * 案件操作记录 Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */

public interface IClaimCaseRecordService 
{
    /**
     * 查询案件操作记录 
     * 
     * @param recordId 案件操作记录 ID
     * @return 案件操作记录 
     */
    public ClaimCaseRecord selectClaimCaseRecordById(Long recordId);

    /**
     * 查询案件操作记录 列表
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 案件操作记录 集合
     */
    public List<ClaimCaseRecord> selectClaimCaseRecordList(ClaimCaseRecord claimCaseRecord);

    /**
     * 新增案件操作记录 
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 结果
     */
    public int insertClaimCaseRecord(ClaimCaseRecord claimCaseRecord);

    /**
     * 修改案件操作记录 
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 结果
     */
    public int updateClaimCaseRecord(ClaimCaseRecord claimCaseRecord);

    public int updatehistoryFlag(String rptNo);

    /**
     * 批量删除案件操作记录 
     * 
     * @param recordIds 需要删除的案件操作记录 ID
     * @return 结果
     */
    public int deleteClaimCaseRecordByIds(Long[] recordIds);

    /**
     * 删除案件操作记录 信息
     * 
     * @param recordId 案件操作记录 ID
     * @return 结果
     */
    public int deleteClaimCaseRecordById(Long recordId);

    /**
     * 根据报案号和流程节点更新轨迹表
     * @param claimCaseRecord
     * @return
     */
    public int updateClaimCaseRecordByRptNoAndOperator(ClaimCaseRecord claimCaseRecord);

    /**
     * 机构交单-事后-新增案件操作记录
     *
     * @param claimCaseStandingVo 案件操作记录
     * @return 结果
     */
    public int insertClaimCaseRecordAndBatchRecord(ClaimCaseStandingVo claimCaseStandingVo);
}
