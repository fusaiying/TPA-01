package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.order.domain.BaseBalanceExamLog;
import com.paic.ehis.order.mapper.BaseBalanceExamLogMapper;
import com.paic.ehis.order.service.IBaseBalanceExamLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_balance_exam_log(服务结算审核日志)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class BaseBalanceExamLogServiceImpl implements IBaseBalanceExamLogService 
{
    @Autowired
    private BaseBalanceExamLogMapper baseBalanceExamLogMapper;

    /**
     * 查询base_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNo base_balance_exam_log(服务结算审核日志)ID
     * @return base_balance_exam_log(服务结算审核日志)
     */
    @Override
    public BaseBalanceExamLog selectBaseBalanceExamLogById(Long serialNo)
    {
        return baseBalanceExamLogMapper.selectBaseBalanceExamLogById(serialNo);
    }

    /**
     * 查询base_balance_exam_log(服务结算审核日志)列表
     * 
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return base_balance_exam_log(服务结算审核日志)
     */
    @Override
    public List<BaseBalanceExamLog> selectBaseBalanceExamLogList(BaseBalanceExamLog baseBalanceExamLog)
    {
        return baseBalanceExamLogMapper.selectBaseBalanceExamLogList(baseBalanceExamLog);
    }

    /**
     * 新增base_balance_exam_log(服务结算审核日志)
     * 
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    @Override
    public int insertBaseBalanceExamLog(BaseBalanceExamLog baseBalanceExamLog)
    {
        baseBalanceExamLog.setCreateTime(DateUtils.getNowDate());
        return baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
    }

    /**
     * 修改base_balance_exam_log(服务结算审核日志)
     * 
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    @Override
    public int updateBaseBalanceExamLog(BaseBalanceExamLog baseBalanceExamLog)
    {
        baseBalanceExamLog.setUpdateTime(DateUtils.getNowDate());
        return baseBalanceExamLogMapper.updateBaseBalanceExamLog(baseBalanceExamLog);
    }

    /**
     * 批量删除base_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNos 需要删除的base_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceExamLogByIds(Long[] serialNos)
    {
        return baseBalanceExamLogMapper.deleteBaseBalanceExamLogByIds(serialNos);
    }

    /**
     * 删除base_balance_exam_log(服务结算审核日志)信息
     * 
     * @param serialNo base_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceExamLogById(Long serialNo)
    {
        return baseBalanceExamLogMapper.deleteBaseBalanceExamLogById(serialNo);
    }
}
