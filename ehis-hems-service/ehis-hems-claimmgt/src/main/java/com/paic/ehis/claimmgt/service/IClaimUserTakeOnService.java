package com.paic.ehis.claimmgt.service;

import com.paic.ehis.claimmgt.domain.ClaimUserTakeOn;
import com.paic.ehis.claimmgt.domain.dto.UserTakeOnDTO;

import java.util.List;

/**
 * 理赔用户工作承接Service接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface IClaimUserTakeOnService 
{
    /**
     * 查询理赔用户工作承接
     * 
     * @param takeOnId 理赔用户工作承接ID
     * @return 理赔用户工作承接
     */
    public ClaimUserTakeOn selectClaimUserTakeOnById(String takeOnId);

    /**
     * 查询理赔用户工作承接列表
     *
     * @param claimUserTakeOn 理赔用户工作承接
     * @return 理赔用户工作承接集合
     */
    public List<ClaimUserTakeOn> selectClaimUserTakeOnList(ClaimUserTakeOn claimUserTakeOn);

    /**
     * 查询理赔用户工作承接列表
     * 
     * @param userTakeOnDTO 理赔用户工作承接
     * @return 理赔用户工作承接集合
     */
    public List<UserTakeOnDTO> selectClaimUserTakeOnListOne(UserTakeOnDTO userTakeOnDTO);

    /**
     * 新增理赔用户工作承接
     * 
     * @param claimUserTakeOn 理赔用户工作承接
     * @return 结果
     */
    public int insertClaimUserTakeOn(ClaimUserTakeOn claimUserTakeOn);

    /**
     * 修改理赔用户工作承接
     * 
     * @param claimUserTakeOn 理赔用户工作承接
     * @return 结果
     */
    public int updateClaimUserTakeOn(ClaimUserTakeOn claimUserTakeOn);

    /**
     * 批量删除理赔用户工作承接
     * 
     * @param takeOnIds 需要删除的理赔用户工作承接ID
     * @return 结果
     */
    public int deleteClaimUserTakeOnByIds(String[] takeOnIds);

    /**
     * 删除理赔用户工作承接信息
     * 
     * @param takeOnId 理赔用户工作承接ID
     * @return 结果
     */
    public int deleteClaimUserTakeOnById(String takeOnId);

}
