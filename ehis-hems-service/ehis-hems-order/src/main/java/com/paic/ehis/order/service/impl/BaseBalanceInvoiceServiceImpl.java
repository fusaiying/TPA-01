package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.order.domain.BaseBalanceInvoice;
import com.paic.ehis.order.mapper.BaseBalanceInvoiceMapper;
import com.paic.ehis.order.service.IBaseBalanceInvoiceService;
/*
import com.paic.ehis.order.service.enumeration.StatusEnum;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_balance_invoice(服务结算发票信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-27
 */
@Service
public class BaseBalanceInvoiceServiceImpl implements IBaseBalanceInvoiceService 
{
    @Autowired
    private BaseBalanceInvoiceMapper baseBalanceInvoiceMapper;

    /**
     * 查询base_balance_invoice(服务结算发票信息)
     * 
     * @param serialNo base_balance_invoice(服务结算发票信息)ID
     * @return base_balance_invoice(服务结算发票信息)
     */
    @Override
    public BaseBalanceInvoice selectBaseBalanceInvoiceById(Long serialNo)
    {
        return baseBalanceInvoiceMapper.selectBaseBalanceInvoiceById(serialNo);
    }

    /**
     * 查询base_balance_invoice(服务结算发票信息)列表
     * 
     * @param baseBalanceInvoice base_balance_invoice(服务结算发票信息)
     * @return base_balance_invoice(服务结算发票信息)
     */
    @Override
    public List<BaseBalanceInvoice> selectBaseBalanceInvoiceList(BaseBalanceInvoice baseBalanceInvoice)
    {
        return baseBalanceInvoiceMapper.selectBaseBalanceInvoiceList(baseBalanceInvoice);
    }

    /**
     * 新增base_balance_invoice(服务结算发票信息)
     * 
     * @param baseBalanceInvoice base_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    @Override
    public int insertBaseBalanceInvoice(BaseBalanceInvoice baseBalanceInvoice)
    {
        baseBalanceInvoice.setCreateTime(DateUtils.getNowDate());
        baseBalanceInvoice.setUpdateTime(DateUtils.getNowDate());
        baseBalanceInvoice.setCreateBy(SecurityUtils.getUsername());
        baseBalanceInvoice.setUpdateBy(SecurityUtils.getUsername());
      /*  baseBalanceInvoice.setStatus(StatusEnum.VALID.getCode());
        //todo:发票认证接口
        baseBalanceInvoice.setAuth(StatusEnum.VALID.getCode());*/
        baseBalanceInvoice.setInvoiceDate(DateUtils.parseDate(DateUtils.getDate()));
        return baseBalanceInvoiceMapper.insertBaseBalanceInvoice(baseBalanceInvoice);
    }

    /**
     * 修改base_balance_invoice(服务结算发票信息)
     * 
     * @param baseBalanceInvoice base_balance_invoice(服务结算发票信息)
     * @return 结果
     */
    @Override
    public int updateBaseBalanceInvoice(BaseBalanceInvoice baseBalanceInvoice)
    {
        baseBalanceInvoice.setUpdateTime(DateUtils.getNowDate());
        baseBalanceInvoice.setUpdateBy(SecurityUtils.getUsername());
        //todo:发票认证接口
       // baseBalanceInvoice.setAuth(StatusEnum.VALID.getCode());
        baseBalanceInvoice.setInvoiceDate(DateUtils.parseDate(DateUtils.getDate()));
        return baseBalanceInvoiceMapper.updateBaseBalanceInvoice(baseBalanceInvoice);
    }

    /**
     * 批量删除base_balance_invoice(服务结算发票信息)
     * 
     * @param serialNos 需要删除的base_balance_invoice(服务结算发票信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceInvoiceByIds(Long[] serialNos)
    {
        return baseBalanceInvoiceMapper.deleteBaseBalanceInvoiceByIds(serialNos);
    }

    /**
     * 删除base_balance_invoice(服务结算发票信息)信息
     * 
     * @param serialNo base_balance_invoice(服务结算发票信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceInvoiceById(Long serialNo)
    {
        return baseBalanceInvoiceMapper.deleteBaseBalanceInvoiceById(serialNo);
    }
}
