package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseProviderWorktime;
import com.paic.ehis.base.mapper.BaseProviderWorktimeMapper;
import com.paic.ehis.base.service.IBaseProviderWorktimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * base_provider_worktime(服务商服务时间信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
@Transactional
public class BaseProviderWorktimeServiceImpl implements IBaseProviderWorktimeService 
{
    @Autowired
    private BaseProviderWorktimeMapper baseProviderWorktimeMapper;

    /**
     * 查询base_provider_worktime(服务商服务时间信息)
     * 
     * @param providercode base_provider_worktime(服务商服务时间信息)ID
     * @return base_provider_worktime(服务商服务时间信息)
     */
    @Override
    public List<BaseProviderWorktime> selectBaseProviderWorktimeById(String providercode)
    {
        return baseProviderWorktimeMapper.selectBaseProviderWorktimeById(providercode);
    }

    /**
     * 查询base_provider_worktime(服务商服务时间信息)列表
     * 
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return base_provider_worktime(服务商服务时间信息)
     */
    @Override
    public List<BaseProviderWorktime> selectBaseProviderWorktimeList(BaseProviderWorktime baseProviderWorktime)
    {
        return baseProviderWorktimeMapper.selectBaseProviderWorktimeList(baseProviderWorktime);
    }

    /**
     * 新增base_provider_worktime(服务商服务时间信息)
     * 
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderWorktime(BaseProviderWorktime baseProviderWorktime)
    {
        baseProviderWorktime.setCreateTime(DateUtils.getNowDate());
        return baseProviderWorktimeMapper.insertBaseProviderWorktime(baseProviderWorktime);
    }

    /**
     * 修改base_provider_worktime(服务商服务时间信息)
     * 
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderWorktime(BaseProviderWorktime baseProviderWorktime)
    {
        baseProviderWorktime.setUpdateTime(DateUtils.getNowDate());
        return baseProviderWorktimeMapper.updateBaseProviderWorktime(baseProviderWorktime);
    }

    /**
     * 批量删除base_provider_worktime(服务商服务时间信息)
     * 
     * @param providercodes 需要删除的base_provider_worktime(服务商服务时间信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderWorktimeByIds(String[] providercodes)
    {
        return baseProviderWorktimeMapper.deleteBaseProviderWorktimeByIds(providercodes);
    }

    /**
     * 删除base_provider_worktime(服务商服务时间信息)信息
     * 
     * @param providercode base_provider_worktime(服务商服务时间信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderWorktimeById(String providercode)
    {
        return baseProviderWorktimeMapper.deleteBaseProviderWorktimeById(providercode);
    }
}
