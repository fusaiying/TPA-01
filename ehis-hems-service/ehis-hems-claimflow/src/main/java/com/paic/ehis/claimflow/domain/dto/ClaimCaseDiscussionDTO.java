package com.paic.ehis.claimflow.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ClaimCaseDiscussionDTO extends BaseEntity {

    private String createStartTime;

    private String createEndTime;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
    /*交单来源*/
    @Excel(name = "交单来源")
    private String source;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 协谈类型 */
    @Excel(name = "协谈类型")
    private String discType;
    /*提交用户*/
    @Excel(name = "提交用户")
    private String createBy;

}
