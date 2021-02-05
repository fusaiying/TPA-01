package com.paic.ehis.order.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class Ordertaking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //供应商编码
    private String supplierCode;

    //已接单次数
    private String num;

    //优先级
    private String priority;

    //日限次数
    private String limitnum;

    //可用次数
    private String availablenum;

}
