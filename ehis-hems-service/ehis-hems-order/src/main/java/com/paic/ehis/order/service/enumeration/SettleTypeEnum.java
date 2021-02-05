package com.paic.ehis.order.service.enumeration;

/**
 * 结算类型
 */
public enum SettleTypeEnum {
    SETTLE_TYPE_CASE("01","按case（工单）"),
    SETTLE_TYPE_CONT("02","按人头（保单）"),
    SETTLE_TYPE_PRICE("03","按一口价");


    private String code;
    private String msg;

    private SettleTypeEnum(String code, String msg){
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
