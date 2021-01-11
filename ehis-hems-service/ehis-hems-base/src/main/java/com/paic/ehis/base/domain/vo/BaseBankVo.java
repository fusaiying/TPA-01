package com.paic.ehis.base.domain.vo;


import com.paic.ehis.base.domain.BaseBank;
import com.paic.ehis.base.domain.BaseProviderSettle;
import lombok.Data;

import java.util.List;

/**
 * base_bank（银行信息）对象 base_bank
 * 
 * @author sino
 * @date 2020-12-25
 */
@Data
public class BaseBankVo
{
    private static final long serialVersionUID = 1L;


    private BaseProviderSettle baseProviderSettle;


    private List<BaseBank> BaseBankVo;
}
