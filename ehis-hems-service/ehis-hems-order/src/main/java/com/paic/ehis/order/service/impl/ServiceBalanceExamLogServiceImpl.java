package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.order.domain.ServiceBalanceExamLog;
import com.paic.ehis.order.mapper.ServiceBalanceExamLogMapper;
import com.paic.ehis.order.service.IServiceBalanceExamLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service_balance_exam_log(服务结算审核日志)Service业务层处理
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@Service
public class ServiceBalanceExamLogServiceImpl implements IServiceBalanceExamLogService
{
    @Autowired
    private ServiceBalanceExamLogMapper serviceBalanceExamLogMapper;

    /**
     * 查询service_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNo service_balance_exam_log(服务结算审核日志)ID
     * @return service_balance_exam_log(服务结算审核日志)
     */
    @Override
    public ServiceBalanceExamLog selectServiceBalanceExamLogById(Long serialNo)
    {
        return serviceBalanceExamLogMapper.selectServiceBalanceExamLogById(serialNo);
    }

    /**
     * 查询service_balance_exam_log(服务结算审核日志)列表
     * 
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return service_balance_exam_log(服务结算审核日志)
     */
    @Override
    public List<ServiceBalanceExamLog> selectServiceBalanceExamLogList(ServiceBalanceExamLog serviceBalanceExamLog)
    {
        return serviceBalanceExamLogMapper.selectServiceBalanceExamLogList(serviceBalanceExamLog);
    }

    /**
     * 新增service_balance_exam_log(服务结算审核日志)
     * 
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    @Override
    public int insertServiceBalanceExamLog(ServiceBalanceExamLog serviceBalanceExamLog)
    {
        serviceBalanceExamLog.setCreateTime(DateUtils.getNowDate());
        return serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
    }

    /**
     * 修改service_balance_exam_log(服务结算审核日志)
     * 
     * @param serviceBalanceExamLog service_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    @Override
    public int updateServiceBalanceExamLog(ServiceBalanceExamLog serviceBalanceExamLog)
    {
        serviceBalanceExamLog.setUpdateTime(DateUtils.getNowDate());
        return serviceBalanceExamLogMapper.updateServiceBalanceExamLog(serviceBalanceExamLog);
    }

    /**
     * 批量删除service_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNos 需要删除的service_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceExamLogByIds(Long[] serialNos)
    {
        return serviceBalanceExamLogMapper.deleteServiceBalanceExamLogByIds(serialNos);
    }

    /**
     * 删除service_balance_exam_log(服务结算审核日志)信息
     * 
     * @param serialNo service_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceExamLogById(Long serialNo)
    {
        return serviceBalanceExamLogMapper.deleteServiceBalanceExamLogById(serialNo);
    }
}
