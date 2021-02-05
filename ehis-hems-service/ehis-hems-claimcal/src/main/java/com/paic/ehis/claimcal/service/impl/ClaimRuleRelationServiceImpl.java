package com.paic.ehis.claimcal.service.impl;


import com.paic.ehis.claimcal.domain.ClaimRuleRelation;
import com.paic.ehis.claimcal.mapper.ClaimRuleRelationMapper;
import com.paic.ehis.claimcal.service.IClaimRuleRelationService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规则对象关联 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimRuleRelationServiceImpl implements IClaimRuleRelationService
{
    @Autowired
    private ClaimRuleRelationMapper claimRuleRelationMapper;

    /**
     * 查询规则对象关联 
     * 
     * @param relationNo 规则对象关联 ID
     * @return 规则对象关联 
     */
    @Override
    public ClaimRuleRelation selectClaimRuleRelationById(String relationNo)
    {
        return claimRuleRelationMapper.selectClaimRuleRelationById(relationNo);
    }

    /**
     * 查询规则对象关联 列表
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 规则对象关联 
     */
    @Override
    public List<ClaimRuleRelation> selectClaimRuleRelationList(ClaimRuleRelation claimRuleRelation)
    {
        return claimRuleRelationMapper.selectClaimRuleRelationList(claimRuleRelation);
    }

    /**
     * 新增规则对象关联 
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 结果
     */
    @Override
    public int insertClaimRuleRelation(ClaimRuleRelation claimRuleRelation)
    {
        claimRuleRelation.setCreateTime(DateUtils.getNowDate());
        return claimRuleRelationMapper.insertClaimRuleRelation(claimRuleRelation);
    }

    /**
     * 修改规则对象关联 
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 结果
     */
    @Override
    public int updateClaimRuleRelation(ClaimRuleRelation claimRuleRelation)
    {
        claimRuleRelation.setUpdateTime(DateUtils.getNowDate());
        return claimRuleRelationMapper.updateClaimRuleRelation(claimRuleRelation);
    }

    /**
     * 批量删除规则对象关联 
     * 
     * @param relationNos 需要删除的规则对象关联 ID
     * @return 结果
     */
    @Override
    public int deleteClaimRuleRelationByIds(String[] relationNos)
    {
        return claimRuleRelationMapper.deleteClaimRuleRelationByIds(relationNos);
    }

    /**
     * 删除规则对象关联 信息
     * 
     * @param relationNo 规则对象关联 ID
     * @return 结果
     */
    @Override
    public int deleteClaimRuleRelationById(String relationNo)
    {
        return claimRuleRelationMapper.deleteClaimRuleRelationById(relationNo);
    }
}
