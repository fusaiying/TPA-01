package com.paic.ehis.claimcal.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ClaimCaseFilingListVO extends BaseEntity {

    /** 盒号 */
    @Excel(name = "盒号")
    private String caseBoxNo;

    /** 批次号 */
    private String batchNo;

    /** 报案号起 */
    @Excel(name = "报案号起")
    private String rptStartNo;

    /** 报案号止 */
    @Excel(name = "报案号止")
    private String rptEndNo;

    /** 理赔类型 */
    @Excel(name = "理赔类型")
    private String claimType;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /**
     * 登录机构编码
     */
    @Excel(name = "登录机构编码")
    private String deptCode;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateTime;

    /**
     * 已归档案件数量
     */
    @Excel(name = "已归档案件数量")
    private String casenum;

    /** 更新开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateStartTime;

    /** 更新结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateEndTime;

    /** 报案号 */
    private String rptNo;

}
