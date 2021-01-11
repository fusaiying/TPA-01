package com.paic.ehis.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class DeptVo implements Serializable {

    private Long id;
    private String name;
    private Boolean enabled;
    private Long pid;
    private Timestamp createTime;
    private String comcode;
    private String address;
    private String superComcode;

}
