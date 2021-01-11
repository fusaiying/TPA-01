package com.paic.ehis.system.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecordByBatchnoDto implements Serializable {
    private String conclusion ;//'结论'
    private String nopasstype;//'不通过类型'
    private String priremark;//'备注'
}
