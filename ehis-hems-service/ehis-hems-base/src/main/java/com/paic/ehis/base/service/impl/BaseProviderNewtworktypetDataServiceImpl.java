package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseProviderNewtworktypetData;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypetDataMapper;
import com.paic.ehis.base.service.IBaseProviderNewtworktypetDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * base_provider_newtworktypet_data(医疗网络类型信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-04
 */
@Service
@Transactional
public class BaseProviderNewtworktypetDataServiceImpl implements IBaseProviderNewtworktypetDataService 
{
    @Autowired
    private BaseProviderNewtworktypetDataMapper baseProviderNewtworktypetDataMapper;

    /**
     * 查询base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param serNo base_provider_newtworktypet_data(医疗网络类型信息)ID
     * @return base_provider_newtworktypet_data(医疗网络类型信息)
     */
    @Override
    public BaseProviderNewtworktypetData selectBaseProviderNewtworktypetDataById(String serNo)
    {
        return baseProviderNewtworktypetDataMapper.selectBaseProviderNewtworktypetDataById(serNo);
    }

    /**
     * 查询base_provider_newtworktypet_data(医疗网络类型信息)列表
     * 
     * @param baseProviderNewtworktypetData base_provider_newtworktypet_data(医疗网络类型信息)
     * @return base_provider_newtworktypet_data(医疗网络类型信息)
     */
    @Override
    public List<BaseProviderNewtworktypetData> selectBaseProviderNewtworktypetDataList(BaseProviderNewtworktypetData baseProviderNewtworktypetData)
    {
        return baseProviderNewtworktypetDataMapper.selectBaseProviderNewtworktypetDataList(baseProviderNewtworktypetData);
    }

    /**
     * 新增base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param baseProviderNewtworktypetData base_provider_newtworktypet_data(医疗网络类型信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderNewtworktypetData(BaseProviderNewtworktypetData baseProviderNewtworktypetData)
    {
        baseProviderNewtworktypetData.setCreateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypetDataMapper.insertBaseProviderNewtworktypetData(baseProviderNewtworktypetData);
    }

    /**
     * 修改base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param baseProviderNewtworktypetData base_provider_newtworktypet_data(医疗网络类型信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderNewtworktypetData(BaseProviderNewtworktypetData baseProviderNewtworktypetData)
    {
        baseProviderNewtworktypetData.setUpdateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypetDataMapper.updateBaseProviderNewtworktypetData(baseProviderNewtworktypetData);
    }

    /**
     * 批量删除base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param serNos 需要删除的base_provider_newtworktypet_data(医疗网络类型信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypetDataByIds(String[] serNos)
    {
        return baseProviderNewtworktypetDataMapper.deleteBaseProviderNewtworktypetDataByIds(serNos);
    }

    /**
     * 删除base_provider_newtworktypet_data(医疗网络类型信息)信息
     * 
     * @param serNo base_provider_newtworktypet_data(医疗网络类型信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypetDataById(String serNo)
    {
        return baseProviderNewtworktypetDataMapper.deleteBaseProviderNewtworktypetDataById(serNo);
    }
}
