package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimCaseFiling;
import com.paic.ehis.claimflow.domain.ClaimCaseFilingDetail;

import java.util.List;

/**
 * 案件归档明细Mapper接口
 * 
 * @author sino
 * @date 2021-03-05
 */
public interface ClaimCaseFilingDetailMapper 
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
     * 删除案件归档明细
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 结果
     */
    public int deleteClaimCaseFilingDetailById(Long filingDetailId);

    /**
     * 批量删除案件归档明细
     * 
     * @param filingDetailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseFilingDetailByIds(Long[] filingDetailIds);



    /**
     * 新增案件归档明细（添加归档信息时根据 报案号起 止 插入明显记录）
     *
     * modify by : hjw
     * time: 2021-3-8
     */
    public int insertClaimCaseFilingDetailByRpt(ClaimCaseFiling claimCaseFiling);
}
