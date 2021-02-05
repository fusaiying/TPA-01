package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.order.domain.CustomerInfo;
import com.paic.ehis.order.mapper.CustomerInfoMapper;
import com.paic.ehis.order.service.ICustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * customer_info(客户信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-26
 */
@Service
public class CustomerInfoServiceImpl implements ICustomerInfoService 
{
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    /**
     * 查询customer_info(客户信息)
     * 
     * @param orderCode customer_info(客户信息)ID
     * @return customer_info(客户信息)
     */
    @Override
    public CustomerInfo selectCustomerInfoById(String orderCode)
    {
        CustomerInfo customerInfo = customerInfoMapper.selectCustomerInfoByIdNew(orderCode);
        return customerInfo;
    }

    /**
     * 查询customer_info(客户信息)列表
     * 
     * @param customerInfo customer_info(客户信息)
     * @return customer_info(客户信息)
     */
    @Override
    public List<CustomerInfo> selectCustomerInfoList(CustomerInfo customerInfo)
    {
        return customerInfoMapper.selectCustomerInfoList(customerInfo);
    }

    /**
     * 新增customer_info(客户信息)
     * 
     * @param customerInfo customer_info(客户信息)
     * @return 结果
     */
    @Override
    public int insertCustomerInfo(CustomerInfo customerInfo)
    {
        customerInfo.setCreateTime(DateUtils.getNowDate());
        return customerInfoMapper.insertCustomerInfo(customerInfo);
    }

    /**
     * 修改customer_info(客户信息)
     * 
     * @param customerInfo customer_info(客户信息)
     * @return 结果
     */
    @Override
    public int updateCustomerInfo(CustomerInfo customerInfo)
    {
        customerInfo.setUpdateTime(DateUtils.getNowDate());
        return customerInfoMapper.updateCustomerInfo(customerInfo);
    }

    /**
     * 批量删除customer_info(客户信息)
     * 
     * @param serialNos 需要删除的customer_info(客户信息)ID
     * @return 结果
     */
    @Override
    public int deleteCustomerInfoByIds(String[] serialNos)
    {
        return customerInfoMapper.deleteCustomerInfoByIds(serialNos);
    }

    /**
     * 删除customer_info(客户信息)信息
     * 
     * @param serialNo customer_info(客户信息)ID
     * @return 结果
     */
    @Override
    public int deleteCustomerInfoById(String serialNo)
    {
        return customerInfoMapper.deleteCustomerInfoById(serialNo);
    }

}
