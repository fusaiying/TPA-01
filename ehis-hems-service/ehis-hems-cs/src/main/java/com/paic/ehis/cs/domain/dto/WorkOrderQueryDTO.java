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
 * 工单公共查询对象
 * by Louis
 */
@Data
public class WorkOrderQueryDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**工单号  */
    private String workOrderNo;
    /**业务类型  */
    private String businessType;
    /**保单号  */
    private String policyNo;
    /** 渠道 */
    private String channelCode;
    /** 出单机构 */
    private String organCode;
    /** 服务项目 */
    private String serviceItem;
    /**受理开始日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acceptStartDate;
    /**受理结束日期  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acceptEndDate;
    /** 状态 */
    private String acceptStatus;
    /** 结案开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endCaseStartTime;
    /** 结案结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endCaseEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstEndCaseStartTime;
    /** 结案结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstEndCaseEndTime;

    /** 查询多状态 */
    private List<String> acceptStatusList;
    /** 批量获取 */
    private List<String> workOrderNoList;
    /** 多个业务类型 */
    private List<String> businessTypeList;
}
