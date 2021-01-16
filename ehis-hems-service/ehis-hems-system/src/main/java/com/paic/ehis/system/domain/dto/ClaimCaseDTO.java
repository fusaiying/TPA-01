package com.paic.ehis.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName ClaimCaseDTO
 * @Description: TODO
 * @Author 硠君
 * @Date create in 15:16 2021/1/9
 * @Version 1.0
 **/
@Data
public class ClaimCaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    private String rptNo;

    /** 批次号 */
    private String batchNo;

    /** 被保人姓名 */
    private String name;

    /** 案件状态 */
    private String caseStatus;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 是否历史 */
    private String isHistory;

    /** operation */
    private String operation;

    /** 状态（Y-有效，N-无效） */
    private String status;

    /** 操作人 */
    private String updateBy;
}
