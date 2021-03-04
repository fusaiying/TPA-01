package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseBank;
import com.paic.ehis.base.domain.BaseContacts;
import com.paic.ehis.base.domain.BaseSupplierOutlets;
import com.paic.ehis.base.domain.BaseSupplierReceip;

import java.util.List;

public interface PrivateHmpProviderMapper {
    int insertBankAll(List<BaseBank> list);
    int insertContactsAll(List<BaseContacts> list);
    int insertReceiptAll(List<BaseSupplierReceip> list);
    int insertOutletsAll(List<BaseSupplierOutlets> list);


}
