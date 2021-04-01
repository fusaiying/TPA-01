package com.paic.ehis.claimmgt.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import lombok.Data;

@Data
public class ClaimUserRole extends BaseEntity {

    /** 理赔用户角色ID */
    private Long userRoleId;

    /** 角色编码 */
    private String roleCode;

    /** 角色名称 */
    private String roleName;

    /** 是否均分 */
    private String isEqually;

    /** 映射类型 */
    private String mappingType;

    /** 映射内容 */
    private String mappingValue;

    /** 数据状态 */
    private String status;
}
