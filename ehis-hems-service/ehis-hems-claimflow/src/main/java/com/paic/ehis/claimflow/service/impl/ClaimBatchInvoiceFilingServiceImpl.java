package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimBatch;
import com.paic.ehis.claimflow.domain.ClaimBatchInvoiceFiling;
import com.paic.ehis.claimflow.domain.ClaimCaseInsured;
import com.paic.ehis.claimflow.domain.vo.InvoiceFileVo;
import com.paic.ehis.claimflow.mapper.ClaimBatchInvoiceFilingMapper;
import com.paic.ehis.claimflow.mapper.ClaimBatchMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseInsuredMapper;
import com.paic.ehis.claimflow.service.IClaimBatchInvoiceFilingService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 发票归档Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimBatchInvoiceFilingServiceImpl implements IClaimBatchInvoiceFilingService 
{
    @Autowired
    private ClaimBatchInvoiceFilingMapper claimBatchInvoiceFilingMapper;

    @Autowired
    private ClaimCaseInsuredMapper claimCaseInsuredMapper;
    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    /**
     * 查询发票归档
     * 
     * @param batchNo 发票归档ID
     * @return 发票归档
     */
    @Override
    public ClaimBatchInvoiceFiling selectClaimBatchInvoiceFilingById(String batchNo)
    {
        return claimBatchInvoiceFilingMapper.selectClaimBatchInvoiceFilingById(batchNo);
    }

    /**
     * 查询发票归档列表
     * 
     * @param claimBatchInvoiceFiling 发票归档
     * @return 发票归档
     */
    @Override
    public List<ClaimBatchInvoiceFiling> selectClaimBatchInvoiceFilingList(ClaimBatchInvoiceFiling claimBatchInvoiceFiling)
    {
        return claimBatchInvoiceFilingMapper.selectClaimBatchInvoiceFilingList(claimBatchInvoiceFiling);
    }

    /**
     * 新增发票归档
     * 
     * @param claimBatchInvoiceFiling 发票归档
     * @return 结果
     */
    @Override
    public int insertClaimBatchInvoiceFiling(ClaimBatchInvoiceFiling claimBatchInvoiceFiling)
    {
        claimBatchInvoiceFiling.setCreateTime(DateUtils.getNowDate());
        return claimBatchInvoiceFilingMapper.insertClaimBatchInvoiceFiling(claimBatchInvoiceFiling);
    }

    /**
     * 修改发票归档
     * 
     * @param claimBatchInvoiceFiling 发票归档
     * @return 结果
     */
    @Override
    public int updateClaimBatchInvoiceFiling(ClaimBatchInvoiceFiling claimBatchInvoiceFiling)
    {
        claimBatchInvoiceFiling.setUpdateTime(DateUtils.getNowDate());
        return claimBatchInvoiceFilingMapper.updateClaimBatchInvoiceFiling(claimBatchInvoiceFiling);
    }

    /**
     * 批量删除发票归档
     * 
     * @param batchNos 需要删除的发票归档ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchInvoiceFilingByIds(String[] batchNos)
    {
        return claimBatchInvoiceFilingMapper.deleteClaimBatchInvoiceFilingByIds(batchNos);
    }

    /**
     * 删除发票归档信息
     * 
     * @param batchNo 发票归档ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchInvoiceFilingById(String batchNo)
    {
        return claimBatchInvoiceFilingMapper.deleteClaimBatchInvoiceFilingById(batchNo);
    }

    /**
     * 查询发票归档工作池
     * @param invoiceFileVo
     * @return
     */
    @Override
    public List<InvoiceFileVo> selectInvoiceFile(InvoiceFileVo invoiceFileVo) {


        List<InvoiceFileVo>invoiceFileVos=claimBatchInvoiceFilingMapper.selectInvoiceFile(invoiceFileVo);
        //invoiceFileVo.setBatchNo(invoiceFileVo.getBatchNo());
        //需要根据是否收到发票修改  发票是否归档
        for (InvoiceFileVo invoicevo:invoiceFileVos
             ) {
        //String isFiling=invoicevo.getBillrecevieflag();
            //invoicevo.getBatchNo();
           /* invoiceFileVo.setIsFiling(invoiceFileVo.getBillrecevieflag());
            claimBatchInvoiceFilingMapper.updateClaimBatchBill(invoiceFileVo.getBatchNo());
            invoiceFileVo.setIsFiling(invoiceFileVo.getBillrecevieflag());*/
       /* if(isFiling=="01"){
            invoiceFileVo.setIsFiling(isFiling);
            claimBatchInvoiceFilingMapper.updateClaimBatchBill(invoiceFileVo.getBatchNo());
        }else{
            invoiceFileVo.setIsFiling(isFiling);
            claimBatchInvoiceFilingMapper.updateClaimBatchBill(invoiceFileVo.getBatchNo());
        }*/


        List<ClaimCaseInsured> claimCaseInsureds=claimCaseInsuredMapper.selectCaseList(invoicevo.getRptNo());
        for (ClaimCaseInsured c:claimCaseInsureds
             ) {
            invoicevo.setRptNo(c.getRptNo());
            invoicevo.setName(c.getName());
        }}

        return invoiceFileVos;
    }

    /**
     * 发票归档编辑超链接
     *
     * @param invoiceFileVo 发票归档
     * @return 结果
     */
    @Override
    public int updateInvoiceFile(InvoiceFileVo invoiceFileVo)
    {
        String isFiling = invoiceFileVo.getIsFiling();
        if(StringUtils.isNotBlank(isFiling)) {
            ClaimBatch dto = new ClaimBatch();
            dto.setBatchno(invoiceFileVo.getBatchNo());
            dto.setBillrecevieflag(isFiling);
            dto.setUpdateTime(new Date());
            dto.setUpdateBy(SecurityUtils.getUsername());
            claimBatchMapper.updateClaimBatch(dto);
        }
        invoiceFileVo.setUpdateTime(DateUtils.getNowDate());
        return claimBatchInvoiceFilingMapper.updateInvoiceFile(invoiceFileVo);
    }
}
