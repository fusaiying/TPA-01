package com.paic.ehis.policy.mapper;

import com.paic.ehis.policy.domain.PolicyInsured;

import java.util.List;

/**
 * 被保人信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface PolicyInsuredMapper 
{
    /**
     * 查询被保人信息
     * 
     * @param insuredNo 被保人信息ID
     * @return 被保人信息
     */
    public PolicyInsured selectPolicyInsuredById(String insuredNo);

    /**
     * 查询被保人信息列表
     * 
     * @param policyInsured 被保人信息
     * @return 被保人信息集合
     */
    public List<PolicyInsured> selectPolicyInsuredList(PolicyInsured policyInsured);

    /**
     * 新增被保人信息
     * 
     * @param policyInsured 被保人信息
     * @return 结果
     */
    public int insertPolicyInsured(PolicyInsured policyInsured);

    /**
     * 修改被保人信息
     * 
     * @param policyInsured 被保人信息
     * @return 结果
     */
    public int updatePolicyInsured(PolicyInsured policyInsured);

    /**
     * 删除被保人信息
     * 
     * @param insuredNo 被保人信息ID
     * @return 结果
     */
    public int deletePolicyInsuredById(String insuredNo);

    /**
     * 批量删除被保人信息
     * 
     * @param insuredNos 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyInsuredByIds(String[] insuredNos);

    public PolicyInsured selectPolicyInsuredByElement(PolicyInsured policyInsured);

    public String generatorInsuredNo();

}
