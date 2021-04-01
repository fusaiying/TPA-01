package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCPolCert implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 承保机构名称
     */
    private String regionCodeName;
    /**
     * 客户号
     */
    private String customerNo;
    /**
     * 分单号
     */
    private String certno;
    /**
     * 保单号
     */
    private String polno;
}
