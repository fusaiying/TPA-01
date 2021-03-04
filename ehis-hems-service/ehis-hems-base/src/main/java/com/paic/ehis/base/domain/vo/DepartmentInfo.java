package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseProviderDep;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentInfo {
    private static final long serialVersionUID = 1L;

    private List<BaseProviderDep> form;

}
