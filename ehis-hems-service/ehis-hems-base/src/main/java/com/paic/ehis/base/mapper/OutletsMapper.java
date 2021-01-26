package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseSupplierOutlets;

import java.util.List;

public interface OutletsMapper {
    public List<BaseSupplierOutlets> selectOutletsList(BaseSupplierOutlets baseSupplierOutlets);
}
