package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseServiceCategoryKind;
import com.paic.ehis.base.domain.SysDictData;

import java.util.List;

/**
 * base_service_category_kindMapper接口
 * 
 * @author sino
 * @date 2020-12-28
 */
public interface BaseServiceCategoryKindMapper 
{
    /**
     * 查询base_service_category_kind
     * 
     * @param codei base_service_category_kindID
     * @return base_service_category_kind
     */
    public BaseServiceCategoryKind selectBaseServiceCategoryKindById(String codei);

    /**
     * 查询base_service_category_kind列表
     * 
     * @param baseServiceCategoryKind base_service_category_kind
     * @return base_service_category_kind集合
     */
    public List<BaseServiceCategoryKind> selectBaseServiceCategoryKindList(BaseServiceCategoryKind baseServiceCategoryKind);

    /**
     * 新增base_service_category_kind
     * 
     * @param baseServiceCategoryKind base_service_category_kind
     * @return 结果
     */
    public int insertBaseServiceCategoryKind(BaseServiceCategoryKind baseServiceCategoryKind);

    /**
     * 修改base_service_category_kind
     * 
     * @param baseServiceCategoryKind base_service_category_kind
     * @return 结果
     */
    public int updateBaseServiceCategoryKind(BaseServiceCategoryKind baseServiceCategoryKind);

    /**
     * 删除base_service_category_kind
     * 
     * @param codei base_service_category_kindID
     * @return 结果
     */
    public int deleteBaseServiceCategoryKindById(String codei);

    /**
     * 批量删除base_service_category_kind
     * 
     * @param codeis 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseServiceCategoryKindByIds(String[] codeis);

    /**
     * 查询类别
     *
     * @return
     */
    public List<SysDictData> selectCategoryType();

    /**
     * 查询类别下的种类
     *
     * @param category
     * @return
     */
    public List<SysDictData> selectServerType(String category);
}
