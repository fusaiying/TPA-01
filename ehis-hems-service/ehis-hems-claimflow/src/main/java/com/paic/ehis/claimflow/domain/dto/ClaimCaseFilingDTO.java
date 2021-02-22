package com.paic.ehis.claimflow.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ClaimCaseFilingDTO extends BaseEntity {

    /**
     * 登录机构编码
     */
    @Excel(name = "登录机构编码")
    private String deptCode;

    /** 理赔类型 */
    @Excel(name = "理赔类型")
    private String claimType;

    /** 批次号 */
    private String batchNo;

    /** 盒号 */
    @Excel(name = "盒号")
    private String caseBoxNo;

    /** 报案号 */
    private String rptNo;

    /** 报案号起 */
    @Excel(name = "报案号起")
    private String rptStartNo;

    /** 报案号止 */
    @Excel(name = "报案号止")
    private String rptEndNo;

    /** 是否销毁 */
    @Excel(name = "是否销毁 ",readConverterExp = "01=是,02=否")
    private String destroy;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效,N-无效")
    private String status;

    /** 创建日期起 */
    private Date updateStartTime;

    /** 创建日期止 */
    private Date updateEndTime;


}
