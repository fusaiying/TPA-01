package com.paic.ehis.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DispatchVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
    /**  */
    @Excel(name = "案件类型")
    private String claimType;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;


    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitdate;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;
    /** 流程节点操作人 */
    @Excel(name = "流程节点操作人")
    private String operator;
    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;
    /**
     * 交单来源
     */
    @Excel(name = "交单来源")
    private String source;
    private List<DispatchPolicyVo> dispatchVos;
}
