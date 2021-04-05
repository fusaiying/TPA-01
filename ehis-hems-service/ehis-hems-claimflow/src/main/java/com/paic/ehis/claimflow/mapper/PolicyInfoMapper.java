package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimCaseShuntClass;
import com.paic.ehis.claimflow.domain.CustomerInfoMap;
import com.paic.ehis.claimflow.domain.PolicyInfo;
import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.dto.PolicyDTO;
import com.paic.ehis.claimflow.domain.interfaceclass.InsuredNoAndName;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.system.api.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 保单信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
@Mapper
public interface PolicyInfoMapper 
{
    /**
     * 查询保单信息
     * 
     * @param policyNo 保单信息ID
     * @return 保单信息
     */
    public PolicyInfo selectPolicyInfoById(String policyNo);

    /**
     * 查询保单信息列表
     * 
     * @param policyInfo 保单信息
     * @return 保单信息集合
     */
    public List<PolicyInfo> selectPolicyInfoList(PolicyInfo policyInfo);

    /* 根据保单、分单查询保单数据 */
    public CompanyRiskPolicyInfo selectPolicyInfoListByPolicyNo(PolicyAndRiskRelation policyAndRiskRelation);

/* 根据出单公司险种查询 险种层级数据 */
    public CompanyRiskPolicyInfo selectCompanyRiskInfo(PolicyAndRiskRelation policyAndRiskRelation);

    /* 根据出单公司险种查询 分单层级数据 */
    public CompanyRiskPolicyInfo selectCompanyRiskPolicyInfo(PolicyAndRiskRelation policyAndRiskRelation);

    public CustomerInfoMap selectCustomerInfoMap(String insuredNo);

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
     * 删除保单信息
     * 
     * @param policyNo 保单信息ID
     * @return 结果
     */
    public int deletePolicyInfoById(String policyNo);

    /**
     * 批量删除保单信息
     * 
     * @param policyNos 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyInfoByIds(String[] policyNos);

    /**
     * 被保人保单信息页面
     * @param insuredNo
     * @return
     */
    List<PolicyVo> selectInsuredList(String insuredNo);

    PolicyVo selectPolicyRiskType(String policyNo);

    List<DutyVo> selectDutyList(String riskName,String insuredNo);

    PolicyInfo selectPolicyInfoByIdOne(String insuredNo);

    PolicyInfo selectPolicyInfoByIdThree(String insuredNo);

    List<PolicyInfo> selectPolicyInfoByInsuredNo(ClaimCaseShuntClass claimCaseShuntClass);

    List<PolicyInfo> selectPolicyInfoListByinsuredNo(InsuredNoAndName claimCaseShuntClass);

    public List<PolicyListVo> selectPolicyList(ClaimFlowDTO claimFlowDTO);

    /**
     * 提供GCC保单列表
     * @param claimFlowDTO
     * @return
     */
    public List<GCCPolicyListVo> queryPolicyListToGCC(ClaimFlowDTO claimFlowDTO);

    /**
     * GCC保单预授权信息
     * @param riskCode
     * @return
     */
    public List<GCCPreAuthorization> selectPreAuthorizationByRisk(String riskCode);

    /**
     * GCC等待期信息提取
     * @return
     */
    public List<GCCHealthServiceWaitPeriod> queryRiskWaitPeriodToGCC(String riskCode);

    /**
     * GCC保单资料信息接口
     * @param claimFlowDTO
     * @return
     */
    public GCCPolicyInfoVo queryPolicyInfoToGCC(ClaimFlowDTO claimFlowDTO);

    /**
     * GCC分单责任信息
     * @param claimFlowDTO
     * @return
     */
    public GCCPolicyCertInfoVo queryDutyInfoToGCC(ClaimFlowDTO claimFlowDTO);

    /**
     * GCC服务信息
     * @param claimFlowDTO
     * @return
     */
    public GCCHealthServiceInfoVo queryHealthServiceToGCC(ClaimFlowDTO claimFlowDTO);



}
