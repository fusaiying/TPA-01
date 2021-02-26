package com.paic.ehis.claimapt.domain.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName BatchVo
 * @Description: TODO 机构交单
 * @Author 硠君
 * @Date create in 9:43 2020/12/24
 * @Version 1.0
 **/
@Data
public class BatchVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchno;

    /** 交单来源 */
    @Excel(name = "交单来源",readConverterExp="01=线上-PBW,02=线上-E结算,03=线下-机构交单,04=寿险app")
    private String source;

    /** 就诊医院 */
    @Excel(name = "就诊医院")
    private String hospitalname;

    /** 就诊医院ID */
    private String hospitalcode;

    /** 批次理赔类型 */
    @Excel(name = "理赔类型",readConverterExp="01=直结,02=事后")
    private String claimtype;

    /** 交单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitdate;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 案件数量 */
    @Excel(name = "案件数量")
    private Integer casenum;

    /** 批次总金额 */
    @Excel(name = "批次总金额")
    private BigDecimal batchtotal;

    /** 账单币种 */
   // @Excel(name = "账单币种")
    private String currency;

    /** 操作人 */
    @Excel(name = "操作人")
    private String updateBy;

    /** 交单机构编码 */
    @Excel(name = "交单机构" ,readConverterExp="103=研发部门")
    private String organcode;

    /** 批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效 */
    @Excel(name = "批次状态 " ,readConverterExp="01=处理中,02=交单复核,03=交单完成,04=交单退回,05=交单无效")
    private String batchstatus;

    /** 结论 */
    private String conclusion;

    /** 备注 */
    private String remark;

    /** 就诊医院中文名 */
    private String chname1;

    /** 就诊医院英文名 */
    private String enname1;


}
