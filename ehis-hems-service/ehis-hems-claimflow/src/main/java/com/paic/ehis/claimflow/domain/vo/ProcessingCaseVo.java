package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName ProcessingCaseVo
 * @Description: TODO 受理工作池
 * @Author 硠君
 * @Date create in 14:46 2021/1/9
 * @Version 1.0
 **/
@Data
public class ProcessingCaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 归档号 */
    private String filingNo;

    /** 理赔类型 */
    @Excel(name = "是否直结 ",readConverterExp = "01=是,02=否")
    private String claimType;

    /** 案件状态 */
    @Excel(name = "案件状态",readConverterExp = "05=受理")
    private String caseStatus;

    /** 停留时长 */
    @Excel(name = "停留时长")
    private String stayTime;

    /** 操作人 */
    private String updateBy;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
