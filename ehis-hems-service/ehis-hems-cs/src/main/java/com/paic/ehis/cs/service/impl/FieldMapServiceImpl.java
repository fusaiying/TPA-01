package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.FieldMap;
import com.paic.ehis.cs.mapper.FieldMapMapper;
import com.paic.ehis.cs.service.IFieldMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 受理信息映射 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-22
 */
@Service
public class FieldMapServiceImpl implements IFieldMapService
{
    @Autowired
    private FieldMapMapper fieldMapMapper;

    /**
     * 查询受理信息映射 
     * 
     * @param sourceName 受理信息映射 ID
     * @return 受理信息映射
     */
    @Override
    public List<FieldMap> selectFieldMapById(String sourceName)
    {
        return fieldMapMapper.selectFieldMapById(sourceName);
    }

    /**
     * 查询受理信息映射 列表
     * 
     * @param fieldMap 受理信息映射 
     * @return 受理信息映射 
     */
    @Override
    public List<FieldMap> selectFieldMapList(FieldMap fieldMap)
    {
        return fieldMapMapper.selectFieldMapList(fieldMap);
    }

    /**
     * 新增受理信息映射 
     * 
     * @param fieldMap 受理信息映射 
     * @return 结果
     */
    @Override
    public int insertFieldMap(FieldMap fieldMap)
    {
        return fieldMapMapper.insertFieldMap(fieldMap);
    }

    /**
     * 修改受理信息映射 
     * 
     * @param fieldMap 受理信息映射 
     * @return 结果
     */
    @Override
    public int updateFieldMap(FieldMap fieldMap)
    {
        return fieldMapMapper.updateFieldMap(fieldMap);
    }

    /**
     * 批量删除受理信息映射 
     * 
     * @param sourceNames 需要删除的受理信息映射 ID
     * @return 结果
     */
    @Override
    public int deleteFieldMapByIds(String[] sourceNames)
    {
        return fieldMapMapper.deleteFieldMapByIds(sourceNames);
    }

    /**
     * 删除受理信息映射 信息
     * 
     * @param sourceName 受理信息映射 ID
     * @return 结果
     */
    @Override
    public int deleteFieldMapById(String sourceName)
    {
        return fieldMapMapper.deleteFieldMapById(sourceName);
    }
}
