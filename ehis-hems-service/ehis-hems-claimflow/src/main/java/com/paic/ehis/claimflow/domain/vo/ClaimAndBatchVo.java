package com.paic.ehis.claimflow.domain.vo;


import com.paic.ehis.common.core.annotation.Excel;

import java.io.Serializable;

public class ClaimAndBatchVo implements Serializable {
    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 归档号 */
    @Excel(name = "归档号")
    private String filingNo;

    /** 是否申诉案件(01=正常，02=申诉) */
    @Excel(name = "是否申诉案件", readConverterExp = "01=正常,02=申诉")
    private String isAppeal;
    /**
     * 交单来源
     */
    @Excel(name = "交单来源")
    private String source;

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getFilingNo() {
        return filingNo;
    }

    public void setFilingNo(String filingNo) {
        this.filingNo = filingNo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIsAppeal() {
        return isAppeal;
    }

    public void setIsAppeal(String isAppeal) {
        this.isAppeal = isAppeal;
    }

    @Override
    public String toString() {
        return "ClaimAndBatchVo{" +
                "rptNo='" + rptNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", filingNo='" + filingNo + '\'' +
                ", isAppeal='" + isAppeal + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
