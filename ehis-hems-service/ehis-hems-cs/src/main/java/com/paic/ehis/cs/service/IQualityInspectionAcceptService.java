package com.paic.ehis.cs.service;


import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;

import java.util.List;
import java.util.Map;

/**
 * 质检受理服务 IQualityInspectionAcceptService
 * by Louis
 */
public interface IQualityInspectionAcceptService
{
    /**
     * 发起质检工作池
     * @param demandQuery
     * @return
     */
    public List<AcceptVo> selectSendPoolData(WorkOrderQueryDTO demandQuery);


    /**
     * 质检工作池
     * @param demandQuery
     * @return
     */
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO demandQuery);

    /**
     * 发起质检操作
     * @param ids
     * @param param
     * @return
     */
    public int insertAcceptVoBatch(String[] ids, Map<String,String> param);

    /**
     * 通过工单号更新质检状态
     * @param ids
     * @param param
     * @return
     */
    public int inspectionHandleStatusByIds(String[] ids, Map<String,String> param);

    /**
     * 通过工单号获取对象信息
     * @param ids
     * @return
     */
    public List<AcceptVo> getAcceptVoByIds(String[] ids);

}
