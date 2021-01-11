package com.paic.ehis.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName IssuingcompanyRuleDTO
 * @Description: TODO 出单公司规则DTO
 * @Author 硠君
 * @Date create in 9:10 2020/12/30
 * @Version 1.0
 **/
@Data
public class IssuingcompanyRuleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 规则编码 */
    private String ruleno;

    /** 出单公司编码 */
    private String companycode;

    /** 产品编码 */
    private String riskcode;

    /** 结算类型 */
    private String accounttype;

    /** 服务费 */
    private BigDecimal servicefee;

    /** 保费比例% */
    private Integer premrate;
}
