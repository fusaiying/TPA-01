package com.paic.ehis.claimflow.domain;

import lombok.Data;

@Data
public class AccidentInfoMap {

    /**境外出险*/
    private String overseasOccurFlag;

    /**报案时间*/
    private String notiDate;

    /**事故地点*/
    private String acciPlace;

    /**批文详细内容*/
    private String statusStageName;
}
