package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseProviderInfo;

import java.util.List;


public class BaseSupplierOutletsDTO {
    private static final long serialVersionUID = 1L;
    /** 供应商编码 */
    private String serialNo;
    private List<BaseProviderInfo> baseProviderInfoList;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public List<BaseProviderInfo> getBaseProviderInfoList() {
        return baseProviderInfoList;
    }

    public void setBaseProviderInfoList(List<BaseProviderInfo> baseProviderInfoList) {
        this.baseProviderInfoList = baseProviderInfoList;
    }
/* private String providerCode;
    private String chname1;
    private String orgFlag;
    private String addressdetail;

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }


    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getChname1() {
        return chname1;
    }

    public void setChname1(String chname1) {
        this.chname1 = chname1;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }*/
}
