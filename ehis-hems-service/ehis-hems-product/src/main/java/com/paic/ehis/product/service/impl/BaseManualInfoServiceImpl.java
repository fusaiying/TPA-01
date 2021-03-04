package com.paic.ehis.product.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.product.domain.BaseManualInfo;
import com.paic.ehis.product.mapper.BaseManualInfoMapper;
import com.paic.ehis.product.service.IBaseManualInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务手册Service业务层处理
 * 
 * @author sino
 * @date 2021-01-10
 */
@Service
public class BaseManualInfoServiceImpl implements IBaseManualInfoService
{
    @Autowired
    private BaseManualInfoMapper baseManualInfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param manualCode 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BaseManualInfo selectBaseManualInfoById(String manualCode)
    {
        return baseManualInfoMapper.selectBaseManualInfoById(manualCode);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param baseManualInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BaseManualInfo> selectBaseManualInfoList(BaseManualInfo baseManualInfo)
    {
        return baseManualInfoMapper.selectBaseManualInfoList(baseManualInfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param baseManualInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBaseManualInfo(BaseManualInfo baseManualInfo)
    {
        baseManualInfo.setCreateTime(DateUtils.getNowDate());
        return baseManualInfoMapper.insertBaseManualInfo(baseManualInfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param baseManualInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBaseManualInfo(BaseManualInfo baseManualInfo)
    {
        baseManualInfo.setUpdateTime(DateUtils.getNowDate());
        return baseManualInfoMapper.updateBaseManualInfo(baseManualInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param serialNos 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBaseManualInfoByIds(String[] serialNos)
    {
        return baseManualInfoMapper.deleteBaseManualInfoByIds(serialNos);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param manualCode 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBaseManualInfoById(String manualCode)
    {
        return baseManualInfoMapper.deleteBaseManualInfoById(manualCode);
    }
}
