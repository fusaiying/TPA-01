package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class GCCClaimBill implements Serializable {
    private static final long serialVersionUID = 1L;
    //账单金额
    private BigDecimal billAmt;
    //合理费用
    private BigDecimal reasonableAmt;
    //自费金额
    private BigDecimal ownAmt;
    //部分自费(H)
    private BigDecimal subownAmt;
    //不合理金额
    private BigDecimal totalImmoAmt;
    //先期给付
    private BigDecimal preAmt;
    //序号
    private String seqno;
    //账单号
    private String billno;
    //就诊日期
    private String treaDate;
    //账单类型
    private String therapyType;
    //治疗医院
    private String hospitalName;
    //临床诊断
    private String diseaseDesc;

}
