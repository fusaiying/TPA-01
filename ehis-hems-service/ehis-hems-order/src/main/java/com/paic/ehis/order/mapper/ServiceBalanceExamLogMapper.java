package com.paic.ehis.order.mapper;


import com.paic.ehis.order.domain.ServiceBalanceExamLog;

import java.util.List;

/**
 * service_balance_exam_log(服务结算审核日志)Mapper接口
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
public interface ServiceBalanceExamLogMapper 
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
     * 删除service_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNo service_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    public int deleteServiceBalanceExamLogById(Long serialNo);

    /**
     * 批量删除service_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceBalanceExamLogByIds(Long[] serialNos);

    /**
     * service_balance_exam_log(服务结算审核日志)
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    public int updateServiceBalanceExamLogByTaskNo(ServiceBalanceExamLog serviceBalanceExamLog);
}
