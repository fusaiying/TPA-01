package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseServiceInfo;

import java.util.List;

/**
 * base_service_info（服务项目）Mapper接口
 *
 * @author sino
 * @date 2020-12-28
 */
public interface BaseServiceInfoMapper
{
    /**
     * 查询base_service_info（服务项目）
     *
     * @param serviceCode base_service_info（服务项目）ID
     * @return base_service_info（服务项目）
     */
    public BaseServiceInfo selectBaseServiceInfoById(String serviceCode);

    /**
     * 查询base_service_info（服务项目）列表
     *
     * @param baseServiceInfo base_service_info（服务项目）
     * @return base_service_info（服务项目）集合
     */
    public List<BaseServiceInfo> selectBaseServiceInfoList(BaseServiceInfo baseServiceInfo);

    /**
     * 新增base_service_info（服务项目）
     *
     * @param baseServiceInfo base_service_info（服务项目）
     * @return 结果
     */
    public int insertBaseServiceInfo(BaseServiceInfo baseServiceInfo);

    /**
     * 修改base_service_info（服务项目）
     *
     * @param baseServiceInfo base_service_info（服务项目）
     * @return 结果
     */
    public int updateBaseServiceInfo(BaseServiceInfo baseServiceInfo);

    /**
     * 删除base_service_info（服务项目）
     *
     * @param serviceCode base_service_info（服务项目）ID
     * @return 结果
     */
    public int deleteBaseServiceInfoById(String serviceCode);

    /**
     * 批量删除base_service_info（服务项目）
     *
     * @param serviceCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseServiceInfoByIds(String[] serviceCodes);

    /**
     * 根据服务名称查询服务
     *
     * @param serviceName
     * @return
     */
    public List<BaseServiceInfo> selectServiceByName(String serviceName);

    /**
     * 修改服务项目状态为注销 03
     *
     * @param serviceCode
     * @return
     */
    public int updateBaseServiceInfoById(String serviceCode);

    /**
     * 查询当前二级编码下的最大编码
     *
     * @param serverCode
     * @return
     */
    public String selectServerMaxNo(String serverCode);


    /**
     *@Description:  查询所有服务项目
     *@Author: houjiawei
     *@date: 2021/1/11 15:11
     */
    public List<BaseServiceInfo> selectBaseServiceInfoAllList(BaseServiceInfo baseServiceInfo);

    /**
     * 根据服务项目编码修改有效的服务项目
     *
     * @param baseServiceInfo
     * @return
     */
    public int updateBaseServiceInfoByCodeAndState(BaseServiceInfo baseServiceInfo);

    /**
     * 根据别名查询服务项目
     *
     * @param alias
     * @return
     */
    public List<BaseServiceInfo> selectBaseServiceInfoByAlias(String alias);

    /**
     * 根据原名称查询服务项目
     *
     * @param originalName
     * @return
     */
    public List<BaseServiceInfo> selectBaseServiceInfoByorName(String originalName);

}
