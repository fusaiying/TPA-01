package com.paic.ehis.order.service.enumeration;

/**
 * 医疗-服务结算业务状态
 */
public enum BalanceStatusEnum {
    // 业务状态
    WAIT_CONFIRM("01","发起"),
    ALREADY_CONFIRM("02","确认"),
    WAIT_CONFIRM_BACK("03","发起（退回）"),
    ALREADY_EXAMINE("04","审核"),
    ALREADY_WRITE("05","核销"),

    //流程状态
    TYPE_DELETE("DEL","删除"),
    TYPE_IMPORT("IMPORT","导入"),
    TYPE_CONFIRM("CONFIRM","确认"),
    TYPE_EXAMIN("EXAMIN","审核"),
    TYPE_WRITE("WRITE","核销"),
    TYPE_INVOICE("INVOICE","发票核销");


    private String code;
    private String msg;

    private BalanceStatusEnum(String code, String msg){
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
