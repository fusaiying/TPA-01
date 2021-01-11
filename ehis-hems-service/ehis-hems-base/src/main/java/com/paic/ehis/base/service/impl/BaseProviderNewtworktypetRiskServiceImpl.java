package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.service.IBaseProviderNewtworktypetRiskService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseProviderNewtworktypeRisklog;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeRisklogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypetRiskMapper;
import com.paic.ehis.base.domain.BaseProviderNewtworktypetRisk;

/**
 * base_provider_newtworktypet_risk(医疗网络险种信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-04
 */
@Service
public class BaseProviderNewtworktypetRiskServiceImpl implements IBaseProviderNewtworktypetRiskService
{
    @Autowired
    private BaseProviderNewtworktypetRiskMapper baseProviderNewtworktypetRiskMapper;

    @Autowired
    private BaseProviderNewtworktypeRisklogMapper baseProviderNewtworktypeRisklogMapper;

    /**
     * 查询base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param serNo base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    @Override
    public BaseProviderNewtworktypetRisk selectBaseProviderNewtworktypetRiskById(String serNo)
    {
        return baseProviderNewtworktypetRiskMapper.selectBaseProviderNewtworktypetRiskById(serNo);
    }

    /**
     * 查询base_provider_newtworktypet_risk(医疗网络险种信息)列表
     * 
     * @param baseProviderNewtworktypetRisk base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return base_provider_newtworktypet_risk(医疗网络险种信息)
     */
    @Override
    public List<BaseProviderNewtworktypetRisk> selectBaseProviderNewtworktypetRiskList(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk)
    {
        return baseProviderNewtworktypetRiskMapper.selectBaseProviderNewtworktypetRiskList(baseProviderNewtworktypetRisk);
    }

    /**
     * 新增base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param baseProviderNewtworktypetRisks base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return 结果
     */
    @Override
    public int insertBaseProviderNewtworktypetRisk(List<BaseProviderNewtworktypetRisk> baseProviderNewtworktypetRisks)
    {
        int count = 0;
        baseProviderNewtworktypetRiskMapper.deleteBaseProviderNewtworktypetRiskByCode(baseProviderNewtworktypetRisks.get(0).getProviderCode());
        for(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk :baseProviderNewtworktypetRisks){
            baseProviderNewtworktypetRisk.setCreateTime(DateUtils.getNowDate());
            baseProviderNewtworktypetRisk.setStatus("Y");
            int i = baseProviderNewtworktypetRiskMapper.insertBaseProviderNewtworktypetRisk(baseProviderNewtworktypetRisk);
            BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog = new BaseProviderNewtworktypeRisklog();
            baseProviderNewtworktypeRisklog.setNetworktypeCode(baseProviderNewtworktypetRisk.getRiskCode());
            baseProviderNewtworktypeRisklog.setNewDate(DateUtils.getNowDate());
            baseProviderNewtworktypeRisklog.setSupplierCode(baseProviderNewtworktypetRisk.getProviderCode());
            baseProviderNewtworktypeRisklog.setStatus("Y");
            baseProviderNewtworktypeRisklogMapper.insertBaseProviderNewtworktypeRisklog(baseProviderNewtworktypeRisklog);
            count += i;
        }

        return count;
    }

    /**
     * 修改base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param baseProviderNewtworktypetRisk base_provider_newtworktypet_risk(医疗网络险种信息)
     * @return 结果
     */
    @Override
    public int updateBaseProviderNewtworktypetRisk(BaseProviderNewtworktypetRisk baseProviderNewtworktypetRisk)
    {
        baseProviderNewtworktypetRisk.setUpdateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypetRiskMapper.updateBaseProviderNewtworktypetRisk(baseProviderNewtworktypetRisk);
    }

    /**
     * 批量删除base_provider_newtworktypet_risk(医疗网络险种信息)
     * 
     * @param serNos 需要删除的base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypetRiskByIds(String[] serNos)
    {
        return baseProviderNewtworktypetRiskMapper.deleteBaseProviderNewtworktypetRiskByIds(serNos);
    }

    /**
     * 删除base_provider_newtworktypet_risk(医疗网络险种信息)信息
     * 
     * @param serNo base_provider_newtworktypet_risk(医疗网络险种信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypetRiskById(String serNo)
    {
        return baseProviderNewtworktypetRiskMapper.deleteBaseProviderNewtworktypetRiskById(serNo);
    }
}
