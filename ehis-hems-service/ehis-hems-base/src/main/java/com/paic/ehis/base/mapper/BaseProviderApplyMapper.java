package com.paic.ehis.base.mapper;

import java.util.List;
import com.paic.ehis.base.domain.BaseProviderApply;

/**
 * base_provider_apply(服务商预约信息)Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseProviderApplyMapper 
{
    /**
     * 查询base_provider_apply(服务商预约信息)
     * 
     * @param providercode base_provider_apply(服务商预约信息)ID
     * @return base_provider_apply(服务商预约信息)
     */
    public BaseProviderApply selectBaseProviderApplyById(String providercode);

    /**
     * 查询base_provider_apply(服务商预约信息)列表
     * 
     * @param baseProviderApply base_provider_apply(服务商预约信息)
     * @return base_provider_apply(服务商预约信息)集合
     */
    public List<BaseProviderApply> selectBaseProviderApplyList(BaseProviderApply baseProviderApply);

    /**
     * 新增base_provider_apply(服务商预约信息)
     * 
     * @param baseProviderApply base_provider_apply(服务商预约信息)
     * @return 结果
     */
    public int insertBaseProviderApply(BaseProviderApply baseProviderApply);

    /**
     * 修改base_provider_apply(服务商预约信息)
     * 
     * @param baseProviderApply base_provider_apply(服务商预约信息)
     * @return 结果
     */
    public int updateBaseProviderApply(BaseProviderApply baseProviderApply);

    /**
     * 删除base_provider_apply(服务商预约信息)
     * 
     * @param providercode base_provider_apply(服务商预约信息)ID
     * @return 结果
     */
    public int deleteBaseProviderApplyById(String providercode);

    /**
     * 批量删除base_provider_apply(服务商预约信息)
     * 
     * @param providercodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderApplyByIds(String[] providercodes);
}
