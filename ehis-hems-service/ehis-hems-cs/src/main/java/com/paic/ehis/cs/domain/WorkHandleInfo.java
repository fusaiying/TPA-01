package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工单处理信息 对象 work_handle_info
 * 
 * @author sino
 * @date 2021-01-30
 */
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

    public void setHandleId(Long handleId) 
    {
        this.handleId = handleId;
    }

    public Long getHandleId() 
    {
        return handleId;
    }
    public void setHandleType(String handleType) 
    {
        this.handleType = handleType;
    }

    public String getHandleType() 
    {
        return handleType;
    }
    public void setWorkOrderNo(String workOrderNo) 
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
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
    public void setHandleProp1(String handleProp1) 
    {
        this.handleProp1 = handleProp1;
    }

    public String getHandleProp1() 
    {
        return handleProp1;
    }
    public void setHandleProp2(String handleProp2) 
    {
        this.handleProp2 = handleProp2;
    }

    public String getHandleProp2() 
    {
        return handleProp2;
    }
    public void setHandleProp3(String handleProp3) 
    {
        this.handleProp3 = handleProp3;
    }

    public String getHandleProp3() 
    {
        return handleProp3;
    }
    public void setHandleProp4(String handleProp4) 
    {
        this.handleProp4 = handleProp4;
    }

    public String getHandleProp4() 
    {
        return handleProp4;
    }
    public void setHandleProp5(String handleProp5) 
    {
        this.handleProp5 = handleProp5;
    }

    public String getHandleProp5() 
    {
        return handleProp5;
    }
    public void setHandleProp6(String handleProp6) 
    {
        this.handleProp6 = handleProp6;
    }

    public String getHandleProp6() 
    {
        return handleProp6;
    }
    public void setHandleProp7(String handleProp7) 
    {
        this.handleProp7 = handleProp7;
    }

    public String getHandleProp7() 
    {
        return handleProp7;
    }
    public void setHandleProp8(String handleProp8) 
    {
        this.handleProp8 = handleProp8;
    }

    public String getHandleProp8() 
    {
        return handleProp8;
    }
    public void setHandleProp9(String handleProp9) 
    {
        this.handleProp9 = handleProp9;
    }

    public String getHandleProp9() 
    {
        return handleProp9;
    }
    public void setHandleProp10(String handleProp10) 
    {
        this.handleProp10 = handleProp10;
    }

    public String getHandleProp10() 
    {
        return handleProp10;
    }
    public void setHandleProp11(String handleProp11) 
    {
        this.handleProp11 = handleProp11;
    }

    public String getHandleProp11() 
    {
        return handleProp11;
    }
    public void setHandleProp12(String handleProp12) 
    {
        this.handleProp12 = handleProp12;
    }

    public String getHandleProp12() 
    {
        return handleProp12;
    }
    public void setHandleProp13(String handleProp13) 
    {
        this.handleProp13 = handleProp13;
    }

    public String getHandleProp13() 
    {
        return handleProp13;
    }
    public void setHandleProp14(String handleProp14) 
    {
        this.handleProp14 = handleProp14;
    }

    public String getHandleProp14() 
    {
        return handleProp14;
    }
    public void setHandleProp15(String handleProp15) 
    {
        this.handleProp15 = handleProp15;
    }

    public String getHandleProp15() 
    {
        return handleProp15;
    }
    public void setHandleProp16(String handleProp16) 
    {
        this.handleProp16 = handleProp16;
    }

    public String getHandleProp16() 
    {
        return handleProp16;
    }
    public void setHandleProp17(String handleProp17) 
    {
        this.handleProp17 = handleProp17;
    }

    public String getHandleProp17() 
    {
        return handleProp17;
    }
    public void setHandleProp18(String handleProp18) 
    {
        this.handleProp18 = handleProp18;
    }

    public String getHandleProp18() 
    {
        return handleProp18;
    }
    public void setHandleProp19(String handleProp19) 
    {
        this.handleProp19 = handleProp19;
    }

    public String getHandleProp19() 
    {
        return handleProp19;
    }
    public void setHandleProp20(String handleProp20) 
    {
        this.handleProp20 = handleProp20;
    }

    public String getHandleProp20() 
    {
        return handleProp20;
    }
    public void setHandleProp21(String handleProp21) 
    {
        this.handleProp21 = handleProp21;
    }

    public String getHandleProp21() 
    {
        return handleProp21;
    }
    public void setHandleProp22(String handleProp22) 
    {
        this.handleProp22 = handleProp22;
    }

    public String getHandleProp22() 
    {
        return handleProp22;
    }
    public void setHandleProp23(String handleProp23) 
    {
        this.handleProp23 = handleProp23;
    }

    public String getHandleProp23() 
    {
        return handleProp23;
    }
    public void setHandleProp24(String handleProp24) 
    {
        this.handleProp24 = handleProp24;
    }

    public String getHandleProp24() 
    {
        return handleProp24;
    }
    public void setHandleProp25(String handleProp25) 
    {
        this.handleProp25 = handleProp25;
    }

    public String getHandleProp25() 
    {
        return handleProp25;
    }
    public void setHandleProp26(String handleProp26) 
    {
        this.handleProp26 = handleProp26;
    }

    public String getHandleProp26() 
    {
        return handleProp26;
    }
    public void setHandleProp27(String handleProp27) 
    {
        this.handleProp27 = handleProp27;
    }

    public String getHandleProp27() 
    {
        return handleProp27;
    }
    public void setHandleProp28(String handleProp28) 
    {
        this.handleProp28 = handleProp28;
    }

    public String getHandleProp28() 
    {
        return handleProp28;
    }
    public void setHandleProp29(String handleProp29) 
    {
        this.handleProp29 = handleProp29;
    }

    public String getHandleProp29() 
    {
        return handleProp29;
    }
    public void setHandleProp30(String handleProp30) 
    {
        this.handleProp30 = handleProp30;
    }

    public String getHandleProp30() 
    {
        return handleProp30;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("handleId", getHandleId())
            .append("handleType", getHandleType())
            .append("workOrderNo", getWorkOrderNo())
            .append("flag", getFlag())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .append("remark", getRemark())
            .append("handleProp1", getHandleProp1())
            .append("handleProp2", getHandleProp2())
            .append("handleProp3", getHandleProp3())
            .append("handleProp4", getHandleProp4())
            .append("handleProp5", getHandleProp5())
            .append("handleProp6", getHandleProp6())
            .append("handleProp7", getHandleProp7())
            .append("handleProp8", getHandleProp8())
            .append("handleProp9", getHandleProp9())
            .append("handleProp10", getHandleProp10())
            .append("handleProp11", getHandleProp11())
            .append("handleProp12", getHandleProp12())
            .append("handleProp13", getHandleProp13())
            .append("handleProp14", getHandleProp14())
            .append("handleProp15", getHandleProp15())
            .append("handleProp16", getHandleProp16())
            .append("handleProp17", getHandleProp17())
            .append("handleProp18", getHandleProp18())
            .append("handleProp19", getHandleProp19())
            .append("handleProp20", getHandleProp20())
            .append("handleProp21", getHandleProp21())
            .append("handleProp22", getHandleProp22())
            .append("handleProp23", getHandleProp23())
            .append("handleProp24", getHandleProp24())
            .append("handleProp25", getHandleProp25())
            .append("handleProp26", getHandleProp26())
            .append("handleProp27", getHandleProp27())
            .append("handleProp28", getHandleProp28())
            .append("handleProp29", getHandleProp29())
            .append("handleProp30", getHandleProp30())
            .toString();
    }
}
