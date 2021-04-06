package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    //分单号
    private String certno;
    //险种名称
    private String planName;
    //被保人姓名
    private String insName;
    //险种特别约定 没有
    private String planSpecialDesc;
    //险种代码
    private String planCode;
}
