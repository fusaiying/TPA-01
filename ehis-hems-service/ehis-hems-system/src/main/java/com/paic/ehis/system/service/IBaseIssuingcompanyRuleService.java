package com.paic.ehis.system.service;

import java.util.List;
import com.paic.ehis.system.domain.BaseIssuingcompanyRule;

/**
 * 出单公司规则 Service接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface IBaseIssuingcompanyRuleService 
{
    /**
     * 查询出单公司规则 
     * 
     * @param ruleNo 出单公司规则 ID
     * @return 出单公司规则 
     */
    public BaseIssuingcompanyRule selectBaseIssuingcompanyRuleById(String ruleNo);

    /**
     * 查询出单公司规则 列表
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 出单公司规则 集合
     */
    public List<BaseIssuingcompanyRule> selectBaseIssuingcompanyRuleList(BaseIssuingcompanyRule baseIssuingcompanyRule);

    /**
     * 新增出单公司规则 
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 结果
     */
    public int insertBaseIssuingcompanyRule(BaseIssuingcompanyRule baseIssuingcompanyRule);

    /**
     * 修改出单公司规则 
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 结果
     */
    public int updateBaseIssuingcompanyRule(BaseIssuingcompanyRule baseIssuingcompanyRule);

    /**
     * 批量删除出单公司规则 
     * 
     * @param ruleNos 需要删除的出单公司规则 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyRuleByIds(String[] ruleNos);

    /**
     * 删除出单公司规则 信息
     * 
     * @param ruleNo 出单公司规则 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyRuleById(String ruleNo);
}
