package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 提供GCC理赔信息查询接口中 案件对象 基本信息
 */
@Data
@ToString
public class GCCGeneralInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //拒赔原因
    private String rejeCause;
    // 理赔申请治疗分数
    private String claimOfferedFiles;
    // 领取方式
    private String drawMode;
    // 批单号
    private String rcptno;
    //案件状态
    private String caseStsName;
    //事故日期
    private String acciDate;
    //给付日期
    private String gainedDate;
    //转账失败原因
    private String errorDescription;
    //通知日期
    private String endDate;
    //核赔依据
    private String examComment;
    //事故者姓名
    private String acciName;
    //拒赔金额
    private BigDecimal rejeAmt;
    //案件号
    private String caseno;
    //转账状态
    private String virementStatus;
    //事故经过
    private String acciDesc;
    //是否缺资料
    private String isLackBill;
    //理赔方式 直接理赔/事后理赔
    private String isBalancedDirecily;
    //报案号
    private String docuno;
    private String fetchName;
    private String contractTelno;
    private String fetchId;
}
