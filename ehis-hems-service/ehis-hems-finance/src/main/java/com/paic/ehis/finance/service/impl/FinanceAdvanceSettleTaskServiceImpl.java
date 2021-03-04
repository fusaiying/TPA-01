package com.paic.ehis.finance.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.finance.domain.FinanceAdvanceSettleTask;
import com.paic.ehis.finance.mapper.FinanceAdvanceSettleTaskMapper;
import com.paic.ehis.finance.service.IFinanceAdvanceSettleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代垫费结算任务Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceAdvanceSettleTaskServiceImpl implements IFinanceAdvanceSettleTaskService 
{
    @Autowired
    private FinanceAdvanceSettleTaskMapper financeAdvanceSettleTaskMapper;

    /**
     * 查询代垫费结算任务
     * 
     * @param taskId 代垫费结算任务ID
     * @return 代垫费结算任务
     */
    @Override
    public FinanceAdvanceSettleTask selectFinanceAdvanceSettleTaskById(Long taskId)
    {
        return financeAdvanceSettleTaskMapper.selectFinanceAdvanceSettleTaskById(taskId);
    }

    /**
     * 查询代垫费结算任务列表
     * 
     * @param financeAdvanceSettleTask 代垫费结算任务
     * @return 代垫费结算任务
     */
    @Override
    public List<FinanceAdvanceSettleTask> selectFinanceAdvanceSettleTaskList(FinanceAdvanceSettleTask financeAdvanceSettleTask)
    {
        return financeAdvanceSettleTaskMapper.selectFinanceAdvanceSettleTaskList(financeAdvanceSettleTask);
    }

    /**
     * 新增代垫费结算任务
     * 
     * @param financeAdvanceSettleTask 代垫费结算任务
     * @return 结果
     */
    @Override
    public int insertFinanceAdvanceSettleTask(FinanceAdvanceSettleTask financeAdvanceSettleTask)
    {
        financeAdvanceSettleTask.setCreateTime(DateUtils.getNowDate());
        return financeAdvanceSettleTaskMapper.insertFinanceAdvanceSettleTask(financeAdvanceSettleTask);
    }

    /**
     * 修改代垫费结算任务
     * 
     * @param financeAdvanceSettleTask 代垫费结算任务
     * @return 结果
     */
    @Override
    public int updateFinanceAdvanceSettleTask(FinanceAdvanceSettleTask financeAdvanceSettleTask)
    {
        financeAdvanceSettleTask.setUpdateTime(DateUtils.getNowDate());
        return financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);
    }

    /**
     * 批量删除代垫费结算任务
     * 
     * @param taskIds 需要删除的代垫费结算任务ID
     * @return 结果
     */
    @Override
    public int deleteFinanceAdvanceSettleTaskByIds(Long[] taskIds)
    {
        return financeAdvanceSettleTaskMapper.deleteFinanceAdvanceSettleTaskByIds(taskIds);
    }

    /**
     * 删除代垫费结算任务信息
     * 
     * @param taskId 代垫费结算任务ID
     * @return 结果
     */
    @Override
    public int deleteFinanceAdvanceSettleTaskById(Long taskId)
    {
        return financeAdvanceSettleTaskMapper.deleteFinanceAdvanceSettleTaskById(taskId);
    }
}
