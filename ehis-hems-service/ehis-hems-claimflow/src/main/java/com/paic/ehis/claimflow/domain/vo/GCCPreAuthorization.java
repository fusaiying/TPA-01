package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCPreAuthorization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String code;
}
