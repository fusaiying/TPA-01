package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCLiabSubItem implements Serializable {
    private static final long serialVersionUID = 1L;
    //剩余天数
    private String remainingDays;
    //实际责任名称
    private String liabSubItemNameAlias;
    //赔付比例
    private String payRate;
    //次限额
    private String limitAmtOneTime;
    //等待期
    private String waitTime;
    //共用剩余保额
    private String remainingCommonInsurance;
    //剩余次数
    private String remainingTimes;
    //原始次数
    private String originalTimes;
    //单项剩余保额
    private String coverageAmount;
    //单项原始保额
    private String originalAmount;
    //赔付比例
    private String showPersonPayRateListGCC;
    //日津贴
    private String dailySubsidyAmt;
    //免赔天数
    private String remitDay;
    //责任项目
    private String liabSubItemDesc;
    //共用原始保额
    private String originalCommonInsurance;
    //原始天数
    private String originalDays;
}
