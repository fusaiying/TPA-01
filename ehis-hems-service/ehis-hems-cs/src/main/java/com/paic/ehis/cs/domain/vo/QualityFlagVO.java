package com.paic.ehis.cs.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

@Data
public class QualityFlagVO {
    @Excel(name = "工单号")
    String workOrderNo;
    @Excel(name = "服务项目",readConverterExp = "01=增值服务类,02=理赔类,03=其他,04=VIP门诊预约,05=特殊门诊直接结算服务,06=门诊直接结算,07=住院直接结算,08=门诊中医治疗直接结算,B00006=投诉,B00034=根因改善")
    String itemCode;
    @Excel(name = "结案处理人")
    String modifyBy;
    @Excel(name = "质检人")
    String updatedBy;

    String businessType;
}
