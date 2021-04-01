package com.paic.ehis.cs.domain.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class ComplaintErrorRateVO {
    //投诉差错率统计

    @Excel(name = "结案时间范围")
    private Date closingTime;

    @Excel(name = "服务提供机构")
    private String organCode;

    @Excel(name = "处理人")
    private String updatedBy;

    @Excel(name = "抽检数量")
    private String samplingQuantity;

    @Excel(name = "差错数量")
    private String errorsNumber;

    @Excel(name = "差错率")
    private String errorsRate;
}
