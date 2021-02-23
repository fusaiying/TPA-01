package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.FieldMap;

import java.util.List;
import java.util.Map;


/**
 * 受理信息映射 Mapper接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface FieldMapMapper 
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

    public List<FieldMap> selectKVMap(String targetTableName,String sourceName);

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
     * 删除受理信息映射 
     * 
     * @param sourceName 受理信息映射 ID
     * @return 结果
     */
    public int deleteFieldMapById(String sourceName);

    /**
     * 批量删除受理信息映射 
     * 
     * @param sourceNames 需要删除的数据ID
     * @return 结果
     */
    public int deleteFieldMapByIds(String[] sourceNames);
}
