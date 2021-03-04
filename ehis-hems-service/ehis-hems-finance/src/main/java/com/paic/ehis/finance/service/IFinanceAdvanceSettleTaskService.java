package com.paic.ehis.finance.service;

import com.paic.ehis.finance.domain.FinanceAdvanceSettleTask;

import java.util.List;

/**
 * 代垫费结算任务Service接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface IFinanceAdvanceSettleTaskService 
{
    /**
     * 查询代垫费结算任务
     * 
     * @param taskId 代垫费结算任务ID
     * @return 代垫费结算任务
     */
    public FinanceAdvanceSettleTask selectFinanceAdvanceSettleTaskById(Long taskId);

    /**
     * 查询代垫费结算任务列表
     * 
     * @param financeAdvanceSettleTask 代垫费结算任务
     * @return 代垫费结算任务集合
     */
    public List<FinanceAdvanceSettleTask> selectFinanceAdvanceSettleTaskList(FinanceAdvanceSettleTask financeAdvanceSettleTask);

    /**
     * 新增代垫费结算任务
     * 
     * @param financeAdvanceSettleTask 代垫费结算任务
     * @return 结果
     */
    public int insertFinanceAdvanceSettleTask(FinanceAdvanceSettleTask financeAdvanceSettleTask);

    /**
     * 修改代垫费结算任务
     * 
     * @param financeAdvanceSettleTask 代垫费结算任务
     * @return 结果
     */
    public int updateFinanceAdvanceSettleTask(FinanceAdvanceSettleTask financeAdvanceSettleTask);

    /**
     * 批量删除代垫费结算任务
     * 
     * @param taskIds 需要删除的代垫费结算任务ID
     * @return 结果
     */
    public int deleteFinanceAdvanceSettleTaskByIds(Long[] taskIds);

    /**
     * 删除代垫费结算任务信息
     * 
     * @param taskId 代垫费结算任务ID
     * @return 结果
     */
    public int deleteFinanceAdvanceSettleTaskById(Long taskId);
}
