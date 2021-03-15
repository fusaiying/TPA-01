package com.paic.ehis.cs.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 前端页面公共查询对象
 * by Louis
 */
@Data
public class WorkOrderQueryDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号  */
    private String workOrderNo;
    /** 业务类型  */
    private String businessType;
    /** 保单号  */
    private String policyNo;
    /** 分单号 */
    private String policyItemNo;
    /** 渠道 */
    private String channelCode;
    /** 出单机构 */
    private String organCode;
    /** 服务项目 */
    private String serviceItem;
    private String itemCode;
    private String acceptBy;
    /** 受理开始日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acceptStartDate;
    /** 受理结束日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acceptEndDate;
    private String updateBy;
    /** 处理开始日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateStartDate;
    /** 处理结束日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateEndDate;
    private String modifyBy;
    /** 处理开始日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyStartDate;
    /** 处理结束日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyEndDate;
    /** 投保人 */
    private String holderName;
    /** 被保人 */
    private String insuredName;
    /** 被保人证件号 */
    private String idNumber;
    /** 被保人联系电话 */
    private String mobilePhone;
    /** 预约开始日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentStartDate;
    /** 预约结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentEndDate;
    /** 优先级 */
    private String priorityLevel;
    /** VIP */
    private String vipFlag;
    /** 状态 */
    private String acceptStatus;
    private String status;
    /** 结案开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endCaseStartTime;
    /** 结案结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endCaseEndTime;

    /** 查询多状态 */
    private List<String> acceptStatusList;
    /** 批量获取 */
    private List<String> workOrderNoList;
    /** 多个业务类型 */
    private List<String> businessTypeList;
}
