package com.paic.ehis.base.domain.dto;

import com.paic.ehis.base.domain.BaseIssuingcompany;
import com.paic.ehis.base.domain.BaseIssuingcompanyInvoice;

import java.io.Serializable;

public class IssuingAndCompanyDTO implements Serializable {
    private BaseIssuingcompanyInvoice baseIssuingcompanyInvoice;//开票信息
    private BaseIssuingcompany baseIssuingcompany;//出单公司

    public BaseIssuingcompanyInvoice getBaseIssuingcompanyInvoice() {
        return baseIssuingcompanyInvoice;
    }

    public void setBaseIssuingcompanyInvoice(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice) {
        this.baseIssuingcompanyInvoice = baseIssuingcompanyInvoice;
    }

    public BaseIssuingcompany getBaseIssuingcompany() {
        return baseIssuingcompany;
    }

    public void setBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany) {
        this.baseIssuingcompany = baseIssuingcompany;
    }

    @Override
    public String toString() {
        return "IssuingAndCompanyDTO{" +
                "baseIssuingcompanyInvoice=" + baseIssuingcompanyInvoice +
                ", baseIssuingcompany=" + baseIssuingcompany +
                '}';
    }
}
