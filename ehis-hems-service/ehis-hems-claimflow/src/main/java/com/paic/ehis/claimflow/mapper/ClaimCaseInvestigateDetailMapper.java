package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.ClaimCaseInvestigateDetail;

import java.util.List;

/**
 * 案件调查明细Mapper接口
 * 
 * @author sino
 * @date 2021-03-04
 */
public interface ClaimCaseInvestigateDetailMapper 
{
    /**
     * 查询案件调查明细
     * 
     * @param investigateDetailNo 案件调查明细ID
     * @return 案件调查明细
     */
    public ClaimCaseInvestigateDetail selectClaimCaseInvestigateDetailById(Long investigateDetailNo);

    /**
     * 查询案件调查明细列表
     * 
     * @param claimCaseInvestigateDetail 案件调查明细
     * @return 案件调查明细集合
     */
    public List<ClaimCaseInvestigateDetail> selectClaimCaseInvestigateDetailList(ClaimCaseInvestigateDetail claimCaseInvestigateDetail);

    /**
     * 新增案件调查明细
     * 
     * @param claimCaseInvestigateDetail 案件调查明细
     * @return 结果
     */
    public int insertClaimCaseInvestigateDetail(ClaimCaseInvestigateDetail claimCaseInvestigateDetail);

    /**
     * 修改案件调查明细
     * 
     * @param claimCaseInvestigateDetail 案件调查明细
     * @return 结果
     */
    public int updateClaimCaseInvestigateDetail(ClaimCaseInvestigateDetail claimCaseInvestigateDetail);

    /**
     * 删除案件调查明细
     * 
     * @param investigateDetailNo 案件调查明细ID
     * @return 结果
     */
    public int deleteClaimCaseInvestigateDetailById(Long investigateDetailNo);

    /**
     * 批量删除案件调查明细
     * 
     * @param investigateDetailNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseInvestigateDetailByIds(Long[] investigateDetailNos);

    /**
     * 根据报案号查案件调差历史记录
     *
     * @param rptNo 报案号
     * @return 案件调查明细集合
     */
    public List<ClaimCaseInvestigateDetail> selectClaimCaseInvestigateDetailByRptNo(String rptNo);
}
