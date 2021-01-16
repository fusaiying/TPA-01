package com.paic.ehis.system.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName IssuingcompanyRuleVO
 * @Description: TODO
 * @Author 硠君
 * @Date create in 12:13 2020/12/30
 * @Version 1.0
 **/
@Data
public class IssuingcompanyRuleVO implements Serializable {
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

    /** 出单公司名称 */
    private String companyname;


}
