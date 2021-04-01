package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 提供GCC理赔信息查询接口 案件对象 事故信息
 */
@Data
@ToString
public class GCCAccidentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //境外出险
    private String overseasOccurFlag;
    //报案时间
    private String notiDate;
    //事故地点
    private String acciPlace;
    //事故者现状
    private String statusStageName;

}
