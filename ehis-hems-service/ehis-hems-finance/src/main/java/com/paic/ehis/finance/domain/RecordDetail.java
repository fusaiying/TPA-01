package com.paic.ehis.finance.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName RecordDetail
 * @Description: TODO 对公支付明细推送接口细类
 * @Author 硠君
 * @Date create in 17:22 2021/2/23
 * @Version 1.0
 **/
public class RecordDetail {

    /** 业务受理机构 */
    private String branchCode;

    /** 主附续约号 */
    private String brno;

    /** 业务来源类型 */
    private String busiSrcType;

    /** 标识号 */
    private String businessNo;

    /** 分单号 */
    private String certno;

    /** 币种 */
    private String curno;

    /** 部门 */
    private String deptno;

    /** 折扣金额 */
    private String detailDiscountAmount;

    /** 优惠费率 */
    private String detailDiscountRate;

    /** 优惠类型 */
    private String detailDiscountType;

    /** 总金额(TotalAmount=TradAmount+DiscountAmount) */
    private String detailTotalAmount;

    /** 实际交易金额 */
    private String detailTradAmount;

    /** 客户号 */
    private String insno;

    /** 支付方式 */
    private String payMode;

    /** 险种代码 */
    private String planCode;

    /** 险种计划 */
    private String planCodeProject;

    /** 保单号 */
    private String polno;

    /** 产品代码 */
    private String productCode;

    /** 产品计划 */
    private String productProject;

    /** 收据号 */
    private String rcptno;

    /** 机构代码 */
    private String regionCode;

    /** 保单年度 */
    private String polYr;

    /** 缴费年期 */
    private String premTerm;

    /** 缴别 */
    private String premType;

    /** 缴次 */
    private String premTimes;

    /** 客户代码 */
    private String clientno;

    /** 客户名称 */
    private String clientName;

    /** 投保性质 */
    private String appType;

    /** 明细交易类型（核心直结赔付款-011、核心直结追讨款-012、核心直结自付款-013、核心借款-014、TPA直结垫付款-015、TPA直结追讨款-016、TPA直结自付款-017、TPA借款-018） */
    private String amtCode;

    /** 险种性质分类代码（医疗险-HZ、意外险-AZ） */
    private String planAttributeTypeCode;

    /** 险种保险期限类型代码（极短期-0、短期不定期(一年期)-1、短险-2、长期险-3） */
    private String planPeriodTypeCode;

    /** 责任大类 （保障-C、服务-S、税-T、不区分-N）*/
    private String liabType;

    /** 赠险标识 （非赠险-N、赠险-Y）*/
    private String isPresent;

    /** 数据唯一标志 */
    private String uniqueId;

    /** 备注 */
    private String remark;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBrno() {
        return brno;
    }

    public void setBrno(String brno) {
        this.brno = brno;
    }

    public String getBusiSrcType() {
        return busiSrcType;
    }

    public void setBusiSrcType(String busiSrcType) {
        this.busiSrcType = busiSrcType;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getCertno() {
        return certno;
    }

    public void setCertno(String certno) {
        this.certno = certno;
    }

    public String getCurno() {
        return curno;
    }

    public void setCurno(String curno) {
        this.curno = curno;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDetailDiscountAmount() {
        return detailDiscountAmount;
    }

    public void setDetailDiscountAmount(String detailDiscountAmount) {
        this.detailDiscountAmount = detailDiscountAmount;
    }

    public String getDetailDiscountRate() {
        return detailDiscountRate;
    }

    public void setDetailDiscountRate(String detailDiscountRate) {
        this.detailDiscountRate = detailDiscountRate;
    }

    public String getDetailDiscountType() {
        return detailDiscountType;
    }

    public void setDetailDiscountType(String detailDiscountType) {
        this.detailDiscountType = detailDiscountType;
    }

    public String getDetailTotalAmount() {
        return detailTotalAmount;
    }

    public void setDetailTotalAmount(String detailTotalAmount) {
        this.detailTotalAmount = detailTotalAmount;
    }

    public String getDetailTradAmount() {
        return detailTradAmount;
    }

    public void setDetailTradAmount(String detailTradAmount) {
        this.detailTradAmount = detailTradAmount;
    }

    public String getInsno() {
        return insno;
    }

    public void setInsno(String insno) {
        this.insno = insno;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanCodeProject() {
        return planCodeProject;
    }

    public void setPlanCodeProject(String planCodeProject) {
        this.planCodeProject = planCodeProject;
    }

    public String getPolno() {
        return polno;
    }

    public void setPolno(String polno) {
        this.polno = polno;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductProject() {
        return productProject;
    }

    public void setProductProject(String productProject) {
        this.productProject = productProject;
    }

    public String getRcptno() {
        return rcptno;
    }

    public void setRcptno(String rcptno) {
        this.rcptno = rcptno;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getPolYr() {
        return polYr;
    }

    public void setPolYr(String polYr) {
        this.polYr = polYr;
    }

    public String getPremTerm() {
        return premTerm;
    }

    public void setPremTerm(String premTerm) {
        this.premTerm = premTerm;
    }

    public String getPremType() {
        return premType;
    }

    public void setPremType(String premType) {
        this.premType = premType;
    }

    public String getPremTimes() {
        return premTimes;
    }

    public void setPremTimes(String premTimes) {
        this.premTimes = premTimes;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAmtCode() {
        return amtCode;
    }

    public void setAmtCode(String amtCode) {
        this.amtCode = amtCode;
    }

    public String getPlanAttributeTypeCode() {
        return planAttributeTypeCode;
    }

    public void setPlanAttributeTypeCode(String planAttributeTypeCode) {
        this.planAttributeTypeCode = planAttributeTypeCode;
    }

    public String getPlanPeriodTypeCode() {
        return planPeriodTypeCode;
    }

    public void setPlanPeriodTypeCode(String planPeriodTypeCode) {
        this.planPeriodTypeCode = planPeriodTypeCode;
    }

    public String getLiabType() {
        return liabType;
    }

    public void setLiabType(String liabType) {
        this.liabType = liabType;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
