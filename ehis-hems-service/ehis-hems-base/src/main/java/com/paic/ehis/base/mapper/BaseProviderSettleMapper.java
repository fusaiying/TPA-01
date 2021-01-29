package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderSettle;

import java.util.List;

/**
 * base_provider_settle(服务商结算信息)Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseProviderSettleMapper 
{
    /**
     * 查询base_provider_settle(服务商结算信息)
     * 
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return base_provider_settle(服务商结算信息)
     */
    public BaseProviderSettle selectBaseProviderSettleById(String providercode);

    /**
     * 查询base_provider_settle(服务商结算信息)
     *
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return base_provider_settle(服务商结算信息)
     */
    public BaseProviderSettle selectBaseProviderSettleByIdNew(String providercode);

    /**
     * 查询base_provider_settle(服务商结算信息)列表
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return base_provider_settle(服务商结算信息)集合
     */
    public List<BaseProviderSettle> selectBaseProviderSettleList(BaseProviderSettle baseProviderSettle);

    public List<BaseProviderSettle> selectBaseProviderSettleListNew(BaseProviderSettle baseProviderSettle);

    /**
     * 新增base_provider_settle(服务商结算信息)
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int insertBaseProviderSettle(BaseProviderSettle baseProviderSettle);


    /**
     * 新增base_provider_settle(服务商结算信息)
     *
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int insertBaseProviderSettleNew(BaseProviderSettle baseProviderSettle);

    /**
     * 修改base_provider_settle(服务商结算信息)
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int updateBaseProviderSettle(BaseProviderSettle baseProviderSettle);


    /**
     * 通过serialNo修改base_provider_settle(服务商结算信息)
     *
     * @param providerCode base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int updateBaseProviderSettleByCode(String providerCode);


    /**
     * 通过serialNo修改base_provider_settle(服务商结算信息)
     *
     * @param providerCode base_provider_settle(服务商结算信息)
     * @return 结果
     */
    public int updateBaseProviderSettleByCodeNew(String providerCode);

    /**
     * 删除base_provider_settle(服务商结算信息)
     * 
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return 结果
     */
    public int deleteBaseProviderSettleById(String providercode);

    /**
     * 批量删除base_provider_settle(服务商结算信息)
     * 
     * @param providercodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderSettleByIds(String[] providercodes);
}
