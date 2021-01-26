package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseCheckInfo;
import com.paic.ehis.base.domain.BaseProviderInfo;
import com.paic.ehis.base.domain.vo.AddressInfo;
import com.paic.ehis.base.domain.vo.AddressVO;
import com.paic.ehis.base.domain.vo.Adress;
import com.paic.ehis.base.domain.vo.ProviderInfoVo;

import java.util.ArrayList;
import java.util.List;

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
     * 查询base_provider_info_bak(服务商基本信息)列表
     * 
     * @param baseProviderInfo base_provider_info_bak(服务商基本信息)
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<BaseProviderInfo> selectBaseProviderInfoList(BaseProviderInfo baseProviderInfo);

    /**
     * 查询base_provider_info(服务商基本信息)列表

     */
    public List<BaseProviderInfo> selectBaseProviderInfoListNew(BaseProviderInfo baseProviderInfo);



    /**
     * 查询base_provider_info中省的信息
     *
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<AddressInfo> getprovince();

    public List<BaseProviderInfo> selectProvideInfoList(BaseProviderInfo baseProviderInfo);

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


    /**
     * 新增base_provider_info(提交待审核状态)
     *
     * @param providerInfoVo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int insertCheckInfo(ProviderInfoVo providerInfoVo);

    /**
     * 查询审核信息列表
     *
     * @param baseProviderInfo base_provider_info(服务商基本信息)
     * @return base_provider_info(服务商基本信息)集合
     */
    public List<BaseProviderInfo> selectBaseProviderInfoCheckList(BaseProviderInfo baseProviderInfo);


    /**
     * 新增base_provider_info(审核数据)
     *
     * @param providerInfoVo base_provider_info(服务商基本信息)
     * @return 结果
     */
    public int insertChecDatak(ProviderInfoVo providerInfoVo);


    /**
     * 新增base_provider_info(审核数据)
     *
     * @param providerCode base_provider_info(服务商基本信息)
     * @return 结果
     */
    public  List<BaseCheckInfo> selectBaseProviderCheckList(String providerCode);

    public  List<BaseProviderInfo> selectBaseProviderInfoByNames(BaseProviderInfo baseProviderInfo);

    public  List<BaseProviderInfo> selectBaseProviderInfoByNamesNew(BaseProviderInfo baseProviderInfo);

    public  List<BaseProviderInfo> selectBaseProviderInfos(BaseProviderInfo baseProviderInfo);

    public  List<BaseProviderInfo> selectHospitalInfo(BaseProviderInfo baseProviderInfo);


}
