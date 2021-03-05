package com.paic.ehis.cs.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.io.Serializable;

public class CodeDictDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String complaintBusinessCode;

    private String complaintBusinessName;

    private String complaintBusinessType;

    private String insuranceSourceCode;

    private String insuranceSourceName;

    private String insuranceSourceType;

    public String getComplaintBusinessCode() {
        return complaintBusinessCode;
    }

    public void setComplaintBusinessCode(String complaintBusinessCode) {
        this.complaintBusinessCode = complaintBusinessCode;
    }

    public String getComplaintBusinessName() {
        return complaintBusinessName;
    }

    public void setComplaintBusinessName(String complaintBusinessName) {
        this.complaintBusinessName = complaintBusinessName;
    }

    public String getComplaintBusinessType() {
        return complaintBusinessType;
    }

    public void setComplaintBusinessType(String complaintBusinessType) {
        this.complaintBusinessType = complaintBusinessType;
    }

    public String getInsuranceSourceCode() {
        return insuranceSourceCode;
    }

    public void setInsuranceSourceCode(String insuranceSourceCode) {
        this.insuranceSourceCode = insuranceSourceCode;
    }

    public String getInsuranceSourceName() {
        return insuranceSourceName;
    }

    public void setInsuranceSourceName(String insuranceSourceName) {
        this.insuranceSourceName = insuranceSourceName;
    }

    public String getInsuranceSourceType() {
        return insuranceSourceType;
    }

    public void setInsuranceSourceType(String insuranceSourceType) {
        this.insuranceSourceType = insuranceSourceType;
    }
}
