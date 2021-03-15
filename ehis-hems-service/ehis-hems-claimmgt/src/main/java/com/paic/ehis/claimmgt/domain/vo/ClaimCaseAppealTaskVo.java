package com.paic.ehis.claimmgt.domain.vo;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClaimCaseAppealTaskVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 申诉ID */
    private Long taskId;

    /** 被申诉报案号 */
    private String appealRptNo;

    /** 新报案号 */
    private String newRptNo;

    /** 交单来源 */
    private String source;

    /** 被保人姓名 */
    private String name;

    /** 被保人证件号 */
    private String idNo;

    /** 理赔类型 */
    private String claimType;

    /** 出单公司 */
    private String companyCode;
    private String companyName;

    /** 承保机构 */
    private String policyManageCom;

    /** 赔付金额 */
    private BigDecimal payAmount;

    /** 申诉状态 */
    private String appealStatus;

    /** 申诉类型 */
    private String appealType;

    /** 申诉原因 */
    private String appealReason;

    /** 申诉原因细类 */
    private String appealSubReason;

    /** 申诉说明 */
    private String appealRemark;

    /** 申请人 */
    private String applyOperator;

    /** 审核人 */
    private String auditor;

    /** 数据状态（Y-有效，N-无效） */
    private String status;

    /** 登录机构编码 */
    private String deptCode;

    /**根据 pageType 查询固定的数据*/
    private String pageType;

    private String createStartTime;

    private String  createEndTime;

}
