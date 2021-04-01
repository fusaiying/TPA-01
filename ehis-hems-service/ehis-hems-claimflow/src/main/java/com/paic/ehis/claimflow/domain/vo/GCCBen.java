package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCBen implements Serializable {
    private static final long serialVersionUID = 1L;
    //险种名称
    private String planName;
    //分单号
    private String certno;
}
