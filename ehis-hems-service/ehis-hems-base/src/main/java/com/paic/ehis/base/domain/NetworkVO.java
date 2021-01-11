package com.paic.ehis.base.domain;

/**
 * 将供应商和网点数量进行封装
 */
public class NetworkVO {
    /**
     * 供应商
     */
    private HmpServOpera hmpServOpera;

    /**
     * 网点数量
     */
    private Integer netnum;

    public HmpServOpera getHmpServOpera() {
        return hmpServOpera;
    }

    public void setHmpServOpera(HmpServOpera hmpServOpera) {
        this.hmpServOpera = hmpServOpera;
    }

    public Integer getNetnum() {
        return netnum;
    }

    public void setNetnum(Integer netnum) {
        this.netnum = netnum;
    }

    @Override
    public String toString() {
        return "NeworkVO{" +
                "hmpServOpera=" + hmpServOpera +
                ", netnum=" + netnum +
                '}';
    }
}
