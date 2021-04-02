package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.service.PayCheckService;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xicc on 2021/4/1
 */
@Service
@Slf4j
public class PayCheckServiceImpl implements PayCheckService {


    @Override
    public ClaimCaseCalculateInfo calculateCheck(ClaimCaseCalculateInfo claimCaseCalculateInfo) {

        List<ClaimCaseBillInfo> billInfoList = claimCaseCalculateInfo.getClaimCaseBillInfoList();
        for (ClaimCaseBillInfo billInfo : billInfoList) {

            Date treatmentStartDate = billInfo.getTreatmentStartDate();


            List<ClaimCaseBillDetailInfo> detailInfoList = billInfo.getClaimCaseBillDetailInfoList();
            for (ClaimCaseBillDetailInfo detailInfo : detailInfoList) {

                Date validStartDate = detailInfo.getValidStartDate(); //保单有效起期
                Date validEndDate = detailInfo.getValidEndDate(); //保单有效止期

                //不在保单有效期
                if(treatmentStartDate.before(validStartDate)||treatmentStartDate.after(validEndDate)){
                    detailInfo.setCalAmount(BigDecimal.ZERO);
                }
                //找不到责任匹配
                if (StringUtils.isEmpty(detailInfo.getDutyCode()) || StringUtils.isEmpty(detailInfo.getDutyDetailCode())) {
                    detailInfo.setCalAmount(BigDecimal.ZERO);
                }

            }
        }


        return claimCaseCalculateInfo;
    }
}
