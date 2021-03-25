package com.paic.ehis.cs.utils;

/**
 * 用于代码中处理码值
 * by Louis
 */

public enum CodeEnum {
    /**
     * 性别
     */
    SEX_01("cs_sex", "01", "男"),
    SEX_02("cs_sex", "02", "女"),

    /**
     * 工单状态
     */
    ORDER_STATE_01("cs_order_state","01","待处理"),
    ORDER_STATE_02("cs_order_state","02","处理中"),
    ORDER_STATE_03("cs_order_state","03","已处理"),
    ORDER_STATE_04("cs_order_state","04","已完成"),
    ORDER_STATE_05("cs_order_state","05","已取消"),
    /**
     * 协办状态
     */
    COLLABORATIVE_STATE_01("cs_collaborative_state","01","协办中"),
    COLLABORATIVE_STATE_02("cs_collaborative_state","02","已协办"),

    /**
     * 质检状态
     */
    INSPECTION_STATE_01("cs_inspection_state","01","待质检"),
    INSPECTION_STATE_02("cs_inspection_state","02","质检中"),
    INSPECTION_STATE_03("cs_inspection_state","03","质检完成"),
   /**
    * 质检处理状态
    */
    CONFIRM_STATE_01("cs_confirm_state","01","待确认"),
    CONFIRM_STATE_02("cs_confirm_state","02","已确认"),
    CONFIRM_STATE_03("cs_confirm_state","03","结案"),
    CONFIRM_STATE_04("cs_confirm_state","04","处理中"),

    /** 质检结果 */
    INSPECTION_RESULT_01("cs_inspection_result","01","合格"),
    INSPECTION_RESULT_02("cs_inspection_result","02","不合格"),

    /**
     * 业务类型
     */
    BUSINESS_TYPE_01("cs_business_type","01","信息需求"),
    BUSINESS_TYPE_02("cs_business_type","02","预约"),
    BUSINESS_TYPE_03("cs_business_type","03","投诉"),

    /**
     * 流程编号
     */
    LINK_CODE_01("cs_link_code","01","待处理"),
    LINK_CODE_02("cs_link_code","02","处理中"),
    LINK_CODE_03("cs_link_code","03","已处理"),
    LINK_CODE_04("cs_link_code","04","已完成"),
    LINK_CODE_05("cs_link_code","05","已取消"),
    LINK_CODE_06("cs_link_code","06","协办中"),
    LINK_CODE_07("cs_link_code","07","已协办"),
    LINK_CODE_08("cs_link_code","08","待质检"),
    LINK_CODE_09("cs_link_code","09","质检中"),
    LINK_CODE_10("cs_link_code","10","质检完成"),

    /**
     * 操作状态
     */
    ACTION_TYPE_01("cs_action_type","01","新增"),
    ACTION_TYPE_02("cs_action_type","02","获取"),
    ACTION_TYPE_03("cs_action_type","03","修改"),
    ACTION_TYPE_04("cs_action_type","04","取消"),
    ACTION_TYPE_05("cs_action_type","05","处理"),
    ACTION_TYPE_06("cs_action_type","06","受理"),
    ACTION_TYPE_07("cs_action_type","07","上传附件"),
    ACTION_TYPE_08("cs_action_type","08","提交"),
    ACTION_TYPE_09("cs_action_type","09","确定"),
    ACTION_TYPE_10("cs_action_type","10","转办"),
    ACTION_TYPE_11("cs_action_type","11","协办完成"),
    ACTION_TYPE_12("cs_action_type","12","暂存"),
    ACTION_TYPE_13("cs_action_type","13","发起质检"),
    ACTION_TYPE_14("cs_action_type","14","响应"),
    ACTION_TYPE_15("cs_action_type","15","发起协办"),
    ACTION_TYPE_16("cs_action_type","16","撤销协办"),
    ACTION_TYPE_17("cs_action_type","17","退回修改"),
    ACTION_TYPE_18("cs_action_type","18","结案"),
    ACTION_TYPE_19("cs_action_type","19","差错提交"),













    ;

    /**
     * 码表类型:对应字典中的命名
     */
    private String codeType;
    /**
     * 本系统码表值:对应编码
     */
    private String code;
    /**
     * 码表值描述：对应编码意思
     */
    private String codeDesc;

    private CodeEnum(String codeType, String code, String codeDesc) {
        this.codeType = codeType;
        this.code = code;
        this.codeDesc = codeDesc;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

}
