package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseProviderDep;
import com.paic.ehis.base.domain.vo.BaseProviderDepVo;

import java.util.List;

/**
 * base_provider_dept(服务商科室)Service接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface IBaseProviderDepService 
{
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
     * @param baseProviderDepVo base_provider_dept(服务商科室)
     * @return 结果
     */
    public int insertBaseProviderDep(BaseProviderDepVo baseProviderDepVo);

    /**
     * 新增base_provider_dept(服务商科室)
     *
     * @param providerCode base_provider_dept(服务商科室)
     * @return 结果
     */
    public int insertBaseProviderDepNew(String providerCode);

    /**
     * 修改base_provider_dept(服务商科室)
     * 
     * @param baseProviderDep base_provider_dept(服务商科室)
     * @return 结果
     */
    public int updateBaseProviderDep(BaseProviderDep baseProviderDep);

    /**
     * 批量删除base_provider_dept(服务商科室)
     * 
     * @param ids 需要删除的base_provider_dept(服务商科室)ID
     * @return 结果
     */
    public int deleteBaseProviderDepByIds(String[] ids);

    /**
     * 删除base_provider_dept(服务商科室)信息
     * 
     * @param id base_provider_dept(服务商科室)ID
     * @return 结果
     */
    public int deleteBaseProviderDepById(String id);

    /**
     * 查询base_provider_dept(服务商科室)
     *
     * @param providercode base_provider_dept(服务商科室)
     * @return base_provider_dept(服务商科室)
     */
    public List<BaseProviderDep> selectBaseProviderDepByCode(BaseProviderDep baseProviderDep);
}
