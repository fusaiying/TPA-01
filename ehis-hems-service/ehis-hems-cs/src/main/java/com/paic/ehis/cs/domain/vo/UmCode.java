package com.paic.ehis.cs.domain.vo;

import lombok.Data;

@Data
public class UmCode {
    private static final long serialVersionUID = 1L;
    //电话中心业务流水号
    private String key;
    /** 工单编号 */
    private String value;
}
