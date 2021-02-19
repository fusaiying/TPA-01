package com.paic.ehis.claimcal.mapper;


import com.paic.ehis.claimcal.domain.PolicyRiskRelation;

import java.util.List;

/**
 * 保单险种关联Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface PolicyRiskRelationMapper 
{

    /**
     * 查询保单险种关联列表
     *
     * @param policyRiskRelation 保单险种关联
     * @return 保单险种关联集合
     */
    public List<PolicyRiskRelation> selectPolicyRiskRelationList(PolicyRiskRelation policyRiskRelation);
}
