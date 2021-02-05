package com.paic.ehis.order.mapper;


import com.paic.ehis.order.domain.ServiceBalanceInvoice;

import java.util.List;

/**
 * service_balance_invoice(服务结算发票信息)Mapper接口
 * 
 * @author chenhaiming
 * @date 2021-01-27
 */
public interface ServiceBalanceInvoiceMapper 
{
    /**
     * 查询service_balance_invoice(服务结算发票信息)
     * 
     * @param serialNo service_balance_invoice(服务结算发票信息)ID
     * @return service_balance_invoice(服务结算发票信息)
     */
    public ServiceBalanceInvoice selectServiceBalanceInvoiceById(Long serialNo);

    /**
     * 查询service_balance_invoice(服务结算发票信息)列表
     * 
     * @param serviceBalanceInvoice service_balance_invoice(服务结算发票信息)
     * @return service_balance_invoice(服务结算发票信息)集合
     */
    public List<ServiceBalanceInvoice> selectServiceBalanceInvoiceList(ServiceBalanceInvoice serviceBalanceInvoice);

    /**
     * 新增service_balance_invoice(服务结算发票信息)
     * 
     * @param serviceBalanceInvoice service_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    public int insertServiceBalanceInvoice(ServiceBalanceInvoice serviceBalanceInvoice);

    /**
     * 修改service_balance_invoice(服务结算发票信息)
     * 
     * @param serviceBalanceInvoice service_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    public int updateServiceBalanceInvoice(ServiceBalanceInvoice serviceBalanceInvoice);

    /**
     * 删除service_balance_invoice(服务结算发票信息)
     * 
     * @param serialNo service_balance_invoice(服务结算发票信息)ID
     * @return 结果
     */
    public int deleteServiceBalanceInvoiceById(Long serialNo);

    /**
     * 批量删除service_balance_invoice(服务结算发票信息)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceBalanceInvoiceByIds(Long[] serialNos);
}
