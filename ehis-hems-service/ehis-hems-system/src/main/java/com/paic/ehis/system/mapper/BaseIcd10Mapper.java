package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.BaseIcd10;

/**
 * ICD10数据 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface BaseIcd10Mapper 
{
    /**
     * 查询ICD10数据 
     * 
     * @param icdCode ICD10数据 ID
     * @return ICD10数据 
     */
    public BaseIcd10 selectBaseIcd10ById(String icdCode);

    public BaseIcd10 selectBaseIcd10ByName(String icdmname);

    /**
     * 查询ICD10数据 列表
     * 
     * @param baseIcd10 ICD10数据 
     * @return ICD10数据 集合
     */
    public List<BaseIcd10> selectBaseIcd10List(BaseIcd10 baseIcd10);


    /**
     * 新增ICD10数据 
     * 
     * @param baseIcd10 ICD10数据 
     * @return 结果
     */
    public int insertBaseIcd10(BaseIcd10 baseIcd10);

    /**
     * 修改ICD10数据 
     * 
     * @param baseIcd10 ICD10数据 
     * @return 结果
     */
    public int updateBaseIcd10(BaseIcd10 baseIcd10);

    /**
     * 删除ICD10数据 
     * 
     * @param icdCode ICD10数据 ID
     * @return 结果
     */
    public int deleteBaseIcd10ById(String icdCode);

    /**
     * 批量删除ICD10数据 
     * 
     * @param icdCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseIcd10ByIds(String[] icdCodes);
}
