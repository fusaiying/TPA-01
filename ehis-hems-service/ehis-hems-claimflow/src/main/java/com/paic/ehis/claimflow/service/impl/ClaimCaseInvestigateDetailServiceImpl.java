package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseInvestigateDetail;
import com.paic.ehis.claimflow.mapper.ClaimCaseInvestigateDetailMapper;
import com.paic.ehis.claimflow.service.IClaimCaseInvestigateDetailService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件调查明细Service业务层处理
 * 
 * @author sino
 * @date 2021-03-04
 */
@Service
public class ClaimCaseInvestigateDetailServiceImpl implements IClaimCaseInvestigateDetailService
{
    @Autowired
    private ClaimCaseInvestigateDetailMapper claimCaseInvestigateDetailMapper;

    /**
     * 查询案件调查明细
     * 
     * @param investigateDetailNo 案件调查明细ID
     * @return 案件调查明细
     */
    @Override
    public ClaimCaseInvestigateDetail selectClaimCaseInvestigateDetailById(Long investigateDetailNo)
    {
        return claimCaseInvestigateDetailMapper.selectClaimCaseInvestigateDetailById(investigateDetailNo);
    }

    /**
     * 查询案件调查明细列表
     * 
     * @param claimCaseInvestigateDetail 案件调查明细
     * @return 案件调查明细
     */
    @Override
    public List<ClaimCaseInvestigateDetail> selectClaimCaseInvestigateDetailList(ClaimCaseInvestigateDetail claimCaseInvestigateDetail)
    {
        return claimCaseInvestigateDetailMapper.selectClaimCaseInvestigateDetailList(claimCaseInvestigateDetail);
    }

    /**
     * 新增案件调查明细
     * 
     * @param claimCaseInvestigateDetail 案件调查明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseInvestigateDetail(ClaimCaseInvestigateDetail claimCaseInvestigateDetail)
    {
        claimCaseInvestigateDetail.setCreateTime(DateUtils.getNowDate());
        claimCaseInvestigateDetail.setCreateTime(DateUtils.getNowDate());
        return claimCaseInvestigateDetailMapper.insertClaimCaseInvestigateDetail(claimCaseInvestigateDetail);
    }

    /**
     * 修改案件调查明细
     * 
     * @param claimCaseInvestigateDetail 案件调查明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseInvestigateDetail(ClaimCaseInvestigateDetail claimCaseInvestigateDetail)
    {
        claimCaseInvestigateDetail.setUpdateTime(DateUtils.getNowDate());
        claimCaseInvestigateDetail.setUpdateTime(DateUtils.getNowDate());
        return claimCaseInvestigateDetailMapper.updateClaimCaseInvestigateDetail(claimCaseInvestigateDetail);
    }

    /**
     * 批量删除案件调查明细
     * 
     * @param investigateDetailNos 需要删除的案件调查明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInvestigateDetailByIds(Long[] investigateDetailNos)
    {
        return claimCaseInvestigateDetailMapper.deleteClaimCaseInvestigateDetailByIds(investigateDetailNos);
    }

    /**
     * 删除案件调查明细信息
     * 
     * @param investigateDetailNo 案件调查明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseInvestigateDetailById(Long investigateDetailNo)
    {
        return claimCaseInvestigateDetailMapper.deleteClaimCaseInvestigateDetailById(investigateDetailNo);
    }

    /**
     * 根据报案号查案件调差历史记录
     *
     * @param rptNo 报案号
     * @return 案件调查明细集合
     */
    @Override
    public List<ClaimCaseInvestigateDetail> selectClaimCaseInvestigateDetailListByRptNo(String rptNo)
    {
        return claimCaseInvestigateDetailMapper.selectClaimCaseInvestigateDetailByRptNo(rptNo);
    }
}
