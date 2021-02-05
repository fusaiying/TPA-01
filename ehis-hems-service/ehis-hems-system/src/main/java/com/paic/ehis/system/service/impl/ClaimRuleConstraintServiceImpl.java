package com.paic.ehis.system.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.system.domain.ClaimRuleConstraint;
import com.paic.ehis.system.mapper.ClaimRuleConstraintMapper;
import com.paic.ehis.system.service.IClaimRuleConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规则条件 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimRuleConstraintServiceImpl implements IClaimRuleConstraintService 
{
    @Autowired
    private ClaimRuleConstraintMapper claimRuleConstraintMapper;

    /**
     * 查询规则条件 
     * 
     * @param constraintNo 规则条件 ID
     * @return 规则条件 
     */
    @Override
    public ClaimRuleConstraint selectClaimRuleConstraintById(String constraintNo)
    {
        return claimRuleConstraintMapper.selectClaimRuleConstraintById(constraintNo);
    }

    /**
     * 查询规则条件 列表
     * 
     * @param claimRuleConstraint 规则条件 
     * @return 规则条件 
     */
    @Override
    public List<ClaimRuleConstraint> selectClaimRuleConstraintList(ClaimRuleConstraint claimRuleConstraint)
    {
        return claimRuleConstraintMapper.selectClaimRuleConstraintList(claimRuleConstraint);
    }

    /**
     * 新增规则条件 
     * 
     * @param claimRuleConstraint 规则条件 
     * @return 结果
     */
    @Override
    public int insertClaimRuleConstraint(ClaimRuleConstraint claimRuleConstraint)
    {
        claimRuleConstraint.setCreateTime(DateUtils.getNowDate());
        return claimRuleConstraintMapper.insertClaimRuleConstraint(claimRuleConstraint);
    }

    /**
     * 修改规则条件 
     * 
     * @param claimRuleConstraint 规则条件 
     * @return 结果
     */
    @Override
    public int updateClaimRuleConstraint(ClaimRuleConstraint claimRuleConstraint)
    {
        claimRuleConstraint.setUpdateTime(DateUtils.getNowDate());
        return claimRuleConstraintMapper.updateClaimRuleConstraint(claimRuleConstraint);
    }

    /**
     * 批量删除规则条件 
     * 
     * @param constraintNos 需要删除的规则条件 ID
     * @return 结果
     */
    @Override
    public int deleteClaimRuleConstraintByIds(String[] constraintNos)
    {
        return claimRuleConstraintMapper.deleteClaimRuleConstraintByIds(constraintNos);
    }

    /**
     * 删除规则条件 信息
     * 
     * @param constraintNo 规则条件 ID
     * @return 结果
     */
    @Override
    public int deleteClaimRuleConstraintById(String constraintNo)
    {
        return claimRuleConstraintMapper.deleteClaimRuleConstraintById(constraintNo);
    }
}
