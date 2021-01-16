package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.PolicyRiskRelation;

/**
 * 保单险种关联Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface IPolicyRiskRelationService 
{
    /**
     * 查询保单险种关联
     * 
     * @param relationId 保单险种关联ID
     * @return 保单险种关联
     */
    public PolicyRiskRelation selectPolicyRiskRelationById(Long relationId);

    /**
     * 查询保单险种关联列表
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 保单险种关联集合
     */
    public List<PolicyRiskRelation> selectPolicyRiskRelationList(PolicyRiskRelation policyRiskRelation);

    /**
     * 新增保单险种关联
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 结果
     */
    public int insertPolicyRiskRelation(PolicyRiskRelation policyRiskRelation);

    /**
     * 修改保单险种关联
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 结果
     */
    public int updatePolicyRiskRelation(PolicyRiskRelation policyRiskRelation);

    /**
     * 批量删除保单险种关联
     * 
     * @param relationIds 需要删除的保单险种关联ID
     * @return 结果
     */
    public int deletePolicyRiskRelationByIds(Long[] relationIds);

    /**
     * 删除保单险种关联信息
     * 
     * @param relationId 保单险种关联ID
     * @return 结果
     */
    public int deletePolicyRiskRelationById(Long relationId);
}
