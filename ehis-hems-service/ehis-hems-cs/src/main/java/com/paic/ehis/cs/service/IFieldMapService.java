package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.FieldMap;

import java.util.List;


/**
 * 受理信息映射 Service接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface IFieldMapService 
{
    /**
     * 查询受理信息映射 
     * 
     * @param sourceName 受理信息映射 ID
     * @return 受理信息映射
     */
    public List<FieldMap> selectFieldMapById(String sourceName);

    /**
     * 查询受理信息映射 列表
     * 
     * @param fieldMap 受理信息映射 
     * @return 受理信息映射 集合
     */
    public List<FieldMap> selectFieldMapList(FieldMap fieldMap);

    /**
     * 新增受理信息映射 
     * 
     * @param fieldMap 受理信息映射 
     * @return 结果
     */
    public int insertFieldMap(FieldMap fieldMap);

    /**
     * 修改受理信息映射 
     * 
     * @param fieldMap 受理信息映射 
     * @return 结果
     */
    public int updateFieldMap(FieldMap fieldMap);

    /**
     * 批量删除受理信息映射 
     * 
     * @param sourceNames 需要删除的受理信息映射 ID
     * @return 结果
     */
    public int deleteFieldMapByIds(String[] sourceNames);

    /**
     * 删除受理信息映射 信息
     * 
     * @param sourceName 受理信息映射 ID
     * @return 结果
     */
    public int deleteFieldMapById(String sourceName);
}
