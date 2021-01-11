package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.service.IBaseProviderNewtworktypeRisklogService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeRisklogMapper;
import com.paic.ehis.base.domain.BaseProviderNewtworktypeRisklog;

/**
 * base_provider_newtworktype_risklog(医疗网络类型险种记录)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-04
 */
@Service
public class BaseProviderNewtworktypeRisklogServiceImpl implements IBaseProviderNewtworktypeRisklogService
{
    @Autowired
    private BaseProviderNewtworktypeRisklogMapper baseProviderNewtworktypeRisklogMapper;

    /**
     * 查询base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param serNo base_provider_newtworktype_risklog(医疗网络类型险种记录)ID
     * @return base_provider_newtworktype_risklog(医疗网络类型险种记录)
     */
    @Override
    public BaseProviderNewtworktypeRisklog selectBaseProviderNewtworktypeRisklogById(String serNo)
    {
        return baseProviderNewtworktypeRisklogMapper.selectBaseProviderNewtworktypeRisklogById(serNo);
    }

    /**
     * 查询base_provider_newtworktype_risklog(医疗网络类型险种记录)列表
     * 
     * @param baseProviderNewtworktypeRisklog base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * @return base_provider_newtworktype_risklog(医疗网络类型险种记录)
     */
    @Override
    public List<BaseProviderNewtworktypeRisklog> selectBaseProviderNewtworktypeRisklogList(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog)
    {
        return baseProviderNewtworktypeRisklogMapper.selectBaseProviderNewtworktypeRisklogList(baseProviderNewtworktypeRisklog);
    }

    /**
     * 新增base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param baseProviderNewtworktypeRisklog base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * @return 结果
     */
    @Override
    public int insertBaseProviderNewtworktypeRisklog(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog)
    {
        baseProviderNewtworktypeRisklog.setCreateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypeRisklogMapper.insertBaseProviderNewtworktypeRisklog(baseProviderNewtworktypeRisklog);
    }

    /**
     * 修改base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param baseProviderNewtworktypeRisklog base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * @return 结果
     */
    @Override
    public int updateBaseProviderNewtworktypeRisklog(BaseProviderNewtworktypeRisklog baseProviderNewtworktypeRisklog)
    {
        baseProviderNewtworktypeRisklog.setUpdateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypeRisklogMapper.updateBaseProviderNewtworktypeRisklog(baseProviderNewtworktypeRisklog);
    }

    /**
     * 批量删除base_provider_newtworktype_risklog(医疗网络类型险种记录)
     * 
     * @param serNos 需要删除的base_provider_newtworktype_risklog(医疗网络类型险种记录)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeRisklogByIds(String[] serNos)
    {
        return baseProviderNewtworktypeRisklogMapper.deleteBaseProviderNewtworktypeRisklogByIds(serNos);
    }

    /**
     * 删除base_provider_newtworktype_risklog(医疗网络类型险种记录)信息
     * 
     * @param serNo base_provider_newtworktype_risklog(医疗网络类型险种记录)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeRisklogById(String serNo)
    {
        return baseProviderNewtworktypeRisklogMapper.deleteBaseProviderNewtworktypeRisklogById(serNo);
    }
}
