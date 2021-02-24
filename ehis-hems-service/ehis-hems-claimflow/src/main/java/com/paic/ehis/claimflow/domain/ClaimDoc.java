package com.paic.ehis.claimflow.domain;


import lombok.Data;

@Data
public class ClaimDoc {

    /**收件人*/
    private String clmdocReceiver;

    /**材料接收日期*/
    private String clmdocDate;

    /**收件机构*/
    private String clmdocBranch;

    /**是否收到材料*/
    private String clmdocSts;

}
