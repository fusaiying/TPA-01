package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class CaseDispatchVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 保单号
     */
    private String policyNo;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 报案号
     */
    @Excel(name = "报案号")
    private String rptNo;

    /**
     * 交单来源
     */
    @Excel(name = "交单来源")
    private String source;

    /**
     * 被保人姓名
     */
    @Excel(name = "被保人姓名")
    private String name;

    /**
     * 被保人证件号
     */
    @Excel(name = "证件号码")
    private String idNo;

    @Excel(name = "理赔类型",readConverterExp = "01=直结,02=事后")
    private String claimType;

    /**
     * 出单公司编码
     */
    private String companyCode;

    @Excel(name = "出单公司名称")
    private String companyName;

    /**
     * 交单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "监控时效", width = 30, dateFormat = "dd-HH-mm")
    private String monitoringTime;//监控时效=监控时效=当前系统时间-案件受理完成时间，格式为：a天b时c分,仅在处理中工作池显示/** 案件状态 */

   @Excel(name = "案件状态",readConverterExp = "00=挂起,01=交单复核,02=交单退回,03=交单失败,04=交单完成,05=受理,30=问题件,97=撤件可申诉,98=撤件,06=录入,07=审核,31=协谈,32=调查,08=抽检,99=结案")
    private String caseStatus;

    /**
     * 流程节点操作人
     */
    @Excel(name = "操作人")
    private String operator;

    /**
     * 被保人编码
     */
    private String insuredNo;

}
