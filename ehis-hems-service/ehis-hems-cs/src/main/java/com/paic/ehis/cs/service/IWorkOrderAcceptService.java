package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author sino
 * @date 2021-01-05
 */
public interface IWorkOrderAcceptService
{
    /**
     * 工单信息
     */
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO demandQuery);

    public int inspectionHandleStatusByIds(String[] ids, Map<String,String> param);

    public List<AcceptVo> getAcceptVoByIds(String[] ids);
}
