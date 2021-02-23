package com.paic.ehis.claimflow.service;



import com.paic.ehis.claimflow.domain.ClaimCaseCalRuleExcept;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryTwoVO;

import java.util.List;

/**
 * 案件赔付规则例外Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IClaimCaseCalRuleExceptService 
{
    /**
     * 查询案件赔付规则例外
     * 
     * @param ruleExcId 案件赔付规则例外ID
     * @return 案件赔付规则例外
     */
    public ClaimCaseCalRuleExcept selectClaimCaseCalRuleExceptById(Long ruleExcId);

    /**
     * 查询案件赔付规则例外列表
     * 
     * @param claimCaseCalRuleExcept 案件赔付规则例外
     * @return 案件赔付规则例外集合
     */
    public List<ClaimCaseCalRuleExcept> selectClaimCaseCalRuleExceptList(ClaimCaseCalRuleExcept claimCaseCalRuleExcept);

    /**
     * 新增案件赔付规则例外
     * 
     * @param claimCaseCalRuleExcept 案件赔付规则例外
     * @return 结果
     */
    public int insertClaimCaseCalRuleExcept(ClaimCaseCalRuleExcept claimCaseCalRuleExcept);

    /**
     * 修改案件赔付规则例外
     * 
     * @param claimCaseCalRuleExcept 案件赔付规则例外
     * @return 结果
     */
    public int updateClaimCaseCalRuleExcept(ClaimCaseCalRuleExcept claimCaseCalRuleExcept);

    /**
     * 批量删除案件赔付规则例外
     * 
     * @param ruleExcIds 需要删除的案件赔付规则例外ID
     * @return 结果
     */
    public int deleteClaimCaseCalRuleExceptByIds(Long[] ruleExcIds);

    /**
     * 删除案件赔付规则例外信息
     * 
     * @param ruleExcId 案件赔付规则例外ID
     * @return 结果
     */
    public int deleteClaimCaseCalRuleExceptById(Long ruleExcId);

    public List<TheAdjustmentSubsidiaryTwoVO> selectClaimCaseCalRuleListByRptNoTwo(String rptNo);
}
