package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCOtherInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //特别约定
    private String polDesc;
    //生效日
    private String effDate;
    //满期日
    private String matuDate;
}
