package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工单处理信息 对象 work_handle_info
 * 
 * @author sino
 * @date 2021-02-02
 */
@Data
public class WorkHandleInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 处理流水号 */
    private Long handleId;

    /** 处理类型 处理；取消；转办；协办；质检 */
    @Excel(name = "处理类型 处理；取消；转办；协办；质检")
    private String handleType;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 标志 */
    @Excel(name = "标志")
    private String flag;

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

    /** 处理属性1 见转意表 */
    @Excel(name = "处理属性1 见转意表")
    private String handleProp1;

    /** 处理属性2 见转意表 */
    @Excel(name = "处理属性2 见转意表")
    private String handleProp2;

    /** 处理属性3 见转意表 */
    @Excel(name = "处理属性3 见转意表")
    private String handleProp3;

    /** 处理属性4 见转意表 */
    @Excel(name = "处理属性4 见转意表")
    private String handleProp4;

    /** 处理属性5 见转意表 */
    @Excel(name = "处理属性5 见转意表")
    private String handleProp5;

    /** 处理属性6 见转意表 */
    @Excel(name = "处理属性6 见转意表")
    private String handleProp6;

    /** 处理属性7 见转意表 */
    @Excel(name = "处理属性7 见转意表")
    private String handleProp7;

    /** 处理属性8 见转意表 */
    @Excel(name = "处理属性8 见转意表")
    private String handleProp8;

    /** 处理属性9 见转意表 */
    @Excel(name = "处理属性9 见转意表")
    private String handleProp9;

    /** 处理属性10 见转意表 */
    @Excel(name = "处理属性10 见转意表")
    private String handleProp10;

    /** 处理属性11 见转意表 */
    @Excel(name = "处理属性11 见转意表")
    private String handleProp11;

    /** 处理属性12 见转意表 */
    @Excel(name = "处理属性12 见转意表")
    private String handleProp12;

    /** 处理属性13 见转意表 */
    @Excel(name = "处理属性13 见转意表")
    private String handleProp13;

    /** 处理属性14 见转意表 */
    @Excel(name = "处理属性14 见转意表")
    private String handleProp14;

    /** 处理属性15 见转意表 */
    @Excel(name = "处理属性15 见转意表")
    private String handleProp15;

    /** 处理属性16 见转意表 */
    @Excel(name = "处理属性16 见转意表")
    private String handleProp16;

    /** 处理属性17 见转意表 */
    @Excel(name = "处理属性17 见转意表")
    private String handleProp17;

    /** 处理属性18 见转意表 */
    @Excel(name = "处理属性18 见转意表")
    private String handleProp18;

    /** 处理属性19 见转意表 */
    @Excel(name = "处理属性19 见转意表")
    private String handleProp19;

    /** 处理属性20 见转意表 */
    @Excel(name = "处理属性20 见转意表")
    private String handleProp20;

    /** 处理属性21 见转意表 */
    @Excel(name = "处理属性21 见转意表")
    private String handleProp21;

    /** 处理属性22 见转意表 */
    @Excel(name = "处理属性22 见转意表")
    private String handleProp22;

    /** 处理属性23 见转意表 */
    @Excel(name = "处理属性23 见转意表")
    private String handleProp23;

    /** 处理属性24 见转意表 */
    @Excel(name = "处理属性24 见转意表")
    private String handleProp24;

    /** 处理属性25 见转意表 */
    @Excel(name = "处理属性25 见转意表")
    private String handleProp25;

    /** 处理属性26 见转意表 */
    @Excel(name = "处理属性26 见转意表")
    private String handleProp26;

    /** 处理属性27 见转意表 */
    @Excel(name = "处理属性27 见转意表")
    private String handleProp27;

    /** 处理属性28 见转意表 */
    @Excel(name = "处理属性28 见转意表")
    private String handleProp28;

    /** 处理属性29 见转意表 */
    @Excel(name = "处理属性29 见转意表")
    private String handleProp29;

    /** 处理属性30 见转意表 */
    @Excel(name = "处理属性30 见转意表")
    private String handleProp30;

    private String remark;
}
