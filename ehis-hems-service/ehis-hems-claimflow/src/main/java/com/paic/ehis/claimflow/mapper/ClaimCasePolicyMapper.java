package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.dto.PolicyDTO;
import com.paic.ehis.claimflow.domain.vo.PolicyVo;
import com.paic.ehis.system.api.domain.ClaimCasePolicy;

import java.util.List;

/**
 * 案件保单关联Mapper接口
 * 
 * @author sino
 * @date 2021-01-14
 */
public interface ClaimCasePolicyMapper 
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
     * 删除案件保单关联
     * 
     * @param relationId 案件保单关联ID
     * @return 结果
     */
    public int deleteClaimCasePolicyById(Long relationId);

    /**
     * 批量删除案件保单关联
     * 
     * @param relationIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCasePolicyByIds(Long[] relationIds);

    /**
     *
     * @param rptNo
     * @return
     */
    public List<ClaimCasePolicy> selectClaimCasePolicyByRptNo(String rptNo);

    List<ClaimCasePolicy> selectClaimCasePolicyByRptNoOne(String rptNo);

    List<PolicyVo> selectInsuredListByNumber(PolicyDTO policyDTO);

}
