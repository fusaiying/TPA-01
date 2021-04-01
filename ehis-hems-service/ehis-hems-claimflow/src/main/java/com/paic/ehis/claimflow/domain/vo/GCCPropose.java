package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GCCPropose implements Serializable {
    private static final long serialVersionUID = 1L;
    //保障区域
    private String coverageAreaName;
    //门诊已就诊次数
    private String cntCount;
    //保单免赔额
    private String remitAmt;
    //保单剩余免赔额
    private String hosremainingremitamt;
    //特定医院赔付比例
    private String specialHospitalRate;
    // 计划序号
    private String gradeLevel;
}
