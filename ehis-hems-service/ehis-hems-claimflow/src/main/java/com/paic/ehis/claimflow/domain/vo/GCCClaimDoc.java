package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GCCClaimDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    //收件人
    private String clmdocReceiver;
    //材料接收日期
    private String clmdocDate;
    //收件机构
    private String clmdocBranch;
    //是否收到材料
    private String clmdocSts;

}
