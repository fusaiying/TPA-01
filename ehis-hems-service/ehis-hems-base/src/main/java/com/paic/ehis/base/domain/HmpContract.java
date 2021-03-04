package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 供应商合约对象 hpcontract
 * 
 * @author sino
 * @date 2020-09-23
 */
public class HmpContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 合约编码 */
    private String contractno;
    /** 合约名称 */
    private String contractname;
    /** 合约类型 */
    private String contracttype;
    /** 合约对象编码 */
    private String servcomno;
    /** 合约对象名称 */
    private String contractobjectname;
    /** 合约签订日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signdate;
    /** 合约状态 */
    private String constate;
    /** 合约生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cvalidate;
    /** 合约期限类型 */
    private String contracttermtype;
    /** 合约截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expirydate;
    /** 提前协商天数 */
    private double contractadvance;
    /** 合同号 */
    private String contractversion;
    /** 合同分类 */
    private String contractsort;
    /** 合约备注 */
    private String remark;
    /** 备用字段1 */
    private String alternatefield1;
    /** 备用字段2 */
    private String alternatefield2;
    /** 操作员 */
    private String operator;
    /** 录入日期 */
    private Date makedate;
    /** 录入时间 */
    private String maketime;
    /** 最后一次修改人 */
    private String modifyoperator;
    /** 最后一次修改日期 */
    private Date modifydate;
    /** 最后一次修改时间 */
    private String modifytime;

    /** 类型封装 合约签订日期开始时间 合约结束时间 */
    private String signdatebegin;
    private String signdateend;

    private String cvalidatebegin;
    private String cvalidateend;

    private String expirydatebegin;
    private String expirydateend;

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        this.contracttype = contracttype;
    }

    public String getServcomno() {
        return servcomno;
    }

    public void setServcomno(String servcomno) {
        this.servcomno = servcomno;
    }

    public String getContractobjectname() {
        return contractobjectname;
    }

    public void setContractobjectname(String contractobjectname) {
        this.contractobjectname = contractobjectname;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public String getConstate() {
        return constate;
    }

    public void setConstate(String constate) {
        this.constate = constate;
    }

    public Date getCvalidate() {
        return cvalidate;
    }

    public void setCvalidate(Date cvalidate) {
        this.cvalidate = cvalidate;
    }

    public String getContracttermtype() {
        return contracttermtype;
    }

    public void setContracttermtype(String contracttermtype) {
        this.contracttermtype = contracttermtype;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public double getContractadvance() {
        return contractadvance;
    }

    public void setContractadvance(double contractadvance) {
        this.contractadvance = contractadvance;
    }

    public String getContractversion() {
        return contractversion;
    }

    public void setContractversion(String contractversion) {
        this.contractversion = contractversion;
    }

    public String getContractsort() {
        return contractsort;
    }

    public void setContractsort(String contractsort) {
        this.contractsort = contractsort;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getModifyoperator() {
        return modifyoperator;
    }

    public void setModifyoperator(String modifyoperator) {
        this.modifyoperator = modifyoperator;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getSigndatebegin() {
        return signdatebegin;
    }

    public void setSigndatebegin(String signdatebegin) {
        this.signdatebegin = signdatebegin;
    }

    public String getSigndateend() {
        return signdateend;
    }

    public void setSigndateend(String signdateend) {
        this.signdateend = signdateend;
    }

    public String getCvalidatebegin() {
        return cvalidatebegin;
    }

    public void setCvalidatebegin(String cvalidatebegin) {
        this.cvalidatebegin = cvalidatebegin;
    }

    public String getCvalidateend() {
        return cvalidateend;
    }

    public void setCvalidateend(String cvalidateend) {
        this.cvalidateend = cvalidateend;
    }

    public String getExpirydatebegin() {
        return expirydatebegin;
    }

    public void setExpirydatebegin(String expirydatebegin) {
        this.expirydatebegin = expirydatebegin;
    }

    public String getExpirydateend() {
        return expirydateend;
    }

    public void setExpirydateend(String expirydateend) {
        this.expirydateend = expirydateend;
    }

    @Override
    public String toString() {
        return "HmpContract{" +
                "contractno='" + contractno + '\'' +
                ", contractname='" + contractname + '\'' +
                ", contracttype='" + contracttype + '\'' +
                ", servcomno='" + servcomno + '\'' +
                ", contractobjectname='" + contractobjectname + '\'' +
                ", signdate=" + signdate +
                ", constate='" + constate + '\'' +
                ", cvalidate=" + cvalidate +
                ", contracttermtype='" + contracttermtype + '\'' +
                ", expirydate=" + expirydate +
                ", contractadvance=" + contractadvance +
                ", contractversion='" + contractversion + '\'' +
                ", contractsort='" + contractsort + '\'' +
                ", remark='" + remark + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", alternatefield2='" + alternatefield2 + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", modifyoperator='" + modifyoperator + '\'' +
                ", modifydate=" + modifydate +
                ", modifytime='" + modifytime + '\'' +
                ", signdatebegin='" + signdatebegin + '\'' +
                ", signdateend='" + signdateend + '\'' +
                ", cvalidatebegin='" + cvalidatebegin + '\'' +
                ", cvalidateend='" + cvalidateend + '\'' +
                ", expirydatebegin='" + expirydatebegin + '\'' +
                ", expirydateend='" + expirydateend + '\'' +
                '}';
    }
}
