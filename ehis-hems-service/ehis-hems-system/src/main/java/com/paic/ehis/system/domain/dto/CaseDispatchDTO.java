package com.paic.ehis.system.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CaseDispatchDTO  implements Serializable {
    /** 报案号 */
    @Excel(name = "报案号")
    private List<String> rptNo;
    /** 更新者 */
    private String updateBy;
}
