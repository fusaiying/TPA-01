package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.BaseIssuingcompanyRiskrela;
import com.paic.ehis.system.mapper.BaseIssuingcompanyRiskrelaMapper;
import com.paic.ehis.system.service.IBaseIssuingcompanyRiskrelaService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出单公司产品关联 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyRiskrelaServiceImpl implements IBaseIssuingcompanyRiskrelaService
{
    @Autowired
    private BaseIssuingcompanyRiskrelaMapper baseIssuingcompanyRiskrelaMapper;

    /**
     * 查询出单公司产品关联 
     * 
     * @param companyCode 出单公司产品关联 ID
     * @return 出单公司产品关联 
     */
    @Override
    public BaseIssuingcompanyRiskrela selectBaseIssuingcompanyRiskrelaById(String companyCode)
    {
        return baseIssuingcompanyRiskrelaMapper.selectBaseIssuingcompanyRiskrelaById(companyCode);
    }

    /**
     * 查询出单公司产品关联 列表
     * 
     * @param baseIssuingcompanyRiskrela 出单公司产品关联 
     * @return 出单公司产品关联 
     */
    @Override
    public List<BaseIssuingcompanyRiskrela> selectBaseIssuingcompanyRiskrelaList(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela)
    {
        return baseIssuingcompanyRiskrelaMapper.selectBaseIssuingcompanyRiskrelaList(baseIssuingcompanyRiskrela);
    }

    /**
     * 新增出单公司产品关联 
     * 
     * @param baseIssuingcompanyRiskrela 出单公司产品关联 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompanyRiskrela(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela)
    {
        baseIssuingcompanyRiskrela.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyRiskrela.setStatus("Y");
        return baseIssuingcompanyRiskrelaMapper.insertBaseIssuingcompanyRiskrela(baseIssuingcompanyRiskrela);
    }

    /**
     * 修改出单公司产品关联 
     * 
     * @param baseIssuingcompanyRiskrela 出单公司产品关联 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompanyRiskrela(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela)
    {
        baseIssuingcompanyRiskrela.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyRiskrelaMapper.updateBaseIssuingcompanyRiskrela(baseIssuingcompanyRiskrela);
    }

    /**
     * 批量删除出单公司产品关联 
     * 
     * @param companyCodes 需要删除的出单公司产品关联 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyRiskrelaByIds(String[] companyCodes)
    {
        return baseIssuingcompanyRiskrelaMapper.deleteBaseIssuingcompanyRiskrelaByIds(companyCodes);
    }

    /**
     * 删除出单公司产品关联 信息
     * 
     * @param companyCode 出单公司产品关联 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyRiskrelaById(String companyCode)
    {
        return baseIssuingcompanyRiskrelaMapper.deleteBaseIssuingcompanyRiskrelaById(companyCode);
    }
}
