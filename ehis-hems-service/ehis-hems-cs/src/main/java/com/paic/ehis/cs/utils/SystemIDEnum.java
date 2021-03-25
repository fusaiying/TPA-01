package com.paic.ehis.cs.utils;

public enum SystemIDEnum {
    SYSTEM_GCC("GCC","金服系统"),
    SYSTEM_TPA_CLAIM("TPA_CLAIM","TAP理赔"),
    ;
    /** 码表类型 */
    private String systemID;
    /** 类型描述 */
    private String systemDesc;
    private SystemIDEnum(String systemID, String systemDesc){
        this.systemID = systemID;
        this.systemDesc = systemDesc;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getSystemDesc() {
        return systemDesc;
    }

    public void setSystemDesc(String systemDesc) {
        this.systemDesc = systemDesc;
    }

}
