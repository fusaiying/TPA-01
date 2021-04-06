package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GCCHealthServiceInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mainPlanName;
    //
    private GCCCustomerInfo customerInfoMap;
    private String mainPlanCode;
    //
    private Object directPayMap;
    //服务信息
    private List<GCCServiceInfo> serviceInfoList;
    //适用直接新策略
    private String applyToDirectPayNewRule;
    //是否显示新策略标志
    private String isHighQualityPlanCode;
}
