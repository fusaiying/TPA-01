package com.paic.ehis.claimcal.utility;


public enum ElementUnit {

    MONEY("01","币种"),
    DAY("A1","天"),
    TIME("A2","次"),
    PERCENT("A3","%"),
    YEAR("A4","年"),
    MONTH("A5","月");

    private String code;
    private String msg;

    private ElementUnit(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
