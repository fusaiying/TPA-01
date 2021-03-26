package com.paic.ehis.base.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 提供客服预约医疗机构信息
 */
@Data
@ToString
public class BaseHospitalForReservationVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 医院编码 */
    private String hospitalCode;
    /** 医院名称 */
    private String hospitalName;
    /** 机构地址 */
    private String address;
    /** 机构电话 **/
    private String consultPhone;
    /** 科室 */
    private List<String> deptList;

}
