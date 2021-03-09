package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.WorkOrderAccept;

public interface IncrementServiceApplicationMapper {

    /**
     * 新增信息需求
     * @param acceptDetailInfo
     */
    int insertAcceptDetialInfoTable(AcceptDetailInfo acceptDetailInfo);

    int insertPersonInfoTable(PersonInfo personInfo);

    int insertWorkOrderAcceptTable(WorkOrderAccept workOrderAccept);
}
