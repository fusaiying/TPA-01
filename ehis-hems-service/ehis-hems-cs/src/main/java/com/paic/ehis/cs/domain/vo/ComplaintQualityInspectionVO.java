package com.paic.ehis.cs.domain.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class ComplaintQualityInspectionVO {
    //投诉质检清单

    @Excel(name = "工单号")
    private String workOrderNo;

    @Excel(name = "出单机构",readConverterExp = "0000001=上海分公司本部,00000011=上海分公司")
    private String organCode;

    @Excel(name = "处理人")
    private String modifyBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @Excel(name = "质检状态",readConverterExp = "01=待质检,02=质检中,03=质检完成")
    private String status;

    @Excel(name = "质检结果",readConverterExp = "01=合格,02=不合格")
    private String result;


    @Excel(name = "响应时效是否存在差错")
    private String qwe02;
    @Excel(name = "响应时效差错说明")
    private String asd02;

    @Excel(name = "投诉损失是否存在差错")
    private String qwe10;
    @Excel(name = "投诉损失是否存在差错说明")
    private String asd10;

    @Excel(name = "受理渠道是否存在差错")
    private String qwe04;
    @Excel(name = "受理渠道差错说明")
    private String asd04;

    @Excel(name = "致诉根因是否存在差错")
    private String qwe14;
    @Excel(name = "致诉根因差错说明")
    private String asd14;

    @Excel(name = "投诉分类是否存在差错")
    private String qwe05;
    @Excel(name = "投诉分类差错说明")
    private String asd05;

    @Excel(name = "投诉是否成立是否存在差错")
    private String qwe11;
    @Excel(name = "投诉是否成立差错说明")
    private String asd11;

    @Excel(name = "处理结果是否存在差错")
    private String qwe15;
    @Excel(name = "处理结果差错说明")
    private String asd15;

    @Excel(name = "投诉原因是否存在差错")
    private String qwe08;
    @Excel(name = "投诉原因差错说明")
    private String asd08;

    @Excel(name = "附件完整性是否存在差错")
    private String qwe16;
    @Excel(name = "附件完整性差错说明")
    private String asd16;

    @Excel(name = "根因改善是否存在差错")
    private String qwe13;
    @Excel(name = "根因改善差错说明")
    private String asd13;

    @Excel(name = "案件真实性是否存在差错")
    private String qwe20;
    @Excel(name = "案件真实性差错说明")
    private String asd20;

    @Excel(name = "投诉件录入时效是否存在差错")
    private String qwe01;
    @Excel(name = "投诉件录入时效差错说明")
    private String asd01;

    @Excel(name = "根因改善闭环时效是否存在差错")
    private String qwe03;
    @Excel(name = "根因改善闭环时效差错说明")
    private String asd03;

    @Excel(name = "征求处理意见是否存在差错")
    private String qwe17;
    @Excel(name = "征求处理意见差错说明")
    private String asd17;

    @Excel(name = "客户反馈是否存在差错")
    private String qwe09;
    @Excel(name = "客户反馈差错说明")
    private String asd09;

    @Excel(name = "监管是否计件是否存在差错")
    private String qwe06;
    @Excel(name = "监管是否计件差错说明")
    private String asd06;

    @Excel(name = "监管撤诉状态是否存在差错")
    private String qwe07;
    @Excel(name = "监管撤诉状态差错说明")
    private String asd07;

    @Excel(name = "投诉根因部门是否存在差错")
    private String qwe12;
    @Excel(name = "投诉根因部门差错说明")
    private String asd12;

    @Excel(name = "处理意见是否存在差错")
    private String qwe18;
    @Excel(name = "处理意见差错说明")
    private String asd18;

    @Excel(name = "行协调或外部鉴定状态是否存在差错")
    private String qwe19;
    @Excel(name = "行协调或外部鉴定状态差错说明")
    private String asd19;

    @Excel(name = "是否申诉",readConverterExp = "01=是,02=否")
    private String appealFlag;
    @Excel(name = "申诉理由")
    private String appealReason;

    @Excel(name = "一级投诉分类")
    private String ccl1;
    @Excel(name = "二级投诉分类")
    private String ccl2;

    @Excel(name = "是否计件")
    private String flag;
    @Excel(name = "撤诉状态")
    private String handleProp5;
    @Excel(name = "投诉是否成立",readConverterExp = "01=是,02=否")
    private String handleProp1;
    @Excel(name = "投诉不成立理由")
    private String handleProp6;

    @Excel(name = "一级投诉原因")
    private String rlevel1;
    @Excel(name = "二级投诉原因")
    private String rlevel2;
    @Excel(name = "三级投诉原因")
    private String rlevel3;

    @Excel(name = "投诉环节(报保监)")
    private String handleProp11;
    @Excel(name = "投诉问题(报保监)")
    private String handleProp12;
    /*@Excel(name = "业务处理情况")
    private String handleProp1;*/
    @Excel(name = "险种类型")
    private String handleProp14;
    @Excel(name = "营销渠道")
    private String handleProp15;
    @Excel(name = "投诉业务类别")
    private String handleProp16;
    @Excel(name = "投诉根因部门")
    private String handleProp17;
    @Excel(name = "致诉根因")
    private String handleProp30;
    @Excel(name = "处理结果")
    private String remark;
    @Excel(name = "客户反馈",readConverterExp = "01=满意,02=接受,03=不接受")
    private String handleProp2;
    @Excel(name = "根因改善")
    private String handleProp19;
    @Excel(name = "投诉损失")
    private String handleProp20;
    @Excel(name = "质检评分(分)")
    private String score;

    /*@Excel(name = "首次反馈时效")
    private String ;*/
    @Excel(name = "质检方式")
    private String way;

}
