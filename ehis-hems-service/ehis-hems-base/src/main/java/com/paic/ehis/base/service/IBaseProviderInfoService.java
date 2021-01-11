package com.paic.ehis.base.service;

import java.util.ArrayList;
import java.util.List;
import com.paic.ehis.base.domain.BaseProviderInfo;
import com.paic.ehis.base.domain.vo.AddressInfo;
import com.paic.ehis.base.domain.vo.AddressVO;
import com.paic.ehis.base.domain.vo.Adress;

/**
 * base_provider_info(服务商基本信息)Service接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface IBaseProviderInfoService 
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
     * 查询base_provider_info中省的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<AddressInfo> getprovince();

    /**
     * 查询base_provider_info中省市区的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public ArrayList<AddressVO> getAddress();


    /**
     * 查询base_provider_info中市或者区的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<AddressInfo> getplace(String placecode);

    /**
     * 获取省市区
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<Adress> selectAdressList();

    /**
     * 新增base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public BaseProviderInfo insertBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    /**
     * 修改base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int updateBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    /**
     * 批量删除base_provider_info(服务商基本信息)
     * 
     * @param providercodes 需要删除的base_provider_info(服务商基本信息)ID
     * @return 结果
     */
    public int deleteBaseProviderInfoByIds(String[] providercodes);

    /**
     * 删除base_provider_info(服务商基本信息)信息
     * 
     * @param providercode base_provider_info(服务商基本信息)ID
     * @return 结果
     */
    public int deleteBaseProviderInfoById(String providercode);
}
