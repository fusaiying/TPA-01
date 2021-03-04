package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BatchNoAndCaseNo {

    /**报案号集合*/
    private List<String> docunoList;

    /**批次号*/

    private String batchNo;

    /**生成批次时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createBatchTime;
}
