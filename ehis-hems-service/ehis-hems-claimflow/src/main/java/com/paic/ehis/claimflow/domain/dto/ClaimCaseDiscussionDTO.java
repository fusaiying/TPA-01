package com.paic.ehis.claimflow.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;

import java.io.Serializable;

public class ClaimCaseDiscussionDTO implements Serializable {
    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
    /*交单来源*/
    @Excel(name = "交单来源")
    private String source;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 协谈类型 */
    @Excel(name = "协谈类型")
    private String discType;
    /*提交用户*/
    @Excel(name = "提交用户")
    private String createBy;

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
