package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 接口信息
 * 
 */
@Data
@ToString
public class InterfaceMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long seqId;
    /** 接口编号 */
    private String interfaceCode;
    /** 应用角色 */
    private String applicationRole;
    /** 参数 */
    private String parameters;
    /** 主体 */
    private String body;
    /** 结果 */
    private String result;
    /** 是否成功 */
    private String successFlag;
    /** 处理状态 */
    private String handleFlag;
    /** 备用状态 */
    private String status;
    /** 创建人 */
    private String createdBy;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /** 更新人 */
    private String updatedBy;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

}
