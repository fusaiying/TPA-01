package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseServiceApplyorimpl;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * base_service_applyorimpl（服务项目申请或实施信息显示配置）Mapper接口
 *
 * @author sino
 * @date 2020-12-28
 */
public interface BaseServiceApplyorimplMapper
{
    /**
     * 查询base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param serviceCode base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return base_service_applyorimpl（服务项目申请或实施信息显示配置）
     */
    public List<BaseServiceApplyorimpl> selectBaseServiceApplyorimplById(String serviceCode);

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
     * 删除base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param serviceCode base_service_applyorimpl（服务项目申请或实施信息显示配置）ID
     * @return 结果
     */
    public int deleteBaseServiceApplyorimplById(String serviceCode);

    /**
     * 批量删除base_service_applyorimpl（服务项目申请或实施信息显示配置）
     *
     * @param serviceCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseServiceApplyorimplByIds(String[] serviceCodes);

    /**
     * 根据服务项目编码改变状态为不可用
     *
     * @param serviceCode
     * @return
     */
    public int updateBaseServiceApplyorimplById(String serviceCode);

    /**
     * 更新服务项目编码
     *
     * @param serviceCode
     * @param newServiceCode
     * @return
     */
    public int updateFieldNewServiceCode(@Param("serviceCode") String serviceCode, @Param("newServiceCode") String newServiceCode);
}
