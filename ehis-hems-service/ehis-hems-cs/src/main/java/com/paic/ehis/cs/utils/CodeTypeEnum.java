package com.paic.ehis.cs.utils;

/**
 * 定义码表类型的枚举类
 */
public enum CodeTypeEnum {

    CS_SEX("cs_sex","性别"),
    CS_INSURANCE_SOURCE("cs_insurance_source","投保来源"),
    CS_COMPLAINT_BUSINESS_ITEM("cs_complaint_business_item","投诉业务类别"),






    ;
    /** 码表类型 */
    private String codeType;
    /** 类型描述 */
    private String typeDesc;
    private CodeTypeEnum(String codeType,String typeDesc){
        this.codeType = codeType;
        this.typeDesc = typeDesc;
    }

    public static CodeTypeEnum oneEnum(String code){
        for (CodeTypeEnum codeTypeEnum:CodeTypeEnum.values()) {
            if (code.equals(codeTypeEnum.getCodeType())){
                return codeTypeEnum;
            }
        }
        return null;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

}
