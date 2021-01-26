package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseProviderSettle;

import java.util.List;

/**
 * base_provider_settle(服务商结算信息)Service接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface IBaseProviderSettleService 
{
    /**
     * 查询base_provider_settle(服务商结算信息)
     * 
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return base_provider_settle(服务商结算信息)
     */
    public BaseProviderSettle selectBaseProviderSettleById(String providercode);

    /**
     * 查询base_provider_settle(服务商结算信息)列表
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return base_provider_settle(服务商结算信息)集合
     */
    public List<BaseProviderSettle> selectBaseProviderSettleList(BaseProviderSettle baseProviderSettle);

    /**
     * 新增base_provider_settle(服务商结算信息)
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int insertBaseProviderSettle(BaseProviderSettle baseProviderSettle);

    /**
     * 修改base_provider_settle(服务商结算信息)
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int updateBaseProviderSettle(BaseProviderSettle baseProviderSettle);

    /**
     * 批量删除base_provider_settle(服务商结算信息)
     * 
     * @param providercodes 需要删除的base_provider_settle(服务商结算信息)ID
     * @return 结果
     */
    public int deleteBaseProviderSettleByIds(String[] providercodes);

    /**
     * 删除base_provider_settle(服务商结算信息)信息
     * 
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return 结果
     */
    public int deleteBaseProviderSettleById(String providercode);
}
