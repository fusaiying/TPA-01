package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.UserInfo;

import java.util.List;


/**
 * 用户信息 针对系统操作用户Mapper接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface UserInfoMapper 
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
     * 删除用户信息 针对系统操作用户
     * 
     * @param userId 用户信息 针对系统操作用户ID
     * @return 结果
     */
    public int deleteUserInfoById(String userId);

    /**
     * 批量删除用户信息 针对系统操作用户
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserInfoByIds(String[] userIds);
}
