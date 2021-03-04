package com.paic.ehis.finance.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.finance.domain.FinanceSettleRecord;
import com.paic.ehis.finance.mapper.FinanceSettleRecordMapper;

import com.paic.ehis.finance.service.IFinanceSettleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 结算操作记录Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceSettleRecordServiceImpl implements IFinanceSettleRecordService 
{
    @Autowired
    private FinanceSettleRecordMapper financeSettleRecordMapper;

    /**
     * 查询结算操作记录
     * 
     * @param recordId 结算操作记录ID
     * @return 结算操作记录
     */
    @Override
    public FinanceSettleRecord selectFinanceSettleRecordById(Long recordId)
    {
        return financeSettleRecordMapper.selectFinanceSettleRecordById(recordId);
    }

    /**
     * 查询结算操作记录列表
     * 
     * @param financeSettleRecord 结算操作记录
     * @return 结算操作记录
     */
    @Override
    public List<FinanceSettleRecord> selectFinanceSettleRecordList(FinanceSettleRecord financeSettleRecord)
    {
        return financeSettleRecordMapper.selectFinanceSettleRecordList(financeSettleRecord);
    }

    /**
     * 新增结算操作记录
     * 
     * @param financeSettleRecord 结算操作记录
     * @return 结果
     */
    @Override
    public int insertFinanceSettleRecord(FinanceSettleRecord financeSettleRecord)
    {
        financeSettleRecord.setCreateTime(DateUtils.getNowDate());
        return financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
    }

    /**
     * 修改结算操作记录
     * 
     * @param financeSettleRecord 结算操作记录
     * @return 结果
     */
    @Override
    public int updateFinanceSettleRecord(FinanceSettleRecord financeSettleRecord)
    {
        financeSettleRecord.setUpdateTime(DateUtils.getNowDate());
        return financeSettleRecordMapper.updateFinanceSettleRecord(financeSettleRecord);
    }

    /**
     * 批量删除结算操作记录
     * 
     * @param recordIds 需要删除的结算操作记录ID
     * @return 结果
     */
    @Override
    public int deleteFinanceSettleRecordByIds(Long[] recordIds)
    {
        return financeSettleRecordMapper.deleteFinanceSettleRecordByIds(recordIds);
    }

    /**
     * 删除结算操作记录信息
     * 
     * @param recordId 结算操作记录ID
     * @return 结果
     */
    @Override
    public int deleteFinanceSettleRecordById(Long recordId)
    {
        return financeSettleRecordMapper.deleteFinanceSettleRecordById(recordId);
    }
}
