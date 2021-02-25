package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件申请类别对象 claim_case_apply_type
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCaseApplyType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请类别ID */
    private Long applyId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 申请类别 */
    @Excel(name = "申请类别")
    private String applyType;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

}
