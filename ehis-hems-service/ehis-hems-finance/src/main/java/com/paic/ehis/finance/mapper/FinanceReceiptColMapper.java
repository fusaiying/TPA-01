package com.paic.ehis.finance.mapper;


import com.paic.ehis.finance.domain.FinanceCollectionInfo;
import com.paic.ehis.finance.domain.FinanceReceiptCol;

import java.util.List;

/**
 * 收款核销Mapper接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface FinanceReceiptColMapper 
{
    /**
     * 查询收款核销
     * 
     * @param colId 收款核销ID
     * @return 收款核销
     */
    public FinanceReceiptCol selectFinanceReceiptColById(Long colId);

    /**
     * 查询收款核销列表
     * 
     * @param financeReceiptCol 收款核销
     * @return 收款核销集合
     */
    public List<FinanceReceiptCol> selectFinanceReceiptColList(FinanceReceiptCol financeReceiptCol);

    /**
     * 新增收款核销
     * 
     * @param financeReceiptCol 收款核销
     * @return 结果
     */
    public int insertFinanceReceiptCol(FinanceReceiptCol financeReceiptCol);

    /**
     * 修改收款核销
     * 
     * @param financeReceiptCol 收款核销
     * @return 结果
     */
    public int updateFinanceReceiptCol(FinanceReceiptCol financeReceiptCol);

    /**
     * 删除收款核销
     * 
     * @param colId 收款核销ID
     * @return 结果
     */
    public int deleteFinanceReceiptColById(Long colId);

    /**
     * 批量删除收款核销
     * 
     * @param colIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceReceiptColByIds(Long[] colIds);
}
