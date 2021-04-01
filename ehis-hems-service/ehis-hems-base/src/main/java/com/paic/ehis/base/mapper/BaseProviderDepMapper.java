package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderDep;

import java.util.List;

/**
 * base_provider_dept(服务商科室)Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseProviderDepMapper {
    /**
     * 查询base_provider_dept(服务商科室)
     *
     * @param id base_provider_dept(服务商科室)ID
     * @return base_provider_dept(服务商科室)
     */
    public BaseProviderDep selectBaseProviderDepById(String id);

    /**
     * 查询base_provider_dept(服务商科室)列表
     *
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return base_provider_dept(服务商科室)集合
     */
    public List<BaseProviderDep> selectBaseProviderDepList(BaseProviderDep baseProviderDep);

    /**
     * 新增base_provider_dept(服务商科室)
     *
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return 结果
     */
    public int insertBaseProviderDep(BaseProviderDep baseProviderDep);

    /**
     * 新增base_provider_dept(服务商科室)
     *
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return 结果
     */
    public int insertBaseProviderDepNew(BaseProviderDep baseProviderDep);

    /**
     * 修改base_provider_dept(服务商科室)
     *
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return 结果
     */
    public int updateBaseProviderDep(BaseProviderDep baseProviderDep);

    /**
     * 修改base_provider_dept(服务商科室)
     *
     * @param providerCode base_provider_dept(服务商科室)
     * @return 结果
     */
    public int updateBaseProviderDepByCode(String providerCode);

    /**
     * 修改base_provider_dept(服务商科室)
     *
     * @param providerCode base_provider_dept(服务商科室)
     * @return 结果
     */
    public int updateBaseProviderDepByCodeNew(String providerCode);

    /**
     * 删除base_provider_dept(服务商科室)
     *
     * @param id base_provider_dept(服务商科室)ID
     * @return 结果
     */
    public int deleteBaseProviderDepById(String id);

    /**
     * 批量删除base_provider_dept(服务商科室)
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderDepByIds(String[] ids);


    /**
     * 查询base_provider_dept(服务商科室)
     *
     * @param providercode base_provider_dept(服务商科室)ID
     * @return base_provider_dept(服务商科室)
     */
    public List<BaseProviderDep> selectBaseProviderDepByCode(String providercode);

    /**
     * 查询base_provider_dept(服务商科室)
     *
     * @param providercode base_provider_dept(服务商科室)ID
     * @return base_provider_dept(服务商科室)
     */
    public List<BaseProviderDep> selectBaseProviderDepByCodeNew(String providercode);

    /**
     * 删除base_provider_dept(服务商科室)
     *
     * @param iproviderCode base_provider_dept(服务商科室)ID
     * @return 结果
     */
    public int deleteBaseProviderDepByCode(String iproviderCode);

    public List<String> selectDeptById(String providercode);
}