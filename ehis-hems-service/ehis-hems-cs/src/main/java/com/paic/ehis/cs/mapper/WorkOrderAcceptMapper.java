package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;


/**
 *
 */
public interface WorkOrderAcceptMapper
{
    /**
     *
     */
    public List<AcceptVo> selectAcceptByVo(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     *
     * @param workOrderQueryDTO
     * @return
     */
    public int updateAcceptDetailByVoDTO(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     *
     * @param workOrderQueryDTO
     * @return
     */
    public int updateAcceptByVoDTO(WorkOrderQueryDTO workOrderQueryDTO);

    /**
     * 通过id获取对象
     * @param ids
     * @return
     */
//    public List<AcceptVo> getAcceptVoByIds(String[] ids);

}
