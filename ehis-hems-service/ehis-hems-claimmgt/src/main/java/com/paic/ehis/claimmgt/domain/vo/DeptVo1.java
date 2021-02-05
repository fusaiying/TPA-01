package com.paic.ehis.claimmgt.domain.vo;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.system.api.domain.SysDept;

import lombok.Data;

import java.util.List;

@Data
public class DeptVo1 extends BaseEntity {
    private long deptId;
    private List<SysDept> deptlist;
}
