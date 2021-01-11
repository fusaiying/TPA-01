package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.mapper.BaseIcd10Mapper;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.domain.BaseIcd10;
import com.paic.ehis.system.service.IBaseIcd10Service;

/**
 * ICD10数据 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIcd10ServiceImpl implements IBaseIcd10Service 
{
    @Autowired
    private BaseIcd10Mapper baseIcd10Mapper;

    /**
     * 查询ICD10数据 
     * 
     * @param icdCode ICD10数据 ID
     * @return ICD10数据 
     */
    @Override
    public BaseIcd10 selectBaseIcd10ById(String icdCode)
    {
        return baseIcd10Mapper.selectBaseIcd10ById(icdCode);
    }

    /**
     * 查询ICD10数据 列表
     * 
     * @param baseIcd10 ICD10数据 
     * @return ICD10数据 
     */
    @Override
    public List<BaseIcd10> selectBaseIcd10List(BaseIcd10 baseIcd10)
    {
        return baseIcd10Mapper.selectBaseIcd10List(baseIcd10);
    }

    /**
     * 新增ICD10数据 
     * 
     * @param baseIcd10 ICD10数据 
     * @return 结果
     */
    @Override
    public int insertBaseIcd10(BaseIcd10 baseIcd10)
    {
        baseIcd10.setCreateTime(DateUtils.getNowDate());
        return baseIcd10Mapper.insertBaseIcd10(baseIcd10);
    }

    /**
     * 修改ICD10数据 
     * 
     * @param baseIcd10 ICD10数据 
     * @return 结果
     */
    @Override
    public int updateBaseIcd10(BaseIcd10 baseIcd10)
    {
        baseIcd10.setUpdateTime(DateUtils.getNowDate());
        return baseIcd10Mapper.updateBaseIcd10(baseIcd10);
    }

    /**
     * 批量删除ICD10数据 
     * 
     * @param icdCodes 需要删除的ICD10数据 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIcd10ByIds(String[] icdCodes)
    {
        return baseIcd10Mapper.deleteBaseIcd10ByIds(icdCodes);
    }

    /**
     * 删除ICD10数据 信息
     * 
     * @param icdCode ICD10数据 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIcd10ById(String icdCode)
    {
        return baseIcd10Mapper.deleteBaseIcd10ById(icdCode);
    }
}
