package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseDebtReceipt;
import com.paic.ehis.claimflow.domain.dto.DebtInfoDTO;

import java.util.List;

/**
 * 案件追讨收款明细Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseDebtReceiptMapper 
{
    /**
     * 查询案件追讨收款明细
     * 
     * @param receiptNo 案件追讨收款明细ID
     * @return 案件追讨收款明细
     */
    public ClaimCaseDebtReceipt selectClaimCaseDebtReceiptById(String receiptNo);

    /**
     * 查询案件追讨收款明细列表
     * 
     * @param claimCaseDebtReceipt 案件追讨收款明细
     * @return 案件追讨收款明细集合
     */
    public List<ClaimCaseDebtReceipt> selectClaimCaseDebtReceiptList(ClaimCaseDebtReceipt claimCaseDebtReceipt);

    /**
     * 新增案件追讨收款明细
     * 
     * @param claimCaseDebtReceipt 案件追讨收款明细
     * @return 结果
     */
    public int insertClaimCaseDebtReceipt(ClaimCaseDebtReceipt claimCaseDebtReceipt);

    /**
     * 修改案件追讨收款明细
     * 
     * @param claimCaseDebtReceipt 案件追讨收款明细
     * @return 结果
     */
    public int updateClaimCaseDebtReceipt(ClaimCaseDebtReceipt claimCaseDebtReceipt);

    /**
     * 删除案件追讨收款明细
     * 
     * @param receiptNo 案件追讨收款明细ID
     * @return 结果
     */
    public int deleteClaimCaseDebtReceiptById(String receiptNo);

    /**
     * 批量删除案件追讨收款明细
     * 
     * @param receiptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseDebtReceiptByIds(String[] receiptNos);

    /**
     * 查询近3个月的收款明细
     *
     * @return
     */
    public List<ClaimCaseDebtReceipt> selectClaimCaseDebtReceiptListNear();

    /**
     * 根据被保人姓名/日期查询收款明细
     *
     * @param debtInfoDTO
     * @return
     */
    public List<ClaimCaseDebtReceipt> selectClaimCaseDebtReceiptListByInsuredOrDate(DebtInfoDTO debtInfoDTO);
}
