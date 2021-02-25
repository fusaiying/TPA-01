package com.paic.ehis.claimflow.domain.interfaceclass;

import lombok.Data;

/**
 * Created by IntelliJ IDEA
 * User: fusaiying-傅赛赢
 *
 * @author: 傅赛赢
 * Date: 2021/2/25
 * subject:
 */
@Data
public class ApplicantInformationVO {
    /**申请人客户号*/
    private String registerNo;
    /**申请人类型*/
    private String registerType;
    /**申请人姓名*/
    private String registerName;
    /**申请人别名*/
    private String registerAlias;
    /**申请人证件类型*/
    private String registerIdType;
    /**申请人证件号码*/
    private String registerIdNo;
    /**申请人联系电话*/
    private String registerPhone;
    /**申请人联系手机*/
    private String registerMobile;
    /**联系地址*/
    private String Address;
    /**通知送达地址*/
    private String NotifyAddress;
    /**邮编*/
    private String postalCode;
    /**邮箱*/
    private String register_email;
    /**通知语种*/
    private String informLanguageType;
    /**申请人与出险人关系*/
    private String registerAndAccidentRelation;
    /**申请人与被保人关系*/
    private String registerAndInsuredRelation;
}
