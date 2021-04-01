package com.paic.ehis.claimmgt.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件分配规则对象 claim_case_dist
 * 
 * @author sino
 * @date 2021-01-22
 */
@Data
public class ClaimCaseDist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分配规则ID */
    private Long distId;

    /** 用户名称 */
    @Excel(name = "用户")
    private String userName;

    /** 用户所属机构 */
    private String userOrganCode;

    /** 角色编码 */
    @Excel(name = "角色编码")
    private String roleCode;

    /** 分配比例 */
    @Excel(name = "分配比例")
    private BigDecimal rate;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;
}
