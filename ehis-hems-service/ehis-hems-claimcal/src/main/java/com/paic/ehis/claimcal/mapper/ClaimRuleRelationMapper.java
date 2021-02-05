package com.paic.ehis.claimcal.mapper;



import com.paic.ehis.claimcal.domain.ClaimRuleRelation;
import com.paic.ehis.claimcal.domain.ClaimRuleRelationInfo;

import java.util.List;

/**
 * 规则对象关联 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface ClaimRuleRelationMapper 
{
    /**
     * 查询规则对象关联 
     * 
     * @param relationNo 规则对象关联 ID
     * @return 规则对象关联 
     */
    public ClaimRuleRelation selectClaimRuleRelationById(String relationNo);

    /**
     * 查询规则对象关联信息 列表
     *
     * @param claimRuleRelation 规则对象关联信息
     * @return 规则对象关联信息 集合
     */
    public List<ClaimRuleRelationInfo> selectClaimRuleRelationInfoList(ClaimRuleRelation claimRuleRelation);


    /**
     * 查询规则对象关联 列表
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 规则对象关联 集合
     */
    public List<ClaimRuleRelation> selectClaimRuleRelationList(ClaimRuleRelation claimRuleRelation);

    /**
     * 新增规则对象关联 
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 结果
     */
    public int insertClaimRuleRelation(ClaimRuleRelation claimRuleRelation);

    /**
     * 修改规则对象关联 
     * 
     * @param claimRuleRelation 规则对象关联 
     * @return 结果
     */
    public int updateClaimRuleRelation(ClaimRuleRelation claimRuleRelation);

    /**
     * 删除规则对象关联 
     * 
     * @param relationNo 规则对象关联 ID
     * @return 结果
     */
    public int deleteClaimRuleRelationById(String relationNo);

    /**
     * 批量删除规则对象关联 
     * 
     * @param relationNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimRuleRelationByIds(String[] relationNos);

    /**
     * 批量插入规则对象关联
     *
     * @param claimRuleRelationList 规则对象关联List
     * @return 结果
     */
    public int insertList(List<ClaimRuleRelation> claimRuleRelationList);

    /**
     * 批量将Status置为N
     *
     * @param relationNos 规则对象关联 ID
     * @return 结果
     */
    public int deleteByUpdateN(String[] relationNos);
}
