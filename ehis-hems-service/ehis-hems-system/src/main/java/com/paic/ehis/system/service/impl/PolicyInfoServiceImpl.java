package com.paic.ehis.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paic.ehis.system.domain.PolicyInfo;
import com.paic.ehis.system.domain.dto.PolicyDTO;
import com.paic.ehis.system.domain.vo.DutyVo;
import com.paic.ehis.system.domain.vo.PolicyVo;
import com.paic.ehis.system.mapper.PolicyInfoMapper;
import com.paic.ehis.system.service.IPolicyInfoService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<PolicyVo> policyVoList = policyInfoMapper.selectInsuredList(policyDTO);//属性值
        PolicyVo policyVo1 = new PolicyVo();
        for (PolicyVo policyVo:policyVoList){
            policyVo1.setPolicyNo(policyVo.getPolicyNo());
            policyVo1.setPolicyItemNo(policyVo.getPolicyItemNo());
            policyVo1.setName(policyVo.getName());
            policyVo1.setValidStartDate(policyVo.getValidStartDate());
            policyVo1.setValidEndDate(policyVo.getValidEndDate());
            policyVo1.setPolicyRiskType(policyVo.getPolicyRiskType());
            policyVo1.setPolicyStatus(policyVo.getPolicyStatus());
            policyVo1.setSpecialAgreement(policyVo.getSpecialAgreement());
            policyVo1.setCompanyName(policyVo.getCompanyName());
            policyVo1.setPolicyType(policyVo.getPolicyType());
            policyVo1.setSsFlag(policyVo.getSsFlag());
            policyVo1.setRiskName(policyVo.getRiskName());
            List<DutyVo> dutyVos = policyInfoMapper.selectDutyList(policyVo.getRiskName());//小集合
            policyVo1.setMinData(dutyVos);
            l.add(policyVo1);

        }
        System.out.println("aaaa");
       /*
        List<DutyVo> dutyVos = policyInfoMapper.selectDutyList(policyDTO);
        for (DutyVo duty: dutyVos){
            duty.setRiskCode(SecurityUtils.getUsername());
            duty.setDutyCode(SecurityUtils.getUsername());
            duty.setDutyName(SecurityUtils.getUsername());
            duty.setDutyDetailCode(SecurityUtils.getUsername());
            duty.setDutyDetailName(SecurityUtils.getUsername());
        }*/
       // l.setMinData(policyInfoMapper.selectDutyList(policyDTO));
        return l;
    }
}
