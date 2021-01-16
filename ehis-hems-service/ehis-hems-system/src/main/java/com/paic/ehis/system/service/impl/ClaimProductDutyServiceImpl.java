package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimProductDuty;
import com.paic.ehis.system.mapper.ClaimProductDutyMapper;
import com.paic.ehis.system.service.IClaimProductDutyService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 责任信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimProductDutyServiceImpl implements IClaimProductDutyService
{
    @Autowired
    private ClaimProductDutyMapper claimProductDutyMapper;

    /**
     * 查询责任信息 
     * 
     * @param dutyCode 责任信息 ID
     * @return 责任信息 
     */
    @Override
    public ClaimProductDuty selectClaimProductDutyById(String dutyCode)
    {
        return claimProductDutyMapper.selectClaimProductDutyById(dutyCode,dutyCode);
    }

    /**
     * 查询责任信息 列表
     * 
     * @param claimProductDuty 责任信息 
     * @return 责任信息 
     */
    @Override
    public List<ClaimProductDuty> selectClaimProductDutyList(ClaimProductDuty claimProductDuty)
    {
        return claimProductDutyMapper.selectClaimProductDutyList(claimProductDuty);
    }

    /**
     * 新增责任信息 
     * 
     * @param claimProductDuty 责任信息 
     * @return 结果
     */
    @Override
    public int insertClaimProductDuty(ClaimProductDuty claimProductDuty)
    {
        claimProductDuty.setCreateTime(DateUtils.getNowDate());
        return claimProductDutyMapper.insertClaimProductDuty(claimProductDuty);
    }

    /**
     * 修改责任信息 
     * 
     * @param claimProductDuty 责任信息 
     * @return 结果
     */
    @Override
    public int updateClaimProductDuty(ClaimProductDuty claimProductDuty)
    {
        claimProductDuty.setUpdateTime(DateUtils.getNowDate());
        return claimProductDutyMapper.updateClaimProductDuty(claimProductDuty);
    }

    /**
     * 批量删除责任信息 
     * 
     * @param dutyCodes 需要删除的责任信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductDutyByIds(String[] dutyCodes)
    {
        return claimProductDutyMapper.deleteClaimProductDutyByIds(dutyCodes);
    }

    /**
     * 删除责任信息 信息
     * 
     * @param dutyCode 责任信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductDutyById(String dutyCode)
    {
        return claimProductDutyMapper.deleteClaimProductDutyById(dutyCode);
    }
}
