package com.paic.ehis.system.domain;

import com.paic.ehis.system.api.domain.SysUser;

public class OrderUserInfo {
    private HmpServiceOrder serviceOrder;

    private SysUser user;

    public HmpServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(HmpServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
