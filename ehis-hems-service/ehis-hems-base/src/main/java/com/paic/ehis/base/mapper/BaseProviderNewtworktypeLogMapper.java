package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderNewtworktypeLog;

import java.util.List;

/**
 * base_provider_newtworktype_log(医疗网络类型记录)Mapper接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface BaseProviderNewtworktypeLogMapper 
{
    /**
     * 查询base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param serNo base_provider_newtworktype_log(医疗网络类型记录)ID
     * @return base_provider_newtworktype_log(医疗网络类型记录)
     */
    public BaseProviderNewtworktypeLog selectBaseProviderNewtworktypeLogById(String serNo);

    /**
     * 查询base_provider_newtworktype_log(医疗网络类型记录)列表
     * 
     * @param baseProviderNewtworktypeLog base_provider_newtworktype_log(医疗网络类型记录)
     * @return base_provider_newtworktype_log(医疗网络类型记录)集合
     */
    public List<BaseProviderNewtworktypeLog> selectBaseProviderNewtworktypeLogList(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog);

    /**
     * 新增base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param baseProviderNewtworktypeLog base_provider_newtworktype_log(医疗网络类型记录)
     * @return 结果
     */
    public int insertBaseProviderNewtworktypeLog(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog);

    /**
     * 修改base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param baseProviderNewtworktypeLog base_provider_newtworktype_log(医疗网络类型记录)
     * @return 结果
     */
    public int updateBaseProviderNewtworktypeLog(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog);

    /**
     * 删除base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param serNo base_provider_newtworktype_log(医疗网络类型记录)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeLogById(String serNo);

    /**
     * 批量删除base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param serNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeLogByIds(String[] serNos);
}
