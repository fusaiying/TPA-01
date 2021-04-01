package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //保单号
    private String polno;
    //生效日
    private String effDate;
    //满期日
    private String matuDate;
    //保单下理赔次数
    private String claimTimes;
}
