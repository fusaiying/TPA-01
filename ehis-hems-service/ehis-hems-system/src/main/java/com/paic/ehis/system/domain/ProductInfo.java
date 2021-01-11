package com.paic.ehis.system.domain;

import java.util.List;

public class ProductInfo {

    private HmpProdInfo prodInfo;

    private List<HmpProdServCom> prodServComs;

    private List<HmpComWebSite> comWebSites;

    private List<HmpProdSalCom> prodSalComs;

    private HmpAuditTrack audit;

    public HmpProdInfo getProdInfo() {
        return prodInfo;
    }

    public void setProdInfo(HmpProdInfo prodInfo) {
        this.prodInfo = prodInfo;
    }

    public List<HmpProdServCom> getProdServComs() {
        return prodServComs;
    }

    public void setProdServComs(List<HmpProdServCom> prodServComs) {
        this.prodServComs = prodServComs;
    }

    public List<HmpComWebSite> getComWebSites() {
        return comWebSites;
    }

    public void setComWebSites(List<HmpComWebSite> comWebSites) {
        this.comWebSites = comWebSites;
    }

    public List<HmpProdSalCom> getProdSalComs() {
        return prodSalComs;
    }

    public void setProdSalComs(List<HmpProdSalCom> prodSalComs) {
        this.prodSalComs = prodSalComs;
    }

    public HmpAuditTrack getAudit() {
        return audit;
    }

    public void setAudit(HmpAuditTrack audit) {
        this.audit = audit;
    }
}
