package com.paic.ehis.system.api.factory;
import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.BaseContractService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author admin
 */
@Component
public class GetProviderInfoFallbackFactory implements FallbackFactory<GetProviderInfoService>
{
    private static final Logger log = LoggerFactory.getLogger(com.paic.ehis.system.api.factory.GetProviderInfoFallbackFactory.class);

    @Override
    public GetProviderInfoService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new GetProviderInfoService()
        {
            @Override
            public List<BaseContractService> selectBaseContractServiceInfo(BaseContractService baseContractService) {
                return null;
            }

            @Override
            public R<List<BaseProviderInfo>> selectOrgInfo(BaseProviderInfo baseProviderInfo)
            {
                return null;
            }

            @Override
            public List<BaseProviderSettle> selectsettleInfoNew(BaseProviderSettle baseProviderSettle) {
                return null;
            }


        };
    }
}
