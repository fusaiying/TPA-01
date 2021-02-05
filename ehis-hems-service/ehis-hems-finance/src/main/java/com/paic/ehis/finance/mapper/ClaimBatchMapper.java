package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.ClaimBatch;
import com.paic.ehis.finance.domain.dto.ClaimCasePayDTO;

import java.util.List;
import java.util.Map;

/**
 * 理赔批次 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface ClaimBatchMapper 
{
    /**
     * 查询理赔批次
     *
     * @param batchNo 理赔批次 ID
     * @return 理赔批次
     */
    public ClaimBatch selectClaimBatchById(String batchNo);

    /**
     * 对公支付首页初始化查询
     *
     * @param deptId
     * @return
     */
    public List<Map<String,Object>> selectPayBatchInit(String deptId);

    /**
     * 查询对公支付的批次信息
     *
     * @param claimCasePayDTO
     * @return
     */
    public List<Map<String,Object>> selectPayBatchList(ClaimCasePayDTO claimCasePayDTO);

    /**
     * 部门ID下币种不为人民币、支付状态不全为已支付状态的
     * @param deptId
     * @return
     */
    public List<Map<String,Object>> selectPayForeignBatchInit(String deptId);

    /**
     * 对公外币支付-查询
     *
     * @param claimCasePayDTO
     * @return
     */
    public List<Map<String,Object>> selectPayForeignBatchList(ClaimCasePayDTO claimCasePayDTO);
}
