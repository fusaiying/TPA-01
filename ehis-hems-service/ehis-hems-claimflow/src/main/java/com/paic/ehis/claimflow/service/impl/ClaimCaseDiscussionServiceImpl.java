package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDiscussionDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseDiscussionVO;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.IClaimCaseDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 案件协谈信息Service业务层处理
 *
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseDiscussionServiceImpl implements IClaimCaseDiscussionService
{
    @Autowired
    private ClaimCaseDiscussionMapper claimCaseDiscussionMapper;

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    /**
     * 查询案件协谈信息
     *
     * @param discId 案件协谈信息ID
     * @return 案件协谈信息
     */
    @Override
    public ClaimCaseDiscussion selectClaimCaseDiscussionById(Long discId)
    {
        return claimCaseDiscussionMapper.selectClaimCaseDiscussionById(discId);
    }

    /**
     * 查询案件历史协谈信息
     *
     * @param rptNo 案件协谈信息ID
     * @return 案件协谈信息
     */
    @Override
    public List<ClaimCaseDiscussion> selectDiscussionListByRptNo(String rptNo)
    {
        ClaimCaseDiscussion claimCaseDiscussion = new ClaimCaseDiscussion();
        claimCaseDiscussion.setRptNo(rptNo);
        claimCaseDiscussion.setIsHistory("Y");
        claimCaseDiscussion.setStatus(ClaimStatus.DATAYES.getCode());
        return claimCaseDiscussionMapper.selectDiscussionListByRptNo(claimCaseDiscussion);
    }

    /**
     * 查询案件协谈信息列表
     *
     * @param claimCaseDiscussion 案件协谈信息
     * @return 案件协谈信息
     */
    @Override
    public List<ClaimCaseDiscussion> selectClaimCaseDiscussionList(ClaimCaseDiscussion claimCaseDiscussion)
    {
        return claimCaseDiscussionMapper.selectClaimCaseDiscussionList(claimCaseDiscussion);
    }

    /**
     * 新增案件协谈信息
     *
     * @param claimCaseDiscussion 案件协谈信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertClaimCaseDiscussion(ClaimCaseDiscussion claimCaseDiscussion)
    {
        ClaimCaseRecord caseRecord = new ClaimCaseRecord();
        caseRecord.setRptNo(claimCaseDiscussion.getRptNo());
        caseRecord.setStatus(ClaimStatus.DATAYES.getCode());
        caseRecord.setHistoryFlag("N");
        caseRecord.setOperation(ClaimStatus.CASEAUDIT.getCode());
        ClaimCaseRecord claimCaseRecord = claimCaseRecordMapper.selectRecentClaimCaseRecord(caseRecord);
        if (StringUtils.isNotNull(claimCaseRecord)) {
            claimCaseRecord.setHistoryFlag("Y");
            claimCaseRecord.setOperator(SecurityUtils.getUsername());
            claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
            claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
            claimCaseRecordMapper.updateClaimCaseRecord(claimCaseRecord);
        }

        caseRecord.setOrgRecordId(claimCaseRecord.getRecordId());
        caseRecord.setOperation(ClaimStatus.CASETALKING.getCode());
        caseRecord.setCreateBy(SecurityUtils.getUsername());
        caseRecord.setCreateTime(DateUtils.getNowDate());
        caseRecord.setUpdateBy(SecurityUtils.getUsername());
        caseRecord.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(caseRecord);

        ClaimCase claimCase = new ClaimCase();
        claimCase.setRptNo(claimCaseDiscussion.getRptNo());
        claimCase.setCaseStatus(ClaimStatus.CASETALKING.getCode());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCaseMapper.updateClaimCase(claimCase);

        claimCaseDiscussion.setIsHistory("N");
        claimCaseDiscussion.setStatus(ClaimStatus.DATAYES.getCode());
        claimCaseDiscussion.setCreateBy(SecurityUtils.getUsername());
        claimCaseDiscussion.setCreateTime(DateUtils.getNowDate());
        claimCaseDiscussion.setUpdateBy(SecurityUtils.getUsername());
        claimCaseDiscussion.setUpdateTime(DateUtils.getNowDate());
        return claimCaseDiscussionMapper.insertClaimCaseDiscussion(claimCaseDiscussion);
    }

    /**
     * 修改案件协谈信息
     *
     * @param claimCaseDiscussion 案件协谈信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseDiscussion(ClaimCaseDiscussion claimCaseDiscussion)
    {
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCaseDiscussion.getRptNo());
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setOperation("07");
        ClaimCaseRecord caseRecord = claimCaseRecordMapper.selectRecentClaimCaseRecord(claimCaseRecord);
        ClaimCase claimCase=claimCaseMapper.selectClaimCaseById(claimCaseDiscussion.getRptNo());
        claimCase.setUpdateBy(caseRecord.getOperator());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCase.setCaseStatus("07");

        ClaimCaseRecord record = claimCaseRecordMapper.selectClaimCaseRecordByrptNoFive(claimCaseDiscussion.getRptNo());
        record.setOperator(SecurityUtils.getUsername());
        record.setHistoryFlag("Y");
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecord(record);

        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setOperation("07");
        claimCaseRecord.setOrgRecordId(record.getRecordId());
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
        claimCaseMapper.updateClaimCase(claimCase);

        return claimCaseDiscussionMapper.updateClaimCaseDiscussion(claimCaseDiscussion);
    }

    /**
     * 批量删除案件协谈信息
     *
     * @param discIds 需要删除的案件协谈信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDiscussionByIds(Long[] discIds)
    {
        return claimCaseDiscussionMapper.deleteClaimCaseDiscussionByIds(discIds);
    }

    /**
     * 删除案件协谈信息信息
     *
     * @param discId 案件协谈信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDiscussionById(Long discId)
    {
        return claimCaseDiscussionMapper.deleteClaimCaseDiscussionById(discId);
    }
    //未处理协谈
    @Override
    public List<ClaimCaseDiscussionVO> selectCaseDisListUnder(ClaimCaseDiscussionDTO claimCaseDiscussionDTO) {
        //查询出协谈处理中的所有的数据
        List<ClaimCaseDiscussionVO> claimCaseDiscussionVOS = claimCaseDiscussionMapper.selectCaseDisListUnder(claimCaseDiscussionDTO);
        //已有：rpt_no,/*案件受理报案号*/  source,/*交单来源*/  name,/*被保人姓名*/  disc_type,/*协谈类型*/
        // create_by,/*提交用户,流程节点操作人*/  case_status,/*案件状态*/  organ_code,/*承保机构*/  id_no,/*证件号码*/
        // submit_date交单日期  source交单来源
        //还差：停留时长 监控时效
        for (ClaimCaseDiscussionVO claimCaseDiscussionVO:claimCaseDiscussionVOS) {
            //停留时长
            ClaimCaseRecord claimCaseRecord = claimCaseRecordMapper.selectClaimCaseRecordByrptNoOne(claimCaseDiscussionVO.getRptNo());
            if (null != claimCaseRecord) {
                Date begin = claimCaseRecord.getCreateTime();
                Date end = new Date();
                long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
                long day1 = between / (24 * 3600);
                long hour1 = between % (24 * 3600) / 3600;
                long minute1 = between % 3600 / 60;
                String time = "" + day1 + "天" + hour1 + "小时" + minute1 + "分";
                claimCaseDiscussionVO.setStopTime(time);
            }
            //监控时效
            ClaimCaseRecord claimCaseRecord1 = claimCaseRecordMapper.selectClaimCaseRecordByrptNoTwo(claimCaseDiscussionVO.getRptNo());
            if (null != claimCaseRecord1) {
                Date begin1 = claimCaseRecord1.getCreateTime();
                Date end1 = new Date();
                long between1 = (end1.getTime() - begin1.getTime()) / 1000;//除以1000是为了转换成秒
                long day2 = between1 / (24 * 3600);
                long hour2 = between1 % (24 * 3600) / 3600;
                long minute2 = between1 % 3600 / 60;
                String time2 = "" + day2 + "天" + hour2 + "小时" + minute2 + "分";
                claimCaseDiscussionVO.setMonitoringTime(time2);
            }
        }
        return claimCaseDiscussionVOS;
    }

    //已处理协谈
    @Override
    public List<ClaimCaseDiscussionVO> selectCaseDisListOver(ClaimCaseDiscussionDTO claimCaseDiscussionDTO) {
        return claimCaseDiscussionMapper.selectCaseDisListOver(claimCaseDiscussionDTO);
    }

    @Override
    public int updatecasediscussionStatus(String repNo) {
        return claimCaseMapper.updatecaseStatus(repNo);
    }

    /*查询基础信息表*/
    @Override
    public ClaimCaseDiscussionVO  selectCaseBaseInfo(String rptNo){
        return claimCaseDiscussionMapper.selectCaseBaseInfo(rptNo);
    }

}
