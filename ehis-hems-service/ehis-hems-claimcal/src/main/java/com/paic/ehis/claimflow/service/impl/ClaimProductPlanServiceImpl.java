package com.paic.ehis.claimflow.service.impl;

import com.sino.common.core.utils.DateUtils;
import com.sino.system.domain.ClaimProductPlan;
import com.sino.system.mapper.ClaimProductPlanMapper;
import com.sino.system.service.IClaimProductPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 计划信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimProductPlanServiceImpl implements IClaimProductPlanService 
{
    @Autowired
    private ClaimProductPlanMapper claimProductPlanMapper;

    /**
     * 查询计划信息 
     * 
     * @param planCode 计划信息 ID
     * @return 计划信息 
     */
    @Override
    public ClaimProductPlan selectClaimProductPlanById(String planCode)
    {
        return claimProductPlanMapper.selectClaimProductPlanById(planCode);
    }

    /**
     * 查询计划信息 列表
     * 
     * @param claimProductPlan 计划信息 
     * @return 计划信息 
     */
    @Override
    public List<ClaimProductPlan> selectClaimProductPlanList(ClaimProductPlan claimProductPlan)
    {
        return claimProductPlanMapper.selectClaimProductPlanList(claimProductPlan);
    }

    /**
     * 新增计划信息 
     * 
     * @param claimProductPlan 计划信息 
     * @return 结果
     */
    @Override
    public int insertClaimProductPlan(ClaimProductPlan claimProductPlan)
    {
        claimProductPlan.setCreateTime(DateUtils.getNowDate());
        return claimProductPlanMapper.insertClaimProductPlan(claimProductPlan);
    }

    /**
     * 修改计划信息 
     * 
     * @param claimProductPlan 计划信息 
     * @return 结果
     */
    @Override
    public int updateClaimProductPlan(ClaimProductPlan claimProductPlan)
    {
        claimProductPlan.setUpdateTime(DateUtils.getNowDate());
        return claimProductPlanMapper.updateClaimProductPlan(claimProductPlan);
    }

    /**
     * 批量删除计划信息 
     * 
     * @param planCodes 需要删除的计划信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductPlanByIds(String[] planCodes)
    {
        return claimProductPlanMapper.deleteClaimProductPlanByIds(planCodes);
    }

    /**
     * 删除计划信息 信息
     * 
     * @param planCode 计划信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductPlanById(String planCode)
    {
        return claimProductPlanMapper.deleteClaimProductPlanById(planCode);
    }
}
