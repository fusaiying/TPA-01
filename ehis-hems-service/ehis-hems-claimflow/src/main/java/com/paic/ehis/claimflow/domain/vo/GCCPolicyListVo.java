package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class GCCPolicyListVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //个人保益状态
    private String personalBenStsName;
    //保单保益可用状态
    private String polBeanAvailableFlag;
    //体检预约医院类型
    private String hospitalType;
    //特定医院赔付比例
    private String specialHostpitalRate;
    //app绑定标识
    private String appFlag;
    //客户号
    private String customerNo;
    //保单生效日期
    private String effDate;
    //分单号
    private String certno;
    //首次生效日期
    private String firstEffDate;
    //是否健康会所
    private String isHealthClub;
    //原单号
    private String lbsPolNo;
    //主险名称
    private String mainPlanName;
    //机构名称
    private String regionCodeName;
    //保单号
    private String polno;
    //三类医院赔付比例
    private String threeClassHospitalRate;
    //四类医院赔付比例
    private String fourClassHospitalRate;
    //主险险种档次（个险）
    private String mainPlanCoverageLevel;
    //预授权信息
    private List<Object> preAuthorizationList;
    //是否续保
    private String isRenewal;
    //投保类型
    private String appType;
    //保单终止日
    private String matuDate;
    //被保人客户号
    private String insno;
    //机构编码
    private String regionCode;
    //
    private String isICSsVisable;
    //门诊直接标志
    private String directPatientFlag;
    //保益生效日期
    private String benEffDate;
    //预授权flag
    private String havingPreAuthorizationFlag;
    //主险代码
    private String mainPlanCode;
    //保益失效日
    private String benMatuDate;
    //可预约服务状态：待解析
    private String healthServiceInfoMap;
    //被保险人姓名
    private String insName;
    //
    private String directHospitalFlag;
    //一类医院赔付比例
    private String oneClassHospitalRate;
    //分单保益状态
    private String certStsName;
    //险种代码
    private String planCode;
//    private String modalTotalPrem;
//    private String applicant;
    //二类医院赔付比例
    private String twoClassHospitalRate;
    //等待期信息
    private List<GCCHealthServiceWaitPeriod> healthServiceWaitPeriodList;
    //投保人 无 传空
    private String applicantName;


}
