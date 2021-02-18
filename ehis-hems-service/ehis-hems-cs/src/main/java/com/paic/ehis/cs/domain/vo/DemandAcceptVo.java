package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.UserInfo;
import lombok.Data;

import java.util.Date;

/**
 * 信息需求查询对象 demand_query
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class DemandAcceptVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "工单号")
    private String workOrderNo;

    private String businessType;

    @Excel(name = "受理渠道")
    private String channel;

    @Excel(name = "服务项目")
    private String serviceItem;

    private String businessService;

    @Excel(name = "保单号")
    private String policyNo;

    @Excel(name = "分单号")
    private String policyItemNo;

    @Excel(name = "险种号")
    private String riskCode;

    private PersonInfo insuredPerson;

    private PersonInfo holderPerson;

    private UserInfo acceptUser;

    private UserInfo modifyUser;

    private String vipFlag;

    private String priorityLevel;

    private String organCode;

    /** 受理日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

//    /** 状态（Y-有效，N-无效） */
//    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
//    private String status;
    //是否红色字体
    private Boolean isRedWord=false;

}
