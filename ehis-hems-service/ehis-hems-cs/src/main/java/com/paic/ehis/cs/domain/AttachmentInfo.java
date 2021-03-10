package com.paic.ehis.cs.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 受理详情 对象 AttachmentInfo
 * 
 * @author sino
 * @date 2021-01-22
 */
@Data
public class AttachmentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String attachmentId ;
    /** 业务号;是什么环节的附件 */
    private String businessNo ;
    /** 子业务号 */
    private String subBusinessNo ;
    /** 业务类型 */
    private String businessType ;
    /** 文件系统中编号;外部系统主键 */
    private String filenetid ;
    /** 附件名称 */
    private String attachmentName ;
    /** 所在地址 */
    private String attachmentUrl ;
    /** 附件类型 */
    private String attachmentType ;
    /** 附件状态 */
    private String state ;
    /** 备注 */
    private String remark ;
    /** 排序 */
    private Integer orderId ;
    /** 状态 */
    private String status ;
    /** 上传人 */
    private String uplodBy ;
    /** 上传时间 */
    private Date uploadTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;
}
