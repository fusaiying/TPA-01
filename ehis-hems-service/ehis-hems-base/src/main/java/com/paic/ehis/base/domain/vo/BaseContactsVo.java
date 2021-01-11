package com.paic.ehis.base.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.base.domain.BaseContacts;
import lombok.Data;

import java.util.List;

/**
 * base_contacts（联系人信息）对象 base_contacts
 * 
 * @author sino
 * @date 2020-12-25
 */
@Data
public class BaseContactsVo
{
    private static final long serialVersionUID = 1L;

    /** 所属单位编码 */
    @Excel(name = "所属单位编码")
    private String suppliercode;

    private List<BaseContacts> aseContacts;

}
