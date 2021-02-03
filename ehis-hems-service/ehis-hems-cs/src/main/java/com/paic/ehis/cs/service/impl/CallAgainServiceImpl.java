package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.CallAgain;
import com.paic.ehis.cs.mapper.CallAgainMapper;
import com.paic.ehis.cs.service.ICallAgainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 再次来电信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class CallAgainServiceImpl implements ICallAgainService
{
    @Autowired
    private CallAgainMapper callAgainMapper;

    /**
     * 查询再次来电信息 
     * 
     * @param callSerialNum 再次来电信息 ID
     * @return 再次来电信息 
     */
    @Override
    public CallAgain selectCallAgainById(String callSerialNum)
    {
        return callAgainMapper.selectCallAgainById(callSerialNum);
    }

    /**
     * 查询再次来电信息 列表
     * 
     * @param callAgain 再次来电信息 
     * @return 再次来电信息 
     */
    @Override
    public List<CallAgain> selectCallAgainList(CallAgain callAgain)
    {
        return callAgainMapper.selectCallAgainList(callAgain);
    }

    /**
     * 新增再次来电信息 
     * 
     * @param callAgain 再次来电信息 
     * @return 结果
     */
    @Override
    public int insertCallAgain(CallAgain callAgain)
    {
        return callAgainMapper.insertCallAgain(callAgain);
    }

    /**
     * 修改再次来电信息 
     * 
     * @param callAgain 再次来电信息 
     * @return 结果
     */
    @Override
    public int updateCallAgain(CallAgain callAgain)
    {
        return callAgainMapper.updateCallAgain(callAgain);
    }

    /**
     * 批量删除再次来电信息 
     * 
     * @param callSerialNums 需要删除的再次来电信息 ID
     * @return 结果
     */
    @Override
    public int deleteCallAgainByIds(String[] callSerialNums)
    {
        return callAgainMapper.deleteCallAgainByIds(callSerialNums);
    }

    /**
     * 删除再次来电信息 信息
     * 
     * @param callSerialNum 再次来电信息 ID
     * @return 结果
     */
    @Override
    public int deleteCallAgainById(String callSerialNum)
    {
        return callAgainMapper.deleteCallAgainById(callSerialNum);
    }

    /**
     * 查询再次来电工单提醒
     * @param callAgain
     * @return
     */
    @Override
    public List<CallAgain> selectCallAgain(CallAgain callAgain) {
        return callAgainMapper.selectCallAgain(callAgain);
    }
}
