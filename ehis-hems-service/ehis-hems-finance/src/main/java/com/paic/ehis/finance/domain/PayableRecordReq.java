package com.paic.ehis.finance.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName PayableRecordReq
 * @Description: TODO 对公支付明细推送
 * @Author 硠君
 * @Date create in 18:12 2021/2/23
 * @Version 1.0
 **/

public class PayableRecordReq {

    /** 请求时间 */
    private String requestTime;

    /** 场景代码 */
    private String sceneCode;

    /** 签 */
    private String sign;

    /** 系统代码 */
    private String systemCode;

    /** 记账ID（申诉时携带） */
    private String accRecordID;

    /** 业务申请号 */
    private String busiApplyNo;

    /** 唯一申请号 */
    private String busiOrderId;

    /** 业务类型（TPA业务场景-07） */
    private String busiType;

    /** 关联id（申诉时必写busiOrderId） */
    private String relationId;

    /** 申诉标识 （已支付交易申诉-01、未支付交易申诉-02） */
    private String appealFlag;

    /** 收据号 */
    private String rcptno;

    /** 实际交易金额 */
    private List<RecordDetail> recordDetails;

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getAccRecordID() {
        return accRecordID;
    }

    public void setAccRecordID(String accRecordID) {
        this.accRecordID = accRecordID;
    }

    public String getBusiApplyNo() {
        return busiApplyNo;
    }

    public void setBusiApplyNo(String busiApplyNo) {
        this.busiApplyNo = busiApplyNo;
    }

    public String getBusiOrderId() {
        return busiOrderId;
    }

    public void setBusiOrderId(String busiOrderId) {
        this.busiOrderId = busiOrderId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getAppealFlag() {
        return appealFlag;
    }

    public void setAppealFlag(String appealFlag) {
        this.appealFlag = appealFlag;
    }

    public String getRcptno() {
        return rcptno;
    }

    public void setRcptno(String rcptno) {
        this.rcptno = rcptno;
    }

    public List<RecordDetail> getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(List<RecordDetail> recordDetails) {
        this.recordDetails = recordDetails;
    }
}
