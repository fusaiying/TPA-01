package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 修改明细 对象 edit_detail
 * 
 * @author sino
 * @date 2021-02-05
 */
public class EditDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细流水号 */
    private String detailId;

    /** 修改流水号 */
    @Excel(name = "修改流水号")
    private String editId;

    /** 属性名字典类型 针对对象在字典中的属性和名称的定义 */
    @Excel(name = "属性名字典类型 针对对象在字典中的属性和名称的定义")
    private String keyDictType;

    /** 修改属性名称 */
    @Excel(name = "修改属性名称")
    private String itemName;

    /** 修改属性编码 */
    @Excel(name = "修改属性编码")
    private String itemKey;

    /** 原值 */
    @Excel(name = "原值")
    private String oldValue;

    /** 新值 */
    @Excel(name = "新值")
    private String nowValue;

    /** 属性值字典类型 针对具体属性在字典中的类型 */
    @Excel(name = "属性值字典类型 针对具体属性在字典中的类型")
    private String valueDictType;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderNo;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    /** 修改原因 */
    @Excel(name = "修改原因")
    private String editReason;

    /** 修改说明 */
    @Excel(name = "修改说明")
    private String editRemark;

    public String getEditReason() {
        return editReason;
    }

    public void setEditReason(String editReason) {
        this.editReason = editReason;
    }

    public String getEditRemark() {
        return editRemark;
    }

    public void setEditRemark(String editRemark) {
        this.editRemark = editRemark;
    }

    public void setDetailId(String detailId)
    {
        this.detailId = detailId;
    }

    public String getDetailId() 
    {
        return detailId;
    }
    public void setEditId(String editId) 
    {
        this.editId = editId;
    }

    public String getEditId() 
    {
        return editId;
    }
    public void setKeyDictType(String keyDictType) 
    {
        this.keyDictType = keyDictType;
    }

    public String getKeyDictType() 
    {
        return keyDictType;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setItemKey(String itemKey) 
    {
        this.itemKey = itemKey;
    }

    public String getItemKey() 
    {
        return itemKey;
    }
    public void setOldValue(String oldValue) 
    {
        this.oldValue = oldValue;
    }

    public String getOldValue() 
    {
        return oldValue;
    }
    public void setNowValue(String nowValue) 
    {
        this.nowValue = nowValue;
    }

    public String getNowValue() 
    {
        return nowValue;
    }
    public void setValueDictType(String valueDictType) 
    {
        this.valueDictType = valueDictType;
    }

    public String getValueDictType() 
    {
        return valueDictType;
    }
    public void setOrderNo(Long orderNo) 
    {
        this.orderNo = orderNo;
    }

    public Long getOrderNo() 
    {
        return orderNo;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("editId", getEditId())
            .append("keyDictType", getKeyDictType())
            .append("itemName", getItemName())
            .append("itemKey", getItemKey())
            .append("oldValue", getOldValue())
            .append("nowValue", getNowValue())
            .append("valueDictType", getValueDictType())
            .append("orderNo", getOrderNo())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
