package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * HCS预约修改 对象 hcs_modification
 * 
 * @author sino
 * @date 2021-01-21
 */
@Data
public class HcsModification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号 */
    private String workOrderNo;

    /** 修改序列号 */
    private String alterId;

    /** 修改人 */
    @Excel(name = "修改人")
    private String alterBy;

    /** 修改人UM编号 */
    @Excel(name = "修改人UM编号")
    private String umCode;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date alterTime;

    /** 修改渠道 */
    @Excel(name = "修改渠道")
    private String alterChannel;

    /** 修改内容描述 */
    @Excel(name = "修改内容描述")
    private String alterContent;

    /** 关联修改流水号 关联edit_info表中的主键 */
    @Excel(name = "关联修改流水号 关联edit_info表中的主键")
    private String relaEditId;

    /** 来电流水号 */
    @Excel(name = "来电流水号")
    private String callSerialNum;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    //处理优先级 取值 0,1 越大优先级越高
    private String modifyPriority;

    //修改类型
    private String alterType;

    //外部系统主键号
    private String otherNo;

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
