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
    ACTION_TYPE_20("cs_action_type","20","案件复核"),

    /**
     * 代码备用状态
     */
    OPERATE_CODE_01("cs_operate_code","01","受理"),
    OPERATE_CODE_02("cs_operate_code","02","接收"),
    OPERATE_CODE_03("cs_operate_code","03","修改"),
    OPERATE_CODE_04("cs_operate_code","04","转办"),
    OPERATE_CODE_05("cs_operate_code","05","协办"),
    OPERATE_CODE_06("cs_operate_code","06","暂存"),
    OPERATE_CODE_07("cs_operate_code","07","提交"),
    OPERATE_CODE_08("cs_operate_code","08","取消"),
    OPERATE_CODE_09("cs_operate_code","09","上传"),
    OPERATE_CODE_10("cs_operate_code","10","下载"),
    OPERATE_CODE_11("cs_operate_code","11","关联"),
    OPERATE_CODE_12("cs_operate_code","12","解除"),
    OPERATE_CODE_13("cs_operate_code","13","挂起"),
    OPERATE_CODE_14("cs_operate_code","14","催办"),
    OPERATE_CODE_15("cs_operate_code","15","撤销"),
    OPERATE_CODE_16("cs_operate_code","16","发起"),
    OPERATE_CODE_17("cs_operate_code","17","获取"),
    OPERATE_CODE_18("cs_operate_code","18","处理"),
    OPERATE_CODE_19("cs_operate_code","19","结案"),
    OPERATE_CODE_20("cs_operate_code","20","复核"),
    OPERATE_CODE_21("cs_operate_code","21","退回"),
    OPERATE_CODE_22("cs_operate_code","22","导出"),
    OPERATE_CODE_23("cs_operate_code","23","导入"),

    SERVICE_ITEM_01("cs_service_item","01","增值服务类"),
    SERVICE_ITEM_02("cs_service_item","02","理赔类"),
    SERVICE_ITEM_03("cs_service_item","03","其他"),
    SERVICE_ITEM_04("cs_service_item","04","VIP门诊预约"),
    SERVICE_ITEM_05("cs_service_item","05","特殊门诊直接结算服务"),
    SERVICE_ITEM_06("cs_service_item","06","门诊直接结算"),
    SERVICE_ITEM_07("cs_service_item","07","住院直接结算"),
    SERVICE_ITEM_08("cs_service_item","08","门诊中医治疗直接结算"),
    SERVICE_ITEM_B00006("cs_service_item","B00006","投诉"),
    SERVICE_ITEM_B00034("cs_service_item","B00034","根因改善"),







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
