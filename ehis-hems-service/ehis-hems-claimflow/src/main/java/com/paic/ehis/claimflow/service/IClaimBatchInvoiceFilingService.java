package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.ClaimBatchInvoiceFiling;
import com.paic.ehis.claimflow.domain.vo.InvoiceFileVo;

import java.util.List;

/**
 * 发票归档Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IClaimBatchInvoiceFilingService 
{
    /**
     * 查询发票归档
     * 
     * @param batchNo 发票归档ID
     * @return 发票归档
     */
    public ClaimBatchInvoiceFiling selectClaimBatchInvoiceFilingById(String batchNo);

    /**
     * 查询发票归档列表
     * 
     * @param claimBatchInvoiceFiling 发票归档
     * @return 发票归档集合
     */
    public List<ClaimBatchInvoiceFiling> selectClaimBatchInvoiceFilingList(ClaimBatchInvoiceFiling claimBatchInvoiceFiling);

    /**
     * 新增发票归档
     * 
     * @param claimBatchInvoiceFiling 发票归档
     * @return 结果
     */
    public int insertClaimBatchInvoiceFiling(ClaimBatchInvoiceFiling claimBatchInvoiceFiling);

    /**
     * 修改发票归档
     * 
     * @param claimBatchInvoiceFiling 发票归档
     * @return 结果
     */
    public int updateClaimBatchInvoiceFiling(ClaimBatchInvoiceFiling claimBatchInvoiceFiling);

    /**
     * 批量删除发票归档
     * 
     * @param batchNos 需要删除的发票归档ID
     * @return 结果
     */
    public int deleteClaimBatchInvoiceFilingByIds(String[] batchNos);

    /**
     * 删除发票归档信息
     * 
     * @param batchNo 发票归档ID
     * @return 结果
     */
    public int deleteClaimBatchInvoiceFilingById(String batchNo);

    /**
     * 查询发票归档工作池
     * @param invoiceFileVo
     * @return
     */
    List<InvoiceFileVo> selectInvoiceFile(InvoiceFileVo invoiceFileVo);

    /**
     * 发票归档编辑超链接
     * @param invoiceFileVo
     * @return
     */
    int updateInvoiceFile(InvoiceFileVo invoiceFileVo);
}
