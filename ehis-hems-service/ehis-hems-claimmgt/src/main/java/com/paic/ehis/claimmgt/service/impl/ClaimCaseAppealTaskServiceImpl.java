package com.paic.ehis.claimmgt.service.impl;

import java.util.Date;
import java.util.List;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;
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
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        bean.setCreateTime(nowDate);
        bean.setCreateBy(username);
        bean.setUpdateTime(nowDate);
        bean.setUpdateBy(username);
        bean.setStatus("Y");
        bean.setAppealStatus("01");
        bean.setApplyOperator(username);
        return claimCaseAppealTaskMapper.insertClaimCaseAppealTask(bean);
    }

    /**
     * 修改案件申诉任务
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 结果
     */
    @Override
    public int updateClaimCaseAppealTask(ClaimCaseAppealTask claimCaseAppealTask)
    {
        claimCaseAppealTask.setUpdateTime(DateUtils.getNowDate());
        return claimCaseAppealTaskMapper.updateClaimCaseAppealTask(claimCaseAppealTask);
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
}
