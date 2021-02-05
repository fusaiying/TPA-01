package com.paic.ehis.claimcal.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AuditWorkPoolDTO extends BaseEntity {

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    //被保人姓名
    private String name;

    private String operator;//操作人

    /** 操作开始时间 */
    private Date updateStartTime;

    /** 操作结束时间 */
    private Date updateEndTime;

    @Override
    public String toString() {
        return "AuditWorkPoolDTO{" +
                "batchNo='" + batchNo + '\'' +
                ", rptNo='" + rptNo + '\'' +
                ", name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", updateStartTime=" + updateStartTime +
                ", updateEndTime=" + updateEndTime +
                '}';
    }
}
