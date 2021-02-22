package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseCalRuleExcept;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryTwoVO;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalRuleExceptMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalRuleExceptService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件赔付规则例外Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalRuleExceptServiceImpl implements IClaimCaseCalRuleExceptService
{
    @Autowired
    private ClaimCaseCalRuleExceptMapper claimCaseCalRuleExceptMapper;

    /**
     * 查询案件赔付规则例外
     * 
     * @param ruleExcId 案件赔付规则例外ID
     * @return 案件赔付规则例外
     */
    @Override
    public ClaimCaseCalRuleExcept selectClaimCaseCalRuleExceptById(Long ruleExcId)
    {
        return claimCaseCalRuleExceptMapper.selectClaimCaseCalRuleExceptById(ruleExcId);
    }

    /**
     * 查询案件赔付规则例外列表
     * 
     * @param claimCaseCalRuleExcept 案件赔付规则例外
     * @return 案件赔付规则例外
     */
    @Override
    public List<ClaimCaseCalRuleExcept> selectClaimCaseCalRuleExceptList(ClaimCaseCalRuleExcept claimCaseCalRuleExcept)
    {
        return claimCaseCalRuleExceptMapper.selectClaimCaseCalRuleExceptList(claimCaseCalRuleExcept);
    }

    /**
     * 新增案件赔付规则例外
     * 
     * @param claimCaseCalRuleExcept 案件赔付规则例外
     * @return 结果
     */
    @Override
    public int insertClaimCaseCalRuleExcept(ClaimCaseCalRuleExcept claimCaseCalRuleExcept)
    {
        claimCaseCalRuleExcept.setCreateTime(DateUtils.getNowDate());
        return claimCaseCalRuleExceptMapper.insertClaimCaseCalRuleExcept(claimCaseCalRuleExcept);
    }

    /**
     * 修改案件赔付规则例外
     * 
     * @param claimCaseCalRuleExcept 案件赔付规则例外
     * @return 结果
     */
    @Override
    public int updateClaimCaseCalRuleExcept(ClaimCaseCalRuleExcept claimCaseCalRuleExcept)
    {
        claimCaseCalRuleExcept.setStatus("N");
        claimCaseCalRuleExcept.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCalRuleExceptMapper.updateClaimCaseCalRuleExcept(claimCaseCalRuleExcept);
    }

    /**
     * 批量删除案件赔付规则例外
     * 
     * @param ruleExcIds 需要删除的案件赔付规则例外ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalRuleExceptByIds(Long[] ruleExcIds)
    {
        return claimCaseCalRuleExceptMapper.deleteClaimCaseCalRuleExceptByIds(ruleExcIds);
    }

    /**
     * 删除案件赔付规则例外信息
     * 
     * @param ruleExcId 案件赔付规则例外ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalRuleExceptById(Long ruleExcId)
    {
        return claimCaseCalRuleExceptMapper.deleteClaimCaseCalRuleExceptById(ruleExcId);
    }

    @Override
    public List<TheAdjustmentSubsidiaryTwoVO> selectClaimCaseCalRuleListByRptNoTwo(String rptNo){

        return claimCaseCalRuleExceptMapper.selectClaimCaseCalRuleListByRptNoTwo(rptNo);
    }
}
