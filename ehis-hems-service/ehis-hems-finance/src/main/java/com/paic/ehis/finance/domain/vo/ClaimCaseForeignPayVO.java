package com.paic.ehis.finance.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClaimCaseForeignPayVO implements Serializable {

    /** 批次号 */
    private String batchNo;

    /** 案件信息 */
    private List<ClaimCaseForeignPayInfoVO> caseInfoList;

    /** 支付信息 */
    private ClaimCasePaymentVO payment;

    /** 部门编码 */
    private String organCode;

    /** 确认支付 */
    private String payFlag;

    /** 案件借款 */
    private String borrowFlag;
}
