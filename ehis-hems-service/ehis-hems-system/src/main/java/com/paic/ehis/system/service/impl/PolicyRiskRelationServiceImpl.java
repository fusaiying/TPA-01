package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.PolicyRiskRelation;
import com.paic.ehis.system.mapper.PolicyRiskRelationMapper;
import com.paic.ehis.system.service.IPolicyRiskRelationService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 保单险种关联Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class PolicyRiskRelationServiceImpl implements IPolicyRiskRelationService
{
    @Autowired
    private PolicyRiskRelationMapper policyRiskRelationMapper;

    /**
     * 查询保单险种关联
     * 
     * @param relationId 保单险种关联ID
     * @return 保单险种关联
     */
    @Override
    public PolicyRiskRelation selectPolicyRiskRelationById(Long relationId)
    {
        return policyRiskRelationMapper.selectPolicyRiskRelationById(relationId);
    }

    /**
     * 查询保单险种关联列表
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 保单险种关联
     */
    @Override
    public List<PolicyRiskRelation> selectPolicyRiskRelationList(PolicyRiskRelation policyRiskRelation)
    {
        return policyRiskRelationMapper.selectPolicyRiskRelationList(policyRiskRelation);
    }

    /**
     * 新增保单险种关联
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 结果
     */
    @Override
    public int insertPolicyRiskRelation(PolicyRiskRelation policyRiskRelation)
    {
        policyRiskRelation.setCreateTime(DateUtils.getNowDate());
        return policyRiskRelationMapper.insertPolicyRiskRelation(policyRiskRelation);
    }

    /**
     * 修改保单险种关联
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 结果
     */
    @Override
    public int updatePolicyRiskRelation(PolicyRiskRelation policyRiskRelation)
    {
        policyRiskRelation.setUpdateTime(DateUtils.getNowDate());
        return policyRiskRelationMapper.updatePolicyRiskRelation(policyRiskRelation);
    }

    /**
     * 批量删除保单险种关联
     * 
     * @param relationIds 需要删除的保单险种关联ID
     * @return 结果
     */
    @Override
    public int deletePolicyRiskRelationByIds(Long[] relationIds)
    {
        return policyRiskRelationMapper.deletePolicyRiskRelationByIds(relationIds);
    }

    /**
     * 删除保单险种关联信息
     * 
     * @param relationId 保单险种关联ID
     * @return 结果
     */
    @Override
    public int deletePolicyRiskRelationById(Long relationId)
    {
        return policyRiskRelationMapper.deletePolicyRiskRelationById(relationId);
    }
}
