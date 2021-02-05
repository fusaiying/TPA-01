package com.paic.ehis.claimcal.service;



import com.paic.ehis.claimcal.domain.ClaimRule;
import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import com.paic.ehis.common.core.web.page.TableDataInfo;

import java.util.List;

/**
 * 福利规则 Service接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface IClaimRuleService 
{
    /**
     * 查询福利规则 
     * 
     * @param ruleNo 福利规则 ID
     * @return 福利规则 
     */
    public ClaimRule selectClaimRuleById(String ruleNo);

    /**
     * 查询福利规则信息
     *
     * @param ruleNo 福利规则 ID
     * @return 福利规则信息
     */
    public ClaimRuleInfo selectClaimRuleInfoById(String ruleNo);

    /**
     * 查询福利规则 列表
     * 
     * @param claimRule 福利规则 
     * @return 福利规则 集合
     */
    public List<ClaimRule> selectClaimRuleList(ClaimRule claimRule);

    /**
     * 查询福利规则信息 列表
     *
     * @param claimRule 福利规则
     * @return 福利规则信息 集合
     */
    public TableDataInfo selectClaimRuleInfoList(ClaimRule claimRule);
    /**
     * 新增福利规则 
     * 
     * @param claimRule 福利规则 
     * @return 结果
     */
    public int insertClaimRule(ClaimRule claimRule);

    /**
     * 新增福利规则
     *
     * @param claimRuleInfo 福利规则
     * @return 结果
     */
    public int insertClaimRuleInfo(ClaimRuleInfo claimRuleInfo);

    /**
     * 修改福利规则 
     * 
     * @param claimRule 福利规则 
     * @return 结果
     */
    public int updateClaimRule(ClaimRule claimRule);

    /**
     * 修改福利规则
     *
     * @param claimRuleInfo 福利规则
     * @return 结果
     */
    public int updateClaimRuleInfo(ClaimRuleInfo claimRuleInfo);

    /**
     * 批量删除福利规则 
     * 
     * @param ruleNos 需要删除的福利规则 ID
     * @return 结果
     */
    public int deleteClaimRuleByIds(String[] ruleNos);

    /**
     * 批量删除福利规则
     *
     * @param ruleNos 需要删除的福利规则 ID
     * @return 结果
     */
    public int deleteClaimRuleInfoByIds(String[] ruleNos);

    /**
     * 删除福利规则 信息
     * 
     * @param ruleNo 福利规则 ID
     * @return 结果
     */
    public int deleteClaimRuleById(String ruleNo);
}
