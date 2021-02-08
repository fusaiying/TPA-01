package com.paic.ehis.claimcal.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClaimCaseStandingVo1 extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    @Excel(name = "被保险人姓名")
    private String name;

    /** 理赔材料 */
    @Excel(name = "理赔材料字符串")
    //private List<String> claimmaterials;
    private String claimmaterials;

    /** 理赔材料 */
    @Excel(name = "理赔材料集合")
    private List<String> claimmaterialList;

    /**
     * 其他（案件去向）
     */
    @Excel(name = "其他", readConverterExp = "案件去向")
    private String otherinfo;

    /**
     * 快递单号
     */
    @Excel(name = "快递单号")
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

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remerk;


    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    /**
     * 交单机构编码
     */
    @Excel(name = "交单机构编码")
    private String organcode;



    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;


    private String userName;


}
