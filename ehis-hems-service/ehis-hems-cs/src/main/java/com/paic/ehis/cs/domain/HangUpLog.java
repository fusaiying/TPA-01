package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HangUpLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 挂起流水号 */
    private Long hangUpId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 挂起状态 */
    @Excel(name = "挂起状态")
    private String hangState;

    /** 挂起原因 */
    @Excel(name = "挂起原因")
    private String hangReason;

    /** 挂起时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "挂起时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date upTime;

    /** 解挂原因 */
    @Excel(name = "解挂原因")
    private String unhangReason;

    /** 解挂时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "解挂时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date downTime;

    /** 挂到解时长 */
    @Excel(name = "挂到解时长")
    private BigDecimal times;

    /** 数据状态 Y-有效；N-无效 */
    @Excel(name = "数据状态 Y-有效；N-无效")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
