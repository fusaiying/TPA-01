package com.paic.ehis.claimflow.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName ReviewCompletedDTO
 * @Description: TODO 理算审核 审核完毕
 * @Author 硠君
 * @Date create in 19:32 2021/1/22
 * @Version 1.0
 **/
@Data
public class ReviewCompletedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    private String batchNo;

    /** 账单币种 */
    private String billCurrency;

    /** 是否申诉案件 */
    private String isAppeal;

    /** 赔付金额 */
    private BigDecimal payAmount;

    /** 拒赔金额 */
    private BigDecimal refusedAmount;

    /*案件类型*/
    private String caseType;

    /*赔付结论*/
    private String payConclusion;
}
