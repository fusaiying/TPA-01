package com.paic.ehis.finance.service;
import com.paic.ehis.finance.domain.FinanceSettleRecord;
import java.util.List;

/**
 * 结算操作记录Service接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface IFinanceSettleRecordService 
{
    /**
     * 查询结算操作记录
     * 
     * @param recordId 结算操作记录ID
     * @return 结算操作记录
     */
    public FinanceSettleRecord selectFinanceSettleRecordById(Long recordId);

    /**
     * 查询结算操作记录列表
     * 
     * @param financeSettleRecord 结算操作记录
     * @return 结算操作记录集合
     */
    public List<FinanceSettleRecord> selectFinanceSettleRecordList(FinanceSettleRecord financeSettleRecord);

    /**
     * 新增结算操作记录
     * 
     * @param financeSettleRecord 结算操作记录
     * @return 结果
     */
    public int insertFinanceSettleRecord(FinanceSettleRecord financeSettleRecord);

    /**
     * 修改结算操作记录
     * 
     * @param financeSettleRecord 结算操作记录
     * @return 结果
     */
    public int updateFinanceSettleRecord(FinanceSettleRecord financeSettleRecord);

    /**
     * 批量删除结算操作记录
     * 
     * @param recordIds 需要删除的结算操作记录ID
     * @return 结果
     */
    public int deleteFinanceSettleRecordByIds(Long[] recordIds);

    /**
     * 删除结算操作记录信息
     * 
     * @param recordId 结算操作记录ID
     * @return 结果
     */
    public int deleteFinanceSettleRecordById(Long recordId);
}
