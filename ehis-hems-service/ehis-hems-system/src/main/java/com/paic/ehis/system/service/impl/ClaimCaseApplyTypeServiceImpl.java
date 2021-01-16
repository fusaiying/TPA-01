package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseApplyType;
import com.paic.ehis.system.mapper.ClaimCaseApplyTypeMapper;
import com.paic.ehis.system.service.IClaimCaseApplyTypeService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件申请类别Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseApplyTypeServiceImpl implements IClaimCaseApplyTypeService
{
    @Autowired
    private ClaimCaseApplyTypeMapper claimCaseApplyTypeMapper;

    /**
     * 查询案件申请类别
     * 
     * @param applyId 案件申请类别ID
     * @return 案件申请类别
     */
    @Override
    public ClaimCaseApplyType selectClaimCaseApplyTypeById(Long applyId)
    {
        return claimCaseApplyTypeMapper.selectClaimCaseApplyTypeById(applyId);
    }

    /**
     * 查询案件申请类别列表
     * 
     * @param claimCaseApplyType 案件申请类别
     * @return 案件申请类别
     */
    @Override
    public List<ClaimCaseApplyType> selectClaimCaseApplyTypeList(ClaimCaseApplyType claimCaseApplyType)
    {
        return claimCaseApplyTypeMapper.selectClaimCaseApplyTypeList(claimCaseApplyType);
    }

    /**
     * 新增案件申请类别
     * 
     * @param claimCaseApplyType 案件申请类别
     * @return 结果
     */
    @Override
    public int insertClaimCaseApplyType(ClaimCaseApplyType claimCaseApplyType)
    {
        claimCaseApplyType.setCreateTime(DateUtils.getNowDate());
        return claimCaseApplyTypeMapper.insertClaimCaseApplyType(claimCaseApplyType);
    }

    /**
     * 修改案件申请类别
     * 
     * @param claimCaseApplyType 案件申请类别
     * @return 结果
     */
    @Override
    public int updateClaimCaseApplyType(ClaimCaseApplyType claimCaseApplyType)
    {
        claimCaseApplyType.setUpdateTime(DateUtils.getNowDate());
        return claimCaseApplyTypeMapper.updateClaimCaseApplyType(claimCaseApplyType);
    }

    /**
     * 批量删除案件申请类别
     * 
     * @param applyIds 需要删除的案件申请类别ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseApplyTypeByIds(Long[] applyIds)
    {
        return claimCaseApplyTypeMapper.deleteClaimCaseApplyTypeByIds(applyIds);
    }

    /**
     * 删除案件申请类别信息
     * 
     * @param applyId 案件申请类别ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseApplyTypeById(Long applyId)
    {
        return claimCaseApplyTypeMapper.deleteClaimCaseApplyTypeById(applyId);
    }
}
