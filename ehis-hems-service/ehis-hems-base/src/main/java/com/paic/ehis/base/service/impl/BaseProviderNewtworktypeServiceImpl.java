package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.service.IBaseProviderNewtworktypeService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseProviderNewtworktypeLog;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.base.mapper.BaseProviderNewtworktypeMapper;
import com.paic.ehis.base.domain.BaseProviderNewtworktype;

/**
 * base_provider_newtworktypet(医疗网络类型)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-04
 */
@Service
public class BaseProviderNewtworktypeServiceImpl implements IBaseProviderNewtworktypeService
{
    @Autowired
    private BaseProviderNewtworktypeMapper baseProviderNewtworktypeMapper;

    @Autowired
    private BaseProviderNewtworktypeLogMapper baseProviderNewtworktypeLogMapper;

    /**
     * 查询base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return base_provider_newtworktypet(医疗网络类型)
     */
    @Override
    public BaseProviderNewtworktype selectBaseProviderNewtworktypeById(String serNo)
    {
        return baseProviderNewtworktypeMapper.selectBaseProviderNewtworktypeById(serNo);
    }

    /**
     * 查询base_provider_newtworktypet(医疗网络类型)列表
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return base_provider_newtworktypet(医疗网络类型)
     */
    @Override
    public List<BaseProviderNewtworktype> selectBaseProviderNewtworktypeList(BaseProviderNewtworktype baseProviderNewtworktype)
    {
        return baseProviderNewtworktypeMapper.selectBaseProviderNewtworktypeList(baseProviderNewtworktype);
    }

    /**
     * 新增base_provider_newtworktypet(医疗网络类型)
     * 
     * @param baseProviderNewtworktype base_provider_newtworktypet(医疗网络类型)
     * @return 结果
     */
    @Override
    public int insertBaseProviderNewtworktype(BaseProviderNewtworktype baseProviderNewtworktype)
    {
        baseProviderNewtworktype.setCreateTime(DateUtils.getNowDate());
        return baseProviderNewtworktypeMapper.insertBaseProviderNewtworktype(baseProviderNewtworktype);
    }

    /**
     * 修改base_provider_newtworktypet(医疗网络类型)
     * 
     * @param baseProviderNewtworktypes base_provider_newtworktypet(医疗网络类型)
     * @return 结果
     */
    @Override
    public int updateBaseProviderNewtworktype(List<BaseProviderNewtworktype> baseProviderNewtworktypes)
    {
        int count = 0;
        for(BaseProviderNewtworktype baseProviderNewtworktype :baseProviderNewtworktypes){
            baseProviderNewtworktype.setUpdateTime(DateUtils.getNowDate());
            //修改当前选项
            int i = baseProviderNewtworktypeMapper.updateBaseProviderNewtworktype(baseProviderNewtworktype);
            //保存修改记录
            BaseProviderNewtworktypeLog baseProviderNewtworktypeLog = new BaseProviderNewtworktypeLog();
            baseProviderNewtworktypeLog.setNewChoose(baseProviderNewtworktype.getNewChoose());
            baseProviderNewtworktypeLog.setNewDate(baseProviderNewtworktype.getNewDate());
            baseProviderNewtworktypeLog.setStatus(baseProviderNewtworktype.getSupplierCode());
            baseProviderNewtworktypeLog.setNetworktypeCode(baseProviderNewtworktype.getNetworktypeCode());
            baseProviderNewtworktypeLog.setCreateTime(DateUtils.getNowDate());
            baseProviderNewtworktypeLogMapper.insertBaseProviderNewtworktypeLog(baseProviderNewtworktypeLog);
            count += i;
        }
        return count;
    }

    /**
     * 批量删除base_provider_newtworktypet(医疗网络类型)
     * 
     * @param serNos 需要删除的base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeByIds(String[] serNos)
    {
        return baseProviderNewtworktypeMapper.deleteBaseProviderNewtworktypeByIds(serNos);
    }

    /**
     * 删除base_provider_newtworktypet(医疗网络类型)信息
     * 
     * @param serNo base_provider_newtworktypet(医疗网络类型)ID
     * @return 结果
     */
    @Override
    public int deleteBaseProviderNewtworktypeById(String serNo)
    {
        return baseProviderNewtworktypeMapper.deleteBaseProviderNewtworktypeById(serNo);
    }
}
