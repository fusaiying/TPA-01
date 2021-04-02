package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.PolicyAndRiskRelation;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import com.paic.ehis.claimflow.domain.vo.ClaimHistory;
import com.paic.ehis.claimflow.domain.vo.GCCPlan;
import com.paic.ehis.system.api.domain.CompanyRiskPolicyInfo;

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

    public ClaimHistory selectRisk(String policyNo);

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
     * @return 出单公司险种保单集合
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
     * 删除保单险种关联
     * 
     * @param relationId 保单险种关联ID
     * @return 结果
     */
    public int deletePolicyRiskRelationById(Long relationId);

    /**
     * 批量删除保单险种关联
     * 
     * @param relationIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyRiskRelationByIds(Long[] relationIds);

    public List<PolicyRiskRelation> selectRiskNameInsuredList(String policyNo);

    public List<GCCPlan> selectRiskList(String policyNo);
}
