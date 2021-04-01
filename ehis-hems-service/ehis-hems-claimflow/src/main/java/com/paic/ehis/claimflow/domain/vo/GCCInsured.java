package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCInsured implements Serializable {
    private static final long serialVersionUID = 1L;
    //分单号
    private String certno;
    //被保人名称
    private String insName;
}
