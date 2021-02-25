package com.paic.ehis.claimflow.domain;


import lombok.Data;

@Data
public class Claim {

    private GeneralInfoMap generalInfoMap;

    /**核赔结论*/
    private String examResult;

    /**客户积累理赔额*/
    private double historyAmt;

    /**结算金额*/
    private double paidAmt;

    private AccidentInfoMap accidentInfoMap;

    private String entTxt;


}
