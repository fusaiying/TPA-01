package com.paic.ehis.policy.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.policy.domain.PolicyInsured;
import com.paic.ehis.policy.mapper.PolicyInsuredMapper;
import com.paic.ehis.policy.service.IPolicyInsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * 根据5要素查询被保人信息 ，如果没查询到则新增一条数据
     * modify: hjw
     * time:2021-3-8
     */
    @Override
    public PolicyInsured getInfoByElement(PolicyInsured bean)  {
        PolicyInsured result = policyInsuredMapper.selectPolicyInsuredByElement(bean);
        if(null == result) {
            Date nowDate = new Date();
            String username = SecurityUtils.getUsername();
            bean.setCreateBy(username);
            bean.setUpdateBy(username);
            bean.setCreateTime(nowDate);
            bean.setUpdateTime(nowDate);
            // 根据5要素没有查询到被保险人， 则需要新增一条记录
            String insuredNo = policyInsuredMapper.generatorInsuredNo();
            bean.setInsuredNo(insuredNo);
            bean.setStatus("Y");
            int insertResult = policyInsuredMapper.insertPolicyInsured(bean);
            if(insertResult  == 1) {
               return bean;
            }
        }
        return result;
    }

}
