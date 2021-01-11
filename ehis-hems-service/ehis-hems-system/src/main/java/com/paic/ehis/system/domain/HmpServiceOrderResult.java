package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 服务申请信息
 * @author zkrbailz
 * @date 2020-11-17
 */
public class HmpServiceOrderResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 结果编码
     */
    private String resultcode;

    /**
     * 工单编码
     */
    private String ordercode;

    /**
     * 实施内容
     */
    private String resultcontent;

    /**
     * 实施状态
     */
    private String status;

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


    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getResultcontent() {
        return resultcontent;
    }

    public void setResultcontent(String resultcontent) {
        this.resultcontent = resultcontent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "HmpServiceOrderResult{" +
                "resultcode='" + resultcode + '\'' +
                ", ordercode='" + ordercode + '\'' +
                ", resultcontent='" + resultcontent + '\'' +
                ", status='" + status + '\'' +
                ", createby='" + createby + '\'' +
                ", createtime=" + createtime +
                ", updateby='" + updateby + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }
}
