package com.paic.ehis.cs.mapper;


import com.paic.ehis.cs.domain.InterfaceMessage;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 */
@Component
public interface InterfaceMessageMapper
{
    public InterfaceMessage selectInterfaceMessageById(String seqId);

    public List<InterfaceMessage> selectInterfaceMessageList(InterfaceMessage interfaceMessage);

    public int insertInterfaceMessage(InterfaceMessage interfaceMessage);

    public int updateInterfaceMessage(InterfaceMessage interfaceMessage);

}
