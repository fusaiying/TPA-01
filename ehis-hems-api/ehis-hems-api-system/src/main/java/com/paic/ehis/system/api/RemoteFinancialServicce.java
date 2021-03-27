package com.paic.ehis.system.api;

import com.paic.ehis.common.core.constant.ServiceNameConstants;
import com.paic.ehis.system.api.domain.FinanceBorrowInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "RemoteFinancialServicce", value = ServiceNameConstants.FINANCE_SERVICE)
public interface RemoteFinancialServicce {

    /**
     * 置借款数据为无效
     *
     * @param rptNo
     * @return
     */
    @PostMapping("/payInfo/deleteBorrow")
    public int deleteBorrow(@RequestBody String rptNo);

    /**
     * 根据报案号查询借款信息
     *
     * @param rptNo
     * @return
     */
    @PostMapping("/payInfo/selectBorrowInfo")
    public FinanceBorrowInfo selectBorrowInfo(@RequestBody String rptNo);

    /**
     * 更新借款数据
     *
     * @param borrowInfo
     * @return
     */
    @PostMapping("/payInfo/updateBorrowInfo")
    public int updateBorrowInfo(@RequestBody FinanceBorrowInfo borrowInfo);
}
