package com.paic.ehis.cs.domain.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class QualityAcceptVo1 {
    private String workOrderNo;
    private String inspectionId;
    private String organCode;
    private String modifyBy;
    private String endDate;
    private String status;
    private String result;
    private String createdBy;


}
