package com.paic.ehis.claimmgt.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;


@Data
public class ClaimCaseDistVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;


    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;


    /**计算案件数量*/
    @Excel(name="计算案件数量")
    private int quantity;
}
