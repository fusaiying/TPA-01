package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseServiceApplyorimpl;

import java.util.List;

/**
 * base_service_applyorimpl（服务项目申请或实施信息显示配置）Service接口
 *
 * @author sino
 * @date 2020-12-28
 */
public interface IBaseServiceApplyorimplService
{
    /**
     * 查询base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param servicecode base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return base_service_applyorimpl（服务项目申请或实施信息显示配置）
     */
    public List<BaseServiceApplyorimpl> selectBaseServiceApplyorimplById(String servicecode);

    /**
     * 查询base_service_applyorimpl（服务项目申请或实施信息显示配置）列表
     *
     * @param baseServiceApplyorimpl base_service_applyorimpl（服务项目申请或实施信息显示配置）
     * @return base_service_applyorimpl（服务项目申请或实施信息显示配置）集合
     */
    public List<BaseServiceApplyorimpl> selectBaseServiceApplyorimplList(BaseServiceApplyorimpl baseServiceApplyorimpl);

    /**
     * 新增base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param baseServiceApplyorimpl base_service_applyorimpl（服务项目申请或实施信息显示配置）
     * @return 结果
     */
    public int insertBaseServiceApplyorimpl(BaseServiceApplyorimpl baseServiceApplyorimpl);

    /**
     * 修改base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param baseServiceApplyorimpl base_service_applyorimpl（服务项目申请或实施信息显示配置）
     * @return 结果
     */
    public int updateBaseServiceApplyorimpl(BaseServiceApplyorimpl baseServiceApplyorimpl);

    /**
     * 批量删除base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param serviceCodes 需要删除的base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return 结果
     */
    public int deleteBaseServiceApplyorimplByIds(String[] serviceCodes);

    /**
     * 删除base_service_applyorimpl（服务项目申请或实施信息显示配置）信息
     *
     * @param serviceCode base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return 结果
     */
    public int deleteBaseServiceApplyorimplById(String serviceCode);

    /**
     * 根据服务项目编码更新状态为不可用(status='02')
     *
     * @param serviceCode
     * @return
     */
    public int updateBaseServiceApplyorimplById(String serviceCode);

    /**
     * 更新服务项目编码
     *
     * @param servicecode
     * @param newServiceCode
     * @return
     */
    public int updateFieldNewServiceCode(String servicecode, String newServiceCode);
}
