package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimRuleRelation;

/**
 * 规则对象关联 Service接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface IClaimRuleRelationService 
{
    /**
     * 查询规则对象关联 
     * 
     * @param relationNo 规则对象关联 ID
     * @return 规则对象关联 
     */
    public ClaimRuleRelation selectClaimRuleRelationById(String relationNo);

    /**
     * 查询规则对象关联 列表
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 规则对象关联 集合
     */
    public List<ClaimRuleRelation> selectClaimRuleRelationList(ClaimRuleRelation claimRuleRelation);

    /**
     * 新增规则对象关联 
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 结果
     */
    public int insertClaimRuleRelation(ClaimRuleRelation claimRuleRelation);

    /**
     * 修改规则对象关联 
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 结果
     */
    public int updateClaimRuleRelation(ClaimRuleRelation claimRuleRelation);

    /**
     * 批量删除规则对象关联 
     * 
     * @param relationNos 需要删除的规则对象关联 ID
     * @return 结果
     */
    public int deleteClaimRuleRelationByIds(String[] relationNos);

    /**
     * 删除规则对象关联 信息
     * 
     * @param relationNo 规则对象关联 ID
     * @return 结果
     */
    public int deleteClaimRuleRelationById(String relationNo);
}
