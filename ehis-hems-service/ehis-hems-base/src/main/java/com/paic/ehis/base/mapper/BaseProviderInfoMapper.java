package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseCheckInfo;
import com.paic.ehis.base.domain.BaseProviderInfo;
import com.paic.ehis.base.domain.vo.AddressInfo;
import com.paic.ehis.base.domain.vo.Sn_base_addressDO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

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
     * @param providercode base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    public BaseProviderInfo selectBaseProviderInfoByIdNew(String providercode);


    /**
     * 查询base_provider_info(服务商基本信息)
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)ID
     * @return base_provider_info(服务商基本信息)
     */
    public List<BaseProviderInfo> selectBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    public List<BaseProviderInfo> selectBaseProviderInfoNew(BaseProviderInfo baseProviderInfo);

    /**
     * 查询base_provider_info(服务商基本信息)列表
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<BaseProviderInfo> selectBaseProviderInfoList(BaseProviderInfo baseProviderInfo);

    /**
     * 查询base_provider_info(服务商基本信息)列表
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<BaseProviderInfo> selectBaseProviderInfoListNew(BaseProviderInfo baseProviderInfo);


    /**
     * 查询审核信息
     *
     */
    public List<BaseCheckInfo> selectBaseProviderCheckList(String proviceCode);


    /**
     * 查询base_provider_info(服务商基本信息)列表
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<BaseProviderInfo> selectBaseProviderInfoCheckList(BaseProviderInfo baseProviderInfo);


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


    public int insertBaseProviderInfoNew(BaseProviderInfo baseProviderInfo);

    /**
     * 修改base_provider_info(服务商基本信息)
     * 
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int updateBaseProviderInfo(BaseProviderInfo baseProviderInfo);

    /**
     * 医院状态修改成有效
     */
    public int updateBaseProviderInfoByproviderCode(String providerCode);

    /**
     * 医院状态修改成无效
     */
    public int updateBaseProviderInfoStatus(String providerCode);


    /**
     * 医院状态修改成有效
     */
    public int updateBaseProviderInfoByproviderCodeNew(String providerCode);


    /**
     * 根据流水号修改base_provider_info(服务商基本信息)
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int updateBaseProviderInfoByCode(BaseProviderInfo baseProviderInfo);

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


    public int insertCheckInfo(BaseCheckInfo baseCheckInfo);

    List<BaseProviderInfo> selectProvideInfoList(BaseProviderInfo baseProviderInfo);

    public String selectName(String  province);

    public String selectName1(String  providerCode);

    public String selectNum(String  province);

    public  List<BaseProviderInfo> selectBaseProviderInfos(BaseProviderInfo baseProviderInfo);

    public  List<BaseProviderInfo> selectHospitalInfo(BaseProviderInfo baseProviderInfo);
}
