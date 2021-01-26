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
    private com.paic.ehis.base.domain.HmpContract hmpContract;

    /**
     * 合约服务项目关联
     */
    private List<com.paic.ehis.base.domain.HmpServConpro> hmpServConpro;


    public com.paic.ehis.base.domain.HmpContract getHmpContract() {
        return hmpContract;
    }

    public void setHmpContract(com.paic.ehis.base.domain.HmpContract hmpContract) {
        this.hmpContract = hmpContract;
    }

    public List<com.paic.ehis.base.domain.HmpServConpro> getHmpServConpro() {
        return hmpServConpro;
    }

    public void setHmpServConpro(List<com.paic.ehis.base.domain.HmpServConpro> hmpServConpro) {
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
