package com.paic.ehis.system.domain.vo;

import com.paic.ehis.system.domain.HmpServiceOrder;
import com.paic.ehis.system.domain.HmpServiceOrderApplication;
import com.paic.ehis.system.domain.HmpServiceOrderAssign;
import com.paic.ehis.system.domain.HmpServiceOrderResult;

/**
 * 服务信息
 */
public class ServiceInfoVo {

    private HmpServiceOrder hmpServiceOrder;
    private HmpServiceOrderApplication hmpServiceOrderApplication;
    private HmpServiceOrderAssign hmpServiceOrderAssign;
    private HmpServiceOrderResult hmpServiceOrderResult;

    public HmpServiceOrder getHmpServiceOrder() {
        return hmpServiceOrder;
    }

    public void setHmpServiceOrder(HmpServiceOrder hmpServiceOrder) {
        this.hmpServiceOrder = hmpServiceOrder;
    }

    public HmpServiceOrderApplication getHmpServiceOrderApplication() {
        return hmpServiceOrderApplication;
    }

    public void setHmpServiceOrderApplication(HmpServiceOrderApplication hmpServiceOrderApplication) {
        this.hmpServiceOrderApplication = hmpServiceOrderApplication;
    }

    public HmpServiceOrderAssign getHmpServiceOrderAssign() {
        return hmpServiceOrderAssign;
    }

    public void setHmpServiceOrderAssign(HmpServiceOrderAssign hmpServiceOrderAssign) {
        this.hmpServiceOrderAssign = hmpServiceOrderAssign;
    }

    public HmpServiceOrderResult getHmpServiceOrderResult() {
        return hmpServiceOrderResult;
    }

    public void setHmpServiceOrderResult(HmpServiceOrderResult hmpServiceOrderResult) {
        this.hmpServiceOrderResult = hmpServiceOrderResult;
    }

    @Override
    public String toString() {
        return "ServiceInfoVo{" +
                "hmpServiceOrder=" + hmpServiceOrder +
                ", hmpServiceOrderApplication=" + hmpServiceOrderApplication +
                ", hmpServiceOrderAssign=" + hmpServiceOrderAssign +
                ", hmpServiceOrderResult=" + hmpServiceOrderResult +
                '}';
    }
}
