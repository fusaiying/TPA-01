package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpBenefitRuleConditionMapper;
import com.paic.ehis.system.domain.HmpBenefitRuleCondition;
import com.paic.ehis.system.service.IHmpBenefitRuleConditionService;

/**
 * 规则条件Service业务层处理
 * 
 * @author sino
 * @date 2020-11-23
 */
@Service
public class HmpBenefitRuleConditionServiceImpl implements IHmpBenefitRuleConditionService 
{
    @Autowired
    private HmpBenefitRuleConditionMapper hmpBenefitRuleConditionMapper;

    /**
     * 查询规则条件
     * 
     * @param conditiontype 规则条件ID
     * @return 规则条件
     */
    @Override
    public HmpBenefitRuleCondition selectHmpBenefitRuleConditionById(String conditiontype)
    {
        return hmpBenefitRuleConditionMapper.selectHmpBenefitRuleConditionById(conditiontype);
    }

    /**
     * 查询规则条件列表
     * 
     * @param hmpBenefitRuleCondition 规则条件
     * @return 规则条件
     */
    @Override
    public List<HmpBenefitRuleCondition> selectHmpBenefitRuleConditionList(HmpBenefitRuleCondition hmpBenefitRuleCondition)
    {
        return hmpBenefitRuleConditionMapper.selectHmpBenefitRuleConditionList(hmpBenefitRuleCondition);
    }

    /**
     * 新增规则条件
     * 
     * @param hmpBenefitRuleCondition 规则条件
     * @return 结果
     */
    @Override
    public int insertHmpBenefitRuleCondition(HmpBenefitRuleCondition hmpBenefitRuleCondition)
    {
        hmpBenefitRuleCondition.setCreateTime(DateUtils.getNowDate());
        return hmpBenefitRuleConditionMapper.insertHmpBenefitRuleCondition(hmpBenefitRuleCondition);
    }

    /**
     * 修改规则条件
     * 
     * @param hmpBenefitRuleCondition 规则条件
     * @return 结果
     */
    @Override
    public int updateHmpBenefitRuleCondition(HmpBenefitRuleCondition hmpBenefitRuleCondition)
    {
        hmpBenefitRuleCondition.setUpdateTime(DateUtils.getNowDate());
        return hmpBenefitRuleConditionMapper.updateHmpBenefitRuleCondition(hmpBenefitRuleCondition);
    }

    /**
     * 批量删除规则条件
     * 
     * @param conditiontypes 需要删除的规则条件ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitRuleConditionByIds(String[] conditiontypes)
    {
        return hmpBenefitRuleConditionMapper.deleteHmpBenefitRuleConditionByIds(conditiontypes);
    }

    /**
     * 删除规则条件信息
     * 
     * @param conditiontype 规则条件ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitRuleConditionById(String conditiontype)
    {
        return hmpBenefitRuleConditionMapper.deleteHmpBenefitRuleConditionById(conditiontype);
    }
}
