package com.paic.ehis.common.core.enums;


public enum PbwOnlineP {

    Success("00", "调用成功"),

    Defeat("02", "调用失败"),

    BatchSources0("0", "线下交单"),

    BatchSourcesD("D", "E结算"),

    BatchSourcesj("T", "基金物流件"),

    P_sourcesOne("01", "在线交单"),

    P_sourcesTwo("02", "E结算"),

    Claim_typeOne("01","直结"),

    Claim_typeTwo("02","事后");

    private String status;
    private String message;

    private PbwOnlineP(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
