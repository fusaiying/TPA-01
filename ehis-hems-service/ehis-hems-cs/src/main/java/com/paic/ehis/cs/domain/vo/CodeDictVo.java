package com.paic.ehis.cs.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class CodeDictVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "投保业务类别ID")
    private String complaintBusinessCode;

    @Excel(name = "投保业务类别名称")
    private String complaintBusinessName;

    @Excel(name = "投保来源ID")
    private String insuranceSourceCode;

    @Excel(name = "投保来源名称")
    private String insuranceSourceName;

    private String parCodeType;

    private String subCodeType;
    private String complaintBusinessType;
    private String insuranceSourceType;

}
