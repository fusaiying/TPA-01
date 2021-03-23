package com.paic.ehis.finance.service;


import com.paic.ehis.finance.domain.FinanceCollectionInfo;
import com.paic.ehis.finance.domain.FinanceTpaSettleTask;
import com.paic.ehis.finance.domain.TpaSettleInfo;
import com.paic.ehis.finance.domain.dto.TpaSettleDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * TPA服务费结算任务Service接口
 *
 * @author sino
 * @date 2021-01-30
 */
public interface IFinanceTpaSettleTaskService
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
     * 任务发起
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    public List<TpaSettleInfo> tpaTaskInitiated(TpaSettleDTO tpaSettleDTO) throws Exception;

    /**
     * 多页面 查询TPA服务费结算任务 列表
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    public List<FinanceTpaSettleTask> selectTpaSettleTaskList(TpaSettleDTO tpaSettleDTO);

    /**
     * 查询TPA服务费结算 子页面列表
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    public List<TpaSettleInfo> selectFinanceTpaSettleTaskViewDetail(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 导入TPA服务费结算
     *
     * @param file TPA服务费结算任务
     * @return TPA服务费结算任务Map
     */
    public int importTpaSettleTask(MultipartFile file);

    /**
     * 根据companyCode查询收款核销列表
     *
     * @param tpaSettleDTO 收款核销
     * @return 收款核销集合
     */
    public List<FinanceCollectionInfo> selectTpaTaskReceiptColList(TpaSettleDTO tpaSettleDTO);

    /**
     * 新增TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    public int insertFinanceTpaSettleTask(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 修改TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    public int updateFinanceTpaSettleTask(FinanceTpaSettleTask financeTpaSettleTask);

    /**
     * 确认TPA服务费结算任务
     *
     * @param settleTaskNo TPA服务费结算任务
     * @return 结果
     */
    public int confirmTpaSettleTask(String settleTaskNo);

    /**
     * 删除TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    public int modifyTaskSettle(FinanceTpaSettleTask financeTpaSettleTask);


    /**
     * 批量删除TPA服务费结算任务
     *
     * @param taskIds 需要删除的TPA服务费结算任务ID
     * @return 结果
     */
    public int deleteFinanceTpaSettleTaskByIds(Long[] taskIds);

    /**
     * 删除TPA服务费结算任务信息
     *
     * @param taskId TPA服务费结算任务ID
     * @return 结果
     */
    public int deleteFinanceTpaSettleTaskById(Long taskId);
}
