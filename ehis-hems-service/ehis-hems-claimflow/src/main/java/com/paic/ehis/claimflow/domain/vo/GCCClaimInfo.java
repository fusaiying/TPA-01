package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提供GCC理赔信息查询接口 案件对象
 */
@Data
@ToString
public class GCCClaimInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //案件基本信息
    private GCCGeneralInfo generalInfoMap;
    //核赔结论
    private String examResult;
    //客户积累理赔额
    private BigDecimal historyAmt;
    //赔付金额
    private BigDecimal paidAmt;
    //事故信息
    private GCCAccidentInfo accidentInfoMap;
    //批文详细内容
    private String entTxt;

}
