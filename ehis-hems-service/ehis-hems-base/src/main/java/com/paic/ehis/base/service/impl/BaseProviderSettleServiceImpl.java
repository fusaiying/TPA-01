package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.mapper.BaseProviderSettleMapper;
import com.paic.ehis.base.service.IBaseProviderSettleService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.domain.BaseProviderSettle;

/**
 * base_provider_settle(服务商结算信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseProviderSettleServiceImpl implements IBaseProviderSettleService
{
    @Autowired
    private BaseProviderSettleMapper baseProviderSettleMapper;

    /**
     * 查询base_provider_settle(服务商结算信息)
     * 
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return base_provider_settle(服务商结算信息)
     */
    @Override
    public List<BaseProviderSettle> selectBaseProviderSettleById(String providercode)
    {
        return baseProviderSettleMapper.selectBaseProviderSettleById(providercode);
    }

    /**
     * 查询base_provider_settle(服务商结算信息)列表
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return base_provider_settle(服务商结算信息)
     */
    @Override
    public List<BaseProviderSettle> selectBaseProviderSettleList(BaseProviderSettle baseProviderSettle)
    {
        return baseProviderSettleMapper.selectBaseProviderSettleList(baseProviderSettle);
    }

    /**
     * 新增base_provider_settle(服务商结算信息)
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderSettle(BaseProviderSettle baseProviderSettle)
    {
        baseProviderSettle.setCreateTime(DateUtils.getNowDate());
        return baseProviderSettleMapper.insertBaseProviderSettle(baseProviderSettle);
    }

    /**
     * 修改base_provider_settle(服务商结算信息)
     * 
     * @param baseProviderSettle base_provider_settle(服务商结算信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderSettle(BaseProviderSettle baseProviderSettle)
    {
        baseProviderSettle.setUpdateTime(DateUtils.getNowDate());
        return baseProviderSettleMapper.updateBaseProviderSettle(baseProviderSettle);
    }

    /**
     * 批量删除base_provider_settle(服务商结算信息)
     * 
     * @param providercodes 需要删除的base_provider_settle(服务商结算信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderSettleByIds(String[] providercodes)
    {
        return baseProviderSettleMapper.deleteBaseProviderSettleByIds(providercodes);
    }

    /**
     * 删除base_provider_settle(服务商结算信息)信息
     * 
     * @param providercode base_provider_settle(服务商结算信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderSettleById(String providercode)
    {
        return baseProviderSettleMapper.deleteBaseProviderSettleById(providercode);
    }
}
