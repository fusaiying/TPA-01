package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.domain.SyncExchangeRate;
import com.paic.ehis.claimcal.service.IExchangeRateService;
import com.paic.ehis.claimcal.service.PayableService;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xicc on 2021/3/27
 */
@Service
@Slf4j
public class PayableServiceImpl implements PayableService {

    @Autowired
    private IExchangeRateService exchangeRateService;

    @Override
    public ClaimCaseCalculateInfo calculatePayable(ClaimCaseCalculateInfo claimCaseCalculateInfo) {

        List<ClaimCaseBillInfo> billInfoList = claimCaseCalculateInfo.getClaimCaseBillInfoList();
        for (ClaimCaseBillInfo billInfo : billInfoList) {
            SyncExchangeRate exchangeRate = new SyncExchangeRate();
            exchangeRate.setBeforeMoney(billInfo.getBillCurrency());
            exchangeRate.setAfterMoney("CNY");
            exchangeRate.setDateConvert(billInfo.getTreatmentStartDate());
            exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
//                if(StringUtils.isNull(exchangeRate)){
//                    throw new RuntimeException("获取汇率失败！");
//                }
            if(!StringUtils.isNull(exchangeRate)){//为获取到，按1汇率
                exchangeRate = new SyncExchangeRate();
                exchangeRate.setParities(BigDecimal.ONE);
            }
            List<ClaimCaseBillDetailInfo> detailInfoList = billInfo.getClaimCaseBillDetailInfoList();
            for (ClaimCaseBillDetailInfo detailInfo : detailInfoList) {

                BigDecimal billDetailAmount = StringUtils.isNull(detailInfo.getBillDetailAmount())? BigDecimal.ZERO : detailInfo.getBillDetailAmount();
                BigDecimal selfAmount = StringUtils.isNull(detailInfo.getSelfAmount())? BigDecimal.ZERO : detailInfo.getSelfAmount();
                BigDecimal advancePayment = StringUtils.isNull(detailInfo.getAdvancePayment())? BigDecimal.ZERO : detailInfo.getAdvancePayment();
                BigDecimal hosDiscountAmount = StringUtils.isNull(detailInfo.getHosDiscountAmount())? BigDecimal.ZERO : detailInfo.getHosDiscountAmount();
                BigDecimal unableAmount = StringUtils.isNull(detailInfo.getUnableAmount())? BigDecimal.ZERO : detailInfo.getUnableAmount();

                BigDecimal calAmount = billDetailAmount.subtract(selfAmount).subtract(advancePayment).subtract(hosDiscountAmount).subtract(unableAmount);

                calAmount = calAmount.multiply(exchangeRate.getParities()); //乘以汇率

                detailInfo.setCalAmount(calAmount);
                detailInfo.setRefusedAmount(BigDecimal.ZERO);
                detailInfo.setDeduUsed(BigDecimal.ZERO);
            }
        }


        return claimCaseCalculateInfo;
    }
}
