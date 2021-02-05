package com.paic.ehis.product.mapper;

import com.paic.ehis.product.domain.BaseManualInfo;

import java.util.List;

/**
 * 服务手册Mapper接口
 * 
 * @author sino
 * @date 2021-01-10
 */
public interface BaseManualInfoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param manualCode 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public BaseManualInfo selectBaseManualInfoById(String manualCode);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param baseManualInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BaseManualInfo> selectBaseManualInfoList(BaseManualInfo baseManualInfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param baseManualInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertBaseManualInfo(BaseManualInfo baseManualInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param baseManualInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateBaseManualInfo(BaseManualInfo baseManualInfo);

    /**
     * 删除【请填写功能名称】
     * 
     * @param manualCode 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBaseManualInfoById(String manualCode);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseManualInfoByIds(String[] serialNos);
}
