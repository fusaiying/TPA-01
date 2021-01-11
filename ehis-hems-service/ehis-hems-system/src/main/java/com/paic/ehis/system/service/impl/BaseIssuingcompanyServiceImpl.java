package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.BaseIssuingcompany;
import com.paic.ehis.system.mapper.BaseIssuingcompanyMapper;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.service.IBaseIssuingcompanyService;

/**
 * 出单公司信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyServiceImpl implements IBaseIssuingcompanyService 
{
    @Autowired
    private BaseIssuingcompanyMapper baseIssuingcompanyMapper;

    /**
     * 查询出单公司信息 
     * 
     * @param companyCode 出单公司信息 ID
     * @return 出单公司信息 
     */
    @Override
    public BaseIssuingcompany selectBaseIssuingcompanyById(String companyCode)
    {
        return baseIssuingcompanyMapper.selectBaseIssuingcompanyById(companyCode);
    }

    /**
     * 查询出单公司信息 列表
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 出单公司信息 
     */
    @Override
    public List<BaseIssuingcompany> selectBaseIssuingcompanyList(BaseIssuingcompany baseIssuingcompany)
    {
        return baseIssuingcompanyMapper.selectBaseIssuingcompanyList(baseIssuingcompany);
    }

    /**
     * 新增出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany)
    {
        baseIssuingcompany.setCreateTime(DateUtils.getNowDate());
        return baseIssuingcompanyMapper.insertBaseIssuingcompany(baseIssuingcompany);
    }

    /**
     * 修改出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany)
    {
        baseIssuingcompany.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyMapper.updateBaseIssuingcompany(baseIssuingcompany);
    }

    /**
     * 批量删除出单公司信息 
     * 
     * @param companyCodes 需要删除的出单公司信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyByIds(String[] companyCodes)
    {
        return baseIssuingcompanyMapper.deleteBaseIssuingcompanyByIds(companyCodes);
    }

    /**
     * 删除出单公司信息 信息
     * 
     * @param companyCode 出单公司信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyById(String companyCode)
    {
        return baseIssuingcompanyMapper.deleteBaseIssuingcompanyById(companyCode);
    }
}
