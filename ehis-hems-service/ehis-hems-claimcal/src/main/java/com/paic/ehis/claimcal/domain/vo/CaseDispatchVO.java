package com.paic.ehis.claimcal.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class CaseDispatchVO extends BaseEntity {
   private static final long serialVersionUID = 1L;

   /** 保单号 */
   private String policyNo;

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

   /** 交单日期*/
   @JsonFormat(pattern = "yyyy-MM-dd")
   @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
   private Date submitdate;

   /** 流程节点操作人 */
   @Excel(name = "流程节点操作人")
   private String operator;
   /**
    * 交单来源
    */
   @Excel(name = "交单来源")
   private String source;
   /** 出单公司编码 */
   @Excel(name = "出单公司编码")
   private String companyCode;
   @Excel(name = "出单公司名称")
   private String companyName;
   /** 被保人姓名 */
   @Excel(name = "被保人姓名")
   private String name;
   /** 被保人证件号 */
   @Excel(name = "被保人证件号")
   private String idNo;

   /** 被保人编码 */
   @Excel(name = "被保人编码")
   private String insuredNo;

   @JsonFormat(pattern = "yyyy-MM-dd")
   @Excel(name = "监控时效", width = 30, dateFormat = "dd-HH-mm")
   private String monitoringTime;//监控时效=监控时效=当前系统时间-案件受理完成时间，格式为：a天b时c分,仅在处理中工作池显示
}
