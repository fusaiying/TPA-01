package com.paic.ehis.system.utility;


public enum RuleElement {
    YEARLIMIT("01","年上限"),
    DAYLIMIT("02","日上限"),
    TIMELIMIT("03","次上限"),
    YEARDEDUCTIBLE("04","年免赔"),
    COPAY("05","赔付比例"),
    WAITINGPERIOD("06","等待期"),
    TIMEDEDUCTIBLE("07","次免赔"),
    ENSUREREGION("08","保障区域");

    private String code;
    private String msg;

    private RuleElement(String code, String msg) {
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