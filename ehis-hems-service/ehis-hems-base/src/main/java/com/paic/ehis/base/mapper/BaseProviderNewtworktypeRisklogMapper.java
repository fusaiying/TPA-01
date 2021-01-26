package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseProviderNewtworktypeRisklog;

import java.util.List;

/**
 * base_provider_newtworktype_risklog(医疗网络类型险种记录)Mapper接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface BaseProviderNewtworktypeRisklogMapper 
{
    /**
     * 查询base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param serNo base_provider_newtworktype_risklog(医疗网络类型险种记录)ID
     * @return base_provider_newtworktype_risklog(医疗网络类型险种记录)
     */
    public BaseProviderNewtworktypeRisklog selectBaseProviderNewtworktypeRisklogById(String serNo);

    /**
     * 查询base_provider_newtworktype_risklog(医疗网络类型险种记录)列表
     * 
     * @param baseProviderNewtworktypeRisklog base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * @return base_provider_newtworktype_risklog(医疗网络类型险种记录)集合
     */
    public List<BaseProviderNewtworktypeRisklog> selectBaseProviderNewtworktypeRisklogList(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog);

    /**
     * 新增base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param baseProviderNewtworktypeRisklog base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * @return 结果
     */
    public int insertBaseProviderNewtworktypeRisklog(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog);

    /**
     * 修改base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param baseProviderNewtworktypeRisklog base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * @return 结果
     */
    public int updateBaseProviderNewtworktypeRisklog(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog);

    /**
     * 删除base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param serNo base_provider_newtworktype_risklog(医疗网络类型险种记录)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeRisklogById(String serNo);

    /**
     * 批量删除base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param serNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypeRisklogByIds(String[] serNos);
}
