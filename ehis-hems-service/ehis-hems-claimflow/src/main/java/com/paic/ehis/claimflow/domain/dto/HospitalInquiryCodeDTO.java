package com.paic.ehis.claimflow.domain.dto;

import java.io.Serializable;

public class HospitalInquiryCodeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rptNo;

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    @Override
    public String toString() {
        return "HospitalInquiryCodeDTO{" +
                "rptNo='" + rptNo + '\'' +
                '}';
    }
}
