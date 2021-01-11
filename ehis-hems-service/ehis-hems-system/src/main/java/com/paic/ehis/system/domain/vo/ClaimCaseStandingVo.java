package com.paic.ehis.system.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

//输入输出参数类型
public class ClaimCaseStandingVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 批次号 */
    private String batchno;

    /** 报案号 */
    private String rptno;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idno;

    /** 被保险人姓名 */
    @Excel(name = "被保险人姓名")
    private String name;

    /** 理赔材料 */
    @Excel(name = "理赔材料")
    private List<String> claimmaterials;

    /** 其他（案件去向） */
    @Excel(name = "其他", readConverterExp = "案=件去向")
    private String otherinfo;

    public String getBatchno() {
        return batchno;
    }
    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public void setRptno(String rptno)
    {
        this.rptno = rptno;
    }
    public String getRptno()
    {
        return rptno;
    }

    public void setIdno(String idno)
    {
        this.idno = idno;
    }
    public String getIdno()
    {
        return idno;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public List<String> getClaimmaterials() {
        return claimmaterials;
    }

    public void setClaimmaterials(List<String> claimmaterials) {
        this.claimmaterials = claimmaterials;
    }

    public void setOtherinfo(String otherinfo)
    {
        this.otherinfo = otherinfo;
    }
    public String getOtherinfo()
    {
        return otherinfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("batchno", getBatchno())
                .append("rptno", getRptno())
                .append("idno", getIdno())
                .append("name", getName())
                .append("claimmaterials", getClaimmaterials())
                .append("remark", getRemark())
                .append("otherinfo", getOtherinfo())
                .toString();
    }
}
