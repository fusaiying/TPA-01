package com.paic.ehis.cs.domain.vo;

import lombok.Data;

@Data
public class QualityFlagVO {
    String workOrderNo;

    String itemCode;

    String modifyBy;

    String updatedBy;

    String businessType;
}
