package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.UserInfo;

import java.util.List;


/**
 * 用户信息 针对系统操作用户Service接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface IUserInfoService 
{
    /**
     * 查询用户信息 针对系统操作用户
     * 
     * @param userId 用户信息 针对系统操作用户ID
     * @return 用户信息 针对系统操作用户
     */
    public UserInfo selectUserInfoById(String userId);

    /**
     * 查询用户信息 针对系统操作用户列表
     * 
     * @param userInfo 用户信息 针对系统操作用户
     * @return 用户信息 针对系统操作用户集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增用户信息 针对系统操作用户
     * 
     * @param userInfo 用户信息 针对系统操作用户
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息 针对系统操作用户
     * 
     * @param userInfo 用户信息 针对系统操作用户
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除用户信息 针对系统操作用户
     * 
     * @param userIds 需要删除的用户信息 针对系统操作用户ID
     * @return 结果
     */
    public int deleteUserInfoByIds(String[] userIds);

    /**
     * 删除用户信息 针对系统操作用户信息
     * 
     * @param userId 用户信息 针对系统操作用户ID
     * @return 结果
     */
    public int deleteUserInfoById(String userId);
}
