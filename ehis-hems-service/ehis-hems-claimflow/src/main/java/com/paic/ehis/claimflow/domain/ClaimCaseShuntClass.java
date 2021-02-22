package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class ClaimCaseShuntClass extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private ClaimCase claimCase;

    /**
     * 01-提示：此被保人只有健康险保单，确认后将提交至健康险
     * 02-提示：该被保人不存在保单信息，请撤件
     */
    private String caseStypeFind;

    //被保人客户号
    private String insuredNo;

    //被保人姓名
    private String name;

    @Override
    public String toString() {
        return "ClaimCaseShuntClass{" +
                "claimCase=" + claimCase +
                ", caseStypeFind='" + caseStypeFind + '\'' +
                ", insuredNo='" + insuredNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
