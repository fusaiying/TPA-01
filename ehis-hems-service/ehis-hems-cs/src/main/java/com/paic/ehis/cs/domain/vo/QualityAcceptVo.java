package com.paic.ehis.cs.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

@Data
public class QualityAcceptVo {

    @Excel(name = "工单号")
    private String workOrderNo;

    @Excel(name = "出单机构")
    private String organCode;

    @Excel(name = "服务项目")
    private String itemCode;

    @Excel(name = "结案日期")
    private String endDate;

    @Excel(name = "质检分组")
    private String itemType;

    @Excel(name = "质检人")
    private String updatedBy;

    @Excel(name = "质检日期")
    private String updatedTime;

    @Excel(name = "质检结果")
    private String result;
}
