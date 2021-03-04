package com.paic.ehis.claimmgt.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClaimCaseStandingVo1 extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String standingId;

    private String batchNo;
    /** 前三个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dBefore;

    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dNow;

    /**
     * 报案号
     */
    @Excel(name = "报案号")
    private String rptno;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String idno;

    /**
     * 被保险人姓名
     */
    @Excel(name = "被保险人")
    private String name;

    /** 理赔材料 */
    @Excel(name = "理赔材料",readConverterExp = "1=理赔申请书,2=身份证,3=保险卡,4=银行卡,5=诊断证明,6=病例,7=检查报告,8=处方,9=票据、发票,10=明细、清单")
    //private List<String> claimmaterials;
    private String claimmaterials;

    /** 理赔材料 */
    private List<String> claimmaterialList;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 其他（案件去向）
     */
    @Excel(name = "其他（案件去向）")
    private String otherinfo;

    /**
     * 快递单号
     */
    @Excel(name = "快递号")
    private String expressnumber;


    /**
     * 接单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receivedate;

    /**
     * 交件人
     */
    @Excel(name = "交件人")
    private String sendby;



    /** 出单公司名称 */
    @Excel(name = "出单公司")
    private String companyName;

    /**
     * 交单机构编码
     */
    private String organcode;

    /**
     * 交单机构编码
     */
    @Excel(name = "机构")
    private String organname;


    /** 创建者 */
    @Excel(name = "操作人")
    private String createBy;

    private String userName;


}
