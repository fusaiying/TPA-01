package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseServiceCategoryKind;
import com.paic.ehis.base.domain.SysDictData;
import com.paic.ehis.base.mapper.BaseServiceCategoryKindMapper;
import com.paic.ehis.base.service.IBaseServiceCategoryKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_service_category_kindService业务层处理
 * 
 * @author sino
 * @date 2020-12-28
 */
@Service
public class BaseServiceCategoryKindServiceImpl implements IBaseServiceCategoryKindService
{
    @Autowired
    private BaseServiceCategoryKindMapper baseServiceCategoryKindMapper;

    /**
     * 查询base_service_category_kind
     * 
     * @param codei base_service_category_kindID
     * @return base_service_category_kind
     */
    @Override
    public BaseServiceCategoryKind selectBaseServiceCategoryKindById(String codei)
    {
        return baseServiceCategoryKindMapper.selectBaseServiceCategoryKindById(codei);
    }

    /**
     * 查询base_service_category_kind列表
     * 
     * @param baseServiceCategoryKind base_service_category_kind
     * @return base_service_category_kind
     */
    @Override
    public List<BaseServiceCategoryKind> selectBaseServiceCategoryKindList(BaseServiceCategoryKind baseServiceCategoryKind)
    {
        return baseServiceCategoryKindMapper.selectBaseServiceCategoryKindList(baseServiceCategoryKind);
    }

    /**
     * 新增base_service_category_kind
     * 
     * @param baseServiceCategoryKind base_service_category_kind
     * @return 结果
     */
    @Override
    public int insertBaseServiceCategoryKind(BaseServiceCategoryKind baseServiceCategoryKind)
    {
        baseServiceCategoryKind.setCreateTime(DateUtils.getNowDate());
        return baseServiceCategoryKindMapper.insertBaseServiceCategoryKind(baseServiceCategoryKind);
    }

    /**
     * 修改base_service_category_kind
     * 
     * @param baseServiceCategoryKind base_service_category_kind
     * @return 结果
     */
    @Override
    public int updateBaseServiceCategoryKind(BaseServiceCategoryKind baseServiceCategoryKind)
    {
        baseServiceCategoryKind.setUpdateTime(DateUtils.getNowDate());
        return baseServiceCategoryKindMapper.updateBaseServiceCategoryKind(baseServiceCategoryKind);
    }

    /**
     * 批量删除base_service_category_kind
     * 
     * @param codeis 需要删除的base_service_category_kindID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceCategoryKindByIds(String[] codeis)
    {
        return baseServiceCategoryKindMapper.deleteBaseServiceCategoryKindByIds(codeis);
    }

    /**
     * 删除base_service_category_kind信息
     * 
     * @param codei base_service_category_kindID
     * @return 结果
     */
    @Override
    public int deleteBaseServiceCategoryKindById(String codei)
    {
        return baseServiceCategoryKindMapper.deleteBaseServiceCategoryKindById(codei);
    }

    /**
     * 查询类别
     *
     * @return
     */
    @Override
    public List<SysDictData> selectCategoryType() {
        return baseServiceCategoryKindMapper.selectCategoryType();
    }

    /**
     * 查询类别下的所有种类
     *
     * @param category
     * @return
     */
    @Override
    public List<SysDictData> selectServerType(String category) {
        List<SysDictData> serverType = baseServiceCategoryKindMapper.selectServerType(category);
        return serverType;
    }

}
