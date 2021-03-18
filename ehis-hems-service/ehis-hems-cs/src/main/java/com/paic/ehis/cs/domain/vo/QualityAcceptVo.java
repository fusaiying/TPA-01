package com.paic.ehis.cs.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

@Data
public class QualityAcceptVo {

    @Excel(name = "工单号")
    private String workOrderNo;

    @Excel(name = "出单机构",readConverterExp = "0000001=上海分公司本部,00000011=上海分公司")
    private String organCode;

    @Excel(name = "服务项目",readConverterExp = "01=增值服务类,02=理赔类,03=其他,04=VIP门诊预约,05=特殊门诊直接结算服务,06=门诊直接结算,07=住院直接结算,08=门诊中医治疗直接结算,B00006=投诉,B00034=根因改善")
    private String itemCode;

    @Excel(name = "结案日期", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String endDate;

    @Excel(name = "质检分组")
    private String itemType;

    @Excel(name = "质检人")
    private String updatedBy;

   // @Excel(name = "质检日期")
    private String updatedTime;

    @Excel(name = "质检结果")
    private String result;
}
