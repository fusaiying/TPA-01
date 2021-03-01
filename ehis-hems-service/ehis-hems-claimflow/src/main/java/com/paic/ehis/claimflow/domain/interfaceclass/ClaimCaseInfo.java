package com.paic.ehis.claimflow.domain.interfaceclass;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件信息 对象 claim_case
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCaseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 归档号 */
    @Excel(name = "归档号")
    private String filingNo;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;

    /** 撤件类别 */
    @Excel(name = "撤件类别")
    private String pulloutType;

    /** 撤件原因 */
    @Excel(name = "撤件原因")
    private String pulloutReason;

    /** 撤件描述 */
    @Excel(name = "撤件描述")
    private String pulloutDescribe;

    /** 结案日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endCaseTime;

    /** 是否申诉案件(01=正常，02=申诉) */
    @Excel(name = "是否申诉案件(01=正常，02=申诉)")
    private String isAppeal;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String payStatus;

    /** 是否财务回退 */
    @Excel(name = "是否财务回退")
    private String isFinanceBack;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

}
