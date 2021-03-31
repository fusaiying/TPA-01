package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.InterfaceMessage;

import java.util.List;


/**
 * 业务流转信息 Service接口
 * 
 * @author sino
 * @date 2021-01-29
 */
public interface IInterfaceMessageService
{
    public InterfaceMessage selectInterfaceMessageById(String seqId);

    public List<InterfaceMessage> selectInterfaceMessageList(InterfaceMessage interfaceMessage);

    public int insertInterfaceMessage(InterfaceMessage interfaceMessage);

    public int updateInterfaceMessage(InterfaceMessage interfaceMessage);

}
