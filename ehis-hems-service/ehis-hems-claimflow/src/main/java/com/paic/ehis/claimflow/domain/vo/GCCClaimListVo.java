package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 提供GCC理赔信息查询接口 案件列表对象
 */
@Data
public class GCCClaimListVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //主险名称
    private String mainPlanName;
    private String polno;
    private String customerNo;
    private Integer age;
    //客户对象信息
//    private GCCCustomerInfo customerInfoMap;
    //主险代码
    private String mainPlanCode;
    //理赔案件信息
    private List<GCCClaimInfo> claimList;
}
