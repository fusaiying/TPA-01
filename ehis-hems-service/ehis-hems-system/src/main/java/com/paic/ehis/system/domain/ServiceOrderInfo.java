package com.paic.ehis.system.domain;

public class ServiceOrderInfo {

    private HmpBenefitCustomerService customerService;

    private HmpServiceOrder serviceOrder;

    private HmpServiceOrderApplication serviceOrderApplication;

    public HmpBenefitCustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(HmpBenefitCustomerService customerService) {
        this.customerService = customerService;
    }

    public HmpServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(HmpServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public HmpServiceOrderApplication getServiceOrderApplication() {
        return serviceOrderApplication;
    }

    public void setServiceOrderApplication(HmpServiceOrderApplication serviceOrderApplication) {
        this.serviceOrderApplication = serviceOrderApplication;
    }
}
