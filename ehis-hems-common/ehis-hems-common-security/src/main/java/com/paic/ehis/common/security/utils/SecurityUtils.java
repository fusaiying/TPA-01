package com.paic.ehis.common.security.utils;


import com.paic.ehis.common.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 权限获取工具类
 *
 * @author admin
 */
@Component
public class SecurityUtils
{

    @Autowired
    TokenService tokenService;
    /**
     * 获取用户
     */
    public String getUsername()
    {
        return tokenService.getLoginUser().getUsername();
    }


}
