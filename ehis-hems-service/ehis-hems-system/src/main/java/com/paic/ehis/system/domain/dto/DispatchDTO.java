package com.paic.ehis.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class DispatchDTO  implements Serializable {

    /** 交单日期 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitstartdate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitenddate;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;
    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
    /**  */
    @Excel(name = "案件类型")
    private String claimType;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;
    /** 更新者 */
    private String updateBy;
}
