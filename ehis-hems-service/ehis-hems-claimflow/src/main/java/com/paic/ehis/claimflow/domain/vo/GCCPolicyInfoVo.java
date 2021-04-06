package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCPolicyInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //保单号
    private String polno;
    //客户号
    private String customerNo;
    //被保人名称
    private String insName;
    //年龄
    private String age;
    //特别约定
    private String polDesc;
    //生效日
    private String effDate;
    //满期日
    private String matuDate;
    //保单年度
    private String polYear;
    //保单下理赔次数
    private Integer claimTimes;
    //险种信息
    private List<GCCPlan> planList;
}
