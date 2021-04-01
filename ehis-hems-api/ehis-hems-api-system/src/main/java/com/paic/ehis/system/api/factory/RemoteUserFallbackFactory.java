package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.dto.MenuIdDTO;
import com.paic.ehis.system.api.model.LoginUser;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 *
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<LoginUser> getUserInfo(String username)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult getOrganInfo(List<String> organCodes) {
                return AjaxResult.error("获取机构信息失败，原因：" + throwable.getMessage());
            }

            @Override
            public AjaxResult userInfo() {
                return AjaxResult.error("获取用户信息失败，原因：" + throwable.getMessage());
            }

            @Override
            public TableDataInfo getUsersByMenuId(MenuIdDTO menuIdDTO) {
                return null;
            }
        };
    }
}
