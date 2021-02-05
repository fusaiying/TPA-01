package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.BaseBalanceInvoice;

import java.util.List;

/**
 * base_balance_invoice(服务结算发票信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-27
 */
public interface BaseBalanceInvoiceMapper 
{
    /**
     * 查询base_balance_invoice(服务结算发票信息)
     * 
     * @param serialNo base_balance_invoice(服务结算发票信息)ID
     * @return base_balance_invoice(服务结算发票信息)
     */
    public BaseBalanceInvoice selectBaseBalanceInvoiceById(Long serialNo);

    /**
     * 查询base_balance_invoice(服务结算发票信息)列表
     * 
     * @param baseBalanceInvoice base_balance_invoice(服务结算发票信息)
     * @return base_balance_invoice(服务结算发票信息)集合
     */
    public List<BaseBalanceInvoice> selectBaseBalanceInvoiceList(BaseBalanceInvoice baseBalanceInvoice);

    /**
     * 新增base_balance_invoice(服务结算发票信息)
     * 
     * @param baseBalanceInvoice base_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    public int insertBaseBalanceInvoice(BaseBalanceInvoice baseBalanceInvoice);

    /**
     * 修改base_balance_invoice(服务结算发票信息)
     * 
     * @param baseBalanceInvoice base_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    public int updateBaseBalanceInvoice(BaseBalanceInvoice baseBalanceInvoice);

    /**
     * 删除base_balance_invoice(服务结算发票信息)
     * 
     * @param serialNo base_balance_invoice(服务结算发票信息)ID
     * @return 结果
     */
    public int deleteBaseBalanceInvoiceById(Long serialNo);

    /**
     * 批量删除base_balance_invoice(服务结算发票信息)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseBalanceInvoiceByIds(Long[] serialNos);
}
