package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCCustomerInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //保单号
    private String polno;
    //客户号
    private String customerNo;
    //年龄
    private String age;
    //分单号
    private String certno;
    //被保人姓名
    private String insName;
}
