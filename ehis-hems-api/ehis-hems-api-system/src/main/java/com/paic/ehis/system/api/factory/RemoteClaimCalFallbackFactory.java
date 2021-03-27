package com.paic.ehis.system.api.factory;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.RemoteClaimCalService;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RemoteClaimCalFallbackFactory implements FallbackFactory<RemoteClaimCalService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteClaimCalFallbackFactory.class);

    @Override
    public RemoteClaimCalService create(Throwable throwable)
    {
        log.error("理赔作业处理服务调用失败:{}", throwable.getMessage());
        return new RemoteClaimCalService() {
            @Override
            public AjaxResult Clear(String rptNo) {
                return AjaxResult.error("案件：" + rptNo + "，理算计算信息清理失败，原因：" + throwable.getMessage());
            }

            @Override
            public AjaxResult getCaseInfo(String rptNo) {
                return AjaxResult.error("案件：" + rptNo + "，理算计算获取账单数据失败，原因：" + throwable.getMessage());
            }

            @Override
            public AjaxResult getCasePolicyInfo(String rptNo) {
                return AjaxResult.error("案件：" + rptNo + "，理算计算获取案件保单数据失败，原因：" + throwable.getMessage());
            }

            @Override
            public AjaxResult saveCaseCalInfo(ClaimCaseCalInfo claimCaseCalInfo) {
                return AjaxResult.error("案件：" + claimCaseCalInfo.getClaimCaseCalInfo().getRptNo() + "，理算计算插入理算数据失败，原因：" + throwable.getMessage());
            }

            @Override
            public AjaxResult getCaInfo(ClaimCaseCalItemDTO claimCaseCalItemDTO) {
                return AjaxResult.error("案件：" + claimCaseCalItemDTO.getRptNo() + "，理算获取使用值数据失败，原因：" + throwable.getMessage());
            }

        };
    }
}
