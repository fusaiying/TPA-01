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
public class ApplicantInformationDTO {
    /**
     * 申请人客户号
     */
    private String rgtNo;
    /**
     * 申请人类型
     */
    private String rgtType;
    /**
     * 申请人姓名
     */
    private String rgtName;
    /**
     * 申请人别名
     */
    private String rgtAlias;
    /**
     * 申请人证件类型
     */
    private String rgtIdType;
    /**
     * 申请人证件号码
     */
    private String rgtIdNo;
    /**
     * 申请人联系电话
     */
    private String rgtPhone;
    /**
     * 申请人联系手机
     */
    private String rgtMobile;
    /**
     * 联系地址
     */
    private String rgtAddress;
    /**
     * 通知送达地址
     */
    private String rgtNotifyAddress;
    /**
     * 邮编
     */
    private String postalCode;
    /**
     * 邮箱
     */
    private String rgtEmail;
    /**
     * 通知语种
     */
    private String informLanguageType;
    /**
     * 申请人与出险人关系
     */
    private String relationAcc;
    /**
     * 申请人与被保人关系
     */
    private String relationIns;
}
