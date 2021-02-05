package com.paic.ehis.finance.mapper;

import com.paic.ehis.system.api.domain.SysUser;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author admin
 */
public interface SysUserMapper
{

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);


}
