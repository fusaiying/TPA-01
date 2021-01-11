package com.paic.ehis.system.api;

import com.paic.ehis.system.api.factory.RemoteUserFallbackFactory;
import com.paic.ehis.system.api.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.common.core.domain.R;

/**
 * 用户服务
 * 
 * @author admin
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<UserInfo> getUserInfo(@PathVariable("username") String username);
}
