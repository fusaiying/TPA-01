package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseServiceProcess;
import com.paic.ehis.base.domain.vo.ServiceProcess;

import java.util.List;

/**
 * base_service_process（服务项目流程配置）Service接口
 *
 * @author sino
 * @date 2020-12-28
 */
public interface IBaseServiceProcessService
{
    /**
     * 查询base_service_process（服务项目流程配置）
     *
     * @param servicecode base_service_process（服务项目流程配置）ID
     * @return base_service_process（服务项目流程配置）
     */
    public List<BaseServiceProcess> selectBaseServiceProcessById(String servicecode);

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
     * 批量删除base_service_process（服务项目流程配置）
     *
     * @param serviceCodes 需要删除的base_service_process（服务项目流程配置）ID
     * @return 结果
     */
    public int deleteBaseServiceProcessByIds(String[] serviceCodes);

    /**
     * 删除base_service_process（服务项目流程配置）信息
     *
     * @param serviceCode base_service_process（服务项目流程配置）ID
     * @return 结果
     */
    public int deleteBaseServiceProcessById(String serviceCode);

    /**
     * 查询服务项目配置信息
     *
     * @param servicecode
     * @return
     */
    public ServiceProcess getServiceProcess(String servicecode);

    /**
     * 根据服务项目编码修改状态为不可用
     *
     * @param servicecode
     * @return
     */
    public int updateBaseServiceProcessById(String servicecode);

    /**
     * 更新服务项目编码（status='01'）
     *
     * @param servicecode
     * @param newServiceCode
     * @return
     */
    public int updateProcessNewServiceCode(String servicecode, String newServiceCode);

}
