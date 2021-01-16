package com.paic.ehis.system.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DispatchPolicyVo  implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;
    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;

}
