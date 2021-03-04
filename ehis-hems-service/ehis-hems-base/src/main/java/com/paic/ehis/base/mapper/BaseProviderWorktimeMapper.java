package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderWorktime;

import java.util.List;

/**
 * base_provider_worktime(服务商服务时间信息)Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseProviderWorktimeMapper 
{
    /**
     * 查询base_provider_worktime(服务商服务时间信息)
     * 
     * @param providercode base_provider_worktime(服务商服务时间信息)ID
     * @return base_provider_worktime(服务商服务时间信息)
     */
    public List<BaseProviderWorktime> selectBaseProviderWorktimeById(String providercode);

    /**
     * 查询base_provider_worktime(服务商服务时间信息)
     *
     * @param providercode base_provider_worktime(服务商服务时间信息)ID
     * @return base_provider_worktime(服务商服务时间信息)
     */
    public List<BaseProviderWorktime> selectBaseProviderWorktimeBycode(String providercode);


    /**
     * 查询base_provider_worktime(服务商服务时间信息)
     *
     * @param providercode base_provider_worktime(服务商服务时间信息)ID
     * @return base_provider_worktime(服务商服务时间信息)
     */
    public List<BaseProviderWorktime> selectBaseProviderWorktimeBycodeNew(String providercode);

    /**
     * 查询base_provider_worktime(服务商服务时间信息)列表
     * 
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return base_provider_worktime(服务商服务时间信息)集合
     */
    public List<BaseProviderWorktime> selectBaseProviderWorktimeList(BaseProviderWorktime baseProviderWorktime);

    /**
     * 新增base_provider_worktime(服务商服务时间信息)
     * 
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    public int insertBaseProviderWorktime(BaseProviderWorktime baseProviderWorktime);

    /**
     * 新增base_provider_worktime(服务商服务时间信息)
     *
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    public int insertBaseProviderWorktimeNew(BaseProviderWorktime baseProviderWorktime);

    /**
     * 修改base_provider_worktime(服务商服务时间信息)
     * 
     * @param baseProviderWorktime base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    public int updateBaseProviderWorktime(BaseProviderWorktime baseProviderWorktime);

    /**
     * 删除base_provider_worktime(服务商服务时间信息)
     * 
     * @param providercode base_provider_worktime(服务商服务时间信息)ID
     * @return 结果
     */
    public int deleteBaseProviderWorktimeById(String providercode);

    /**
     * 批量删除base_provider_worktime(服务商服务时间信息)
     * 
     * @param providercodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderWorktimeByIds(String[] providercodes);

    /**
     * 修改base_provider_worktime(服务商服务时间信息)
     *
     * @param providerCode base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    public int updateBaseProviderWorktimeByCode(String providerCode);

    /**
     * 修改base_provider_worktime(服务商服务时间信息)
     *
     * @param providerCode base_provider_worktime(服务商服务时间信息)
     * @return 结果
     */
    public int updateBaseProviderWorktimeByCodeNew(String providerCode);
}
