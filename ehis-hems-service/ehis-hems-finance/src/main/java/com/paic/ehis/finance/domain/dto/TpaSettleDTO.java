package com.paic.ehis.finance.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName TpaSettleDTO
 * @Description: TODO TPA服务费结算入参
 * @Author 硠君
 * @Date create in 10:48 2021/2/1
 * @Version 1.0
 **/


public class TpaSettleDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 结算任务号 */
    private String settleTaskNo;

    /** 出单公司编码 */
    private String companyCode;

    /** 结算类型 01-服务费 02-保费比例% */
    private String settlementType;

    /** 险种编码 */
    private String riskCode;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date settleEndDate;

    /** 建立日期 */
    private Date creationStartDate;
    private Date creationEndDate;

    /** 结算状态 (01-待确认、02-待结算、03-已结算) */
    private String settleStatus;

    private String pageStatus;

    private String status;

    public String getSettleTaskNo() {
        return settleTaskNo;
    }

    public void setSettleTaskNo(String settleTaskNo) {
        this.settleTaskNo = settleTaskNo;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public Date getSettleEndDate() {
        return settleEndDate;
    }

    public void setSettleEndDate(Date settleEndDate) {
        this.settleEndDate = settleEndDate;
    }

    public Date getCreationStartDate() {
        return creationStartDate;
    }

    public void setCreationStartDate(Date creationStartDate) {
        this.creationStartDate = creationStartDate;
    }

    public Date getCreationEndDate() {
        return creationEndDate;
    }

    public void setCreationEndDate(Date creationEndDate) {
        this.creationEndDate = creationEndDate;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(String pageStatus) {
        this.pageStatus = pageStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TpaSettleDTO{" +
                "settleTaskNo='" + settleTaskNo + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", settlementType='" + settlementType + '\'' +
                ", riskCode='" + riskCode + '\'' +
                ", settleEndDate=" + settleEndDate +
                ", creationStartDate=" + creationStartDate +
                ", creationEndDate=" + creationEndDate +
                ", settleStatus='" + settleStatus + '\'' +
                ", pageStatus='" + pageStatus + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
