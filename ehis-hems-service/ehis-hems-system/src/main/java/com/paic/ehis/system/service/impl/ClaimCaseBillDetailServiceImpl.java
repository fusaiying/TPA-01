package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseBillDetail;
import com.paic.ehis.system.mapper.ClaimCaseBillDetailMapper;
import com.paic.ehis.system.service.IClaimCaseBillDetailService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件账单费用项明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseBillDetailServiceImpl implements IClaimCaseBillDetailService
{
    @Autowired
    private ClaimCaseBillDetailMapper claimCaseBillDetailMapper;

    /**
     * 查询案件账单费用项明细
     * 
     * @param detailId 案件账单费用项明细ID
     * @return 案件账单费用项明细
     */
    @Override
    public ClaimCaseBillDetail selectClaimCaseBillDetailById(Long detailId)
    {
        return claimCaseBillDetailMapper.selectClaimCaseBillDetailById(detailId);
    }

    /**
     * 查询案件账单费用项明细列表
     * 
     * @param claimCaseBillDetail 案件账单费用项明细
     * @return 案件账单费用项明细
     */
    @Override
    public List<ClaimCaseBillDetail> selectClaimCaseBillDetailList(ClaimCaseBillDetail claimCaseBillDetail)
    {
        return claimCaseBillDetailMapper.selectClaimCaseBillDetailList(claimCaseBillDetail);
    }

    /**
     * 新增案件账单费用项明细
     * 
     * @param claimCaseBillDetail 案件账单费用项明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseBillDetail(ClaimCaseBillDetail claimCaseBillDetail)
    {
        claimCaseBillDetail.setCreateTime(DateUtils.getNowDate());
        return claimCaseBillDetailMapper.insertClaimCaseBillDetail(claimCaseBillDetail);
    }

    /**
     * 修改案件账单费用项明细
     * 
     * @param claimCaseBillDetail 案件账单费用项明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseBillDetail(ClaimCaseBillDetail claimCaseBillDetail)
    {
        claimCaseBillDetail.setUpdateTime(DateUtils.getNowDate());
        return claimCaseBillDetailMapper.updateClaimCaseBillDetail(claimCaseBillDetail);
    }

    /**
     * 批量删除案件账单费用项明细
     * 
     * @param detailIds 需要删除的案件账单费用项明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillDetailByIds(Long[] detailIds)
    {
        return claimCaseBillDetailMapper.deleteClaimCaseBillDetailByIds(detailIds);
    }

    /**
     * 删除案件账单费用项明细信息
     * 
     * @param detailId 案件账单费用项明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillDetailById(Long detailId)
    {
        return claimCaseBillDetailMapper.deleteClaimCaseBillDetailById(detailId);
    }
}
