package com.paic.ehis.claimmgt.service;



import com.paic.ehis.claimmgt.domain.ClaimCaseCheckRule;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseCheckDTO;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseCheckRuleDTO;

import java.util.List;

/**
 * 案件抽检规则Service接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface IClaimCaseCheckRuleService 
{
    /**
     * 查询案件抽检规则
     * 
     * @param checkRuleNo 案件抽检规则ID
     * @return 案件抽检规则
     */
    public ClaimCaseCheckRule selectClaimCaseCheckRuleById(String checkRuleNo);

    /**
     * 查询案件抽检规则列表
     * 
     * @param claimCaseCheckRule 案件抽检规则
     * @return 案件抽检规则集合
     */
    public List<ClaimCaseCheckRule> selectClaimCaseCheckRuleList(ClaimCaseCheckRule claimCaseCheckRule);

    /**
     * 新增案件抽检规则
     * 
     * @param claimCaseCheckRule 案件抽检规则
     * @return 结果
     */
    public int insertClaimCaseCheckRule(ClaimCaseCheckRule claimCaseCheckRule);

    /**
     * 修改案件抽检规则
     * 
     * @param claimCaseCheckRule 案件抽检规则
     * @return 结果
     */
    public int updateClaimCaseCheckRule(ClaimCaseCheckRule claimCaseCheckRule);

    /**
     * 批量删除案件抽检规则
     * 
     * @param checkRuleNos 需要删除的案件抽检规则ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleByIds(String[] checkRuleNos);

    /**
     * 删除案件抽检规则信息
     * 
     * @param checkRuleNo 案件抽检规则ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleById(String checkRuleNo);

    int insertClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO);

    int updateClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO);

    /** 判断案件是否符合流程抽检岗规则 */
    ClaimCaseCheckDTO judgeClaimCaseCheckRule(ClaimCaseCheckDTO claimCaseCheckDTO);

    /** 查询案件抽检规则 */
    List<ClaimCaseCheckRuleDTO> selectClaimCaseCheckRule(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO);
}
