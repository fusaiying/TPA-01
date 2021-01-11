package com.paic.ehis.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IssuingCompanyVo implements Serializable {

    private String companycode;

    private String companyname;

    private String simplename;

}
