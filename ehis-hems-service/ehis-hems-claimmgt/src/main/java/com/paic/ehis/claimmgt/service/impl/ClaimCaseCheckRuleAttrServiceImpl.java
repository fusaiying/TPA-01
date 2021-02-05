package com.paic.ehis.claimmgt.service.impl;


import com.paic.ehis.claimmgt.domain.ClaimCaseCheckRuleAttr;
import com.paic.ehis.claimmgt.mapper.ClaimCaseCheckRuleAttrMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseCheckRuleAttrService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件抽检规则属性Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class ClaimCaseCheckRuleAttrServiceImpl implements IClaimCaseCheckRuleAttrService
{
    @Autowired
    private ClaimCaseCheckRuleAttrMapper claimCaseCheckRuleAttrMapper;

    /**
     * 查询案件抽检规则属性
     * 
     * @param attrId 案件抽检规则属性ID
     * @return 案件抽检规则属性
     */
    @Override
    public ClaimCaseCheckRuleAttr selectClaimCaseCheckRuleAttrById(Long attrId)
    {
        return claimCaseCheckRuleAttrMapper.selectClaimCaseCheckRuleAttrById(attrId);
    }

    /**
     * 查询案件抽检规则属性列表
     * 
     * @param claimCaseCheckRuleAttr 案件抽检规则属性
     * @return 案件抽检规则属性
     */
    @Override
    public List<ClaimCaseCheckRuleAttr> selectClaimCaseCheckRuleAttrList(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr)
    {
        return claimCaseCheckRuleAttrMapper.selectClaimCaseCheckRuleAttrList(claimCaseCheckRuleAttr);
    }

    /**
     * 新增案件抽检规则属性
     * 
     * @param claimCaseCheckRuleAttr 案件抽检规则属性
     * @return 结果
     */
    @Override
    public int insertClaimCaseCheckRuleAttr(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr)
    {
        claimCaseCheckRuleAttr.setCreateTime(DateUtils.getNowDate());
        return claimCaseCheckRuleAttrMapper.insertClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr);
    }

    /**
     * 修改案件抽检规则属性
     * 
     * @param claimCaseCheckRuleAttr 案件抽检规则属性
     * @return 结果
     */
    @Override
    public int updateClaimCaseCheckRuleAttr(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr)
    {
        claimCaseCheckRuleAttr.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCheckRuleAttrMapper.updateClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr);
    }

    /**
     * 批量删除案件抽检规则属性
     * 
     * @param attrIds 需要删除的案件抽检规则属性ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCheckRuleAttrByIds(Long[] attrIds)
    {
        return claimCaseCheckRuleAttrMapper.deleteClaimCaseCheckRuleAttrByIds(attrIds);
    }

    /**
     * 删除案件抽检规则属性信息
     * 
     * @param attrId 案件抽检规则属性ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCheckRuleAttrById(Long attrId)
    {
        return claimCaseCheckRuleAttrMapper.deleteClaimCaseCheckRuleAttrById(attrId);
    }
}
