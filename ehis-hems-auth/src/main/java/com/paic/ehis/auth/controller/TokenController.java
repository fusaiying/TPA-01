package com.paic.ehis.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.auth.form.LoginBody;
import com.paic.ehis.auth.service.SysLoginService;
import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.exception.BaseException;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.security.service.TokenService;
import com.paic.ehis.system.api.LognToBService;
import com.paic.ehis.system.api.domain.dto.RoleLoginInfo;
import com.paic.ehis.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * token 控制
 * 
 *
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private LognToBService lognToBService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    //B段用户登录
    @PostMapping("loginToB")
    public R<?> loginToB(@RequestBody RoleLoginInfo roleLoginInfo)
    {
        // 用户登录
        AjaxResult ajaxResult=lognToBService.getUserInfo(roleLoginInfo);
        if(!"200".equals(String.valueOf(ajaxResult.get("code")))) {
            throw new BaseException(String.valueOf(ajaxResult.get("data")));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        RoleLoginInfo info = objectMapper.convertValue(ajaxResult.get("data"),RoleLoginInfo.class);
        //获取登录token
        return R.ok(tokenService.createBToken(info));
    }


    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String username = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }
}
