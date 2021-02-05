package com.paic.ehis.claimcal.mapper;



import com.paic.ehis.claimcal.domain.ClaimCaseBill;

import java.util.List;

/**
 * 案件账单明细Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ClaimCaseBillMapper 
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
     * 删除案件账单明细
     * 
     * @param billId 案件账单明细ID
     * @return 结果
     */
    public int deleteClaimCaseBillById(Long billId);

    /**
     * 批量删除案件账单明细
     * 
     * @param billIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseBillByIds(Long[] billIds);

    /**
     * 根据报案号查询账单明细表
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseBill selectClaimCaseBillListByRptNo(String rptNo);



    /**
     * 根据账单ID更新账单明细表，数据失效
     *
     * @param billId
     * @return
     */
    public int updateClaimCaseBillById(Long billId);


    List<ClaimCaseBill> selectClaimCaseBillByIdOne(String rptNo);
}
