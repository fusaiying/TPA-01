package com.paic.ehis.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BatchRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 批次号 */
    private String batchno;

    /** 就诊医院ID */
    private String hospitalname;

    /** 理赔类型 */
    private String claimtype;

    /** 交单日期 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitstartdate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitenddate;

    /** 交单机构编码 */
    private String organcode;

    /** 操作人 */
    private String updateBy;

    /** 批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效 */
    private String batchstatus;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatestartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateendTime;

    /** 操作状态 */
    private String operation;
}
