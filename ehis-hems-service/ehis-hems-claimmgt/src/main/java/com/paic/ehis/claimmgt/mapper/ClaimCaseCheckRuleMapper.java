package com.paic.ehis.claimmgt.mapper;



import com.paic.ehis.claimmgt.domain.ClaimCaseCheckRule;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseCheckDTO;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseCheckRuleDTO;

import java.util.List;

/**
 * 案件抽检规则Mapper接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface ClaimCaseCheckRuleMapper 
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
     * 删除案件抽检规则
     * 
     * @param checkRuleNo 案件抽检规则ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleById(String checkRuleNo);

    /**
     * 批量删除案件抽检规则
     * 
     * @param checkRuleNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleByIds(String[] checkRuleNos);

    /** 判断案件是否符合流程抽检岗规则 */
    List<ClaimCaseCheckRule> selectClaimCaseCheckRule(ClaimCaseCheckDTO claimCaseCheckDTO);




    /**修改*/
    public int updateClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO);
    /**新增*/
    public int insertClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO);
    /**查询*/
    List<ClaimCaseCheckRuleDTO> selectClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO);

    List<ClaimCaseCheckDTO> selectClaimCaseCheckRuleMatch(ClaimCaseCheckDTO claimCaseCheckDTO);
}
