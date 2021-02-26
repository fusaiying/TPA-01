package com.paic.ehis.claimflow.domain.interfaceclass;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件备注对象 claim_case_remark
 * 
 * @author sino
 * @date 2021-01-09
 */
@Data
public class ClaimCaseRemarkInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 备注ID */
    private Long remarkId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 备注类型 */
    @Excel(name = "备注类型")
    private String remarkType;

    /** 备注内容 */
    private String remark;

    /** 岗位编码 */
    @Excel(name = "岗位编码")
    private String station;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

}
