package com.paic.ehis.claimapt.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName BatchDTO
 * @Description: TODO 机构交单入参
 * @Author 硠君
 * @Date create in 10:46 2020/12/24
 * @Version 1.0
 **/
@Data
public class BatchDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 批次号 */
    private String batchno;

    /** 页面传回的值为就诊医院名称的模糊查询，但数据库存储数据为就诊医院ID */
    private String hospitalname;

    /** 批次理赔类型 */
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

    /** 状态（Y-有效，N-无效） */
    private String status;

    /** 报案号 */
    private String rptno;

}
