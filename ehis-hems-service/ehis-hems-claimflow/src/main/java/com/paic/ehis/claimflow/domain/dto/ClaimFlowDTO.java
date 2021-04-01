package com.paic.ehis.claimflow.domain.dto;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ClaimFlowDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 保单号 */
    private String policyNo;
    /** 分单号 */
    private String policyItemNo;
    /** 客户号 */
    private String insuredNo;
    /** 被保人证件号 */
    private String idNo;
    /** 被保人姓名 */
    private String name;
    /** 被保人出生日期 */
    private String birthday;
    /** 当前记录起始索引 */
    private int pageNum;
    /** 每页显示记录数 */
    private int pageSize;


    //报案号
    private String docuno;
    //寿险客户号
    private String customerNo;
    //分单号
    private String certno;
    //保单号
    private String polno;
    //账单序号
    private String seqno;
}
