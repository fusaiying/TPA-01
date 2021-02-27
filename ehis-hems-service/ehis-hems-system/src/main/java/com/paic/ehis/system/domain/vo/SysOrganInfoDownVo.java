package com.paic.ehis.system.domain.vo;

import com.paic.ehis.system.domain.SysOrganInfo;
import lombok.Data;

import java.util.List;

@Data
public class SysOrganInfoDownVo {

    private SysOrganInfo sysOrganInfo;

    private List<SysOrganInfo> sysOrganInfoList;
}
