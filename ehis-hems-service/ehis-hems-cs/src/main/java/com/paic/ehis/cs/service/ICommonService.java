package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;

import java.util.List;


/**
 * 工单申请信息 Service接口
 *
 * @author sino
 * @date 2021-01-21
 */
public interface ICommonService
{

    /**
     * 获取案件个数
     * @param workOrderQueryDTO
     * @return
     */
    public List<AcceptVo> getWorkOrderCountByUserId(WorkOrderQueryDTO workOrderQueryDTO);

}
