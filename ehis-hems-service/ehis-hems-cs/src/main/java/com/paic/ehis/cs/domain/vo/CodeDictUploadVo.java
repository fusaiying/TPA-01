package com.paic.ehis.cs.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class CodeDictUploadVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "上传成功条数")
    private String cgNumber;

    @Excel(name = "上传失败条数")
    private String sbNumber;

    @Excel(name = "导入批次号")
    private String batchNo;

}
