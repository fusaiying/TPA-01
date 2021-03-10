package com.paic.ehis.claimflow.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DebtInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    private String rptNo;

    /** 保单号 */
    private String policyNo;

    /** 就诊医院 */
    private String hospitalCode;

    /** 被保人姓名 */
    private String insuredName;

    /** 证件号码 */
    private String idNo;

    /** 开始时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /** 分单号 */
    private String policyItemNo;

    /** 白名单标记 */
    private String whiteStatus;

    /** 0:初始化 1:点击查询按钮 */
    private String flag;

}
