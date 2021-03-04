package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.ClaimCaseRecord;

import java.util.List;

/**
 * 案件操作记录 Mapper接口
 *
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseRecordMapper
{
    /**
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseRecord selectClaimCaseRecordByrptNoOne(String rptNo);

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
     * 根据报案号和流程节点更新轨迹表
     *
     * @param claimCaseRecord
     * @return
     */
    public int updateClaimCaseRecordByRptNoAndOperator(ClaimCaseRecord claimCaseRecord);

}
