package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.claimflow.domain.PolicyAndRiskRelation;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;

import java.util.List;

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
     * 跨服务调用   查询保单险种关联列表
     *
     * @param policyAndRiskRelation 保单险种关联
     * @return 保单险种关联集合
     */
    public List<PolicyRiskRelation> selectPolicyAndRiskRelation(PolicyAndRiskRelation policyAndRiskRelation);

    /**
     * 跨服务调用   查询保单险种出单公司列表
     *
     * @param policyAndRiskRelation 保单险种关联
     * @return 保单险种关联集合
     */
    public List<CompanyRiskPolicyInfo> selectPolicyByCompanyRisk(PolicyAndRiskRelation policyAndRiskRelation);

    /**
     * 查询出单公司险种保单 字段列表
     *
     * @param policyAndRiskRelation  出单公司编码、险种编码
     * @return 结果
     */
    public List<PolicyRiskRelation> selectValidStartDateList(PolicyAndRiskRelation policyAndRiskRelation);

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
