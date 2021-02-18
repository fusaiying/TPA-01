package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 */
@Repository
public interface FlowLogMapper
{
    /**
     *
     */
    public int insertBatch(List<FlowLog> flowLogList);


}
