package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseServiceProcess;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * base_service_process（服务项目流程配置）Mapper接口
 *
 * @author sino
 * @date 2020-12-28
 */
public interface BaseServiceProcessMapper
{
    /**
     * 查询base_service_process（服务项目流程配置）
     *
     * @param serviceCode base_service_process（服务项目流程配置）ID
     * @return base_service_process（服务项目流程配置）
     */
    public List<BaseServiceProcess> selectBaseServiceProcessById(String serviceCode);

    /**
     * 查询base_service_process（服务项目流程配置）列表
     *
     * @param baseServiceProcess base_service_process（服务项目流程配置）
     * @return base_service_process（服务项目流程配置）集合
     */
    public List<BaseServiceProcess> selectBaseServiceProcessList(BaseServiceProcess baseServiceProcess);

    /**
     * 新增base_service_process（服务项目流程配置）
     *
     * @param baseServiceProcess base_service_process（服务项目流程配置）
     * @return 结果
     */
    public int insertBaseServiceProcess(BaseServiceProcess baseServiceProcess);

    /**
     * 修改base_service_process（服务项目流程配置）
     *
     * @param baseServiceProcess base_service_process（服务项目流程配置）
     * @return 结果
     */
    public int updateBaseServiceProcess(BaseServiceProcess baseServiceProcess);

    /**
     * 删除base_service_process（服务项目流程配置）
     *
     * @param serviceCode base_service_process（服务项目流程配置）ID
     * @return 结果
     */
    public int deleteBaseServiceProcessById(String serviceCode);

    /**
     * 批量删除base_service_process（服务项目流程配置）
     *
     * @param serviceCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseServiceProcessByIds(String[] serviceCodes);

    /**
     * 根据服务项目编码修改状态为不可用
     *
     * @param serviceCode
     * @return
     */
    public int updateBaseServiceProcessById(String serviceCode);

    /**
     * 更新服务项目编码（status = '01'）
     *
     * @param serviceCode
     * @param newServiceCode
     * @return
     */
    public int updateProcessNewServiceCode(@Param("serviceCode") String serviceCode, @Param("newServiceCode") String newServiceCode);
}
