package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.system.api.domain.BaseIcd10;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 基础信息接口
 */
@FeignClient(contextId = "baseService", value = ServiceNameConstants.PROVIDER_SERVICE)
public interface BaseService {
    /**
     * 模糊查询ICD，默认展示200条
     *
     * @param baseIcd
     * @return
     */
    @PostMapping("/icd10/selectIcdFuzzy")
    public List<BaseIcd10> selectIcdFuzzy(@RequestBody BaseIcd10 baseIcd);
}
