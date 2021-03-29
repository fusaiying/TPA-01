package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.ClaimCaseCal;
import com.paic.ehis.finance.domain.vo.ClaimCasePaymentVO;

import java.util.List;

/**
 * 案件赔付信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseCalMapper 
{
    /**
     * 查询案件赔付信息
     * 
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    public ClaimCaseCal selectClaimCaseCalByRptNo(String rptNo);

    /**
     * 查询理赔总金额
     *
     * @param batchNo 批次号
     * @return 案件赔付信息
     */
    public ClaimCasePaymentVO selectClaimCaseCalAmountByBatchNo(String batchNo);

}
