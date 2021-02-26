package com.paic.ehis.claimflow.domain.interfaceclass;

import lombok.Data;

import java.util.List;

@Data
public class RptNoAndFilingNo {
    /**归档号*/
    private String filingNo;
    /**报案号集合*/
    private String rptNo;
}
