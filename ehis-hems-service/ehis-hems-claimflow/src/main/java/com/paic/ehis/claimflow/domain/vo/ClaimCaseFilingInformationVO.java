package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ClaimCaseFilingInformationVO extends BaseEntity {

    /** 报案号 */
    private String rptNo;

    /** 盒号 */
    @Excel(name = "盒号")
    private String caseBoxNo;

    /**
     * 登录机构编码
     */
    @Excel(name = "登录机构编码")
    private String deptCode;

    /** 理赔类型 */
    @Excel(name = "理赔类型")
    private String claimType;

    /** 发票是否归档 */
    @Excel(name = "发票是否归档")
    private String isFiling;

    /** 发票是否退回 */
    @Excel(name = "发票是否退回")
    private String isInvoiceBack;

    /** 批次是否单张发票 */
    @Excel(name = "批次是否单张发票")
    private String isSingle;

    /** 备注 */
    private String remark;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 批次号 */
    private String batchNo;
}
