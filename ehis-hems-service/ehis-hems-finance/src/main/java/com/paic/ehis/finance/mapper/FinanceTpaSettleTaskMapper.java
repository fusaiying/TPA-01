package com.paic.ehis.finance.mapper;


import com.paic.ehis.finance.domain.FinanceTpaSettleTask;
import com.paic.ehis.finance.domain.TpaSettleInfo;
import com.paic.ehis.finance.domain.dto.TpaSettleDTO;

import java.util.List;

/**
 * TPA服务费结算任务Mapper接口
 *
 * @author sino
 * @date 2021-01-30
 */
public interface FinanceTpaSettleTaskMapper
{
    /**
     * 查询TPA服务费结算任务
     *
     * @param taskId TPA服务费结算任务ID
     * @return TPA服务费结算任务
     */
    public FinanceTpaSettleTask selectFinanceTpaSettleTaskById(Long taskId);

    /**
     * 查询TPA服务费结算任务列表
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    public List<FinanceTpaSettleTask> selectFinanceTpaSettleTaskList(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 查询TPA服务费结算 子页面列表
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    public List<TpaSettleInfo> selectFinanceTpaSettleTaskViewDetail(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 多页面 查询TPA服务费结算任务 列表
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    public List<FinanceTpaSettleTask> selectTpaSettleTaskList(TpaSettleDTO tpaSettleDTO);

    /**
     * 新增TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    public int insertFinanceTpaSettleTask(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 根据任务号修改TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    public int updateFinanceTpaSettleTask(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 修改TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    public int modifyTaskSettle(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 删除TPA服务费结算任务
     *
     * @param taskId TPA服务费结算任务ID
     * @return 结果
     */
    public int deleteFinanceTpaSettleTaskById(Long taskId);

    /**
     * 批量删除TPA服务费结算任务
     *
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceTpaSettleTaskByIds(Long[] taskIds);
}
