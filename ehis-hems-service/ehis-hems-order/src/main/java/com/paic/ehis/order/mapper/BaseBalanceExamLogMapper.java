package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.BaseBalanceExamLog;

import java.util.List;

/**
 * base_balance_exam_log(服务结算审核日志)Mapper接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface BaseBalanceExamLogMapper 
{
    /**
     * 查询base_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNo base_balance_exam_log(服务结算审核日志)ID
     * @return base_balance_exam_log(服务结算审核日志)
     */
    public BaseBalanceExamLog selectBaseBalanceExamLogById(Long serialNo);

    /**
     * 查询base_balance_exam_log(服务结算审核日志)列表
     * 
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return base_balance_exam_log(服务结算审核日志)集合
     */
    public List<BaseBalanceExamLog> selectBaseBalanceExamLogList(BaseBalanceExamLog baseBalanceExamLog);

    /**
     * 新增base_balance_exam_log(服务结算审核日志)
     * 
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    public int insertBaseBalanceExamLog(BaseBalanceExamLog baseBalanceExamLog);

    /**
     * 修改base_balance_exam_log(服务结算审核日志)
     * 
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    public int updateBaseBalanceExamLog(BaseBalanceExamLog baseBalanceExamLog);

    /**
     * 删除base_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNo base_balance_exam_log(服务结算审核日志)ID
     * @return 结果
     */
    public int deleteBaseBalanceExamLogById(Long serialNo);

    /**
     * 批量删除base_balance_exam_log(服务结算审核日志)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseBalanceExamLogByIds(Long[] serialNos);

    /**
     * base_balance_exam_log(服务结算审核日志)
     * @param baseBalanceExamLog base_balance_exam_log(服务结算审核日志)
     * @return 结果
     */
    public int updateBaseBalanceExamLogByTaskNo(BaseBalanceExamLog baseBalanceExamLog);
}
