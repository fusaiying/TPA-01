package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.claimflow.domain.PolicyAndRiskRelation;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import com.paic.ehis.claimflow.mapper.PolicyRiskRelationMapper;
import com.paic.ehis.claimflow.service.IPolicyRiskRelationService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     *  查询保单险种关联列表
     * 
     * @param policyRiskRelation 保单险种关联
     * @return 保单险种关联
     */
    @Override
    public List<PolicyRiskRelation> selectPolicyRiskRelationList(PolicyRiskRelation policyRiskRelation)
    {
        policyRiskRelation.setStatus("Y");
        return policyRiskRelationMapper.selectPolicyRiskRelationList(policyRiskRelation);
    }

    /**
     * 跨服务调用   查询保单险种关联列表
     *
     * @param policyAndRiskRelation 保单险种关联
     * @return 保单险种关联集合
     */
    @Override
    public List<PolicyRiskRelation> selectPolicyAndRiskRelation(PolicyAndRiskRelation policyAndRiskRelation) {
        return policyRiskRelationMapper.selectPolicyAndRiskRelation(policyAndRiskRelation);
    }

    /**
     * 跨服务调用   查询保单险种出单公司列表
     *
     * @param policyAndRiskRelation 保单险种关联
     * @return 保单险种关联集合
     */
    @Override
    public List<CompanyRiskPolicyInfo> selectPolicyByCompanyRisk(PolicyAndRiskRelation policyAndRiskRelation) {
        return policyRiskRelationMapper.selectPolicyByCompanyRisk(policyAndRiskRelation);
    }

    /**
     * 查询出单公司险种保单 字段列表
     *
     * @param policyAndRiskRelation 出单公司编码、险种编码
     * @return 结果
     */
    @Override
    public List<PolicyRiskRelation> selectValidStartDateList(PolicyAndRiskRelation policyAndRiskRelation) {
        return policyRiskRelationMapper.selectValidStartDateList(policyAndRiskRelation);
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
