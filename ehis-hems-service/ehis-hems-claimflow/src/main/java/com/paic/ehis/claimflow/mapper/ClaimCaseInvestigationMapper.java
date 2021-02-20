package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.BaseCodeMappingNew;
import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseInvestigation;

import java.util.List;

/**
 * 案件调查任务Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ClaimCaseInvestigationMapper 
{
    /**
     * 查询案件调查任务
     * 
     * @param invNo 案件调查任务ID
     * @return 案件调查任务
     */
    public ClaimCaseInvestigation selectClaimCaseInvestigationById(Long invNo);
    public ClaimCaseInvestigation selectClaimCaseInvestigationByNew(String rptNo);
    public ClaimCaseInvestigation selectClaimCaseInvestigationByIdOne(String rptNo);


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
     * 删除案件调查任务
     * 
     * @param invNo 案件调查任务ID
     * @return 结果
     */
    public int deleteClaimCaseInvestigationById(Long invNo);

    /**
     * 批量删除案件调查任务
     * 
     * @param invNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseInvestigationByIds(Long[] invNos);


    public BaseCodeMappingNew selectBaseCodeMappingNew(BaseCodeMappingNew baseCodeMappingNew);


}
