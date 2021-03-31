package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 业务码 对象 code_dict
 * 
 * @author sino
 * @date 2021-02-27
 */
public class CodeDictTemporary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /*导入流水号*/
    @Excel(name = "导入流水号")
    private String serialNo;

    /*导入批次号*/
    @Excel(name = "导入批次号")
    private String batchNo;

    /** 编码类型 类型code */
    @Excel(name = "编码类型")
    private String codeType;

    /** 编码值 code值 */
    @Excel(name = "编码")
    private String code;

    /** 编码名称 字面意思 */
    @Excel(name = "编码名称 字面意思")
    private String codeName;

    /** 编码简称 */
    @Excel(name = "编码简称")
    private String codeAlias;

    /** 编码状态 有效状态: 1-有效；0-无效 */
    @Excel(name = "编码状态 有效状态: 1-有效；0-无效")
    private String status;

    /** 父节点 父节点Code值 */
    @Excel(name = "父节点 父节点Code值")
    private String parentCode;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 编码排序 排序 */
    @Excel(name = "编码排序 排序")
    private Long orderNo;

    /*导入错误提示信息*/
    @Excel(name = "导入错误提示信息")
    private String reason;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeAlias() {
        return codeAlias;
    }

    public void setCodeAlias(String codeAlias) {
        this.codeAlias = codeAlias;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
