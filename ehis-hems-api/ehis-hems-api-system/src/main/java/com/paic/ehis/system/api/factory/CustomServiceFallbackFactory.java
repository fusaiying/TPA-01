package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.CustomServiceService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomServiceFallbackFactory implements FallbackFactory<CustomServiceService>
{
    private static final Logger log = LoggerFactory.getLogger(com.paic.ehis.system.api.factory.CustomServiceFallbackFactory.class);

    @Override
    public CustomServiceService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new CustomServiceService() {
            @Override
            public AjaxResult batchAcceptVo(String invalidDate) {
                return null;
            }
        };
    }
}
