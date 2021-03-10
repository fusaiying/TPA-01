package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.PolicyInsured;
import com.paic.ehis.claimflow.domain.vo.PolicyFlagVo;
import com.paic.ehis.claimflow.domain.vo.PolicyVo;
import com.paic.ehis.claimflow.mapper.PolicyInfoMapper;
import com.paic.ehis.claimflow.mapper.PolicyInsuredMapper;
import com.paic.ehis.claimflow.service.IPolicyInsuredService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 被保人信息Service业务层处理
 *
 * @author sino
 * @date 2021-01-09
 */
@Service
public class PolicyInsuredServiceImpl implements IPolicyInsuredService
{
    @Autowired
    private PolicyInsuredMapper policyInsuredMapper;
    @Autowired
    private PolicyInfoMapper policyInfoMapper;

    /**
     * 查询被保人信息
     *
     * @param insuredNo 被保人信息ID
     * @return 被保人信息
     */
    @Override
    public PolicyInsured selectPolicyInsuredById(String insuredNo)
    {
        return policyInsuredMapper.selectPolicyInsuredById(insuredNo);
    }

    /**
     * 查询被保人信息列表
     *
     * @param policyInsured 被保人信息
     * @return 被保人信息
     */
    @Override
    public List<PolicyInsured> selectPolicyInsuredList(PolicyInsured policyInsured)
    {
        return policyInsuredMapper.selectPolicyInsuredList(policyInsured);
    }

    /**
     * 新增被保人信息
     *
     * @param policyInsured 被保人信息
     * @return 结果
     */
    @Override
    public int insertPolicyInsured(PolicyInsured policyInsured)
    {
        policyInsured.setCreateTime(DateUtils.getNowDate());
        return policyInsuredMapper.insertPolicyInsured(policyInsured);
    }

    /**
     * 修改被保人信息
     *
     * @param policyInsured 被保人信息
     * @return 结果
     */
    @Override
    public int updatePolicyInsured(PolicyInsured policyInsured)
    {
        policyInsured.setUpdateTime(DateUtils.getNowDate());
        return policyInsuredMapper.updatePolicyInsured(policyInsured);
    }

    /**
     * 批量删除被保人信息
     *
     * @param insuredNos 需要删除的被保人信息ID
     * @return 结果
     */
    @Override
    public int deletePolicyInsuredByIds(String[] insuredNos)
    {
        return policyInsuredMapper.deletePolicyInsuredByIds(insuredNos);
    }

    /**
     * 删除被保人信息信息
     *
     * @param insuredNo 被保人信息ID
     * @return 结果
     */
    @Override
    public int deletePolicyInsuredById(String insuredNo)
    {
        return policyInsuredMapper.deletePolicyInsuredById(insuredNo);
    }
    /**
     * 被保人查询页面
     * @param policyInsured
     * @return
     */
    @Override
    public PolicyFlagVo selectRecognizee(PolicyInsured policyInsured) {
        PolicyFlagVo policyFlagVo=new PolicyFlagVo();
        //保单号是否为空
        if(policyInsured.getPolicyNo().isEmpty()){
            policyFlagVo.setPolicyInsuredList(policyInsuredMapper.selectRecognizee(policyInsured));
            policyFlagVo.setFlag("1");
        }/*else if(policyInsured.getPolicyNo() ){

        }*/
        else
        {

            PolicyVo policyVos = policyInfoMapper.selectPolicyRiskType(policyInsured.getPolicyNo());

            if(policyInsured.getName().isEmpty()) {

                if (policyVos!=null && "G".equals(policyVos.getPolicyRiskType())) {
                    policyFlagVo.setFlag("2");
                } else {
                    policyFlagVo.setFlag("3");
                }
            }
            else {
                policyFlagVo.setPolicyInsuredList(policyInsuredMapper.selectRecognizee(policyInsured));
                policyFlagVo.setFlag("1");
            }

        }

        return policyFlagVo;
    }
}
