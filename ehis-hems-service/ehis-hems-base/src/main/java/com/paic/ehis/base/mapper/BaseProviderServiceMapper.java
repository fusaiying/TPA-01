package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderService;

import java.util.List;

/**
 * base_provider_service(服务商服务信息)Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseProviderServiceMapper 
{
    /**
     * 查询base_provider_service(服务商服务信息)
     * 
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return base_provider_service(服务商服务信息)
     */
    public BaseProviderService selectBaseProviderServiceById(String providercode);


    /**
     * 查询base_provider_service(服务商服务信息)(正式表)
     *
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return base_provider_service(服务商服务信息)
     */
    public BaseProviderService selectBaseProviderServiceByIdNew(String providercode);

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
     * 新增base_provider_service(服务商服务信息)正式
     *
     */
    public int insertBaseProviderServiceNew(BaseProviderService baseProviderService);

    /**
     * 修改base_provider_service(服务商服务信息)
     * 
     * @param baseProviderService base_provider_service(服务商服务信息)
     * @return 结果
     */
    public int updateBaseProviderService(BaseProviderService baseProviderService);

    /**
     * 修改base_provider_service(服务商服务信息)
     *
     * @param providerCode base_provider_service(服务商服务信息)
     * @return 结果
     */
    public int updateBaseProviderServiceByCode(String providerCode);


    /**
     * 修改base_provider_service(正式表)
     */
    public int updateBaseProviderServiceByCodeNew(String providerCode);

    /**
     * 删除base_provider_service(服务商服务信息)
     * 
     * @param providercode base_provider_service(服务商服务信息)ID
     * @return 结果
     */
    public int deleteBaseProviderServiceById(String providercode);

    /**
     * 批量删除base_provider_service(服务商服务信息)
     * 
     * @param providercodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderServiceByIds(String[] providercodes);
}
