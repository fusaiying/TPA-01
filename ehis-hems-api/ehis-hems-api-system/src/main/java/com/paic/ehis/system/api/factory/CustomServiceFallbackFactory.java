package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.CustomServiceService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class CustomServiceFallbackFactory implements FallbackFactory<CustomServiceService>
{
    private static final Logger log = LoggerFactory.getLogger(com.paic.ehis.system.api.factory.CustomServiceFallbackFactory.class);

    @Override
    public CustomServiceService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new CustomServiceService() {

            //信息需求一周批处理
            @Override
            public AjaxResult batchAcceptVo(String invalidDate) {
                return null;
            }

            //信息需求一个月批处理
            @Override
            public AjaxResult batchAcceptVomonth(String invalidDate) {
                return null;
            }

            //预约12点批处理
            @Override
            public AjaxResult invalidAcceptDetailInfo(String invalidDate){
                return null;
            }

            //投诉每周一凌晨四点批处理
            @Override
            public AjaxResult selectInvalidQiaMondayFour(String invalidDate) {
                return null;
            }

        };
    }
}
