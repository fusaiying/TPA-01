package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseInvestigation;

/**
 * 案件调查任务Service接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface IClaimCaseInvestigationService 
{
    /**
     * 查询案件调查任务
     * 
     * @param invNo 案件调查任务ID
     * @return 案件调查任务
     */
    public ClaimCaseInvestigation selectClaimCaseInvestigationById(Long invNo);

    /**
     * 查询案件调查任务列表
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 案件调查任务集合
     */
    public List<ClaimCaseInvestigation> selectClaimCaseInvestigationList(ClaimCaseInvestigation claimCaseInvestigation);

    /**
     * 新增案件调查任务
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 结果
     */
    public int insertClaimCaseInvestigation(ClaimCaseInvestigation claimCaseInvestigation);

    /**
     * 修改案件调查任务
     * 
     * @param claimCaseInvestigation 案件调查任务
     * @return 结果
     */
    public int updateClaimCaseInvestigation(ClaimCaseInvestigation claimCaseInvestigation);

    /**
     * 批量删除案件调查任务
     * 
     * @param invNos 需要删除的案件调查任务ID
     * @return 结果
     */
    public int deleteClaimCaseInvestigationByIds(Long[] invNos);

    /**
     * 删除案件调查任务信息
     * 
     * @param invNo 案件调查任务ID
     * @return 结果
     */
    public int deleteClaimCaseInvestigationById(Long invNo);
}
