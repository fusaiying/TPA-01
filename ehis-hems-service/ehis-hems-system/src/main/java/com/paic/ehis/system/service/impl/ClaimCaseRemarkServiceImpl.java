package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseRemark;
import com.paic.ehis.system.mapper.ClaimCaseRemarkMapper;
import com.paic.ehis.system.service.IClaimCaseRemarkService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件备注Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseRemarkServiceImpl implements IClaimCaseRemarkService
{
    @Autowired
    private ClaimCaseRemarkMapper claimCaseRemarkMapper;

    /**
     * 查询案件备注
     * 
     * @param remarkId 案件备注ID
     * @return 案件备注
     */
    @Override
    public ClaimCaseRemark selectClaimCaseRemarkById(Long remarkId)
    {
        return claimCaseRemarkMapper.selectClaimCaseRemarkById(remarkId);
    }

    /**
     * 查询案件备注列表
     * 
     * @param claimCaseRemark 案件备注
     * @return 案件备注
     */
    @Override
    public List<ClaimCaseRemark> selectClaimCaseRemarkList(ClaimCaseRemark claimCaseRemark)
    {
        claimCaseRemark.setStatus("Y");
        return claimCaseRemarkMapper.selectClaimCaseRemarkList(claimCaseRemark);
    }

    /**
     * 新增案件备注
     * 
     * @param claimCaseRemark 案件备注
     * @return 结果
     */
    @Override
    public int insertClaimCaseRemark(ClaimCaseRemark claimCaseRemark)
    {
        claimCaseRemark.setCreateBy(SecurityUtils.getUsername());
        claimCaseRemark.setCreateTime(DateUtils.getNowDate());
        claimCaseRemark.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRemark.setUpdateTime(DateUtils.getNowDate());
        claimCaseRemark.setStatus("Y");//状态
        return claimCaseRemarkMapper.insertClaimCaseRemark(claimCaseRemark);
    }

    /**
     * 修改案件备注
     * 
     * @param claimCaseRemark 案件备注
     * @return 结果
     */
    @Override
    public int updateClaimCaseRemark(ClaimCaseRemark claimCaseRemark)
    {
        claimCaseRemark.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRemark.setUpdateTime(DateUtils.getNowDate());
        claimCaseRemark.setStatus("Y");//状态
        return claimCaseRemarkMapper.updateClaimCaseRemark(claimCaseRemark);
    }

    /**
     * 批量删除案件备注
     * 
     * @param remarkIds 需要删除的案件备注ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRemarkByIds(Long[] remarkIds)
    {
        int i=0;
        for (long remarkId:remarkIds){
            ClaimCaseRemark claimCaseRemark = new ClaimCaseRemark();
            claimCaseRemark.setStatus("N");
            claimCaseRemark.setRemarkId(remarkId);
            i = claimCaseRemarkMapper.updateClaimCaseRemark(claimCaseRemark);
        }
        return i;
    }

    /**
     * 删除案件备注信息
     * 
     * @param remarkId 案件备注ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRemarkById(Long remarkId)
    {
        return claimCaseRemarkMapper.deleteClaimCaseRemarkById(remarkId);
    }
}
