package com.paic.ehis.base.domain;

import java.util.Date;

/**
 * 合约与服务项目关联表
 */
public class HmpServConpro {
    private static final long serialVersionUID = 1L;

    /**
     * 合约编码
     */
    private String contactno;

    /**
     * 服务项目编码
     */
    private String projectcode;

    /**
     * 结算方式
     */
    private String payway;

    /**
     * 合约价格
     */
    private Double contactprice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 创建日期
     */
    private Date makedate;

    /**
     * 创建时间
     */
    private String maketime;

    /**
     * 修改日期
     */
    private Date modifieddate;

    /**
     * 修改时间
     */
    private String modifiedtime;

    /**
     * 备用字段1
     */
    private String alternatefield1;

    /**
     * 备用字段2
     */
    private String alternatefield2;

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public Double getContactprice() {
        return contactprice;
    }

    public void setContactprice(Double contactprice) {
        this.contactprice = contactprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public String getMaketime() {
        return maketime;
    }

    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(String modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public String getAlternatefield1() {
        return alternatefield1;
    }

    public void setAlternatefield1(String alternatefield1) {
        this.alternatefield1 = alternatefield1;
    }

    public String getAlternatefield2() {
        return alternatefield2;
    }

    public void setAlternatefield2(String alternatefield2) {
        this.alternatefield2 = alternatefield2;
    }

    @Override
    public String toString() {
        return "HmpServConpro{" +
                "contactno='" + contactno + '\'' +
                ", projectcode='" + projectcode + '\'' +
                ", payway='" + payway + '\'' +
                ", contactprice=" + contactprice +
                ", remark='" + remark + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", modifieddate=" + modifieddate +
                ", modifiedtime='" + modifiedtime + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", alternatefield2='" + alternatefield2 + '\'' +
                '}';
    }
}
