package com.paic.ehis.system.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DictTypeVo implements Serializable {
    @ApiModelProperty(value = "字典类型")
    private String DictType;
}
