package com.paic.ehis.claimflow.domain;

import lombok.Data;

@Data
public class ClaimSend {

    /**通知方式*/
    private String sendType;

    /**通知时间*/
    private String sendDate;

    /**接受手机号*/
    private String mobileNo;

    /**Email地址*/
    private String email;

    /**通知类型*/
    private String noticeType;


    private String agentNo;

    /**发送状态*/
    private String sendStatus;
}
