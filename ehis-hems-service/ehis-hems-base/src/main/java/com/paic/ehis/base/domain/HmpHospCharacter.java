package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 医疗特色信息 hpcontract
 *
 * @author sino
 * @date 2020-10-28
 */
public class HmpHospCharacter extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /** 合作机构编码 */
    private String servcomno;
    /** 床位数 */
    private double bednum;
    /** 日均门诊量 */
    private double outpatient;
    /** 年均出院人数 */
    private double average;
    /** 科室 */
    private String hospitaldeps;
    /** 特色诊疗项目 */
    private String feature;
    /** 工作时间 */
    private String workingtime;
    /** 备用字段1 */
    private String alternatefield1;
    /** 备用字段2 */
    private String alternatefield2;
    /** 操作员 */
    private String operator;
    /** 入机日期 */
    private Date makedate;
    /** 入机时间 */
    private String maketime;

    public String getServcomno() {
        return servcomno;
    }

    public void setServcomno(String servcomno) {
        this.servcomno = servcomno;
    }

    public double getBednum() {
        return bednum;
    }

    public void setBednum(double bednum) {
        this.bednum = bednum;
    }

    public double getOutpatient() {
        return outpatient;
    }

    public void setOutpatient(double outpatient) {
        this.outpatient = outpatient;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getHospitaldeps() {
        return hospitaldeps;
    }

    public void setHospitaldeps(String hospitaldeps) {
        this.hospitaldeps = hospitaldeps;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getWorkingtime() {
        return workingtime;
    }

    public void setWorkingtime(String workingtime) {
        this.workingtime = workingtime;
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

    @Override
    public String toString() {
        return "HmpHospCharacter{" +
                "servcomno='" + servcomno + '\'' +
                ", bednum=" + bednum +
                ", outpatient=" + outpatient +
                ", average=" + average +
                ", hospitaldeps='" + hospitaldeps + '\'' +
                ", feature='" + feature + '\'' +
                ", workingtime='" + workingtime + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", alternatefield2='" + alternatefield2 + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                '}';
    }
}
