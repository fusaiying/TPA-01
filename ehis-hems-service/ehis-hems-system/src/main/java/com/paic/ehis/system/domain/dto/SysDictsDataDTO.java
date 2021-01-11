package com.paic.ehis.system.domain.dto;

import com.paic.ehis.system.domain.SysDictData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SysDictsDataDTO implements Serializable {
    @ApiModelProperty(value = "字典类型")
    private String DictType;

    @ApiModelProperty(value = "字典数据")
    private List<SysDictData> DictDate;
}
