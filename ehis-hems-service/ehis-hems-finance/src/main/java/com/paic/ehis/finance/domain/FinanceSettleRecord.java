package com.paic.ehis.finance.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;

import lombok.Data;
/**
 * 结算操作记录对象 finance_settle_record
 * 
 * @author sino
 * @date 2021-01-30
 */
@Data
public class FinanceSettleRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 操作ID */
    private Long recordId;

    /** 收款类型 (01-TPA服务费、02-代垫款) */
    @Excel(name = "收款类型 (01-TPA服务费、02-代垫款)")
    private String taskType;

    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 流程节点 (01-任务发起、02-任务确认、03-任务核销) */
    @Excel(name = "流程节点 (01-任务发起、02-任务确认、03-任务核销)")
    private String operation;

    /** 流程节点操作人 */
    @Excel(name = "流程节点操作人")
    private String operator;

    /** 是否历史节点（Y-是，N-否） */
    @Excel(name = "是否历史节点", readConverterExp = "Y=-是，N-否")
    private String historyFlag;

    /** 上一环节流程记录ID */
    @Excel(name = "上一环节流程记录ID")
    private Long orgRecordId;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;
}
