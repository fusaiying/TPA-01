package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.ClaimCaseRecord;

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

    /**
     *
     * @param rptNo
     * @return
     */
    public int updatehistoryFlag(String rptNo);

    /**
     * 删除案件操作记录 
     *
     * @param recordId 案件操作记录 ID
     * @return 结果
     */
    public int deleteClaimCaseRecordById(Long recordId);

    /**
     * 批量删除案件操作记录 
     *
     * @param recordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseRecordByIds(Long[] recordIds);

    /**
     * 新增案件操作记录
     *
     * @param claimCaseRecord 案件操作记录
     * @return 结果
     */
    public int insertClaimCaseRecordSecond(ClaimCaseRecord claimCaseRecord);

    /**
     *
     * @param recordId
     * @return
     */
    public Long selectClaimCaseRecordSecondTwo(String recordId);

    /**
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseRecord selectClaimCaseRecordByrptNoOne(String rptNo);

    /**
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseRecord selectClaimCaseRecordByrptNoTwo(String rptNo);

    /**
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseRecord selectClaimCaseRecordByrptNoThree(String rptNo);

    /** 抽检处理中停留时间查询 */
    public ClaimCaseRecord selectClaimCaseRecordByrptNoCase(String rptNo);

    /**
     * 查询监控时效，当前时间-最早受理结束时间
     *
     * @param rptNo
     * @return
     */
    public String selectMinAcceptTime(String rptNo);

    /**
     * 根据报案号和流程节点更新轨迹表
     *
     * @param claimCaseRecord
     * @return
     */
    public int updateClaimCaseRecordByRptNoAndOperator(ClaimCaseRecord claimCaseRecord);

    /**
     * 修改案件调度流程节点操作人
     * @param rptNo
     * @return
     */
    int  updateCaseDispatchList(String rptNo);

    /**
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseRecord selectClaimCaseRecordByrptNoFour(String rptNo);

    /**
     *
     * @param rptNo
     * @return
     */
    public List<ClaimCaseRecord> selectClaimCaseRecordSecondThree(String rptNo);

    //案件抽检处理页面退回审核
    public ClaimCaseRecord selectClaimCaseRecordByrptNoOneNew(String rptNo);

    //查询所有案件状态为：问题件的案件信息-30，全部置为无效
    public List<ClaimCaseRecord> selectClaimCaseRecordProblemShipment(String rptNo);
    //查询所有案件状态为：06和07的案件，全部置为无效
    public List<ClaimCaseRecord> selectClaimCaseRecordAcceptance(String rptNo);
    //查询所有案件状态为：97和98的案件，全部置为无效
    public List<ClaimCaseRecord> selectClaimCaseRecordRevocation(String rptNo);
}
