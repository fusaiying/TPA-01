package com.paic.ehis.claimflow.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ClaimInformationDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String unEqRptNo;

    private String insuredNo;

    /***/
    private String  source;
    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    private String batchNo;

    /** 被保人姓名 */
    private String name;
    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;
    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;
    /** 案件状态 */
    private String caseStatus;
    /** 状态（Y-有效，N-无效） */
    private String status;

    /**根据 pageType 查询固定的数据*/
    private String pageType;

    private String createStartTime;

    private String  createEndTime;

}
