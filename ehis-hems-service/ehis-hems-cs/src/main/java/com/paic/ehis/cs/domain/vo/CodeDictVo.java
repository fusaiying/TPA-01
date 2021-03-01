package com.paic.ehis.cs.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;

public class CodeDictVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "code")
    private String complaintBusinessCode;

    @Excel(name = "中文描述")
    private String complaintBusinessName;

    @Excel(name = "对应投保来源code")
    private String insuranceSourceCode;

    @Excel(name = "投保来源描述")
    private String insuranceSourceName;

    private String parCodeType;

    private String subCodeType;

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

    public String getParCodeType() {
        return parCodeType;
    }

    public void setParCodeType(String parCodeType) {
        this.parCodeType = parCodeType;
    }

    public String getSubCodeType() {
        return subCodeType;
    }

    public void setSubCodeType(String subCodeType) {
        this.subCodeType = subCodeType;
    }
}
