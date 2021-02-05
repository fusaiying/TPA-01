package com.paic.ehis.finance.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClaimCasePayVO implements Serializable {

    /** 批次号 */
    private String batchNo;

    /** 案件信息 */
    private List<ClaimCasePayInfoVO> caseInfoList;

    /** 支付信息 */
    private ClaimCasePaymentVO payment;
}
