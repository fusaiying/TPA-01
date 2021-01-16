package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseInsured;
import com.paic.ehis.system.domain.ClaimCasePolicy;
import com.paic.ehis.system.domain.dto.InsuredAndPolicy;
import com.paic.ehis.system.mapper.ClaimCaseInsuredMapper;
import com.paic.ehis.system.mapper.ClaimCasePolicyMapper;
import com.paic.ehis.system.service.IClaimCaseInsuredService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件被保人信息Service业务层处理
 *
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseInsuredServiceImpl implements IClaimCaseInsuredService
{
    @Autowired
    private ClaimCaseInsuredMapper claimCaseInsuredMapper;

    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    /**
     * 查询案件被保人信息
     *
     * @param rptNo 案件被保人信息ID
     * @return 案件被保人信息
     */
    @Override
    public ClaimCaseInsured selectClaimCaseInsuredById(String rptNo)
    {
        return claimCaseInsuredMapper.selectClaimCaseInsuredById(rptNo);
    }

    /**
     * 查询案件被保人信息列表
     *
     * @param claimCaseInsured 案件被保人信息
     * @return 案件被保人信息
     */
    @Override
    public List<ClaimCaseInsured> selectClaimCaseInsuredList(ClaimCaseInsured claimCaseInsured)
    {
        return claimCaseInsuredMapper.selectClaimCaseInsuredList(claimCaseInsured);
    }

    /**
     * 新增案件被保人信息
     *
     * @param claimCaseInsured 案件被保人信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseInsured(ClaimCaseInsured claimCaseInsured)
    {
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
    public int updateClaimCaseInsured(ClaimCaseInsured claimCaseInsured)
    {
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
    public int deleteClaimCaseInsuredByIds(String[] rptNos)
    {
        return claimCaseInsuredMapper.deleteClaimCaseInsuredByIds(rptNos);
    }

    /**
     * 删除案件被保人信息信息
     *
     * @param rptNo 案件被保人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInsuredById(String rptNo)
    {
        return claimCaseInsuredMapper.deleteClaimCaseInsuredById(rptNo);
    }

    @Override
    public int insertClaimCaseInsuredAndPolicy(InsuredAndPolicy insuredAndPolicy){
        ClaimCaseInsured claimCaseInsured = insuredAndPolicy.getClaimCaseInsured();
        List<String> policyNos = insuredAndPolicy.getPolicyNos();

        claimCaseInsured.setStatus("Y");
        claimCaseInsured.setCreateBy(SecurityUtils.getUsername());
        claimCaseInsured.setCreateTime(DateUtils.getNowDate());
        claimCaseInsured.setUpdateBy(SecurityUtils.getUsername());
        claimCaseInsured.setUpdateTime(DateUtils.getNowDate());

        int i;
        Long relationId=null;
        List<ClaimCaseInsured> claimCaseInsureds = claimCaseInsuredMapper.selectClaimCaseInsuredList(claimCaseInsured);//案件被保人信息表
        if (null == claimCaseInsureds || claimCaseInsureds.size() ==0 ){//为空
             i= claimCaseInsuredMapper.insertClaimCaseInsured(claimCaseInsured);
        }else {
            i=claimCaseInsuredMapper.updateClaimCaseInsured(claimCaseInsured);
        }

        for (String policyNo:policyNos) {
            ClaimCasePolicy claimCasePolicy = new ClaimCasePolicy();
            claimCasePolicy.setPolicyNo(policyNo);//保单号
            claimCasePolicy.setRptNo(claimCaseInsured.getRptNo());//报案号
            claimCasePolicy.setStatus("Y");
            claimCasePolicy.setCreateBy(SecurityUtils.getUsername());
            claimCasePolicy.setCreateTime(DateUtils.getNowDate());
            claimCasePolicy.setUpdateBy(SecurityUtils.getUsername());
            claimCasePolicy.setUpdateTime(DateUtils.getNowDate());

            List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyList(claimCasePolicy);//案件保单关联表
            for (ClaimCasePolicy claimCasePoliciesTwo:claimCasePolicies){
                relationId = claimCasePoliciesTwo.getRelationId();
            }
            if (null == claimCasePolicies || claimCasePolicies.size() == 0) {//为空
                i=claimCasePolicyMapper.insertClaimCasePolicy(claimCasePolicy);
            }else {//非空
                claimCasePolicy.setRelationId(relationId);
                i=claimCasePolicyMapper.updateClaimCasePolicy(claimCasePolicy);
            }
        }

        return i;
    }
}
