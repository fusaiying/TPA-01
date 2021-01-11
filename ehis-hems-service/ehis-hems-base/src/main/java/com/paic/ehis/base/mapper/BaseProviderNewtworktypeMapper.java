package com.paic.ehis.base.mapper;

import java.util.List;
import com.paic.ehis.base.domain.BaseProviderNewtworktype;

/**
 * base_provider_newtworktypet(医疗网络类型)Mapper接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface BaseProviderNewtworktypeMapper 
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
    public int updateBaseProviderNewtworktype(BaseProviderNewtworktype baseProviderNewtworktype);

    /**
     * 删除base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeById(String serNo);

    /**
     * 批量删除base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeByIds(String[] serNos);
}
