package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ClaimCaseFilingListVO extends BaseEntity {

    /** 盒号 */
    @Excel(name = "盒号")
    private String caseBoxNo;

    /** 批次号 */
    private String batchNo;

    /** 报案号起 */
    @Excel(name = "报案号起")
    private String rptStartNo;

    /** 报案号止 */
    @Excel(name = "报案号止")
    private String rptEndNo;

    /** 理赔类型 */
    @Excel(name = "理赔类型")
    private String claimType;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /**
     * 登录机构编码
     */
    @Excel(name = "登录机构编码")
    private String deptCode;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateTime;

    /**
     * 已归档案件数量
     */
    @Excel(name = "已归档案件数量")
    private String casenum;

    /** 更新开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateStartTime;

    /** 更新结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateEndTime;

    /** 报案号 */
    private String rptNo;

    public String getCaseBoxNo() {
        return caseBoxNo;
    }

    public void setCaseBoxNo(String caseBoxNo) {
        this.caseBoxNo = caseBoxNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRptStartNo() {
        return rptStartNo;
    }

    public void setRptStartNo(String rptStartNo) {
        this.rptStartNo = rptStartNo;
    }

    public String getRptEndNo() {
        return rptEndNo;
    }

    public void setRptEndNo(String rptEndNo) {
        this.rptEndNo = rptEndNo;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCasenum() {
        return casenum;
    }

    public void setCasenum(String casenum) {
        this.casenum = casenum;
    }

    public Date getUpdateStartTime() {
        return updateStartTime;
    }

    public void setUpdateStartTime(Date updateStartTime) {
        this.updateStartTime = updateStartTime;
    }

    public Date getUpdateEndTime() {
        return updateEndTime;
    }

    public void setUpdateEndTime(Date updateEndTime) {
        this.updateEndTime = updateEndTime;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }
}
