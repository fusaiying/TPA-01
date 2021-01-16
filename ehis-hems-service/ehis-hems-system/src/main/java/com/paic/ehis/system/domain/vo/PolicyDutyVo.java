package com.paic.ehis.system.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PolicyDutyVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private  List<DutyVo> policyInfoList;
}
