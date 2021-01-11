package com.paic.ehis.base.domain.vo;

import java.io.Serializable;

public class Sn_base_addressDO implements Serializable {
    private String placetype;

    private String placecode;

    private String placename;

    private String upplacename;

    private static final long serialVersionUID = 1L;

    public String getPlacetype() {
        return placetype;
    }

    public void setPlacetype(String placetype) {
        this.placetype = placetype == null ? null : placetype.trim();
    }

    public String getPlacecode() {
        return placecode;
    }

    public void setPlacecode(String placecode) {
        this.placecode = placecode == null ? null : placecode.trim();
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename == null ? null : placename.trim();
    }

    public String getUpplacename() {
        return upplacename;
    }

    public void setUpplacename(String upplacename) {
        this.upplacename = upplacename == null ? null : upplacename.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", placetype=").append(placetype);
        sb.append(", placecode=").append(placecode);
        sb.append(", placename=").append(placename);
        sb.append(", upplacename=").append(upplacename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}