package com.paic.ehis.claimflow.domain.vo;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * @Description:
 * @Author: lyc
 * @CreateTime: 2020/11/30
 * @Company:
 */
@Data
public class UserVo extends BaseEntity {
    private Long id;
    private String userName;
}
