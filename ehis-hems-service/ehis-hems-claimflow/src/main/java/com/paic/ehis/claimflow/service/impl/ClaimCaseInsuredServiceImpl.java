package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseInsured;
import com.paic.ehis.claimflow.domain.ClaimCasePolicy;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import com.paic.ehis.claimflow.domain.dto.InsuredAndPolicy;
import com.paic.ehis.claimflow.domain.dto.PolicyDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseInsureAndPoliyVo;
import com.paic.ehis.claimflow.domain.vo.DutyVo;
import com.paic.ehis.claimflow.domain.vo.PolicyVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseInsuredMapper;
import com.paic.ehis.claimflow.mapper.ClaimCasePolicyMapper;
import com.paic.ehis.claimflow.mapper.PolicyInfoMapper;
import com.paic.ehis.claimflow.mapper.PolicyRiskRelationMapper;
import com.paic.ehis.claimflow.service.IClaimCaseInsuredService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.paic.ehis.claimflow.utils.AgeUtils.getAge;


/**
 * 案件被保人信息Service业务层处理
 *
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseInsuredServiceImpl implements IClaimCaseInsuredService {
    @Autowired
    private ClaimCaseInsuredMapper claimCaseInsuredMapper;

    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    @Autowired
    private PolicyInfoMapper policyInfoMapper;

    @Autowired
    private PolicyRiskRelationMapper policyRiskRelationMapper;

    /**
     * 查询案件被保人信息
     *
     * @param rptNo 案件被保人信息ID
     * @return 案件被保人信息
     */
    @Override
    public ClaimCaseInsureAndPoliyVo selectClaimCaseInsuredById(String rptNo) {
        ClaimCaseInsureAndPoliyVo claimCaseInsureAndPoliyVo = new ClaimCaseInsureAndPoliyVo();
        //查询被保人信息
        ClaimCaseInsured claimCaseInsured1 = claimCaseInsuredMapper.selectClaimCaseInsuredListByRptNoTwo(rptNo);
        //判断查询被保人信息是否为空
        if (null != claimCaseInsured1) {
            claimCaseInsureAndPoliyVo.setClaimCaseInsured(claimCaseInsured1);
            PolicyDTO policyDTO = new PolicyDTO();
            policyDTO.setBirthday(claimCaseInsured1.getBirthday());
            policyDTO.setIdNo(claimCaseInsured1.getIdNo());
            policyDTO.setRptNo(rptNo);
            policyDTO.setIdType(claimCaseInsured1.getIdType());
            policyDTO.setInsuredNo(claimCaseInsured1.getInsuredNo());
            policyDTO.setName(claimCaseInsured1.getName());
            policyDTO.setSex(claimCaseInsured1.getSex());
            //查询案件保单信息关联表
            List<PolicyVo> l = new ArrayList<>();
            List<PolicyVo> policyVoList = claimCasePolicyMapper.selectInsuredListByNumber(policyDTO);
            if (policyVoList.size() != 0) {
                PolicyVo policyVo1 = new PolicyVo();
                for (PolicyVo policyVo : policyVoList) {
                    policyVo1.setPolicyId(policyVo.getPolicyId());
                    policyVo1.setPolicyNo(policyVo.getPolicyNo());
                    policyVo1.setPolicyItemNo(policyVo.getPolicyItemNo());
                    policyVo1.setInsuredNo(policyVo.getInsuredNo());
                    policyVo1.setName(policyVo.getName());
                    policyVo1.setAppName(policyVo.getAppName());
                    policyVo1.setValidStartDate(policyVo.getValidStartDate());
                    policyVo1.setValidEndDate(policyVo.getValidEndDate());
                    policyVo1.setPolicyRiskType(policyVo.getPolicyRiskType());
                    policyVo1.setPolicyStatus(policyVo.getPolicyStatus());
                    policyVo1.setSpecialAgreement(policyVo.getSpecialAgreement());
                    policyVo1.setCompanyName(policyVo.getCompanyName());
                    policyVo1.setCompanyCode(policyVo.getCompanyCode());
                    policyVo1.setPolicyType(policyVo.getPolicyType());
                    policyVo1.setPlanCode(policyVo.getPlanCode());
                    policyVo1.setOrgPolicyNo(policyVo.getOrgPolicyNo());
                    policyVo1.setOrgPolicyItemNo(policyVo.getPolicyItemNo());
                    policyVo1.setSsFlag(policyVo.getSsFlag());
                    //查询保单险种关联表
                    List<PolicyRiskRelation> policyRiskRelations = policyRiskRelationMapper.selectRiskNameInsuredList(policyVo.getPolicyNo());
                    if (policyRiskRelations.size() != 0) {
                        for (PolicyRiskRelation policyRiskRelation : policyRiskRelations) {
                            policyVo1.setRiskName(policyRiskRelation.getRiskName());
                            policyVo1.setRiskCode(policyRiskRelation.getRiskCode());
                        }
                    }
                    List<DutyVo> dutyVos = policyInfoMapper.selectDutyList(policyVo1.getRiskName(),policyVo1.getInsuredNo());//小集合
                    if (dutyVos.size()!=0){
                        policyVo1.setMinData(dutyVos);
                    }
                    l.add(policyVo1);
                }
                claimCaseInsureAndPoliyVo.setPolicyInfominData(l);
            }
        }
        return claimCaseInsureAndPoliyVo;
    }

    /**
     * 查询案件被保人信息列表
     *
     * @param claimCaseInsured 案件被保人信息
     * @return 案件被保人信息
     */
    @Override
    public List<ClaimCaseInsured> selectClaimCaseInsuredList(ClaimCaseInsured claimCaseInsured) {
        List<ClaimCaseInsured> claimCaseInsuredList = claimCaseInsuredMapper.selectClaimCaseInsuredList(claimCaseInsured);
        for (ClaimCaseInsured claimCaseInsured1 : claimCaseInsuredList) {
            Date birthday = claimCaseInsured1.getBirthday();
            claimCaseInsured1.setAge(getAge(birthday));
        }
        return claimCaseInsuredList;
    }

    /**
     * 新增案件被保人信息
     *
     * @param claimCaseInsured 案件被保人信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseInsured(ClaimCaseInsured claimCaseInsured) {
        claimCaseInsured.setCreateTime(DateUtils.getNowDate());
        return claimCaseInsuredMapper.insertClaimCaseInsured(claimCaseInsured);
    }

    /**
     * 修改案件被保人信息
     *
     * @param claimCaseInsured 案件被保人信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseInsured(ClaimCaseInsured claimCaseInsured) {
        claimCaseInsured.setUpdateTime(DateUtils.getNowDate());
        return claimCaseInsuredMapper.updateClaimCaseInsured(claimCaseInsured);
    }

    /**
     * 批量删除案件被保人信息
     *
     * @param rptNos 需要删除的案件被保人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInsuredByIds(String[] rptNos) {
        return claimCaseInsuredMapper.deleteClaimCaseInsuredByIds(rptNos);
    }

    /**
     * 删除案件被保人信息信息
     *
     * @param rptNo 案件被保人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInsuredById(String rptNo) {
        return claimCaseInsuredMapper.deleteClaimCaseInsuredById(rptNo);
    }

    /**
     * 新增被保人信息
     *
     * @param insuredAndPolicy
     * @return
     */
    @Override
    public int insertClaimCaseInsuredAndPolicy(InsuredAndPolicy insuredAndPolicy) {
//        private List<PolicyVo> policyNos;//保单号集合
//        private ClaimCaseInsured claimCaseInsured;//案件被保人信息表
        ClaimCaseInsured claimCaseInsured = insuredAndPolicy.getClaimCaseInsured();//得到案件被保人信息
        List<PolicyVo> policyNos = insuredAndPolicy.getPolicyNos();//得到案件保单关联表信息
        claimCaseInsured.setStatus("Y");
        claimCaseInsured.setCreateBy(SecurityUtils.getUsername());
        claimCaseInsured.setCreateTime(DateUtils.getNowDate());
        claimCaseInsured.setUpdateBy(SecurityUtils.getUsername());
        claimCaseInsured.setUpdateTime(DateUtils.getNowDate());
        int i;
        Long relationId = null;
        List<ClaimCaseInsured> claimCaseInsureds = claimCaseInsuredMapper.selectClaimCaseInsuredListByRptNo(claimCaseInsured.getRptNo());//案件被保人信息表

        if (claimCaseInsureds.size() != 0) {//为空
            for (ClaimCaseInsured claimCaseInsuredsOne : claimCaseInsureds) {//全部变为无效
                ClaimCaseInsured claimCaseInsured1 = new ClaimCaseInsured();
                claimCaseInsured1.setCiId(claimCaseInsuredsOne.getCiId());
                claimCaseInsured1.setStatus("N");
                claimCaseInsuredMapper.updateClaimCaseInsured(claimCaseInsured1);
            }
            i = claimCaseInsuredMapper.insertClaimCaseInsured(claimCaseInsured);
        } else {
            i = claimCaseInsuredMapper.insertClaimCaseInsured(claimCaseInsured);
        }
        for (PolicyVo policyNo : policyNos) {
            ClaimCasePolicy claimCasePolicy = new ClaimCasePolicy();
            claimCasePolicy.setPolicyNo(policyNo.getPolicyNo());//保单号
            claimCasePolicy.setRptNo(claimCaseInsured.getRptNo());//报案号
            claimCasePolicy.setStatus("Y");
            claimCasePolicy.setCreateBy(SecurityUtils.getUsername());
            claimCasePolicy.setCreateTime(DateUtils.getNowDate());
            claimCasePolicy.setUpdateBy(SecurityUtils.getUsername());
            claimCasePolicy.setUpdateTime(DateUtils.getNowDate());
            claimCasePolicy.setInsuredNo(policyNo.getInsuredNo());
            claimCasePolicy.setAppName(policyNo.getAppName());
            claimCasePolicy.setCompanyCode(policyNo.getCompanyCode());
            claimCasePolicy.setCompanyName(policyNo.getCompanyName());
            claimCasePolicy.setName(policyNo.getName());
            claimCasePolicy.setOrgPolicyItemNo(policyNo.getOrgPolicyItemNo());
            claimCasePolicy.setOrgPolicyNo(policyNo.getOrgPolicyNo());
            claimCasePolicy.setPolicyManageCom(policyNo.getPolicyItemNo());
            claimCasePolicy.setValidStartDate(policyNo.getValidStartDate());
            claimCasePolicy.setValidEndDate(policyNo.getValidEndDate());
            claimCasePolicy.setSsFlag(policyNo.getSsFlag());
            claimCasePolicy.setSpecialAgreement(policyNo.getSpecialAgreement());
            claimCasePolicy.setPolicyType(policyNo.getPolicyType());
            claimCasePolicy.setPolicyStatus(policyNo.getPolicyStatus());
            claimCasePolicy.setPolicyItemNo(policyNo.getPolicyItemNo());
            claimCasePolicy.setPolicyRiskType(policyNo.getPolicyRiskType());
            claimCasePolicy.setPlanCode(policyNo.getPlanCode());
            //claimCasePolicy.setSearchValue(policyNo.getSearchValue);
            List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(claimCasePolicy.getRptNo());//案件保单关联表
            if (claimCasePolicies == null || claimCasePolicies.size() == 0) {//为空
                i = claimCasePolicyMapper.insertClaimCasePolicy(claimCasePolicy);
            } else {//非空
                for (ClaimCasePolicy claimCasePoliciesTwo : claimCasePolicies) {//全部置为无效
                    ClaimCasePolicy claimCasePolicy1 = new ClaimCasePolicy();
                    relationId = claimCasePoliciesTwo.getRelationId();
                    claimCasePolicy1.setStatus("N");
                    claimCasePolicy1.setRelationId(relationId);
                    claimCasePolicyMapper.updateClaimCasePolicy(claimCasePolicy1);
                }
                i = claimCasePolicyMapper.insertClaimCasePolicy(claimCasePolicy);
            }
        }
        return i;
    }
}
