package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.mapper.BaseIssuingcompanyRuleMapper;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.domain.BaseIssuingcompanyRule;
import com.paic.ehis.system.service.IBaseIssuingcompanyRuleService;

/**
 * 出单公司规则 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyRuleServiceImpl implements IBaseIssuingcompanyRuleService 
{
    @Autowired
    private BaseIssuingcompanyRuleMapper baseIssuingcompanyRuleMapper;

    /**
     * 查询出单公司规则 
     * 
     * @param ruleNo 出单公司规则 ID
     * @return 出单公司规则 
     */
    @Override
    public BaseIssuingcompanyRule selectBaseIssuingcompanyRuleById(String ruleNo)
    {
        return baseIssuingcompanyRuleMapper.selectBaseIssuingcompanyRuleById(ruleNo);
    }

    /**
     * 查询出单公司规则 列表
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 出单公司规则 
     */
    @Override
    public List<BaseIssuingcompanyRule> selectBaseIssuingcompanyRuleList(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        return baseIssuingcompanyRuleMapper.selectBaseIssuingcompanyRuleList(baseIssuingcompanyRule);
    }

    /**
     * 新增出单公司规则 
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompanyRule(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        baseIssuingcompanyRule.setCreateTime(DateUtils.getNowDate());
        return baseIssuingcompanyRuleMapper.insertBaseIssuingcompanyRule(baseIssuingcompanyRule);
    }

    /**
     * 修改出单公司规则 
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompanyRule(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        baseIssuingcompanyRule.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyRuleMapper.updateBaseIssuingcompanyRule(baseIssuingcompanyRule);
    }

    /**
     * 批量删除出单公司规则 
     * 
     * @param ruleNos 需要删除的出单公司规则 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyRuleByIds(String[] ruleNos)
    {
        return baseIssuingcompanyRuleMapper.deleteBaseIssuingcompanyRuleByIds(ruleNos);
    }

    /**
     * 删除出单公司规则 信息
     * 
     * @param ruleNo 出单公司规则 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyRuleById(String ruleNo)
    {
        return baseIssuingcompanyRuleMapper.deleteBaseIssuingcompanyRuleById(ruleNo);
    }
}
