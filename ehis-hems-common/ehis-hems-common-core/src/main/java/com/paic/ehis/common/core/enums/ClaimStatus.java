package com.paic.ehis.common.core.enums;

/**
 * Created by IntelliJ IDEA
 * User: fusaiying-傅赛赢
 *
 * @author: 傅赛赢
 * Date: 2021/2/7
 * subject:
 */
public enum ClaimStatus {

    BATCHTENDER("01", "交单"),

    BATCHREVIEW("02", "交单复核"),

    BATCHFINISH("03", "交单完成"),

    BATCHRETURN("04", "交单退回"),

    BATCHINVALID("05", "交单无效"),

    CASEHANGUP("00", "挂起"),

    CASEFINISH("04", "交单完成"),

    CASEACCEPTED("05", "受理"),

    CASEPROBLEM("30", "问题件"),

    CASEWITHDRAWALAPPEALED("97", "撤件可申诉"),

    CASEWITHDRAWAL("98", "撤件"),

    CASEENTERING("06", "录入"),

    CASEAUDIT("07", "审核"),

    CASETALKING("31", "协谈"),

    CASESURVEY("32", "调查"),

    CASESPOTCHECK("08", "抽检"),

    CASECIOSE("09", "结案"),

    DATAYES("Y","有效"),

    DATANO("N","无效");

    private String code;
    private String msg;

    private ClaimStatus(String code, String msg) {
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
