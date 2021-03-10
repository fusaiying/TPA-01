package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ServiceProcessingVo  extends BaseEntity {


    private static final long serialVersionUID = 1L;
    /** 处理流水号 */
    @Excel(name="流水号")
    private Long handleId;

    /** 处理类型 处理；取消；转办；协办；质检 */
    @Excel(name = "处理类型 处理；取消；转办；协办；质检")
    private String handleType;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 标志 */
    @Excel(name = "标志")
    private String flag;

    /** 状态 */
    @Excel(name = "状态")
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
    /**业务处理情况*/
    private String businessProcess;

    /**客户反馈*/
    private String customerFeedback;

    /**结案类型*/
    private String closeType;

    /**安抚或通融发生费用成本*/
    private String costsIncurred;
    /**判断暂存保存标记*/
    private String sign;

}
