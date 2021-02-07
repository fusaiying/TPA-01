package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.PolicyRiskRelation;

import java.util.List;

/**
 * 保单险种关联Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface PolicyRiskRelationMapper 
{
    public List<PolicyRiskRelation> selectRiskNameInsuredList(String policyNo);
}
