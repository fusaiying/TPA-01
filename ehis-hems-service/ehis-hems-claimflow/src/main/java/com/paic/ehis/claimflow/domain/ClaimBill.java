package com.paic.ehis.claimflow.domain;


import lombok.Data;

@Data
public class ClaimBill {

    /**账单金额*/
    private String billAmt;

    /**合理费用*/
    private String reasonableAmt;

    /**自费金额*/
    private String ownAmt;

    /**部分自费(H)*/
    private String subownAmt;

    /**不合理金额*/
    private String totalImmoAmt;

    /**先期给付*/
    private String preAmt;

    /**序号*/
    private String seqno;

    /**账单号*/
    private String billno;

    /**就诊日期*/
    private String treatDate;

    /**类型*/
    private String therapyType;

    /**治疗医院*/
    private String hospitalName;

    /**临床诊断*/
    private String diseaseDessc;
}
