package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseContacts;
import lombok.Data;

import java.util.List;

@Data
public class ContactInfo {
    private static final long serialVersionUID = 1L;

    private List<BaseContacts> contacts;

}
