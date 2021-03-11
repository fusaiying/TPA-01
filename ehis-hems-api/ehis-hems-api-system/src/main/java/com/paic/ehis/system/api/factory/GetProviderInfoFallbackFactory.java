package com.paic.ehis.system.api.factory;
import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.*;
import com.paic.ehis.system.api.domain.dto.BaseManualInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

            @Override
            public TableDataInfo getlist(BaseManualInfo baseManualInfo){return null;}

            @Override
            public List<BaseServiceInfo> getServiceInfo(){return null;}

            @Override
            public AjaxResult getSupplierInfo(){return null;}

            //获取医院信息
            @Override
            public List<HospitalInfoVo> getHospitalInfo(){return null;}

            ////获取一级科室
            @Override
            public List<FirstDeptInfoVo> getFirstDeptInfo(){return null;}

            //获取二级科室
            @Override
            public List<SecondDeptInfoVo> getSecondDeptInfo(){return null;}

            @Override
            public List<HospitalInfoVo> getHospitalInfo1(@RequestBody AddressInfo addressInfo){return null;}

            //合约到期后，每天晚上12:00，通过批处理的方式将合约的状态修改为失效
            @Override
            public AjaxResult batchTimeBaseSupplierContract(@RequestBody BaseSupplierContract baseSupplierContract){return null;};
        };
    }
}
