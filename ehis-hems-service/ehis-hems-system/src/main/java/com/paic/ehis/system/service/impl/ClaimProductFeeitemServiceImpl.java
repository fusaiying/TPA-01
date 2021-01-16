package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimProductFeeitem;
import com.paic.ehis.system.mapper.ClaimProductFeeitemMapper;
import com.paic.ehis.system.service.IClaimProductFeeitemService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 费用项信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimProductFeeitemServiceImpl implements IClaimProductFeeitemService
{
    @Autowired
    private ClaimProductFeeitemMapper claimProductFeeitemMapper;

    /**
     * 查询费用项信息 
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 费用项信息 
     */
    @Override
    public ClaimProductFeeitem selectClaimProductFeeitemById(String feeitemCode)
    {
        return claimProductFeeitemMapper.selectClaimProductFeeitemById(feeitemCode);
    }

    /**
     * 查询费用项信息 列表
     * 
     * @param claimProductFeeitem 费用项信息 
     * @return 费用项信息 
     */
    @Override
    public List<ClaimProductFeeitem> selectClaimProductFeeitemList(ClaimProductFeeitem claimProductFeeitem)
    {
        return claimProductFeeitemMapper.selectClaimProductFeeitemList(claimProductFeeitem);
    }

    /**
     * 新增费用项信息 
     * 
     * @param claimProductFeeitem 费用项信息 
     * @return 结果
     */
    @Override
    public int insertClaimProductFeeitem(ClaimProductFeeitem claimProductFeeitem)
    {
        claimProductFeeitem.setCreateTime(DateUtils.getNowDate());
        return claimProductFeeitemMapper.insertClaimProductFeeitem(claimProductFeeitem);
    }

    /**
     * 修改费用项信息 
     * 
     * @param claimProductFeeitem 费用项信息 
     * @return 结果
     */
    @Override
    public int updateClaimProductFeeitem(ClaimProductFeeitem claimProductFeeitem)
    {
        claimProductFeeitem.setUpdateTime(DateUtils.getNowDate());
        return claimProductFeeitemMapper.updateClaimProductFeeitem(claimProductFeeitem);
    }

    /**
     * 批量删除费用项信息 
     * 
     * @param feeitemCodes 需要删除的费用项信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductFeeitemByIds(String[] feeitemCodes)
    {
        return claimProductFeeitemMapper.deleteClaimProductFeeitemByIds(feeitemCodes);
    }

    /**
     * 删除费用项信息 信息
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductFeeitemById(String feeitemCode)
    {
        return claimProductFeeitemMapper.deleteClaimProductFeeitemById(feeitemCode);
    }
}
