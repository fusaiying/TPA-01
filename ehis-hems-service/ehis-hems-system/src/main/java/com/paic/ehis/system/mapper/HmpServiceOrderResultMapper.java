package com.paic.ehis.system.mapper;

import com.paic.ehis.system.domain.HmpServiceOrderResult;

public interface HmpServiceOrderResultMapper {
    /**
     * 新增实施结果信息
     * @param hmpServiceOrderResult
     * @return
     */
    int insertHmpServiceOrderResult(HmpServiceOrderResult hmpServiceOrderResult);

    /**
     * 根据工单查询服务实施结果信息
     * @param ordercode
     * @return
     */
    HmpServiceOrderResult selectHmpServiceOrderResultByCode(String ordercode);

    /**
     * 根据工单编号更新服务实施结果信息
     * @param hmpServiceOrderResult
     * @return
     */
    int updateHmpServiceOrderResult(HmpServiceOrderResult hmpServiceOrderResult);
}
