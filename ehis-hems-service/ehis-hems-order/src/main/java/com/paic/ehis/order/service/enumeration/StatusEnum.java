package com.paic.ehis.order.service.enumeration;

/**
 * 数据状态
 */
public enum StatusEnum {
    VALID("Y","有效"),
    INVALID("N","失效"),
    BACKVALID("S","退回");


    private String code;
    private String msg;

    private StatusEnum(String code, String msg){
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
