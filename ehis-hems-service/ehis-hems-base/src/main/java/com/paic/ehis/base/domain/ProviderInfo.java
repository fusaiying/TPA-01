package com.paic.ehis.base.domain;

import java.util.List;

/**
 * 供应商信息
 */
public class ProviderInfo {
    /**
     * 供应商基础信息
     */
    private HmpServOpera hmpServOpera;
    /**
     * 医疗特色信息
     */
    private HmpHospCharacter hmpHospCharacter;
    /**
     * 联系人
     */
    private List<HmpServContacts> hmpServContactsList;

    public HmpServOpera getHmpServOpera() {
        return hmpServOpera;
    }

    public void setHmpServOpera(HmpServOpera hmpServOpera) {
        this.hmpServOpera = hmpServOpera;
    }

    public HmpHospCharacter getHmpHospCharacter() {
        return hmpHospCharacter;
    }

    public void setHmpHospCharacter(HmpHospCharacter hmpHospCharacter) {
        this.hmpHospCharacter = hmpHospCharacter;
    }

    public List<HmpServContacts> getHmpServContactsList() {
        return hmpServContactsList;
    }

    public void setHmpServContactsList(List<HmpServContacts> hmpServContactsList) {
        this.hmpServContactsList = hmpServContactsList;
    }

    @Override
    public String toString() {
        return "ProviderInfo{" +
                "hmpServOpera=" + hmpServOpera +
                ", hmpHospCharacter=" + hmpHospCharacter +
                ", hmpServContactsList=" + hmpServContactsList +
                '}';
    }
}
