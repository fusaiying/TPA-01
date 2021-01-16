package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseBill;

/**
 * 案件账单明细Service接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface IClaimCaseBillService 
{
    /**
     * 查询案件账单明细
     * 
     * @param billId 案件账单明细ID
     * @return 案件账单明细
     */
    public ClaimCaseBill selectClaimCaseBillById(Long billId);

    /**
     * 查询案件账单明细列表
     * 
     * @param claimCaseBill 案件账单明细
     * @return 案件账单明细集合
     */
    public List<ClaimCaseBill> selectClaimCaseBillList(ClaimCaseBill claimCaseBill);

    /**
     * 新增案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    public int insertClaimCaseBill(ClaimCaseBill claimCaseBill);

    /**
     * 修改案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    public int updateClaimCaseBill(ClaimCaseBill claimCaseBill);

    /**
     * 批量删除案件账单明细
     * 
     * @param billIds 需要删除的案件账单明细ID
     * @return 结果
     */
    public int deleteClaimCaseBillByIds(Long[] billIds);

    /**
     * 删除案件账单明细信息
     * 
     * @param billId 案件账单明细ID
     * @return 结果
     */
    public int deleteClaimCaseBillById(Long billId);
}
