package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCClaimSend implements Serializable {
    private static final long serialVersionUID = 1L;
    //通知方式
    private String sendType;
    //通知时间
    private String sendDate;
    //接收手机号
    private String mobileNo;
    //邮箱
    private String emil;
    //通知类型
    private String noticeType;
    //代理人号
    private String agentNo;
    //发送状态
    private String sendStatus;
}
