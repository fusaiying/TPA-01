package com.paic.ehis.policy.service.impl;

import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.policy.domain.PolicyInsured;
import com.paic.ehis.policy.mapper.PolicyInsuredMapper;
import com.paic.ehis.policy.service.IPolicyInsuredService;
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
public class PolicyInsuredServiceImpl implements IPolicyInsuredService {
    @Autowired
    private PolicyInsuredMapper policyInsuredMapper;

    /**
     * 查询被保人信息
     *
     * @param insuredNo 被保人信息ID
     * @return 被保人信息
     */
    @Override
    public PolicyInsured selectPolicyInsuredById(String insuredNo) {
        return policyInsuredMapper.selectPolicyInsuredById(insuredNo);
    }

    /**
     * 查询被保人信息列表
     *
     * @param policyInsured 被保人信息
     * @return 被保人信息
     */
    @Override
    public List<PolicyInsured> selectPolicyInsuredList(PolicyInsured policyInsured) {
        List<PolicyInsured> policyInsuredList = policyInsuredMapper.selectPolicyInsuredList(policyInsured);
        if (!policyInsuredList.isEmpty()) {
            policyInsuredMapper.selectPolicyInsuredList(policyInsured);
        }
        else {
            for (PolicyInsured policyInsured1 : policyInsuredList) {
            policyInsured1.setCreateTime(DateUtils.getNowDate());
            policyInsured1.setUpdateTime(DateUtils.getNowDate());
            policyInsuredMapper.insertPolicyInsured(policyInsured1);
        }
        }
        return policyInsuredList;
    }

    /**
     * 新增被保人信息
     *
     * @param policyInsured 被保人信息
     * @return 结果
     */
    @Override
    public int insertPolicyInsured(PolicyInsured policyInsured) {
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
    public int updatePolicyInsured(PolicyInsured policyInsured) {
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
    public int deletePolicyInsuredByIds(String[] insuredNos) {
        return policyInsuredMapper.deletePolicyInsuredByIds(insuredNos);
    }

    /**
     * 删除被保人信息信息
     *
     * @param insuredNo 被保人信息ID
     * @return 结果
     */
    @Override
    public int deletePolicyInsuredById(String insuredNo) {
        return policyInsuredMapper.deletePolicyInsuredById(insuredNo);
    }
}
