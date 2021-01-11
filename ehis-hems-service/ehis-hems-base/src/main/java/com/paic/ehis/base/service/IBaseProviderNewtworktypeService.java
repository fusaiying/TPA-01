package com.paic.ehis.base.service;

import java.util.List;
import com.paic.ehis.base.domain.BaseProviderNewtworktype;

/**
 * base_provider_newtworktypet(医疗网络类型)Service接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface IBaseProviderNewtworktypeService 
{
    /**
     * 查询base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return base_provider_newtworktypet(医疗网络类型)
     */
    public BaseProviderNewtworktype selectBaseProviderNewtworktypeById(String serNo);

    /**
     * 查询base_provider_newtworktypet(医疗网络类型)列表
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return base_provider_newtworktypet(医疗网络类型)集合
     */
    public List<BaseProviderNewtworktype> selectBaseProviderNewtworktypeList(BaseProviderNewtworktype baseProviderNewtworktype);

    /**
     * 新增base_provider_newtworktypet(医疗网络类型)
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return 结果
     */
    public int insertBaseProviderNewtworktype(BaseProviderNewtworktype baseProviderNewtworktype);

    /**
     * 修改base_provider_newtworktypet(医疗网络类型)
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return 结果
     */
    public int updateBaseProviderNewtworktype(List<BaseProviderNewtworktype> baseProviderNewtworktype);

    /**
     * 批量删除base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNos 需要删除的base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeByIds(String[] serNos);

    /**
     * 删除base_provider_newtworktypet(医疗网络类型)信息
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeById(String serNo);
}
