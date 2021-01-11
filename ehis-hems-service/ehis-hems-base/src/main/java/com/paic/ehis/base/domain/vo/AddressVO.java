package com.paic.ehis.base.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class AddressVO {
    private String value;
    private String label;
    private List<AddressVO> children;
}
