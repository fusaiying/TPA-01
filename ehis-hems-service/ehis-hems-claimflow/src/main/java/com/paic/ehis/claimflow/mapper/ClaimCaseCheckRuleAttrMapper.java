package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseCheckRuleAttr;

import java.util.List;

/**
 * 案件抽检规则属性Mapper接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface ClaimCaseCheckRuleAttrMapper 
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
     * 删除案件抽检规则属性
     * 
     * @param attrId 案件抽检规则属性ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleAttrById(Long attrId);

    /**
     * 批量删除案件抽检规则属性
     * 
     * @param attrIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseCheckRuleAttrByIds(Long[] attrIds);

    List<String> selectClaimCaseCheckRuleAttrByCheckRuleNo(String checkRuleNo);

    int updateClaimCaseCheckRuleAttrNew(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr);

    List<Long> selectClaimCaseCheckRuleAttrByCheckRuleNoNew(String checkRuleNo);
}
