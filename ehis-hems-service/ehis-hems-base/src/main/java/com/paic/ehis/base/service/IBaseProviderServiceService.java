package com.paic.ehis.base.service;

import java.util.List;
import com.paic.ehis.base.domain.BaseProviderService;

/**
 * base_provider_service(服务商服务信息)Service接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface IBaseProviderServiceService 
{
    /**
     * 查询base_provider_service(服务商服务信息)
     * 
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return base_provider_service(服务商服务信息)
     */
    public BaseProviderService selectBaseProviderServiceById(String providercode);

    /**
     * 查询base_provider_service(服务商服务信息)列表
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return base_provider_service(服务商服务信息)集合
     */
    public List<BaseProviderService> selectBaseProviderServiceList(BaseProviderService baseProviderService);

    /**
     * 新增base_provider_service(服务商服务信息)
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return 结果
     */
    public int insertBaseProviderService(BaseProviderService baseProviderService);

    /**
     * 修改base_provider_service(服务商服务信息)
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return 结果
     */
    public int updateBaseProviderService(BaseProviderService baseProviderService);

    /**
     * 批量删除base_provider_service(服务商服务信息)
     * 
     * @param providercodes 需要删除的base_provider_service(服务商服务信息)ID
     * @return 结果
     */
    public int deleteBaseProviderServiceByIds(String[] providercodes);

    /**
     * 删除base_provider_service(服务商服务信息)信息
     * 
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return 结果
     */
    public int deleteBaseProviderServiceById(String providercode);
}
