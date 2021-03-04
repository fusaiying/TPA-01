package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseProviderNewtworktypetRisk;

import java.util.List;

/**
 * base_provider_newtworktypet_risk(医疗网络险种信息)Service接口
 * 
 * @author sino
 * @date 2021-01-04
 */
public interface IBaseProviderNewtworktypetRiskService 
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
    public int insertBaseProviderNewtworktypetRisk(List<BaseProviderNewtworktypetRisk> baseProviderNewtworktypetRisk);

    /**
     * 修改base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param baseProviderNewtworktypetRisk base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return 结果
     */
    public int updateBaseProviderNewtworktypetRisk(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk);

    /**
     * 批量删除base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param serNos 需要删除的base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetRiskByIds(String[] serNos);

    /**
     * 删除base_provider_newtworktypet_risk(医疗网络险种信息)信息
     * 
     * @param serNo base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return 结果
     */
    public int deleteBaseProviderNewtworktypetRiskById(String serNo);
}
