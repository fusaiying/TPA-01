package com.paic.ehis.base.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.List;

/**
 * 将供应商合约与合约服务项目关联表进行关联
 */
public class ContractVO extends BaseEntity {

    /**
     * 供应商合约
     */
    private HmpContract hmpContract;

    /**
     * 合约服务项目关联
     */
    private List<HmpServConpro> hmpServConpro;


    public HmpContract getHmpContract() {
        return hmpContract;
    }

    public void setHmpContract(HmpContract hmpContract) {
        this.hmpContract = hmpContract;
    }

    public List<HmpServConpro> getHmpServConpro() {
        return hmpServConpro;
    }

    public void setHmpServConpro(List<HmpServConpro> hmpServConpro) {
        this.hmpServConpro = hmpServConpro;
    }

    @Override
    public String toString() {
        return "ContractVO{" +
                "hmpContract=" + hmpContract +
                ", hmpServConpro=" + hmpServConpro +
                '}';
    }
}
