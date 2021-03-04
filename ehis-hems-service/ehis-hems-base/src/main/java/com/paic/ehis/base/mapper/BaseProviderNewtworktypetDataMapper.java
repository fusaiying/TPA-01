package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderNewtworktypetData;

import java.util.List;

/**
 * base_provider_newtworktypet_data(医疗网络类型信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface BaseProviderNewtworktypetDataMapper 
{
    /**
     * 查询base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param serNo base_provider_newtworktypet_data(医疗网络类型信息)ID
     * @return base_provider_newtworktypet_data(医疗网络类型信息)
     */
    public BaseProviderNewtworktypetData selectBaseProviderNewtworktypetDataById(String serNo);

    /**
     * 查询base_provider_newtworktypet_data(医疗网络类型信息)列表
     * 
     * @param baseProviderNewtworktypetData base_provider_newtworktypet_data(医疗网络类型信息)
     * @return base_provider_newtworktypet_data(医疗网络类型信息)集合
     */
    public List<BaseProviderNewtworktypetData> selectBaseProviderNewtworktypetDataList(BaseProviderNewtworktypetData baseProviderNewtworktypetData);

    /**
     * 新增base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param baseProviderNewtworktypetData base_provider_newtworktypet_data(医疗网络类型信息)
     * @return 结果
     */
    public int insertBaseProviderNewtworktypetData(BaseProviderNewtworktypetData baseProviderNewtworktypetData);

    /**
     * 修改base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param baseProviderNewtworktypetData base_provider_newtworktypet_data(医疗网络类型信息)
     * @return 结果
     */
    public int updateBaseProviderNewtworktypetData(BaseProviderNewtworktypetData baseProviderNewtworktypetData);

    /**
     * 删除base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param serNo base_provider_newtworktypet_data(医疗网络类型信息)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetDataById(String serNo);

    /**
     * 批量删除base_provider_newtworktypet_data(医疗网络类型信息)
     * 
     * @param serNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetDataByIds(String[] serNos);
}
