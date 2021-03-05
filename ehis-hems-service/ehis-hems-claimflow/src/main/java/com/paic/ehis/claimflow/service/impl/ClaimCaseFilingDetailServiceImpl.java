package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.mapper.ClaimCaseFilingDetailMapper;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.claimflow.domain.ClaimCaseFilingDetail;
import com.paic.ehis.claimflow.service.IClaimCaseFilingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件归档明细Service业务层处理
 * 
 * @author sino
 * @date 2021-03-05
 */
@Service
public class ClaimCaseFilingDetailServiceImpl implements IClaimCaseFilingDetailService 
{
    @Autowired
    private ClaimCaseFilingDetailMapper claimCaseFilingDetailMapper;

    /**
     * 查询案件归档明细
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 案件归档明细
     */
    @Override
    public ClaimCaseFilingDetail selectClaimCaseFilingDetailById(Long filingDetailId)
    {
        return claimCaseFilingDetailMapper.selectClaimCaseFilingDetailById(filingDetailId);
    }

    /**
     * 查询案件归档明细列表
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 案件归档明细
     */
    @Override
    public List<ClaimCaseFilingDetail> selectClaimCaseFilingDetailList(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        return claimCaseFilingDetailMapper.selectClaimCaseFilingDetailList(claimCaseFilingDetail);
    }

    /**
     * 新增案件归档明细
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseFilingDetail(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        claimCaseFilingDetail.setCreateTime(DateUtils.getNowDate());
        return claimCaseFilingDetailMapper.insertClaimCaseFilingDetail(claimCaseFilingDetail);
    }

    /**
     * 修改案件归档明细
     * 
     * @param claimCaseFilingDetail 案件归档明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseFilingDetail(ClaimCaseFilingDetail claimCaseFilingDetail)
    {
        claimCaseFilingDetail.setUpdateTime(DateUtils.getNowDate());
        return claimCaseFilingDetailMapper.updateClaimCaseFilingDetail(claimCaseFilingDetail);
    }

    /**
     * 批量删除案件归档明细
     * 
     * @param filingDetailIds 需要删除的案件归档明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseFilingDetailByIds(Long[] filingDetailIds)
    {
        return claimCaseFilingDetailMapper.deleteClaimCaseFilingDetailByIds(filingDetailIds);
    }

    /**
     * 删除案件归档明细信息
     * 
     * @param filingDetailId 案件归档明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseFilingDetailById(Long filingDetailId)
    {
        return claimCaseFilingDetailMapper.deleteClaimCaseFilingDetailById(filingDetailId);
    }
}
