package com.paic.ehis.system.api.factory;

import com.paic.ehis.system.api.BaseService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 基础信息接口
 */
@Component
public class BaseServiceFallbackFactory implements FallbackFactory<BaseService> {

    @Override
    public BaseService create(Throwable throwable) {
        return null;
    }
}
