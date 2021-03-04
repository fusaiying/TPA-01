package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_service_category_kind对象 base_service_category_kind
 * 
 * @author sino
 * @date 2020-12-28
 */
public class BaseServiceCategoryKind extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 一级编码 */
    private String codei;

    /** 类别 */
    @Excel(name = "类别")
    private String category;

    /** 二级编码 */
    private String codeii;

    /** 种类 */
    @Excel(name = "种类")
    private String variety;

    public void setCodei(String codei) 
    {
        this.codei = codei;
    }

    public String getCodei() 
    {
        return codei;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setCodeii(String codeii) 
    {
        this.codeii = codeii;
    }

    public String getCodeii() 
    {
        return codeii;
    }
    public void setVariety(String variety) 
    {
        this.variety = variety;
    }

    public String getVariety() 
    {
        return variety;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("codei", getCodei())
            .append("category", getCategory())
            .append("codeii", getCodeii())
            .append("variety", getVariety())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
