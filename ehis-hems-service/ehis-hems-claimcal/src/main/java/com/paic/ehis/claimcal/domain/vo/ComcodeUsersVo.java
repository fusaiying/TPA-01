package com.paic.ehis.claimcal.domain.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: lyc
 * @CreateTime: 2020/11/30
 * @Company:
 */
@Data
public class ComcodeUsersVo {
    private Long id;
    private String username;
    private String enabled;
    private String name;
    private int level;
    private Long comcode;

}
