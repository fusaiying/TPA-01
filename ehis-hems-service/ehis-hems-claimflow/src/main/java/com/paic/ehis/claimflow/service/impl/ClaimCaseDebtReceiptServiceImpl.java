package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseDebtReceipt;
import com.paic.ehis.claimflow.domain.dto.DebtInfoDTO;
import com.paic.ehis.claimflow.mapper.ClaimCaseDebtReceiptMapper;
import com.paic.ehis.claimflow.service.IClaimCaseDebtReceiptService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 案件追讨收款明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseDebtReceiptServiceImpl implements IClaimCaseDebtReceiptService
{
    @Autowired
    private ClaimCaseDebtReceiptMapper claimCaseDebtReceiptMapper;

    /**
     * 查询案件追讨收款明细
     * 
     * @param receiptNo 案件追讨收款明细ID
     * @return 案件追讨收款明细
     */
    @Override
    public ClaimCaseDebtReceipt selectClaimCaseDebtReceiptById(String receiptNo)
    {
        return claimCaseDebtReceiptMapper.selectClaimCaseDebtReceiptById(receiptNo);
    }

    /**
     * 查询案件追讨收款明细列表
     * 
     * @param claimCaseDebtReceipt 案件追讨收款明细
     * @return 案件追讨收款明细
     */
    @Override
    public List<ClaimCaseDebtReceipt> selectClaimCaseDebtReceiptList(ClaimCaseDebtReceipt claimCaseDebtReceipt)
    {
        return claimCaseDebtReceiptMapper.selectClaimCaseDebtReceiptList(claimCaseDebtReceipt);
    }

    /**
     * 新增案件追讨收款明细
     * 
     * @param claimCaseDebtReceipt 案件追讨收款明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseDebtReceipt(ClaimCaseDebtReceipt claimCaseDebtReceipt)
    {
        claimCaseDebtReceipt.setCreateTime(DateUtils.getNowDate());
        return claimCaseDebtReceiptMapper.insertClaimCaseDebtReceipt(claimCaseDebtReceipt);
    }

    /**
     * 修改案件追讨收款明细
     * 
     * @param claimCaseDebtReceipt 案件追讨收款明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseDebtReceipt(ClaimCaseDebtReceipt claimCaseDebtReceipt)
    {
        claimCaseDebtReceipt.setUpdateTime(DateUtils.getNowDate());
        return claimCaseDebtReceiptMapper.updateClaimCaseDebtReceipt(claimCaseDebtReceipt);
    }

    /**
     * 批量删除案件追讨收款明细
     * 
     * @param receiptNos 需要删除的案件追讨收款明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDebtReceiptByIds(String[] receiptNos)
    {
        return claimCaseDebtReceiptMapper.deleteClaimCaseDebtReceiptByIds(receiptNos);
    }

    /**
     * 删除案件追讨收款明细信息
     * 
     * @param receiptNo 案件追讨收款明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDebtReceiptById(String receiptNo)
    {
        return claimCaseDebtReceiptMapper.deleteClaimCaseDebtReceiptById(receiptNo);
    }

    /**
     * 查询近3个月的收款记录
     *
     * @return
     */
    @Override
    public List<ClaimCaseDebtReceipt> selectClaimCaseDebtReceiptListNear() {
        return claimCaseDebtReceiptMapper.selectClaimCaseDebtReceiptListNear();
    }

    /**
     * 根据被保人姓名/日期查询收款明细
     *
     * @param debtInfoDTO
     * @return
     */
    @Override
    public List<ClaimCaseDebtReceipt> selectClaimCaseDebtReceiptListByInsuredOrDate(DebtInfoDTO debtInfoDTO) {
        return claimCaseDebtReceiptMapper.selectClaimCaseDebtReceiptListByInsuredOrDate(debtInfoDTO);
    }

    /**
     * 查询全部收款明细 关联追讨核销表
     *
     * @return
     */
    @Override
    public List<ClaimCaseDebtReceipt> selectReceiptList() {
        return claimCaseDebtReceiptMapper.selectReceiptList();
    }
}
