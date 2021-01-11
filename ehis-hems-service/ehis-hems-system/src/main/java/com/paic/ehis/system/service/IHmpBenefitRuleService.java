package com.paic.ehis.system.service;

import java.util.List;
import com.paic.ehis.system.domain.HmpBenefitRule;

/**
 * 权益分配规则Service接口
 * 
 * @author sino
 * @date 2020-11-23
 */
public interface IHmpBenefitRuleService 
{
    /**
     * 查询权益分配规则
     * 
     * @param rulecode 权益分配规则ID
     * @return 权益分配规则
     */
    public HmpBenefitRule selectHmpBenefitRuleById(String rulecode);

    /**
     * 查询权益分配规则列表
     * 
     * @param hmpBenefitRule 权益分配规则
     * @return 权益分配规则集合
     */
    public List<HmpBenefitRule> selectHmpBenefitRuleList(HmpBenefitRule hmpBenefitRule);

    /**
     * 新增权益分配规则
     * 
     * @param hmpBenefitRule 权益分配规则
     * @return 结果
     */
    public int insertHmpBenefitRule(HmpBenefitRule hmpBenefitRule);

    /**
     * 修改权益分配规则
     * 
     * @param hmpBenefitRule 权益分配规则
     * @return 结果
     */
    public int updateHmpBenefitRule(HmpBenefitRule hmpBenefitRule);

    /**
     * 批量删除权益分配规则
     * 
     * @param rulecodes 需要删除的权益分配规则ID
     * @return 结果
     */
    public int deleteHmpBenefitRuleByIds(String[] rulecodes);

    /**
     * 删除权益分配规则信息
     * 
     * @param rulecode 权益分配规则ID
     * @return 结果
     */
    public int deleteHmpBenefitRuleById(String rulecode);
}
