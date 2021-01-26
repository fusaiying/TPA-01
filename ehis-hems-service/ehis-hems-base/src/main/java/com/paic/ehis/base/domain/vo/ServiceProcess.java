package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseServiceApplyorimpl;
import com.paic.ehis.base.domain.BaseServiceProcess;
import lombok.Data;

import java.util.List;

/**
 * 服务项目流程及配置字段
 *
 * @author sino
 * @date 2020-12-29
 */
@Data
public class ServiceProcess {
    /** 服务项目编码  */
    private String servicecode;
    /** 流程配置信息   */
    private List<BaseServiceProcess> processList;
    /** 字段信息   */
    private List<BaseServiceApplyorimpl> fieldList;
    /** 服务项目状态 */
    private String status;

}
