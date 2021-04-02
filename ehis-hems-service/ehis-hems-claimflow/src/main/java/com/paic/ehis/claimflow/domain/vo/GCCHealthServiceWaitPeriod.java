package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCHealthServiceWaitPeriod implements Serializable {
    private static final long serialVersionUID = 1L;
    //险种号
    private String planCode;
    //是否已过等待期
    private String passedWaitPeriodFlag;
    //等待期
    private String waitPeriod;
    //等待期单位
    private String waitPeriodTypeCode;

    private String serviceItemCode;
}
