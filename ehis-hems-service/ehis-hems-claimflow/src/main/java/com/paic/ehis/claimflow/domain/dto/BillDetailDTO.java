package com.paic.ehis.claimflow.domain.dto;


import com.paic.ehis.claimflow.domain.vo.CaseCalBillVo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName BillDetailDTO
 * @Description: TODO
 * @Author 硠君
 * @Date create in 18:02 2021/1/22
 * @Version 1.0
 **/
@Data
public class BillDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    ArrayList<CaseCalBillVo> billDetailList;

   /* *//** 赔付ID *//*
    private Long calBillId;

    *//** 赔付费用明细ID *//*
    private Long calItemId;

    *//** 赔付金额 *//*
    private BigDecimal payAmount;

    *//** 备注 *//*
    private String remark;

    *//** 赔付结论 *//*
    private String payConclusion;

    *//** 保单号 *//*
    private String policyNo;

    *//** 产品编码 *//*
    private String riskCode;

    *//** 计划编码 *//*
    private String planCode;

    *//** 责任明细编码 *//*
    private String dutyDetailCode;

    *//** 责任编码 *//*
    private String dutyCode;*/
}
