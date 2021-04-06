package com.paic.ehis.finance.service;

import com.paic.ehis.system.api.domain.FinanceBorrowInfo;
import com.paic.ehis.finance.domain.FinancePayInfo;
import com.paic.ehis.finance.domain.dto.TransferfailedDTO;
import com.paic.ehis.finance.domain.vo.TransferfailedVo;

import java.util.List;

/**
 * 支付信息Service接口
 * 
 * @author sino
 * @date 2021-02-01
 */
public interface IFinancePayInfoService 
{
    /**
     * 查询支付信息
     * 
     * @param payId 支付信息ID
     * @return 支付信息
     */
    public FinancePayInfo selectFinancePayInfoById(Long payId);

    /**
     * 查询支付信息列表
     * 
     * @param financePayInfo 支付信息
     * @return 支付信息集合
     */
    public List<FinancePayInfo> selectFinancePayInfoList(FinancePayInfo financePayInfo);

    /**
     * 新增支付信息
     * 
     * @param financePayInfo 支付信息
     * @return 结果
     */
    public int insertFinancePayInfo(FinancePayInfo financePayInfo);

    /**
     * 修改支付信息
     * 
     * @param financePayInfo 支付信息
     * @return 结果
     */
    public int updateFinancePayInfo(FinancePayInfo financePayInfo);

    /**
     * 批量删除支付信息
     * 
     * @param payIds 需要删除的支付信息ID
     * @return 结果
     */
    public int deleteFinancePayInfoByIds(Long[] payIds);

    /**
     * 删除支付信息信息
     * 
     * @param payId 支付信息ID
     * @return 结果
     */
    public int deleteFinancePayInfoById(Long payId);

    /**
     *查询转账失败处理工作池
     */
    public List<TransferfailedVo> selectTransferfailedList(TransferfailedDTO transferfailedDTO);

    //更新
    public TransferfailedVo updateTransferFailedInfo(String batchNo);

    public int deleteFinanceTransferfailedList(String batchNo);

    /**
     * 置借款数据无效
     *
     * @param rptNo
     * @return
     */
    public int deleteFinanceBorrow(String rptNo);

    /**
     * 根据报案号查询借款信息
     *
     * @param rptNo
     * @return
     */
    public FinanceBorrowInfo selectBorrowInfo(String rptNo);

    /**
     * 更新借款表数据
     *
     * @param borrowInfo
     * @return
     */
    public int updateBorrowInfo(FinanceBorrowInfo borrowInfo);
}
