package com.paic.ehis.system.mapper;

import com.paic.ehis.system.domain.HmpServiceOrderAssign;

public interface HmpServiceOrderAssignMapper {


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
