package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.CallAgain;

import java.util.List;


/**
 * 再次来电信息 Service接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface ICallAgainService 
{
    /**
     * 查询再次来电信息 
     * 
     * @param callSerialNum 再次来电信息 ID
     * @return 再次来电信息 
     */
    public CallAgain selectCallAgainById(String callSerialNum);

    /**
     * 查询再次来电信息 列表
     * 
     * @param callAgain 再次来电信息 
     * @return 再次来电信息 集合
     */
    public List<CallAgain> selectCallAgainList(CallAgain callAgain);

    /**
     * 新增再次来电信息 
     * 
     * @param callAgain 再次来电信息 
     * @return 结果
     */
    public int insertCallAgain(CallAgain callAgain);

    /**
     * 修改再次来电信息 
     * 
     * @param callAgain 再次来电信息 
     * @return 结果
     */
    public int updateCallAgain(CallAgain callAgain);

    /**
     * 批量删除再次来电信息 
     * 
     * @param callSerialNums 需要删除的再次来电信息 ID
     * @return 结果
     */
    public int deleteCallAgainByIds(String[] callSerialNums);

    /**
     * 删除再次来电信息 信息
     * 
     * @param callSerialNum 再次来电信息 ID
     * @return 结果
     */
    public int deleteCallAgainById(String callSerialNum);
}
