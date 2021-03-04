package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseProviderNewtworktypeLog;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeLogMapper;
import com.paic.ehis.base.service.IBaseProviderNewtworktypeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * base_provider_newtworktype_log(医疗网络类型记录)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-04
 */
@Service
@Transactional
public class BaseProviderNewtworktypeLogServiceImpl implements IBaseProviderNewtworktypeLogService 
{
    @Autowired
    private BaseProviderNewtworktypeLogMapper baseProviderNewtworktypeLogMapper;

    /**
     * 查询base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param serNo base_provider_newtworktype_log(医疗网络类型记录)ID
     * @return base_provider_newtworktype_log(医疗网络类型记录)
     */
    @Override
    public BaseProviderNewtworktypeLog selectBaseProviderNewtworktypeLogById(String serNo)
    {
        return baseProviderNewtworktypeLogMapper.selectBaseProviderNewtworktypeLogById(serNo);
    }

    /**
     * 查询base_provider_newtworktype_log(医疗网络类型记录)列表
     * 
     * @param baseProviderNewtworktypeLog base_provider_newtworktype_log(医疗网络类型记录)
     * @return base_provider_newtworktype_log(医疗网络类型记录)
     */
    @Override
    public List<BaseProviderNewtworktypeLog> selectBaseProviderNewtworktypeLogList(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog)
    {
        baseProviderNewtworktypeLog.setSupplierCode(baseProviderNewtworktypeLog.getProviderCode());
        return baseProviderNewtworktypeLogMapper.selectBaseProviderNewtworktypeLogList(baseProviderNewtworktypeLog);
    }

    /**
     * 新增base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param baseProviderNewtworktypeLog base_provider_newtworktype_log(医疗网络类型记录)
     * @return 结果
     */
    @Override
    public int insertBaseProviderNewtworktypeLog(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog)
    {
        baseProviderNewtworktypeLog.setCreateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypeLogMapper.insertBaseProviderNewtworktypeLog(baseProviderNewtworktypeLog);
    }

    /**
     * 修改base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param baseProviderNewtworktypeLog base_provider_newtworktype_log(医疗网络类型记录)
     * @return 结果
     */
    @Override
    public int updateBaseProviderNewtworktypeLog(BaseProviderNewtworktypeLog baseProviderNewtworktypeLog)
    {
        baseProviderNewtworktypeLog.setUpdateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypeLogMapper.updateBaseProviderNewtworktypeLog(baseProviderNewtworktypeLog);
    }

    /**
     * 批量删除base_provider_newtworktype_log(医疗网络类型记录)
     * 
     * @param serNos 需要删除的base_provider_newtworktype_log(医疗网络类型记录)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeLogByIds(String[] serNos)
    {
        return baseProviderNewtworktypeLogMapper.deleteBaseProviderNewtworktypeLogByIds(serNos);
    }

    /**
     * 删除base_provider_newtworktype_log(医疗网络类型记录)信息
     * 
     * @param serNo base_provider_newtworktype_log(医疗网络类型记录)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeLogById(String serNo)
    {
        return baseProviderNewtworktypeLogMapper.deleteBaseProviderNewtworktypeLogById(serNo);
    }
}
