package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseProblem;
import com.paic.ehis.claimflow.domain.ClaimCaseRecord;
import com.paic.ehis.claimflow.mapper.ClaimCaseMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseProblemMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseRecordMapper;
import com.paic.ehis.claimflow.service.IClaimCaseProblemService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 案件问题件Service业务层处理
 *
 * @author sino
 * @date 2021-01-11
 */
@Service
public class ClaimCaseProblemServiceImpl implements IClaimCaseProblemService
{
    @Autowired
    private ClaimCaseProblemMapper claimCaseProblemMapper;

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    /**
     * 查询案件问题件
     *
     * @param problemId 案件问题件ID
     * @return 案件问题件
     */
    @Override
    public ClaimCaseProblem selectClaimCaseProblemById(Long problemId)
    {
        return claimCaseProblemMapper.selectClaimCaseProblemById(problemId);
    }

    /**
     * 查询案件问题件列表
     *
     * @param claimCaseProblem 案件问题件
     * @return 案件问题件
     */
    @Override
    public List<ClaimCaseProblem> selectClaimCaseProblemList(ClaimCaseProblem claimCaseProblem)
    {
        return claimCaseProblemMapper.selectClaimCaseProblemList(claimCaseProblem);
    }

    @Override
    public List<ClaimCaseProblem> selectClaimCaseProblemList1(ClaimCaseProblem claimCaseProblem) {
        return claimCaseProblemMapper.selectClaimCaseProblemList1(claimCaseProblem);
    }

    @Override
    public List<ClaimCaseProblem> selectClaimCaseProblemList2(ClaimCaseProblem claimCaseProblem) throws Exception{
//        Map map = Dateutils.getCurrontTime1();
//        claimCaseProblem.setdBefore1(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
//        claimCaseProblem.setdNow1(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
      /*  List<ClaimCaseProblem> claimCaseProblemList=claimCaseProblemMapper.selectClaimCaseProblemList2(claimCaseProblem);
        for (ClaimCaseProblem claimCaseProblem1:claimCaseProblemList) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = sdf.format(date);
            Date nowDate = sdf.parse(now);
            Date createTime=claimCaseProblem1.getCreateTime();
            String stopTime = getDatePoor(nowDate,createTime);
            claimCaseProblem1.setStopTime(stopTime);
        }*/
        return claimCaseProblemMapper.selectClaimCaseProblemList2(claimCaseProblem);
    }

    /**
     * 新增案件问题件
     *
     * @param claimCaseProblem 案件问题件
     * @return 结果
     */
    @Override
    public int  insertClaimCaseProblem(ClaimCaseProblem claimCaseProblem)
    {
        claimCaseProblem.setCreateBy(SecurityUtils.getUsername());
        claimCaseProblem.setCreateTime(DateUtils.getNowDate());
        claimCaseProblem.setUpdateBy(SecurityUtils.getUsername());
        claimCaseProblem.setUpdateTime(DateUtils.getNowDate());
        claimCaseProblem.setStatus("Y");//状态
        claimCaseProblem.setIsHistory("N");
        List<ClaimCaseProblem> claimCaseProblems = claimCaseProblemMapper.selectClaimCaseProblemByRptNo(claimCaseProblem.getRptNo());
        for (ClaimCaseProblem claimCaseProblemsList:claimCaseProblems){
            claimCaseProblem.setProblemId(claimCaseProblemsList.getProblemId());
        }
        if (claimCaseProblems!=null && !claimCaseProblems.isEmpty()){//不为空
            return claimCaseProblemMapper.updateClaimCaseProblem(claimCaseProblem);
        }else {//为空
            return claimCaseProblemMapper.insertClaimCaseProblem(claimCaseProblem);
        }
    }


    /**
     * 修改案件问题件
     *
     * @param claimCaseProblem 案件问题件
     * @return 结果
     */
    @Override
    public int updateClaimCaseProblem(ClaimCaseProblem claimCaseProblem)
    {
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCaseProblem.getRptNo());
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setOperation("05");
        ClaimCaseRecord caseRecord = claimCaseRecordMapper.selectRecentClaimCaseRecord(claimCaseRecord);
        ClaimCase claimCase=claimCaseMapper.selectClaimCaseById(claimCaseProblem.getRptNo());
        claimCase.setUpdateBy(caseRecord.getOperator());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCase.setCaseStatus("05");


        ClaimCaseRecord record = claimCaseRecordMapper.selectClaimCaseRecordByrptNoFive(claimCaseProblem.getRptNo());
        record.setOperator(SecurityUtils.getUsername());
        record.setHistoryFlag("Y");
        record.setUpdateBy(SecurityUtils.getUsername());
        record.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecord(record);

        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setOperation("05");
        claimCaseRecord.setOrgRecordId(record.getRecordId());
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);

        claimCaseMapper.updateClaimCase(claimCase);


        return claimCaseProblemMapper.updateClaimCaseProblem(claimCaseProblem);
    }

    /**
     * 批量删除案件问题件
     *
     * @param problemIds 需要删除的案件问题件ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseProblemByIds(Long[] problemIds)
    {
        return claimCaseProblemMapper.deleteClaimCaseProblemByIds(problemIds);
    }

    /**
     * 删除案件问题件信息
     *
     * @param problemId 案件问题件ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseProblemById(Long problemId)
    {
        return claimCaseProblemMapper.deleteClaimCaseProblemById(problemId);
    }

    /**
     * 查找历史问题件页面
     * @param claimCaseProblem
     * @return
     */
    @Override
    public List<ClaimCaseProblem> selectHistoricalProblem(ClaimCaseProblem claimCaseProblem) {
        claimCaseProblem.setStatus("Y");
        return claimCaseProblemMapper.selectHistoricalProblem(claimCaseProblem);
    }

    @Override
    public List<ClaimCaseProblem> selectClaimCaseProblemByRptNo(String rptNo){
        return claimCaseProblemMapper.selectClaimCaseProblemByRptNo(rptNo);
    }
}