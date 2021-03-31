package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.PolicyInfo;
import com.paic.ehis.claimflow.domain.dto.PolicyDTO;
import com.paic.ehis.claimflow.domain.interfaceclass.InsuredNoAndName;
import com.paic.ehis.claimflow.domain.vo.PolicyVo;
import com.paic.ehis.system.api.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;

import java.util.List;

/**
 * 保单信息Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface IPolicyInfoService 
{
    /**
     * 查询保单信息
     * 
     * @param policyNo 保单信息ID
     * @return 保单信息
     */
    public PolicyInfo selectPolicyInfoById(String policyNo);

    /**
     * 根据保单号、分单号查询保单信息列表
     * 
     * @param policyAndRiskRelation 保单信息
     * @return 保单信息集合
     */
    public CompanyRiskPolicyInfo selectPolicyInfoListByPolicyNo(PolicyAndRiskRelation policyAndRiskRelation);

    /**
     * 查询保单信息列表
     *
     * @param policyInfo 保单信息
     * @return 保单信息集合
     */
    public List<PolicyInfo> selectPolicyInfoList(PolicyInfo policyInfo);

    /**
     * 新增保单信息
     * 
     * @param policyInfo 保单信息
     * @return 结果
     */
    public int insertPolicyInfo(PolicyInfo policyInfo);

    /**
     * 修改保单信息
     * 
     * @param policyInfo 保单信息
     * @return 结果
     */
    public int updatePolicyInfo(PolicyInfo policyInfo);

    /**
     * 批量删除保单信息
     * 
     * @param policyNos 需要删除的保单信息ID
     * @return 结果
     */
    public int deletePolicyInfoByIds(String[] policyNos);

    /**
     * 删除保单信息信息
     * 
     * @param policyNo 保单信息ID
     * @return 结果
     */
    public int deletePolicyInfoById(String policyNo);

    /**
     * 被保人保单信息页面
     * @param policyDTO
     * @return
     */
    public List<PolicyVo> selectInsuredList(PolicyDTO policyDTO);

    /**
     * 查询TPA保单
     *
     * @param insuredNoAndName 客户号和案件号
     * @return 保单信息集合
     */
    public List<PolicyInfo> selectPolicyInfoListByinsuredNo(InsuredNoAndName insuredNoAndName);
}
