package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.HmpBenefitRuleCondition;

/**
 * 规则条件Mapper接口
 * 
 * @author sino
 * @date 2020-11-23
 */
public interface HmpBenefitRuleConditionMapper 
{
    /**
     * 查询规则条件
     * 
     * @param conditiontype 规则条件ID
     * @return 规则条件
     */
    public HmpBenefitRuleCondition selectHmpBenefitRuleConditionById(String conditiontype);

    /**
     * 查询规则条件列表
     * 
     * @param hmpBenefitRuleCondition 规则条件
     * @return 规则条件集合
     */
    public List<HmpBenefitRuleCondition> selectHmpBenefitRuleConditionList(HmpBenefitRuleCondition hmpBenefitRuleCondition);

    /**
     * 新增规则条件
     * 
     * @param hmpBenefitRuleCondition 规则条件
     * @return 结果
     */
    public int insertHmpBenefitRuleCondition(HmpBenefitRuleCondition hmpBenefitRuleCondition);

    /**
     * 修改规则条件
     * 
     * @param hmpBenefitRuleCondition 规则条件
     * @return 结果
     */
    public int updateHmpBenefitRuleCondition(HmpBenefitRuleCondition hmpBenefitRuleCondition);

    /**
     * 删除规则条件
     * 
     * @param conditiontype 规则条件ID
     * @return 结果
     */
    public int deleteHmpBenefitRuleConditionById(String conditiontype);

    /**
     * 批量删除规则条件
     * 
     * @param conditiontypes 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpBenefitRuleConditionByIds(String[] conditiontypes);
}
