package com.paic.ehis.base.mapper;

import java.util.List;
import com.paic.ehis.base.domain.BaseProviderNewtworktypetRisk;

/**
 * base_provider_newtworktypet_risk(医疗网络险种信息)Mapper接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface BaseProviderNewtworktypetRiskMapper 
{
    /**
     * 查询base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param serNo base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    public BaseProviderNewtworktypetRisk selectBaseProviderNewtworktypetRiskById(String serNo);

    /**
     * 查询base_provider_newtworktypet_risk(医疗网络险种信息)列表
     * 
     * @param baseProviderNewtworktypetRisk base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return base_provider_newtworktypet_risk(医疗网络险种信息)集合
     */
    public List<BaseProviderNewtworktypetRisk> selectBaseProviderNewtworktypetRiskList(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk);

    /**
     * 新增base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param baseProviderNewtworktypetRisk base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return 结果
     */
    public int insertBaseProviderNewtworktypetRisk(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk);

    /**
     * 修改base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param baseProviderNewtworktypetRisk base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return 结果
     */
    public int updateBaseProviderNewtworktypetRisk(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk);

    /**
     * 删除base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param serNo base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetRiskById(String serNo);



    /**
     * 删除base_provider_newtworktypet_risk(医疗网络险种信息)
     *
     * @param providerCode base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetRiskByCode(String providerCode);

    /**
     * 批量删除base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param serNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetRiskByIds(String[] serNos);
}
