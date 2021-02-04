package com.paic.ehis.system.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;

/**
 *
 * 地址对象 hmp_address
 * @author zkrbailz
 * @date 2020-10-26
 *
 */
public class HmpAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String placetype;
    /**
     * 编码
     */
    private String placecode;
    /**
     * 中文名称
     */
    private String placename;
    /**
     * 英文名称
     */
    private String enpplacename;
    /**
     * 上级地区编码
     */
    private String upplacecode;

    private String defaultcurr;


    public String getPlacetype() {
        return placetype;
    }

    public void setPlacetype(String placetype) {
        this.placetype = placetype;
    }

    public String getPlacecode() {
        return placecode;
    }

    public void setPlacecode(String placecode) {
        this.placecode = placecode;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getEnpplacename() {
        return enpplacename;
    }

    public void setEnpplacename(String enpplacename) {
        this.enpplacename = enpplacename;
    }

    public String getUpplacecode() {
        return upplacecode;
    }

    public void setUpplacecode(String upplacecode) {
        this.upplacecode = upplacecode;
    }

    public String getDefaultcurr() {
        return defaultcurr;
    }

    public void setDefaultcurr(String defaultcurr) {
        this.defaultcurr = defaultcurr;
    }

    @Override
    public String toString() {
        return "HmpAddress{" +
                "placetype='" + placetype + '\'' +
                ", placecode='" + placecode + '\'' +
                ", placename='" + placename + '\'' +
                ", enpplacename='" + enpplacename + '\'' +
                ", upplacecode='" + upplacecode + '\'' +
                ", defaultcurr='" + defaultcurr + '\'' +
                '}';
    }
}
