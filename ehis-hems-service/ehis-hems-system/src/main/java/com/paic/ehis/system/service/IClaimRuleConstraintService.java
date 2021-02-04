package com.paic.ehis.system.service;

import com.paic.ehis.system.domain.ClaimRuleConstraint;

import java.util.List;

/**
 * 规则条件 Service接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface IClaimRuleConstraintService 
{
    /**
     * 查询规则条件 
     * 
     * @param constraintNo 规则条件 ID
     * @return 规则条件 
     */
    public ClaimRuleConstraint selectClaimRuleConstraintById(String constraintNo);

    /**
     * 查询规则条件 列表
     * 
     * @param claimRuleConstraint 规则条件 
     * @return 规则条件 集合
     */
    public List<ClaimRuleConstraint> selectClaimRuleConstraintList(ClaimRuleConstraint claimRuleConstraint);

    /**
     * 新增规则条件 
     * 
     * @param claimRuleConstraint 规则条件 
     * @return 结果
     */
    public int insertClaimRuleConstraint(ClaimRuleConstraint claimRuleConstraint);

    /**
     * 修改规则条件 
     * 
     * @param claimRuleConstraint 规则条件 
     * @return 结果
     */
    public int updateClaimRuleConstraint(ClaimRuleConstraint claimRuleConstraint);

    /**
     * 批量删除规则条件 
     * 
     * @param constraintNos 需要删除的规则条件 ID
     * @return 结果
     */
    public int deleteClaimRuleConstraintByIds(String[] constraintNos);

    /**
     * 删除规则条件 信息
     * 
     * @param constraintNo 规则条件 ID
     * @return 结果
     */
    public int deleteClaimRuleConstraintById(String constraintNo);
}
