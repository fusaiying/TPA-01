package com.paic.ehis.system.service;

import com.paic.ehis.system.domain.HmpServiceOrderAssign;

public interface IHmpServiceOrderAssignService {
    /**
     * 根据工单编号查询分诊信息
     * @param ordercode
     * @return
     */
    HmpServiceOrderAssign selectHmpServiceOrderAssignByCode(String ordercode);

    /**
     * 新增分诊信息
     * @param hmpServiceOrderAssign
     * @return
     */
    int insertHmpServiceOrderAssign(HmpServiceOrderAssign hmpServiceOrderAssign);

    /**
     * 修改分诊信息
     * @param hmpServiceOrderAssign
     * @return
     */
    int updateHmpServiceOrderAssign(HmpServiceOrderAssign hmpServiceOrderAssign);
}
