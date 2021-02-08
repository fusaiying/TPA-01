package com.paic.ehis.claimapt.service.Impl;

import com.paic.ehis.claimapt.domain.ClaimCase;
import com.paic.ehis.claimapt.domain.ClaimCaseRecord;
import com.paic.ehis.claimapt.domain.Vo.ClaimCaseStandingVo;
import com.paic.ehis.claimapt.mapper.ClaimCaseMapper;
import com.paic.ehis.claimapt.mapper.ClaimCaseRecordMapper;
import com.paic.ehis.claimapt.service.IClaimCaseRecordService;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
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

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

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
        claimCaseRecord.setOperation(ClaimStatus.CASEACCEPTED.getCode());//受理05
        claimCaseRecord.setOperator(SecurityUtils.getUsername());
        claimCaseRecord.setHistoryFlag(ClaimStatus.DATANO.getCode());//N
        claimCaseRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));

        ClaimCase claimCase = new ClaimCase();
        //归档号
        String claimCaseNumber1="JGHDQQW"+DateUtils.dateTimeNow("yyyy")+"X"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,10);
        claimCase.setBatchNo(claimCaseStandingVo.getBatchno());//批次号
        claimCase.setRptNo(claimCaseStandingVo.getRptno());//报案号
        claimCase.setFilingNo(claimCaseNumber1);//归档号
        claimCase.setCaseStatus(ClaimStatus.CASEACCEPTED.getCode());//案件状态05
        claimCase.setCreateBy(SecurityUtils.getUsername());
        claimCase.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimCase.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimCaseMapper.insertClaimCase(claimCase);

        return claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
    }

}
