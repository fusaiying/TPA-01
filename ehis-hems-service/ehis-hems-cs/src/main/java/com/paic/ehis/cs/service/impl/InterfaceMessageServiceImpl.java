package com.paic.ehis.cs.service.impl;

import com.paic.ehis.cs.domain.InterfaceMessage;
import com.paic.ehis.cs.mapper.InterfaceMessageMapper;
import com.paic.ehis.cs.service.IInterfaceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service业务层处理
 * 
 */
@Service
public class InterfaceMessageServiceImpl implements IInterfaceMessageService
{
    @Autowired
    private InterfaceMessageMapper interfaceMessageMapper;


    @Override
    public InterfaceMessage selectInterfaceMessageById(String seqId) {
        return interfaceMessageMapper.selectInterfaceMessageById(seqId);
    }

    @Override
    public List<InterfaceMessage> selectInterfaceMessageList(InterfaceMessage interfaceMessage) {
        return interfaceMessageMapper.selectInterfaceMessageList(interfaceMessage);
    }

    @Override
    public int insertInterfaceMessage(InterfaceMessage interfaceMessage) {
        return interfaceMessageMapper.insertInterfaceMessage(interfaceMessage);
    }

    @Override
    public int updateInterfaceMessage(InterfaceMessage interfaceMessage) {
        return interfaceMessageMapper.updateInterfaceMessage(interfaceMessage);
    }
}
