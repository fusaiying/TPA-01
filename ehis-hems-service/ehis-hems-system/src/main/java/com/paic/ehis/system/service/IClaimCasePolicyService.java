package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCasePolicy;

/**
 * 案件保单关联Service接口
 * 
 * @author sino
 * @date 2021-01-14
 */
public interface IClaimCasePolicyService 
{
    /**
     * 查询案件保单关联
     * 
     * @param relationId 案件保单关联ID
     * @return 案件保单关联
     */
    public ClaimCasePolicy selectClaimCasePolicyById(Long relationId);

    /**
     * 查询案件保单关联列表
     * 
     * @param claimCasePolicy 案件保单关联
     * @return 案件保单关联集合
     */
    public List<ClaimCasePolicy> selectClaimCasePolicyList(ClaimCasePolicy claimCasePolicy);

    /**
     * 新增案件保单关联
     * 
     * @param claimCasePolicy 案件保单关联
     * @return 结果
     */
    public int insertClaimCasePolicy(ClaimCasePolicy claimCasePolicy);

    /**
     * 修改案件保单关联
     * 
     * @param claimCasePolicy 案件保单关联
     * @return 结果
     */
    public int updateClaimCasePolicy(ClaimCasePolicy claimCasePolicy);

    /**
     * 批量删除案件保单关联
     * 
     * @param relationIds 需要删除的案件保单关联ID
     * @return 结果
     */
    public int deleteClaimCasePolicyByIds(Long[] relationIds);

    /**
     * 删除案件保单关联信息
     * 
     * @param relationId 案件保单关联ID
     * @return 结果
     */
    public int deleteClaimCasePolicyById(Long relationId);
}
