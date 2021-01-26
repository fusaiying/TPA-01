package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseServiceInfo;

import java.util.List;

/**
 * base_service_info（服务项目）Service接口
 *
 * @author sino
 * @date 2020-12-28
 */
public interface IBaseServiceInfoService
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
     * 批量删除base_service_info（服务项目）
     *
     * @param serviceCodes 需要删除的base_service_info（服务项目）ID
     * @return 结果
     */
    public int deleteBaseServiceInfoByIds(String[] serviceCodes);

    /**
     * 删除base_service_info（服务项目）信息
     *
     * @param serviceCode base_service_info（服务项目）ID
     * @return 结果
     */
    public int deleteBaseServiceInfoById(String serviceCode);

    /**
     * 根据服务名称查询服务
     *
     * @param servicename
     * @return
     */
    public List<BaseServiceInfo> selectServiceByName(String servicename);

    /**
     * 修改服务项目状态为注销 03
     *
     * @param servicecode
     * @return
     */
    public int updateBaseServiceInfoById(String servicecode);

    /**
     * 获取二级编码下的当前最大编码
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
     * 根据服务编码修改有效的服务项目
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
