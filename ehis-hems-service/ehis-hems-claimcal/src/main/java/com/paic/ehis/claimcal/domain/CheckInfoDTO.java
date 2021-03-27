package com.paic.ehis.claimcal.domain;


import lombok.Data;

import java.util.Date;

/**
 * @Author xicc
 * @create 2021/3/24
 */
@Data
public class CheckInfoDTO {

    private Date treatmentStartDate;
    private Date validStartDate;
    private String gender;
    private String hospitalcode;

    private String riskCode;
    private String planCode;
    private String dutyCode;


    public CheckInfoDTO(Date treatmentStartDate, Date validStartDate, String gender, String hospitalcode, String riskCode, String planCode, String dutyCode) {
        this.treatmentStartDate = treatmentStartDate;
        this.validStartDate = validStartDate;
        this.gender = gender;
        this.hospitalcode = hospitalcode;
        this.riskCode = riskCode;
        this.planCode = planCode;
        this.dutyCode = dutyCode;
    }

    public CheckInfoDTO() {
    }
}
