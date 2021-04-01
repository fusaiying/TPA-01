package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.interfaceclass.InsuredNoAndName;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.claimflow.domain.PolicyInfo;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import com.paic.ehis.claimflow.domain.dto.PolicyDTO;
import com.paic.ehis.claimflow.domain.vo.DutyVo;
import com.paic.ehis.claimflow.domain.vo.PolicyVo;
import com.paic.ehis.claimflow.mapper.PolicyInfoMapper;
import com.paic.ehis.claimflow.mapper.PolicyRiskRelationMapper;
import com.paic.ehis.claimflow.service.IPolicyInfoService;
import com.paic.ehis.system.api.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 保单信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class PolicyInfoServiceImpl implements IPolicyInfoService 
{
    @Autowired
    private PolicyInfoMapper policyInfoMapper;
    @Autowired
    private PolicyRiskRelationMapper policyRiskRelationMapper;

    /**
     * 查询保单信息
     * 
     * @param policyNo 保单信息ID
     * @return 保单信息
     */
    @Override
    public PolicyInfo selectPolicyInfoById(String policyNo)
    {
        return policyInfoMapper.selectPolicyInfoById(policyNo);
    }

    /**
     * 查询保单信息列表
     *
     * @param policyAndRiskRelation 保单信息
     * @return 保单信息集合
     */
    @Override
    public CompanyRiskPolicyInfo selectPolicyInfoListByPolicyNo(PolicyAndRiskRelation policyAndRiskRelation) {
        return policyInfoMapper.selectPolicyInfoListByPolicyNo(policyAndRiskRelation);
    }
    /* 根据出单公司险种查询 险种层级数据 */
    @Override
    public CompanyRiskPolicyInfo selectCompanyRiskInfo(PolicyAndRiskRelation policyAndRiskRelation) {
        return policyInfoMapper.selectCompanyRiskInfo(policyAndRiskRelation);
    }
    /* 根据出单公司险种查询 分单层级数据 */
    @Override
    public CompanyRiskPolicyInfo selectCompanyRiskPolicyInfo(PolicyAndRiskRelation policyAndRiskRelation) {
        return policyInfoMapper.selectCompanyRiskPolicyInfo(policyAndRiskRelation);
    }

    /**
     * 查询保单信息列表
     * 
     * @param policyInfo 保单信息
     * @return 保单信息
     */
    @Override
    public List<PolicyInfo> selectPolicyInfoList(PolicyInfo policyInfo)
    {
        return policyInfoMapper.selectPolicyInfoList(policyInfo);
    }

    /**
     * 新增保单信息
     * 
     * @param policyInfo 保单信息
     * @return 结果
     */
    @Override
    public int insertPolicyInfo(PolicyInfo policyInfo)
    {
        policyInfo.setCreateTime(DateUtils.getNowDate());
        return policyInfoMapper.insertPolicyInfo(policyInfo);
    }

    /**
     * 修改保单信息
     * 
     * @param policyInfo 保单信息
     * @return 结果
     */
    @Override
    public int updatePolicyInfo(PolicyInfo policyInfo)
    {
        policyInfo.setUpdateTime(DateUtils.getNowDate());
        return policyInfoMapper.updatePolicyInfo(policyInfo);
    }

    /**
     * 批量删除保单信息
     * 
     * @param policyNos 需要删除的保单信息ID
     * @return 结果
     */
    @Override
    public int deletePolicyInfoByIds(String[] policyNos)
    {
        return policyInfoMapper.deletePolicyInfoByIds(policyNos);
    }

    /**
     * 删除保单信息信息
     * 
     * @param policyNo 保单信息ID
     * @return 结果
     */
    @Override
    public int deletePolicyInfoById(String policyNo)
    {
        return policyInfoMapper.deletePolicyInfoById(policyNo);
    }

    /**
     * 被保人保单信息页面
     * @param policyDTO
     * @return
     */
    @Override
    public List<PolicyVo> selectInsuredList(PolicyDTO policyDTO) {

        List<PolicyVo> l = new ArrayList<>();
        List<PolicyVo> policyVoList = policyInfoMapper.selectInsuredList(policyDTO.getInsuredNo());//属性值
        PolicyVo policyVo1 = new PolicyVo();
        for (PolicyVo policyVo:policyVoList){
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
            policyVo1.setPolicyManageCom(policyVo.getPolicyManageCom());
            List<PolicyRiskRelation> policyRiskRelations=policyRiskRelationMapper.selectRiskNameInsuredList(policyVo.getPolicyNo());
            for (PolicyRiskRelation policyRiskRelation:policyRiskRelations
                 ) {policyVo1.setRiskName(policyRiskRelation.getRiskName());
                policyVo1.setRiskCode(policyRiskRelation.getRiskCode());
            }
            List<DutyVo> dutyVos = policyInfoMapper.selectDutyList(policyVo1.getRiskName(),policyVo1.getInsuredNo());//小集合
            policyVo.setMinData(dutyVos);
            l.add(policyVo);
            }
       return  l;
    }

    /**
     * 查询TPA保单
     * @param insuredNoAndName 客户号和案件号
     * @return
     */
    @Override
    public List<PolicyInfo> selectPolicyInfoListByinsuredNo(InsuredNoAndName insuredNoAndName){
        List<PolicyInfo> policyInfos = policyInfoMapper.selectPolicyInfoListByinsuredNo(insuredNoAndName);
        return policyInfos;
    }
}
