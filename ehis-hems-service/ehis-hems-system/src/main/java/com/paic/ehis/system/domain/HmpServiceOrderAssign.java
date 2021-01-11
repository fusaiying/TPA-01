package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 分诊信息
 * @author zkrbailz
 * @date 2020-11-17
 */
public class HmpServiceOrderAssign extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分诊编码
     */
    private String assigncode;

    /**
     * 工单编码
     */
    private String ordercode;

    /**
     * 分诊方式
     */
    private String assigntype;

    /**
     * 分诊状态
     */
    private String status;

    /**
     * 备用字段1
     */
    private String alternatefield1;

    /**
     * 创建人
     */
    private String createby;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改人
     */
    private String updateby;

    /**
     * 修改时间
     */
    private Date updatetime;


    public String getAssigncode() {
        return assigncode;
    }

    public void setAssigncode(String assigncode) {
        this.assigncode = assigncode;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getAssigntype() {
        return assigntype;
    }

    public void setAssigntype(String assigntype) {
        this.assigntype = assigntype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlternatefield1() {
        return alternatefield1;
    }

    public void setAlternatefield1(String alternatefield1) {
        this.alternatefield1 = alternatefield1;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "HmpServiceOrderAssign{" +
                "assigncode='" + assigncode + '\'' +
                ", ordercode='" + ordercode + '\'' +
                ", assigntype='" + assigntype + '\'' +
                ", status='" + status + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", createby='" + createby + '\'' +
                ", createtime=" + createtime +
                ", updateby='" + updateby + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }
}
