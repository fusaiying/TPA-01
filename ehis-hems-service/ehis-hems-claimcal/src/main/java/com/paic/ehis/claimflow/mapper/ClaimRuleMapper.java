package com.paic.ehis.claimflow.mapper;

import com.sino.system.domain.ClaimRule;

import java.util.List;

/**
 * 福利规则 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface ClaimRuleMapper 
{
    /**
     * 查询福利规则 
     * 
     * @param ruleNo 福利规则 ID
     * @return 福利规则 
     */
    public ClaimRule selectClaimRuleById(String ruleNo);

    /**
     * 查询福利规则 列表
     * 
     * @param claimRule 福利规则 
     * @return 福利规则 集合
     */
    public List<ClaimRule> selectClaimRuleList(ClaimRule claimRule);

    /**
     * 新增福利规则 
     * 
     * @param claimRule 福利规则 
     * @return 结果
     */
    public int insertClaimRule(ClaimRule claimRule);

    /**
     * 修改福利规则 
     * 
     * @param claimRule 福利规则 
     * @return 结果
     */
    public int updateClaimRule(ClaimRule claimRule);

    /**
     * 删除福利规则 
     * 
     * @param ruleNo 福利规则 ID
     * @return 结果
     */
    public int deleteClaimRuleById(String ruleNo);

    /**
     * 批量删除福利规则 
     * 
     * @param ruleNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimRuleByIds(String[] ruleNos);

    /**
     * 批量将Status置为N
     *
     * @param ruleNos 福利规则 ID
     * @return 结果
     */
    public int deleteByUpdateN(String[] ruleNos);
}
