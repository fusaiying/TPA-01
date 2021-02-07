package com.paic.ehis.claimapt.domain.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PolicyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 被保人客户号 */
    @Excel(name = "被保人客户号")
    private String insuredNo;
    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 被保人性别 */
    @Excel(name = "被保人性别")
    private String sex;

    /** 被保人证件类型 */
    @Excel(name = "被保人证件类型")
    private String idType;

    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;

    /** 被保人生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "被保人生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 保单号 */
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

}
