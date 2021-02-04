package com.paic.ehis.system.domain.dto;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName ProcessedProductDTO
 * @Description: TODO 产品定义已处理工作池
 * @Author 硠君
 * @Date create in 11:01 2021/1/12
 * @Version 1.0
 **/
@Data
public class ProcessedProductDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 产品编码 */
    private String riskCode;

    /** 产品名称 */
    private String riskName;

    /** 产品状态 */
    private String riskStatus;

    /** 产品状态 */
    private String riskLogStatus;

    /** 是否历史 */
    private String isHistory;

    /** 状态（Y-有效，N-无效） */
    private String status;

    /** 操作人 */
    private String updateBy;
}
