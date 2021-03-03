package com.paic.ehis.system.api.factory;

import com.paic.ehis.system.api.BaseService;
import com.paic.ehis.system.api.domain.BaseIcd10;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 基础信息接口
 */
@Component
public class BaseServiceFallbackFactory implements FallbackFactory<BaseService> {
    private static final Logger log = LoggerFactory.getLogger(com.paic.ehis.system.api.factory.BaseServiceFallbackFactory.class);

    @Override
    public BaseService create(Throwable throwable) {
        log.error("理算及福利规则调用失败:{}", throwable.getMessage());
        return new BaseService() {
            @Override
            public List<BaseIcd10> selectIcdFuzzy(BaseIcd10 baseIcd) {
                return null;
            }
        };
    }
}
