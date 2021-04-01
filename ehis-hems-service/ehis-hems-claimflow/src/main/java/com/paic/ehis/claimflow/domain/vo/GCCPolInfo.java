package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCPolInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //生效日
    private String effDate;
    //特别约定
    private String polDesc;
    //保单号
    private String polno;
    //险种名称
    private String planCode;
    //满期日
    private String matuDate;
    //保单年度
    private String polYear;

}
