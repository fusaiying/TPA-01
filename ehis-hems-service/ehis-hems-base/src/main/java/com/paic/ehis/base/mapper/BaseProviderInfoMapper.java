package com.paic.ehis.base.mapper;

import java.util.List;
import com.paic.ehis.base.domain.BaseProviderInfo;
import com.paic.ehis.base.domain.vo.AddressInfo;
import com.paic.ehis.base.domain.vo.Sn_base_addressDO;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * base_provider_info(服务商基本信息)Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseProviderInfoMapper 
{
    /**
     * 查询base_provider_info(服务商基本信息)
     * 
     * @param providercode base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    public BaseProviderInfo selectBaseProviderInfoById(String providercode);


    /**
     * 查询base_provider_info(服务商基本信息)
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    public BaseProviderInfo selectBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    /**
     * 查询base_provider_info(服务商基本信息)列表
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<BaseProviderInfo> selectBaseProviderInfoList(BaseProviderInfo baseProviderInfo);


    /**
     * 查询省的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<AddressInfo> selectprovince();


    /**
     * 查询城市的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<AddressInfo> selectcity(@Param("upplacename") String upplacename);

    /**
     * 查询城市的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<Sn_base_addressDO> selectAllByPlacetype(@Param("placetype") String placetype);


    /**
     * 新增base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int insertBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    /**
     * 修改base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int updateBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    /**
     * 删除base_provider_info(服务商基本信息)
     * 
     * @param providercode base_provider_info(服务商基本信息)ID
     * @return 结果
     */
    public int deleteBaseProviderInfoById(String providercode);

    /**
     * 批量删除base_provider_info(服务商基本信息)
     * 
     * @param providercodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderInfoByIds(String[] providercodes);
}
