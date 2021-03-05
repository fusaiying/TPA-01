package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.BasicServiceAppilcation;

public interface IBasicServiceApplicationService {
    /**
     *信息需求受理保存
     *@param basicServiceAppilcation
     * @return  结果
     */
    int insertBasicServiceApplication(BasicServiceAppilcation basicServiceAppilcation);
}
