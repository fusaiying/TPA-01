package com.paic.ehis.system.utility;

public enum ConstraintType {

    LOCATION("00","地区"),


    INTERVAL("10","区间条件"),
    GENDER("11","性别"),
    ISWAITINGPERIOD("12","等待期内/外"),


    HOSCODE("30","医院编码"),
    HOSATTRIBUTE("31","医院属性"),
    HOSLEVEL("32","医院级别"),
    HOSPARTMENTTYPE("33","部门级别"),
    HOSTYPE("34","医院类别"),
    HOSNET("35","网络内/外"),
    HOSDIRECT("36","是否直付");

    private String code;
    private String msg;

    private ConstraintType(String code, String msg) {
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
