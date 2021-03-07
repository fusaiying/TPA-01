package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.ClaimCaseFilingDetail;

import java.util.List;

/**
 * 案件归档明细Service接口
 * 
 * @author sino
 * @date 2021-03-05
 */
public interface IClaimCaseFilingDetailService 
{
    /**
     * 查询案件归档明细
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 案件归档明细
     */
    public ClaimCaseFilingDetail selectClaimCaseFilingDetailById(Long filingDetailId);

    /**
     * 查询案件归档明细列表
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 案件归档明细集合
     */
    public List<ClaimCaseFilingDetail> selectClaimCaseFilingDetailList(ClaimCaseFilingDetail claimCaseFilingDetail);

    /**
     * 新增案件归档明细
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 结果
     */
    public int insertClaimCaseFilingDetail(ClaimCaseFilingDetail claimCaseFilingDetail);

    /**
     * 修改案件归档明细
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 结果
     */
    public int updateClaimCaseFilingDetail(ClaimCaseFilingDetail claimCaseFilingDetail);

    /**
     * 批量删除案件归档明细
     * 
     * @param filingDetailIds 需要删除的案件归档明细ID
     * @return 结果
     */
    public int deleteClaimCaseFilingDetailByIds(Long[] filingDetailIds);

    /**
     * 删除案件归档明细信息
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 结果
     */
    public int deleteClaimCaseFilingDetailById(Long filingDetailId);
}
