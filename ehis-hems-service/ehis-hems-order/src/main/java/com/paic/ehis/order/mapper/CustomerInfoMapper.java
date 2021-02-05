package com.paic.ehis.order.mapper;

import com.paic.ehis.order.domain.CustomerInfo;

import java.util.List;

/**
 * customer_info(客户信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-26
 */
public interface CustomerInfoMapper 
{
    /**
     * 查询customer_info(客户信息)
     * 
     * @param serialNo customer_info(客户信息)ID
     * @return customer_info(客户信息)
     */
    public CustomerInfo selectCustomerInfoById(String serialNo);

    public CustomerInfo selectCustomerInfoByIdNew(String orderCode);

    /**
     * 查询customer_info(客户信息)列表
     * 
     * @param customerInfo customer_info(客户信息)
     * @return customer_info(客户信息)集合
     */
    public List<CustomerInfo> selectCustomerInfoList(CustomerInfo customerInfo);

    /**
     * 新增customer_info(客户信息)
     * 
     * @param customerInfo customer_info(客户信息)
     * @return 结果
     */
    public int insertCustomerInfo(CustomerInfo customerInfo);

    /**
     * 修改customer_info(客户信息)
     * 
     * @param customerInfo customer_info(客户信息)
     * @return 结果
     */
    public int updateCustomerInfo(CustomerInfo customerInfo);

    /**
     * 删除customer_info(客户信息)
     * 
     * @param serialNo customer_info(客户信息)ID
     * @return 结果
     */
    public int deleteCustomerInfoById(String serialNo);

    /**
     * 批量删除customer_info(客户信息)
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerInfoByIds(String[] serialNos);
}
