package com.paic.ehis.cs.domain.vo;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.CodeDict;
import lombok.Data;

import java.util.List;

@Data
public class CodeEnumVo1 extends BaseEntity {

    private String codeType;
    private List<CodeDict> voList;

}
