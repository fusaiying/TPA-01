package com.paic.ehis.claimflow.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author fusaiying
 */
@Data
public class BatchNoRptNoDTO{

    /**
     * 就诊医院ID
     */
    @NotNull
    private String provider;

    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private Date billDate;

    /**
     * 案件数量
     */
    @NotNull
    private String batchCount;

    /**
     * 批次总金额
     */
    @NotNull
    private String batchAmount;

    /**
     * 交单机构编码
     */
    @NotNull
    private String branchRegion;

    /**
     * 是否收到发票
     */
    @NotNull
    private String isReceiptSubmit;

    /**
     * 收单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private Date receiveDate;

    /**
     * 批次是否单张发票
     */
    @NotNull
    private String directReceiptSign;

    /**
     * 案件第五位标识码
     */
    private String caseFlag;

    @Override
    public String toString() {
        return "BatchNoRptNoDTO{" +
                "provider='" + provider + '\'' +
                ", billDate=" + billDate +
                ", batchCount='" + batchCount + '\'' +
                ", batchAmount='" + batchAmount + '\'' +
                ", branchRegion='" + branchRegion + '\'' +
                ", isReceiptSubmit='" + isReceiptSubmit + '\'' +
                ", receiveDate=" + receiveDate +
                ", directReceiptSign='" + directReceiptSign + '\'' +
                ", caseFlag='" + caseFlag + '\'' +
                '}';
    }
}
