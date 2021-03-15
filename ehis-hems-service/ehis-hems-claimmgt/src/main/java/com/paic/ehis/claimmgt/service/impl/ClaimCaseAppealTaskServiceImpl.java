package com.paic.ehis.claimmgt.service.impl;

import java.util.Date;
import java.util.List;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealRecord;
import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;
import com.paic.ehis.claimmgt.mapper.ClaimCaseAppealRecordMapper;
import com.paic.ehis.claimmgt.mapper.ClaimCaseAppealTaskMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseAppealTaskService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件申诉任务Service业务层处理
 * 
 * @author sino
 * @date 2021-03-15
 */
@Service
public class ClaimCaseAppealTaskServiceImpl implements IClaimCaseAppealTaskService
{
    @Autowired
    private ClaimCaseAppealTaskMapper claimCaseAppealTaskMapper;

    @Autowired

    private ClaimCaseAppealRecordMapper claimCaseAppealRecordMapper;

    /**
     * 查询案件申诉任务
     * 
     * @param taskId 案件申诉任务ID
     * @return 案件申诉任务
     */
    @Override
    public ClaimCaseAppealTask selectClaimCaseAppealTaskById(Long taskId)
    {
        return claimCaseAppealTaskMapper.selectClaimCaseAppealTaskById(taskId);
    }

    /**
     * 查询案件申诉任务列表
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 案件申诉任务
     */
    @Override
    public List<ClaimCaseAppealTask> selectClaimCaseAppealTaskList(ClaimCaseAppealTask claimCaseAppealTask)
    {
        return claimCaseAppealTaskMapper.selectClaimCaseAppealTaskList(claimCaseAppealTask);
    }

    /**
     * 新增案件申诉任务
     * 
     * @param bean 案件申诉任务
     * @return 结果
     */
    @Override
    public int insertClaimCaseAppealTask(ClaimCaseAppealTask bean)
    {
        Long taskId = claimCaseAppealTaskMapper.getTaskId();

        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        bean.setTaskId(taskId);
        bean.setCreateTime(nowDate);
        bean.setCreateBy(username);
        bean.setUpdateTime(nowDate);
        bean.setUpdateBy(username);
        bean.setStatus("Y");
        bean.setAppealStatus("01");
        bean.setApplyOperator(username);
        this.insertRecordLog(bean,null);
        return claimCaseAppealTaskMapper.insertClaimCaseAppealTask(bean);
    }

    /**
     * 修改案件申诉任务
     * 
     * @param bean 案件申诉任务
     * @return 结果
     */
    @Override
    public int updateClaimCaseAppealTask(ClaimCaseAppealTask bean)
    {
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        bean.setUpdateTime(nowDate);
        bean.setUpdateBy(username);

        // 进入申诉初审状态
        if(bean.getDealType().equalsIgnoreCase("initiate")) {
            bean.setAppealStatus("02");
        }
        //初审确认  同意 / 不同意  （申诉完成 / 申诉退回）
        if(bean.getDealType().equalsIgnoreCase("audit")) {
           if(bean.getIsAgree().equals("01")) {
               bean.setAppealStatus("03");
           } else {
               bean.setAppealStatus("04");
           }
        }
        Long orgRecordId = this.updateHistoryFlag(bean);
        this.insertRecordLog(bean,orgRecordId);
        return claimCaseAppealTaskMapper.updateClaimCaseAppealTask(bean);
    }

    /**
     * 批量删除案件申诉任务
     * 
     * @param taskIds 需要删除的案件申诉任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAppealTaskByIds(Long[] taskIds)
    {
        return claimCaseAppealTaskMapper.deleteClaimCaseAppealTaskByIds(taskIds);
    }

    /**
     * 删除案件申诉任务信息
     * 
     * @param taskId 案件申诉任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAppealTaskById(Long taskId)
    {
        return claimCaseAppealTaskMapper.deleteClaimCaseAppealTaskById(taskId);
    }

    /**
     * 查询案件申诉任务列表
     *
     * @param vo 案件申诉任务
     * @return 案件申诉任务集合
     */
    @Override
    public List<ClaimCaseAppealTaskVo> getAppealList(ClaimCaseAppealTaskVo vo) {
        return claimCaseAppealTaskMapper.getAppealList(vo);
    }

    /**
     * 添加节点记录
     * @author: hjw
     * @time : 2021-3-15
     */
    private void  insertRecordLog(ClaimCaseAppealTask bean, Long orgRecordId){
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        ClaimCaseAppealRecord record = new ClaimCaseAppealRecord();
        record.setTaskId(bean.getTaskId());
        record.setOperation(bean.getAppealStatus());
        record.setOperator(username);
        record.setOrgRecordId(orgRecordId);
        record.setHistoryFlag("N");
        record.setCreateTime(nowDate);
        record.setCreateBy(username);
        record.setUpdateTime(nowDate);
        record.setUpdateBy(username);
        record.setStatus("Y");
        claimCaseAppealRecordMapper.insertClaimCaseAppealRecord(record);
    }


    /**
     * taskid记录更新未历史记录
     * @author: hjw
     * @time : 2021-3-15
     */
    private Long updateHistoryFlag(ClaimCaseAppealTask bean) {
        Long result = null;
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        ClaimCaseAppealRecord record = new ClaimCaseAppealRecord();
        record.setHistoryFlag("N");
        record.setTaskId(bean.getTaskId());
        List<ClaimCaseAppealRecord> recordList = claimCaseAppealRecordMapper.selectClaimCaseAppealRecordList(record);
        for(ClaimCaseAppealRecord updateBean : recordList) {
            result =  updateBean.getRecordId();
            ClaimCaseAppealRecord upDto = new ClaimCaseAppealRecord();
            upDto.setRecordId(result);
            upDto.setHistoryFlag("Y");
            upDto.setUpdateTime(nowDate);
            upDto.setUpdateBy(username);
            claimCaseAppealRecordMapper.updateClaimCaseAppealRecord(upDto);
        }
        return result;
    }
}
