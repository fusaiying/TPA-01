package com.paic.ehis.claimflow.domain.vo;


import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class BillProcessingVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 出单公司 */
    @Excel(name = "出单公司")
    private String companyName;

    /** 承保机构 */
    @Excel(name = "承保机构")
    private String policyManageCom;

    /** 停留时长 */
    @Excel(name = "停留时长")
    private String stayTime;

    /** 监控时效 */
    @Excel(name = "监控时效")
    private String monitorAging;

    /** 是否调查 */
    @Excel(name = "是否调查 ",readConverterExp = "01=是,02=否")
    private String investigation;

    /** 提交用户 */
    @Excel(name = "提交用户")
    private String submitUser;

}
