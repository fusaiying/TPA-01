package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务工单与处理人轨迹对象 hmp_order_user
 * 
 * @author sino
 * @date 2020-11-18
 */
public class HmpOrderUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialnum;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String ordercode;

    /** 用户编码 */
    @Excel(name = "用户编码")
    private String userId;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String status;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String maketime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String modifyoperator;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifydate;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String modifytime;

    public void setSerialnum(String serialnum) 
    {
        this.serialnum = serialnum;
    }

    public String getSerialnum() 
    {
        return serialnum;
    }
    public void setOrdercode(String ordercode) 
    {
        this.ordercode = ordercode;
    }

    public String getOrdercode() 
    {
        return ordercode;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setMakedate(Date makedate) 
    {
        this.makedate = makedate;
    }

    public Date getMakedate() 
    {
        return makedate;
    }
    public void setMaketime(String maketime) 
    {
        this.maketime = maketime;
    }

    public String getMaketime() 
    {
        return maketime;
    }
    public void setModifyoperator(String modifyoperator) 
    {
        this.modifyoperator = modifyoperator;
    }

    public String getModifyoperator() 
    {
        return modifyoperator;
    }
    public void setModifydate(Date modifydate) 
    {
        this.modifydate = modifydate;
    }

    public Date getModifydate() 
    {
        return modifydate;
    }
    public void setModifytime(String modifytime) 
    {
        this.modifytime = modifytime;
    }

    public String getModifytime() 
    {
        return modifytime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("serialnum", getSerialnum())
            .append("ordercode", getOrdercode())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifyoperator", getModifyoperator())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .toString();
    }
}
