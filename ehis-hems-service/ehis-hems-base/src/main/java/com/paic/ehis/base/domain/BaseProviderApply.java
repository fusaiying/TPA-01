package com.paic.ehis.base.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_provider_apply(服务商预约信息)对象 base_provider_apply
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseProviderApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务商编码 */
    private String providerCode;

    /** 预约电话 */
    @Excel(name = "预约电话")
    private String appointmentCall;

    /** 本院普通门诊部急诊信息及电话 */
    @Excel(name = "本院普通门诊部急诊信息及电话")
    private String outpatientInfo;

    /** 客户咨询电话 */
    @Excel(name = "客户咨询电话")
    private String hotline;

    /** 门诊提前天数 */
    @Excel(name = "门诊提前天数")
    private String outpatientDay;

    /** 住院提前天数 */
    @Excel(name = "住院提前天数")
    private String hospitalizeDay;

    /** 预约提前时间 */
    @Excel(name = "预约提前时间")
    private List<String> outpatientearly;

    /** 预约提前时间str */
    @Excel(name = "预约提前时间str")
    private String outpatientearlyStr;

    /** 体检确认单发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "体检确认单发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date confirmDeliverytime;

    /** 法定假日营业时间 */
    @Excel(name = "法定假日营业时间")
    private String holidayTime;

    /** 体检确认单发送方式 */
    @Excel(name = "体检确认单发送方式")
    private List<String>sendType;

    /** 是否传真确认 */
    @Excel(name = "体检确认单发送方式str")
    private String sendTypeStr;


    /** 邮件 */
    @Excel(name = "邮件")
    private String mail;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** 是否快递 */
    @Excel(name = "体检报告寄送方式")
    private List<String> sendWay;

    /** 是否自取 */
    @Excel(name = "体检报告寄送方式str")
    private String sendWayStr;

    /** 自取备注信息 */
    @Excel(name = "自取备注信息")
    private String inviteRemark;

    /** 快递备注信息 */
    @Excel(name = "快递备注信息")
    private String fastmailRemark;

    /** 周一至周五门诊开始时间 */
    @Excel(name = "周一至周五门诊开始时间")
    private String workstarttime;

    /** 周一至周五门诊结束时间 */
    @Excel(name = "周一至周五门诊结束时间")
    private String workendtime;

    /** 周一至周五备注信息 */
    @Excel(name = "周一至周五备注信息")
    private String workremark;

    /** 周六门诊开始时间 */
    @Excel(name = "周六门诊开始时间")
    private String saturdaystarttime;

    /** 周六门诊结束时间 */
    @Excel(name = "周六门诊结束时间")
    private String saturdayendtime;


    /** 周六备注信息 */
    @Excel(name = "周六备注信息")
    private String saturdayremark;


    /** 周日门诊开始时间 */
    @Excel(name = "周日门诊开始时间")
    private String sundaystarttime;


    /** 周日门诊结束时间 */
    @Excel(name = "周日门诊结束时间")
    private String sundayendtime;


    /** 周日备注信息 */
    @Excel(name = "周日备注信息")
    private String sundayremark;

    /** 快递备注信息 */
    @Excel(name = "24h急诊开始时间")
    private String emergencycallstarttime;

    /** 快递备注信息 */
    @Excel(name = "24h急诊结束时间")
    private String emergencycallendtime;

    /** 快递备注信息 */
    @Excel(name = "24h急诊备注信息")
    private String emergencycallremark;

    /** 快递备注信息 */
    @Excel(name = "夜间急诊开始时间")
    private String nightemergencystarttime;

    /** 快递备注信息 */
    @Excel(name = "夜间急诊结束时间")
    private String nightemergencyendtime;

    /** 快递备注信息 */
    @Excel(name = "夜间急诊备注信息")
    private String nightemergencyremark;

    /** 直结结算手续接待处 */
    @Excel(name = "直结结算手续接待处")
    private String receptionFlag;

    /** 医院是否配合收取自付费 */
    @Excel(name = "医院是否配合收取自付费")
    private String selfpaymentFlag;

    /** 二证齐全是否发预授权书 */
    @Excel(name = "二证齐全是否发预授权书")
    private String continentFlag;

    /** 二证齐全说明 */
    @Excel(name = "二证齐全说明")
    private String continent;

    /** 直结结算部门医保支付情况 */
    @Excel(name = "直结结算部门医保支付情况")
    private String payment;

    /** 对非直结客户是否提供门诊预约 */
    @Excel(name = "对非直结客户是否提供门诊预约")
    private String nondirectcustomer;

    /** 是否提供直结卡 */
    @Excel(name = "是否提供直结卡")
    private String straightknotflag;

    /** 是否可自行预约 */
    @Excel(name = "是否可自行预约")
    private String makeappointment;

    /** 是否提供VIP门诊预约 */
    @Excel(name = "是否提供VIP门诊预约")
    private String vipappointment;

    /** 是否驻点 */
    @Excel(name = "是否驻点")
    private String leadFlag;

    /** 驻点人员电话 */
    @Excel(name = "驻点人员电话")
    private String arrestpointTel;

    /** 驻点人员姓名 */
    @Excel(name = "驻点人员姓名")
    private String arrestpointName;

    /** 驻点人员联系方式 */
    @Excel(name = "驻点人员联系方式")
    private String arrestpointPhone;

    /** 门诊地址 */
    @Excel(name = "门诊地址")
    private String outpatientAddress;

    /** 预约提示 */
    @Excel(name = "预约提示")
    private String appointreminders;

    /** 预授权书发送及确认方式 */
    @Excel(name = "预授权书发送及确认方式")
    private String preauthsendway;

    /** 门诊病历提供 */
    @Excel(name = "门诊病历提供")
    private String clinicmedicalrecord;

    /** 住院病历提供 */
    @Excel(name = "住院病历提供")
    private String medicalrecords;

    /** 住院押金情况说明及办理流程 */
    @Excel(name = "住院押金情况说明及办理流程")
    private String hospitaldeposit;

    /** 直接结算合作部门急诊信息及电话 */
    @Excel(name = "直接结算合作部门急诊信息及电话")
    private String emergencyinfo;

    /** 网络医院直结类型备注 */
    @Excel(name = "网络医院直结类型备注")
    private String remarks;

    @Excel(name = "状态")
    private String status;

}
