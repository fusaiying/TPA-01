package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseInvestigation;
import com.paic.ehis.system.mapper.ClaimCaseInvestigationMapper;
import com.paic.ehis.system.service.IClaimCaseInvestigationService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件调查任务Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseInvestigationServiceImpl implements IClaimCaseInvestigationService
{
    @Autowired
    private ClaimCaseInvestigationMapper claimCaseInvestigationMapper;

    /**
     * 查询案件调查任务
     * 
     * @param invNo 案件调查任务ID
     * @return 案件调查任务
     */
    @Override
    public ClaimCaseInvestigation selectClaimCaseInvestigationById(Long invNo)
    {
        return claimCaseInvestigationMapper.selectClaimCaseInvestigationById(invNo);
    }

    /**
     * 查询案件调查任务列表
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 案件调查任务
     */
    @Override
    public List<ClaimCaseInvestigation> selectClaimCaseInvestigationList(ClaimCaseInvestigation claimCaseInvestigation)
    {
        return claimCaseInvestigationMapper.selectClaimCaseInvestigationList(claimCaseInvestigation);
    }

    /**
     * 新增案件调查任务
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 结果
     */
    @Override
    public int insertClaimCaseInvestigation(ClaimCaseInvestigation claimCaseInvestigation)
    {
        claimCaseInvestigation.setCreateTime(DateUtils.getNowDate());
        return claimCaseInvestigationMapper.insertClaimCaseInvestigation(claimCaseInvestigation);
    }

    /**
     * 修改案件调查任务
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 结果
     */
    @Override
    public int updateClaimCaseInvestigation(ClaimCaseInvestigation claimCaseInvestigation)
    {
        claimCaseInvestigation.setUpdateTime(DateUtils.getNowDate());
        return claimCaseInvestigationMapper.updateClaimCaseInvestigation(claimCaseInvestigation);
    }

    /**
     * 批量删除案件调查任务
     * 
     * @param invNos 需要删除的案件调查任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInvestigationByIds(Long[] invNos)
    {
        return claimCaseInvestigationMapper.deleteClaimCaseInvestigationByIds(invNos);
    }

    /**
     * 删除案件调查任务信息
     * 
     * @param invNo 案件调查任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInvestigationById(Long invNo)
    {
        return claimCaseInvestigationMapper.deleteClaimCaseInvestigationById(invNo);
    }
}
