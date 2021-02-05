package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.order.domain.ServiceBalanceInvoice;
import com.paic.ehis.order.mapper.ServiceBalanceInvoiceMapper;
import com.paic.ehis.order.service.IServiceBalanceInvoiceService;
import com.paic.ehis.order.service.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service_balance_invoice(服务结算发票信息)Service业务层处理
 * 
 * @author chenhaiming
 * @date 2021-01-27
 */
@Service
public class ServiceBalanceInvoiceServiceImpl implements IServiceBalanceInvoiceService
{
    @Autowired
    private ServiceBalanceInvoiceMapper serviceBalanceInvoiceMapper;

    /**
     * 查询service_balance_invoice(服务结算发票信息)
     * 
     * @param serialNo service_balance_invoice(服务结算发票信息)ID
     * @return service_balance_invoice(服务结算发票信息)
     */
    @Override
    public ServiceBalanceInvoice selectServiceBalanceInvoiceById(Long serialNo)
    {
        return serviceBalanceInvoiceMapper.selectServiceBalanceInvoiceById(serialNo);
    }

    /**
     * 查询service_balance_invoice(服务结算发票信息)列表
     * 
     * @param serviceBalanceInvoice service_balance_invoice(服务结算发票信息)
     * @return service_balance_invoice(服务结算发票信息)
     */
    @Override
    public List<ServiceBalanceInvoice> selectServiceBalanceInvoiceList(ServiceBalanceInvoice serviceBalanceInvoice)
    {
        return serviceBalanceInvoiceMapper.selectServiceBalanceInvoiceList(serviceBalanceInvoice);
    }

    /**
     * 新增service_balance_invoice(服务结算发票信息)
     * 
     * @param serviceBalanceInvoice service_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    @Override
    public int insertServiceBalanceInvoice(ServiceBalanceInvoice serviceBalanceInvoice)
    {
        serviceBalanceInvoice.setCreateTime(DateUtils.getNowDate());
        serviceBalanceInvoice.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceInvoice.setCreateBy(SecurityUtils.getUsername());
        serviceBalanceInvoice.setUpdateBy(SecurityUtils.getUsername());
        serviceBalanceInvoice.setStatus(StatusEnum.VALID.getCode());
        //todo:发票认证接口
        serviceBalanceInvoice.setAuth(StatusEnum.VALID.getCode());
        serviceBalanceInvoice.setInvoiceDate(DateUtils.parseDate(DateUtils.getDate()));
        return serviceBalanceInvoiceMapper.insertServiceBalanceInvoice(serviceBalanceInvoice);
    }

    /**
     * 修改service_balance_invoice(服务结算发票信息)
     * 
     * @param serviceBalanceInvoice service_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    @Override
    public int updateServiceBalanceInvoice(ServiceBalanceInvoice serviceBalanceInvoice)
    {
        serviceBalanceInvoice.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceInvoice.setUpdateBy(SecurityUtils.getUsername());
        //todo:发票认证接口
        serviceBalanceInvoice.setAuth(StatusEnum.VALID.getCode());
        serviceBalanceInvoice.setInvoiceDate(DateUtils.parseDate(DateUtils.getDate()));
        return serviceBalanceInvoiceMapper.updateServiceBalanceInvoice(serviceBalanceInvoice);
    }

    /**
     * 批量删除service_balance_invoice(服务结算发票信息)
     * 
     * @param serialNos 需要删除的service_balance_invoice(服务结算发票信息)ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceInvoiceByIds(Long[] serialNos)
    {
        return serviceBalanceInvoiceMapper.deleteServiceBalanceInvoiceByIds(serialNos);
    }

    /**
     * 删除service_balance_invoice(服务结算发票信息)信息
     * 
     * @param serialNo service_balance_invoice(服务结算发票信息)ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceInvoiceById(Long serialNo)
    {
        return serviceBalanceInvoiceMapper.deleteServiceBalanceInvoiceById(serialNo);
    }
}
