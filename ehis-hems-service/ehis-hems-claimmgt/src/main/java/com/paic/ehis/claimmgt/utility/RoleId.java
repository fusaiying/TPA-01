package com.paic.ehis.claimmgt.utility;

public enum RoleId {
    ACCEPTED("01","05"),
    ENTERING("02","06"),
    Adjustment("03","07"),
    spotcheck("04","08"),
    PROBLEM("05","30"),
    coordinate("06","31"),
    survey("07","32");

    private String code;
    private String msg;

    private RoleId(String code, String msg) {
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
