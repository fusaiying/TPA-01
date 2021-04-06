package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GCCServiceInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //服务项目编号
    private String healthServiceItemCode;
    //服务项目名称
    private String healthServiceItemName;
    //等待标志
    private String passedWaitPeriodFlag;
    //服务等待期
    private String waitPeriod;
    //服务等待期单位
    private String waitPeriodTypeCode;
    //是否受理
    private String isAccept;
    //责任次数
    private String maxServiceTimes;
    //剩余次数
    private String leftServiceTimes;

    private String passedWaitPeriodDesc;
    //已使用次数
    private String currentServiceTimes;

}
