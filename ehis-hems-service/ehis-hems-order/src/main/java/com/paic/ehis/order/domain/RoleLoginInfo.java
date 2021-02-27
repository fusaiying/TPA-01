package com.paic.ehis.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * order_info(工单信息)对象 order_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class RoleLoginInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账号 */
    private String role;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 节点状态 */
    @Excel(name = "节点状态")
    private String nodeStatus;

    /** 工单编码 */
    @Excel(name = "工单编码")
    private String orderCode;

    private String supplierCode;

    private String token;

    private String ipaddr;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    private String name;

    private String isLogin;

    private String newPassword;

    /** 接单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime;

}
