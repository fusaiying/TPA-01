package com.paic.ehis.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class DictDetailDTO implements Serializable {

    private Long id;

    private String label;

    private String value;

    private String sort;

    private Timestamp createTime;
}