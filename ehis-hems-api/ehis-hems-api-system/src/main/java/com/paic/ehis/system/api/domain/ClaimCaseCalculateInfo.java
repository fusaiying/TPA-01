package com.paic.ehis.system.api.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by xicc on 2021/3/25
 */
@Data
public class ClaimCaseCalculateInfo {

    //报案信息
    private String batchNo;
    private String rptNo;
    //是否直结 01=是,02=否
    private String claimType;
    private String caseStatus;
    private String companyCode;


    //被保人信息
    private String policyNo;
    private String insuredNo;
    private String name;
    private String sex;
    private String idType;
    private String idNo;
    private Date birthday;
    private int age;




    //账单信息列表
    private List<ClaimCaseBillInfo> claimCaseBillInfoList;

}
