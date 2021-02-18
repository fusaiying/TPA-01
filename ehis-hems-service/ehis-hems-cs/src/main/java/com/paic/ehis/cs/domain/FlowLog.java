package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 信息需求查询对象 demand_query
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class FlowLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String flowId ;
    /** 业务号 */
    private String workOrderNo ;
    /** 子业务号 */
    private String subId ;
    /** 子业务类型 */
    private String subType ;
    /** 状态 */
    private String linkCode ;
    /** 操作 */
    private String operateCode ;
    /** 处理人 */
    private String makeBy ;
    /** UM账号 */
    private String umNum ;
    /** 处理时间 */
    private Date makeTime ;
    /** 说明 */
    private String remarks ;
    /** 处理意见 */
    private String opinion ;
    /** 流转部门;针对当前操作有转办或协办的操作 */
    private String toDepartment ;
    /** 流转原因 */
    private String toReason ;
    /** 是否有附件 */
    private String attachmentFlag ;
    /** 备用状态 */
    private String status ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;

}
