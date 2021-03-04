package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.claimflow.domain.ClaimCaseRecord;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseStandingVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseRecordMapper;
import com.paic.ehis.claimflow.service.IClaimCaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件操作记录 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseRecordServiceImpl implements IClaimCaseRecordService 
{
    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    /**
     * 查询案件操作记录 
     * 
     * @param recordId 案件操作记录 ID
     * @return 案件操作记录 
     */
    @Override
    public ClaimCaseRecord selectClaimCaseRecordById(Long recordId)
    {
        return claimCaseRecordMapper.selectClaimCaseRecordById(recordId);
    }

    /**
     * 查询案件操作记录 列表
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 案件操作记录 
     */
    @Override
    public List<ClaimCaseRecord> selectClaimCaseRecordList(ClaimCaseRecord claimCaseRecord)
    {
        return claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
    }

    /**
     * 新增案件操作记录 
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 结果
     */
    @Override
    public int insertClaimCaseRecord(ClaimCaseRecord claimCaseRecord)
    {
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        return claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
    }

    /**
     * 修改案件操作记录 
     * 
     * @param claimCaseRecord 案件操作记录 
     * @return 结果
     */
    @Override
    public int updateClaimCaseRecord(ClaimCaseRecord claimCaseRecord)
    {
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        return claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord);
    }

    @Override
    public int updatehistoryFlag(String rptNo) {
        return claimCaseRecordMapper.updatehistoryFlag(rptNo);
    }

    /**
     * 批量删除案件操作记录 
     * 
     * @param recordIds 需要删除的案件操作记录 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRecordByIds(Long[] recordIds)
    {
        return claimCaseRecordMapper.deleteClaimCaseRecordByIds(recordIds);
    }

    /**
     * 删除案件操作记录 信息
     * 
     * @param recordId 案件操作记录 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRecordById(Long recordId)
    {
        return claimCaseRecordMapper.deleteClaimCaseRecordById(recordId);
    }

    /**
     * 根据报案号和流程节点更新轨迹表
     *
     * @param claimCaseRecord
     * @return
     */
    @Override
    public int updateClaimCaseRecordByRptNoAndOperator(ClaimCaseRecord claimCaseRecord) {
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        return claimCaseRecordMapper.updateClaimCaseRecordByRptNoAndOperator(claimCaseRecord);
    }

    /**
     * 机构交单-事后-新增案件操作记录
     *
     * @param claimCaseStandingVo 案件操作记录
     * @return 结果
     */
    @Override
    public int insertClaimCaseRecordAndBatchRecord(ClaimCaseStandingVo claimCaseStandingVo){
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCaseStandingVo.getRptno());
        claimCaseRecord.setOperation("05");//受理
        claimCaseRecord.setOperator(SecurityUtils.getUsername());
        //claimCaseRecord.setOrgRecordId();
        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        return claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
    }

    @Override
    public long selectClaimCaseRecordByRptNo(String rptNo) {
        return claimCaseRecordMapper.selectClaimCaseRecordByRptNo(rptNo);
    }

}
