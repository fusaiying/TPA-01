package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * HCS预约修改信息，外部系统接口入参对象
 * TODO 1.激活的时候，在谁的个人池中？2.无需区分激活合适预约修改？3.是否只是处理预约和信息需求，投诉的没有
 */
@Data
public class ModifyServiceApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    //操作类型： modifyService;completeModifyService外部激活
    private String type;
    //受理号
    private String applicationCaseNo;
    //修改序列号
    private String modifySeqNo;
    //修改内容描述
    private String modifyContent;
    //优先级
    private String modifyPriority;

    //本系统工单号暂存
    private String workOrderNo;
}
