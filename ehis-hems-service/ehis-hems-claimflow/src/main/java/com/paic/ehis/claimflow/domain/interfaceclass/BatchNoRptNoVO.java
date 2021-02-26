package com.paic.ehis.claimflow.domain.interfaceclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BatchNoRptNoVO {

    /**报案号和归档号集合*/
    private List<RptNoAndFilingNo> RptNoAndFilingNoList;

    /**批次号*/

    private String batchNo;

    /**生成批次时间*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createBatchTime;
}
