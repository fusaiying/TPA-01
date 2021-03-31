package com.paic.ehis.cs.domain.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class InformationNeedsQuality {
    //信息需求质检清单

    @Excel(name = "质检号")
    private String inspectionId;

    @Excel(name = "工单号")
    private String workOrderNo;

    @Excel(name = "保单号")
    private String policyNo;

    @Excel(name = "分单号")
    private String policyItemNo;

    @Excel(name = "受理人")
    private String acceptBy;

    @Excel(name = "处理人")
    private String cupdatedBy;

    @Excel(name = "质检人")
    private String zupdatedBy;

    @Excel(name = "质检状态",readConverterExp = "01=待质检,02=质检中,03=质检完成")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理日期", width = 30,  dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String acceptTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理日期", width = 30,  dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String clickTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "抽检日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String ccreatedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "质检日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String zcreatedTime;

    @Excel(name = "是否时效内响应客户")
    private String zxc21;

    @Excel(name = "是否符合短信结案规则")
    private String zxc22;

    @Excel(name = "是否电话通知客户")
    private String zxc23;

    @Excel(name = "是否满足客户诉求")
    private String zxc24;

    @Excel(name = "是否及时升级投诉")
    private String zxc25;

    @Excel(name = "是否规范记录")
    private String zxc26;

    @Excel(name = "是否有其他错误")
    private String zxc27;

    @Excel(name = "是否申诉")
    private String appealFlag;

    @Excel(name = "申述理由")
    private String appealReason;

    @Excel(name = "质检结果")
    private String result;

    @Excel(name = "质检分组")
    private String center;

    @Excel(name = "服务项目")
    private String itemCode;

    @Excel(name = "服务提供结构")
    private String organCode;

    @Excel(name = "激活次数")
    private String activationNum;
}
