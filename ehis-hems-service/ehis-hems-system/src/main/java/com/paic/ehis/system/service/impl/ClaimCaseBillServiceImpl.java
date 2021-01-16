package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseBill;
import com.paic.ehis.system.mapper.ClaimCaseBillMapper;
import com.paic.ehis.system.service.IClaimCaseBillService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件账单明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseBillServiceImpl implements IClaimCaseBillService
{
    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    /**
     * 查询案件账单明细
     * 
     * @param billId 案件账单明细ID
     * @return 案件账单明细
     */
    @Override
    public ClaimCaseBill selectClaimCaseBillById(Long billId)
    {
        return claimCaseBillMapper.selectClaimCaseBillById(billId);
    }

    /**
     * 查询案件账单明细列表
     * 
     * @param claimCaseBill 案件账单明细
     * @return 案件账单明细
     */
    @Override
    public List<ClaimCaseBill> selectClaimCaseBillList(ClaimCaseBill claimCaseBill)
    {
        return claimCaseBillMapper.selectClaimCaseBillList(claimCaseBill);
    }

    /**
     * 新增案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseBill(ClaimCaseBill claimCaseBill)
    {
        claimCaseBill.setCreateTime(DateUtils.getNowDate());
        return claimCaseBillMapper.insertClaimCaseBill(claimCaseBill);
    }

    /**
     * 修改案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseBill(ClaimCaseBill claimCaseBill)
    {
        claimCaseBill.setUpdateTime(DateUtils.getNowDate());
        return claimCaseBillMapper.updateClaimCaseBill(claimCaseBill);
    }

    /**
     * 批量删除案件账单明细
     * 
     * @param billIds 需要删除的案件账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillByIds(Long[] billIds)
    {
        return claimCaseBillMapper.deleteClaimCaseBillByIds(billIds);
    }

    /**
     * 删除案件账单明细信息
     * 
     * @param billId 案件账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillById(Long billId)
    {
        return claimCaseBillMapper.deleteClaimCaseBillById(billId);
    }
}
