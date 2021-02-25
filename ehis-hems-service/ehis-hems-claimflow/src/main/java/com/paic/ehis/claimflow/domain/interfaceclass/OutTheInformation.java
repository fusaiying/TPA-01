package com.paic.ehis.claimflow.domain.interfaceclass;

import lombok.Data;
import org.apache.poi.hpsf.Date;

@Data
public class OutTheInformation {
    /**
     * 出险人代码
     */
    private String accNo;
    /**
     * 出险人/单位名称
     */
    private String accName;
    /**
     * 出险日期
     */
    private Date accDate;
    /**
     * 出险地点
     */
    private String accAddress;
    /**
     * 出险原因
     */
    private String accReason;
    /**
     * 案情描述
     */
    private String describe;
    /**
     * 出险人状况
     */
    private String accsituation;
    /**
     * 出险人联系电话
     */
    private String accPhone;
    /**
     * 出险人家庭地址
     */
    private String accFamilyAddress;
    /**
     * 区域代码
     */
    private String zoneCode;
    /**
     * 亲属姓名
     */
    private String familyName;
    /**
     * 死亡日期
     */
    private Date deathDate;
    /**
     * 确诊日期
     */
    private Date definiteDate;
    /**
     * 出险原因1
     */
    private String accReason1;
    /**
     * 出险原因2
     */
    private String accReason2;
    /**
     * 出险原因3
     */
    private String accReason3;
    /**
     * 客户状态明细
     */
    private String customerStatusDetail;
    /**
     * 事故机构
     */
    private String accMechanism;
    /**
     * 病区床号
     */
    private String inpatientWardBedNo;
    /**
     * 事故处理单位
     */
    private String accProcessingUnit;
    /**
     * 事故处理单位联系电话
     */
    private String accProcessingUnitPhone;
    /**
     * 医院
     */
    private String hospitalCode;
    /**
     * 医院描述
     */
    private String hospitalDescribe;
    /**
     * 科室
     */
    private String department;
    /**
     * 死亡证明
     */
    private String deathCertificate;
    /**
     * 残疾日期
     */
    private Date disabilityDate;
    /**
     * 事故发生国家
     */
    private String accCountry;
    /**
     * 案件组别
     */
    private String caseGroup;
    /**
     * 癌症确诊日期
     */
    private Date cancerDate;
    /**
     * 出险人证件号码
     */
    private String accIdNo;
    /**
     * 出险人证件有效日期
     */
    private Date accIdExpiryDate;
    /**
     * 出险人性别
     */
    private String accSex;
    /**
     * 职业
     */
    private String accProfessional;

}
