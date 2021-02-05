package com.paic.ehis.claimmgt.service;



import com.paic.ehis.claimmgt.domain.ClaimCaseCheckRuleAttr;

import java.util.List;

/**
 * 案件抽检规则属性Service接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface IClaimCaseCheckRuleAttrService 
{
    /**
     * 查询案件抽检规则属性
     * 
     * @param attrId 案件抽检规则属性ID
     * @return 案件抽检规则属性
     */
    public ClaimCaseCheckRuleAttr selectClaimCaseCheckRuleAttrById(Long attrId);

    /**
     * 查询案件抽检规则属性列表
     * 
     * @param claimCaseCheckRuleAttr 案件抽检规则属性
     * @return 案件抽检规则属性集合
     */
    public List<ClaimCaseCheckRuleAttr> selectClaimCaseCheckRuleAttrList(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr);

    /**
     * 新增案件抽检规则属性
     * 
     * @param claimCaseCheckRuleAttr 案件抽检规则属性
     * @return 结果
     */
    public int insertClaimCaseCheckRuleAttr(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr);

    /**
     * 修改案件抽检规则属性
     * 
     * @param claimCaseCheckRuleAttr 案件抽检规则属性
     * @return 结果
     */
    public int updateClaimCaseCheckRuleAttr(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr);

    /**
     * 批量删除案件抽检规则属性
     * 
     * @param attrIds 需要删除的案件抽检规则属性ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleAttrByIds(Long[] attrIds);

    /**
     * 删除案件抽检规则属性信息
     * 
     * @param attrId 案件抽检规则属性ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleAttrById(Long attrId);
}
