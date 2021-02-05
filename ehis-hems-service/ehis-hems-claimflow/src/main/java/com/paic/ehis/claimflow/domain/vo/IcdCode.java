package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 次要诊断
 */
//
@Data
public class IcdCode implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 次要诊断 */
    private String icdCode;
}
