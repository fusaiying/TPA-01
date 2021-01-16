package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCasePolicy;
import com.paic.ehis.system.mapper.ClaimCasePolicyMapper;
import com.paic.ehis.system.service.IClaimCasePolicyService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件保单关联Service业务层处理
 * 
 * @author sino
 * @date 2021-01-14
 */
@Service
public class ClaimCasePolicyServiceImpl implements IClaimCasePolicyService
{
    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    /**
     * 查询案件保单关联
     * 
     * @param relationId 案件保单关联ID
     * @return 案件保单关联
     */
    @Override
    public ClaimCasePolicy selectClaimCasePolicyById(Long relationId)
    {
        return claimCasePolicyMapper.selectClaimCasePolicyById(relationId);
    }

    /**
     * 查询案件保单关联列表
     * 
     * @param claimCasePolicy 案件保单关联
     * @return 案件保单关联
     */
    @Override
    public List<ClaimCasePolicy> selectClaimCasePolicyList(ClaimCasePolicy claimCasePolicy)
    {
        return claimCasePolicyMapper.selectClaimCasePolicyList(claimCasePolicy);
    }

    /**
     * 新增案件保单关联
     * 
     * @param claimCasePolicy 案件保单关联
     * @return 结果
     */
    @Override
    public int insertClaimCasePolicy(ClaimCasePolicy claimCasePolicy)
    {
        claimCasePolicy.setCreateTime(DateUtils.getNowDate());
        return claimCasePolicyMapper.insertClaimCasePolicy(claimCasePolicy);
    }

    /**
     * 修改案件保单关联
     * 
     * @param claimCasePolicy 案件保单关联
     * @return 结果
     */
    @Override
    public int updateClaimCasePolicy(ClaimCasePolicy claimCasePolicy)
    {
        claimCasePolicy.setUpdateTime(DateUtils.getNowDate());
        return claimCasePolicyMapper.updateClaimCasePolicy(claimCasePolicy);
    }

    /**
     * 批量删除案件保单关联
     * 
     * @param relationIds 需要删除的案件保单关联ID
     * @return 结果
     */
    @Override
    public int deleteClaimCasePolicyByIds(Long[] relationIds)
    {
        return claimCasePolicyMapper.deleteClaimCasePolicyByIds(relationIds);
    }

    /**
     * 删除案件保单关联信息
     * 
     * @param relationId 案件保单关联ID
     * @return 结果
     */
    @Override
    public int deleteClaimCasePolicyById(Long relationId)
    {
        return claimCasePolicyMapper.deleteClaimCasePolicyById(relationId);
    }
}
