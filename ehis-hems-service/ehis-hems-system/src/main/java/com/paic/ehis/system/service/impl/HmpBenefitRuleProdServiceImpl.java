package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.HmpBenefitRuleProd;
import com.paic.ehis.system.mapper.HmpBenefitRuleProdMapper;
import com.paic.ehis.system.service.IHmpBenefitRuleProdService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分配内容Service业务层处理
 * 
 * @author sino
 * @date 2020-11-23
 */
@Service
public class HmpBenefitRuleProdServiceImpl implements IHmpBenefitRuleProdService
{
    @Autowired
    private HmpBenefitRuleProdMapper hmpBenefitRuleProdMapper;

    /**
     * 查询分配内容
     * 
     * @param rulecode 分配内容ID
     * @return 分配内容
     */
    @Override
    public HmpBenefitRuleProd selectHmpBenefitRuleProdById(String rulecode)
    {
        return hmpBenefitRuleProdMapper.selectHmpBenefitRuleProdById(rulecode);
    }

    /**
     * 查询分配内容列表
     * 
     * @param hmpBenefitRuleProd 分配内容
     * @return 分配内容
     */
    @Override
    public List<HmpBenefitRuleProd> selectHmpBenefitRuleProdList(HmpBenefitRuleProd hmpBenefitRuleProd)
    {
        return hmpBenefitRuleProdMapper.selectHmpBenefitRuleProdList(hmpBenefitRuleProd);
    }

    /**
     * 新增分配内容
     * 
     * @param hmpBenefitRuleProd 分配内容
     * @return 结果
     */
    @Override
    public int insertHmpBenefitRuleProd(HmpBenefitRuleProd hmpBenefitRuleProd)
    {
        hmpBenefitRuleProd.setCreateTime(DateUtils.getNowDate());
        return hmpBenefitRuleProdMapper.insertHmpBenefitRuleProd(hmpBenefitRuleProd);
    }

    /**
     * 修改分配内容
     * 
     * @param hmpBenefitRuleProd 分配内容
     * @return 结果
     */
    @Override
    public int updateHmpBenefitRuleProd(HmpBenefitRuleProd hmpBenefitRuleProd)
    {
        hmpBenefitRuleProd.setUpdateTime(DateUtils.getNowDate());
        return hmpBenefitRuleProdMapper.updateHmpBenefitRuleProd(hmpBenefitRuleProd);
    }

    /**
     * 批量删除分配内容
     * 
     * @param rulecodes 需要删除的分配内容ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitRuleProdByIds(String[] rulecodes)
    {
        return hmpBenefitRuleProdMapper.deleteHmpBenefitRuleProdByIds(rulecodes);
    }

    /**
     * 删除分配内容信息
     * 
     * @param rulecode 分配内容ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitRuleProdById(String rulecode)
    {
        return hmpBenefitRuleProdMapper.deleteHmpBenefitRuleProdById(rulecode);
    }
}
