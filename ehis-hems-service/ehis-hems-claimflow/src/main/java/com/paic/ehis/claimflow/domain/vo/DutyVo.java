package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class DutyVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 险种名称 */
    @Excel(name = "险种名称")
    private String riskName;
    /** 险种编码 */
    @Excel(name = "险种编码")
    private String riskCode;
    /** 责任明细编码 */
    private String dutyDetailCode;
    /** 责任编码 */
    private String dutyCode;
    /** 责任明细名称 */
    @Excel(name = "责任明细名称")
    private String dutyDetailName;
    /** 责任名称 */
    @Excel(name = "责任名称")
    private String dutyName;
    /** 申请原因编码 */
    @Excel(name = "申请原因编码")
    private String applyReason;
    /**计划编码*/
    @Excel(name = "计划编码")
    private String planCode;
    @Excel(name="客户号")
    private String insuredNo;
}
