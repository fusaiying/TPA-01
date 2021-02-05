package com.paic.ehis.claimcal.mapper;



import com.paic.ehis.claimcal.domain.ClaimProductPlan;

import java.util.List;

/**
 * 计划信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface ClaimProductPlanMapper 
{
    /**
     * 查询计划信息 
     * 
     * @param planCode 计划信息 ID
     * @return 计划信息 
     */
    public ClaimProductPlan selectClaimProductPlanById(String planCode);

    /**
     * 查询计划信息 列表
     * 
     * @param claimProductPlan 计划信息 
     * @return 计划信息 集合
     */
    public List<ClaimProductPlan> selectClaimProductPlanList(ClaimProductPlan claimProductPlan);

    /**
     * 新增计划信息 
     * 
     * @param claimProductPlan 计划信息 
     * @return 结果
     */
    public int insertClaimProductPlan(ClaimProductPlan claimProductPlan);

    /**
     * 修改计划信息 
     * 
     * @param claimProductPlan 计划信息 
     * @return 结果
     */
    public int updateClaimProductPlan(ClaimProductPlan claimProductPlan);

    /**
     * 删除计划信息 
     * 
     * @param planCode 计划信息 ID
     * @return 结果
     */
    public int deleteClaimProductPlanById(String planCode);

    /**
     * 批量删除计划信息 
     * 
     * @param planCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimProductPlanByIds(String[] planCodes);
}
