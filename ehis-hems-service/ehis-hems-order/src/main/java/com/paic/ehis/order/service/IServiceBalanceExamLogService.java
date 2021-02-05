package com.paic.ehis.order.service;


import com.paic.ehis.order.domain.ServiceBalanceExamLog;

import java.util.List;

/**
 * service_balance_exam_log(服务结算审核日志)Service接口
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
public interface IServiceBalanceExamLogService 
{
    /**
     * 查询service_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNo service_balance_exam_log(服务结算审核日志)ID
     * @return service_balance_exam_log(服务结算审核日志)
     */
    public ServiceBalanceExamLog selectServiceBalanceExamLogById(Long serialNo);

    /**
     * 查询service_balance_exam_log(服务结算审核日志)列表
     * 
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return service_balance_exam_log(服务结算审核日志)集合
     */
    public List<ServiceBalanceExamLog> selectServiceBalanceExamLogList(ServiceBalanceExamLog serviceBalanceExamLog);

    /**
     * 新增service_balance_exam_log(服务结算审核日志)
     * 
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    public int insertServiceBalanceExamLog(ServiceBalanceExamLog serviceBalanceExamLog);

    /**
     * 修改service_balance_exam_log(服务结算审核日志)
     * 
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    public int updateServiceBalanceExamLog(ServiceBalanceExamLog serviceBalanceExamLog);

    /**
     * 批量删除service_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNos 需要删除的service_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    public int deleteServiceBalanceExamLogByIds(Long[] serialNos);

    /**
     * 删除service_balance_exam_log(服务结算审核日志)信息
     * 
     * @param serialNo service_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    public int deleteServiceBalanceExamLogById(Long serialNo);
}
