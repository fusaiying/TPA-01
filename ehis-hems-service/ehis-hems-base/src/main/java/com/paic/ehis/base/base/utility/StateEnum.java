package com.paic.ehis.base.base.utility;


public enum StateEnum {
    SUCCESS("1","成功"),
    FAIL("0","失败");

    private String code;
    private String msg;

    private StateEnum(String code, String msg){
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
