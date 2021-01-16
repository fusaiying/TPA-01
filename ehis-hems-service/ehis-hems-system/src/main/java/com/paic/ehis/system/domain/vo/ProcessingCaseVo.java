package com.paic.ehis.system.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName ProcessingCaseVo
 * @Description: TODO
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
    @Excel(name = "案件状态")
    private String caseStatus;

    /** 停留时长 */
    @Excel(name = "停留时长")
    private String stayTime;
}
