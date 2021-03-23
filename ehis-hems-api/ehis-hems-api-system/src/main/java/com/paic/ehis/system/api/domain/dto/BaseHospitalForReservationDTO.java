package com.paic.ehis.system.api.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 提供客服预约医疗机构入参
 */
@Data
@ToString
public class BaseHospitalForReservationDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 医院编码 */
    private String hospitalCode;
    /** 医院名称 */
    private String hospitalName;
    /** 省份 */
    private String province;
    /** 市区 */
    private String city;

}
