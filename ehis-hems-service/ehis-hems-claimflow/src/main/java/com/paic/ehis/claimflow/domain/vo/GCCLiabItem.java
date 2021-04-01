package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCLiabItem implements Serializable {
    private static final long serialVersionUID = 1L;
    //剩余天数
    private String remainingDays;
    //实际剩余天数
    private String liabItemName;
    //次限额
    private String limitAmtOneTime;
    //责任大类
    private String liabItemDesc;
    //剩余次数
    private String remainingTimes;
    //原始次数
    private String originalTimes;
    //单项剩余保额
    private String coverageAmount;
    //保单剩余免赔额
    private String remainingremitamt;
    //单项原始保额
    private String originalAmount;
    //日津贴
    private String dailySubsidyAmt;
    //免赔天数
    private String remitDay;
    //险种
    private String planCode;
    //责任明细
    private List<GCCLiabSubItem> liabSubItemList;
}
