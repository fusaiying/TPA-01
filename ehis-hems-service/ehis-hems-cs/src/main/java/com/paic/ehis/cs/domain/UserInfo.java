package com.paic.ehis.cs.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String userId ;
    /** 用户外部系统ID */
    private String userCode ;
    /** 用户名称 */
    private String userName ;
    /** UM账号 */
    private String umCode ;
    /** 有效状态 */
    private String userState ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;

}
