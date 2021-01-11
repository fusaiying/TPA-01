package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpBenefitRuleMapper;
import com.paic.ehis.system.domain.HmpBenefitRule;
import com.paic.ehis.system.service.IHmpBenefitRuleService;

/**
 * 权益分配规则Service业务层处理
 * 
 * @author sino
 * @date 2020-11-23
 */
@Service
public class HmpBenefitRuleServiceImpl implements IHmpBenefitRuleService 
{
    @Autowired
    private HmpBenefitRuleMapper hmpBenefitRuleMapper;

    /**
     * 查询权益分配规则
     * 
     * @param rulecode 权益分配规则ID
     * @return 权益分配规则
     */
    @Override
    public HmpBenefitRule selectHmpBenefitRuleById(String rulecode)
    {
        return hmpBenefitRuleMapper.selectHmpBenefitRuleById(rulecode);
    }

    /**
     * 查询权益分配规则列表
     * 
     * @param hmpBenefitRule 权益分配规则
     * @return 权益分配规则
     */
    @Override
    public List<HmpBenefitRule> selectHmpBenefitRuleList(HmpBenefitRule hmpBenefitRule)
    {
        return hmpBenefitRuleMapper.selectHmpBenefitRuleList(hmpBenefitRule);
    }

    /**
     * 新增权益分配规则
     * 
     * @param hmpBenefitRule 权益分配规则
     * @return 结果
     */
    @Override
    public int insertHmpBenefitRule(HmpBenefitRule hmpBenefitRule)
    {
        hmpBenefitRule.setCreateTime(DateUtils.getNowDate());
        return hmpBenefitRuleMapper.insertHmpBenefitRule(hmpBenefitRule);
    }

    /**
     * 修改权益分配规则
     * 
     * @param hmpBenefitRule 权益分配规则
     * @return 结果
     */
    @Override
    public int updateHmpBenefitRule(HmpBenefitRule hmpBenefitRule)
    {
        hmpBenefitRule.setUpdateTime(DateUtils.getNowDate());
        return hmpBenefitRuleMapper.updateHmpBenefitRule(hmpBenefitRule);
    }

    /**
     * 批量删除权益分配规则
     * 
     * @param rulecodes 需要删除的权益分配规则ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitRuleByIds(String[] rulecodes)
    {
        return hmpBenefitRuleMapper.deleteHmpBenefitRuleByIds(rulecodes);
    }

    /**
     * 删除权益分配规则信息
     * 
     * @param rulecode 权益分配规则ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitRuleById(String rulecode)
    {
        return hmpBenefitRuleMapper.deleteHmpBenefitRuleById(rulecode);
    }
}
