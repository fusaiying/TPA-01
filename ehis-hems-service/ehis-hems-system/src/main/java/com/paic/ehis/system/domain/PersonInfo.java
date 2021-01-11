package com.paic.ehis.system.domain;

import com.paic.ehis.system.api.domain.SysUser;

public class PersonInfo {

    private HmpPersonService personService;

    private SysUser user;

    public HmpPersonService getPersonService() {
        return personService;
    }

    public void setPersonService(HmpPersonService personService) {
        this.personService = personService;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
