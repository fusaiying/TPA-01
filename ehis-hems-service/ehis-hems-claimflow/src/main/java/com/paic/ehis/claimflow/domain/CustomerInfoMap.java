package com.paic.ehis.claimflow.domain;

import lombok.Data;

@Data
public class CustomerInfoMap {
    /**承保机构名称*/
    private String liveBranch;

    /**保单号*/
    private String polno;

    /**工作单位*/
    private String customerOrganization;

    private String customerNo;

    /**保单主招揽业务员*/
    private String agentNo;

    /**年龄*/
    private int age;
    private String birthday;

    /**职业*/
    private String profession;

}
