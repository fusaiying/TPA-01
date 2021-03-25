package com.paic.ehis.cs.utils;

/**
 * 码表映射
 * by Louis
 **/
public enum  CodeMappingEnum {
    CLAIM_SEX_01("CLAIM","cs_sex","M","01","男"),
    CLAIM_SEX_02("CLAIM","cs_sex","F","02","女"),



    ;
    private String systemID;
    private String codeType;
    private String sCode;
    private String dCode;
    private String codeDesc;
    /**
     *
     * @param systemID 映射其它系统编码
     * @param codeType 本系统中码表类型
     * @param sCode 其它系统中码表编码
     * @param dCode 本系统中码表编码
     * @param dCode 本系统中码表编码说明
     */
    private CodeMappingEnum(String systemID,String codeType,String sCode,String dCode,String codeDesc){
        this.systemID = systemID;
        this.codeType = codeType;
        this.sCode =sCode;
        this.dCode = dCode;
        this.codeDesc = codeDesc;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
