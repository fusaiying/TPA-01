package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseIcd10;
import com.paic.ehis.base.mapper.BaseIcd10Mapper;
import com.paic.ehis.base.service.IBaseIcd10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ICD10数据 Service业务层处理
 *
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIcd10ServiceImpl implements IBaseIcd10Service {
    @Autowired
    private BaseIcd10Mapper baseIcd10Mapper;

    /**
     * 查询ICD10数据
     *
     * @param icdCode ICD10数据 ID
     * @return ICD10数据
     */
    @Override
    public List<BaseIcd10> selectBaseIcd10ById(String icdCode) {
        return baseIcd10Mapper.selectBaseIcd10ById(icdCode);
    }

    /**
     * 查询ICD10数据
     *
     * @param icdmname ICD10数据 name
     * @return ICD10数据
     */
    @Override
    public List<BaseIcd10> selectBaseIcd10ByName(String icdmname) {
        return baseIcd10Mapper.selectBaseIcd10ByName(icdmname);
    }

    /**
     * 查询ICD10数据 列表
     *
     * @param baseIcd10 ICD10数据
     * @return ICD10数据
     */
    @Override
    public List<BaseIcd10> selectBaseIcd10List(BaseIcd10 baseIcd10) {
        baseIcd10.setStatus("Y");
        return baseIcd10Mapper.selectBaseIcd10List(baseIcd10);
    }


    /**
     * 新增ICD10数据
     *
     * @param baseIcd10 ICD10数据
     * @return 结果
     */
    @Override
    public int insertBaseIcd10(BaseIcd10 baseIcd10) {
        String number = "I" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 5);
        baseIcd10.setIcdNo(number);
        baseIcd10.setSource("2");//来源 否 基础库
        baseIcd10.setCreateBy(SecurityUtils.getUsername());
        baseIcd10.setCreateTime(DateUtils.getNowDate());
       /* baseIcd10.setUpdateBy(SecurityUtils.getUsername());
        baseIcd10.setUpdateTime(DateUtils.getNowDate());*/
        baseIcd10.setStatus("Y");//状态
        return baseIcd10Mapper.insertBaseIcd10(baseIcd10);
    }

    /**
     * 修改ICD10数据
     *
     * @param baseIcd10 ICD10数据
     * @return 结果
     */
    @Override
    public int updateBaseIcd10(BaseIcd10 baseIcd10) {
        int i=0;
        BaseIcd10 baseIcd101 = baseIcd10Mapper.selectBaseIcd10ByIdOne(baseIcd10.getIcdcode());//查询以往数据只有一条
        if (baseIcd101 != null) {
            baseIcd10.setIcdNo(baseIcd101.getIcdNo());
            baseIcd10.setUpdateBy(SecurityUtils.getUsername());
            baseIcd10.setUpdateTime(DateUtils.getNowDate());
            i=baseIcd10Mapper.updateBaseIcd10(baseIcd10);
        }
        return i;
    }

    /**
     * 批量删除ICD10数据
     *
     * @param icdCodes 需要删除的ICD10数据 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIcd10ByIds(String[] icdCodes) {
        return baseIcd10Mapper.deleteBaseIcd10ByIds(icdCodes);
    }

    /**
     * 删除ICD10数据 信息
     *
     * @param icdCode ICD10数据 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIcd10ById(String icdCode) {
        return baseIcd10Mapper.deleteBaseIcd10ById(icdCode);
    }

    /**
     * 删除
     *
     * @param baseIcd10
     * @return
     */
    @Override
    public int updateBaseIcd10One(BaseIcd10 baseIcd10) {
        baseIcd10.setStatus("N");
        baseIcd10.setUpdateBy(SecurityUtils.getUsername());
        baseIcd10.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        return baseIcd10Mapper.updateBaseIcd10(baseIcd10);
    }

    /**
     * 模糊查询ICD，默认展示200条
     *
     * @param baseIcd10
     * @return
     */
    @Override
    public List<BaseIcd10> selectIcdFuzzy(BaseIcd10 baseIcd10) {
        baseIcd10.setStatus("Y");
        return baseIcd10Mapper.selectIcdFuzzy(baseIcd10);
    }
}
