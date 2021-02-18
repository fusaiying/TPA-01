package com.paic.ehis.claimflow.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class CaseDispatchDTO extends BaseEntity {

    /** 报案号 */
    @Excel(name = "报案号")
    private List<String> rptNo;

    @Excel(name = "流程节点操作人")
    private String operator;
}
