package com.paic.ehis.claimcal.domain;


import lombok.Data;

import java.util.Date;

/**
 * @Author xicc
 * @create 2021/3/24
 */
@Data
public class QueryUsedDTO {

    private String policyNo;

    private String policyItemNo;

    private String ruleElement;

    private String elementUnit;

    private String riskCode;

    private String planCode;

    private String dutyCode;

    private String dutyDetailCode;

    private String feeitemCode;

    private Date startDate;

    private Date endDate;

    private String hospitalCode;

    private Date treatmentStartDate;

    private String department;

    public QueryUsedDTO(String policyNo, String policyItemNo, String ruleElement, String elementUnit, String riskCode, String planCode, String dutyCode, String dutyDetailCode, String feeitemCode, Date startDate, Date endDate, String hospitalCode, Date treatmentStartDate, String department) {
        this.policyNo = policyNo;
        this.policyItemNo = policyItemNo;
        this.ruleElement = ruleElement;
        this.elementUnit = elementUnit;
        this.riskCode = riskCode;
        this.planCode = planCode;
        this.dutyCode = dutyCode;
        this.dutyDetailCode = dutyDetailCode;
        this.feeitemCode = feeitemCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hospitalCode = hospitalCode;
        this.treatmentStartDate = treatmentStartDate;
        this.department = department;
    }
}
