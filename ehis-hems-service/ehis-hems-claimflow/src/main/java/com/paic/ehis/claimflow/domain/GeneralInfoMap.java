package com.paic.ehis.claimflow.domain;


import lombok.Data;

@Data
public class GeneralInfoMap {

    /**拒赔原因*/
    private String rejeCause;

    /**理赔申请资料分数*/
    private String claimOfferedFiles;

    /**领取方式*/
    private String drawMode;

    /**批单号*/
    private String rcptno;

    /**案件状态*/
    private String caseStsName;

    /**事故时间*/
    private String acciDate;

    /**给付日期*/
    private String gainedDate;

    /**转账失败原因*/
    private String errorDescription;

    /**通知日期*/
    private String endDate;

    /**核赔依据*/
    private String examComment;

    /**事故者姓名*/
    private String acciName;

    /**拒赔金额*/
    private String rejeAmt;

    /**案件号*/
    private String caseno;

    /**转账状态*/
    private String virementStatus;

    /**事故经过*/
    private String acciDesc;

    /**是否缺材料*/
    private String isLackBill;

    /**理赔方式*/
    private String isBalancedDirecily;

    /**报案号*/
    private String docuno;


    private String fetchName;


    private String contractTelno;


    private String fetchId;
}
