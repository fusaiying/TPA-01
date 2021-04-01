package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class GCCClaimBillDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    //项目类型
    private String feeType;
    //项目名称
    private String feeName;
    //账单金额
    private BigDecimal billAmt;
    //自费金额
    private BigDecimal allownAmt;
    //部分自费(H)
    private BigDecimal subownAmt;
    //不合理费用
    private BigDecimal ownAmt;
    //先期给付
    private BigDecimal preAmt;
    //备注
    private String costComment;

}
