package com.paic.ehis.system.api.factory;

import com.paic.ehis.system.api.RemoteFinancialServicce;
import com.paic.ehis.system.api.domain.FinanceBorrowInfo;
import feign.hystrix.FallbackFactory;

public class RemoteFinancialServicceFallbackFactory implements FallbackFactory<RemoteFinancialServicce> {

    @Override
    public RemoteFinancialServicce create(Throwable throwable) {
        return new RemoteFinancialServicce() {
            @Override
            public int deleteBorrow(String rptNo) {
                return 0;
            }

            @Override
            public FinanceBorrowInfo selectBorrowInfo(String rptNo) {
                return null;
            }

            @Override
            public int updateBorrowInfo(FinanceBorrowInfo borrowInfo) {
                return 0;
            }
        };
    }
}
