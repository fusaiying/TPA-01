package com.paic.ehis.cs.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ConsultationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 协办流水号 */
    private Long collaborativeId;

    /** 流转日期始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "流转日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTimeStart;

    /** 流转日期末 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "流转日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTimeEnd;

    /** 流转日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "流转日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 处理状态 意见处理等待 */
    @Excel(name = "处理状态 意见处理等待")
    private String handleState;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 服务项目 */
    @Excel(name = "服务项目")
    private String itemCode;

    /** 当前操作人 */
    @Excel(name = "当前操作人")
    private String operation ;
}

