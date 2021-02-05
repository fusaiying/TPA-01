package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.UserInfo;
import com.paic.ehis.cs.mapper.UserInfoMapper;
import com.paic.ehis.cs.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户信息 针对系统操作用户Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户信息 针对系统操作用户
     * 
     * @param userId 用户信息 针对系统操作用户ID
     * @return 用户信息 针对系统操作用户
     */
    @Override
    public UserInfo selectUserInfoById(String userId)
    {
        return userInfoMapper.selectUserInfoById(userId);
    }

    /**
     * 查询用户信息 针对系统操作用户列表
     * 
     * @param userInfo 用户信息 针对系统操作用户
     * @return 用户信息 针对系统操作用户
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo)
    {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增用户信息 针对系统操作用户
     * 
     * @param userInfo 用户信息 针对系统操作用户
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo)
    {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 修改用户信息 针对系统操作用户
     * 
     * @param userInfo 用户信息 针对系统操作用户
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo)
    {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除用户信息 针对系统操作用户
     * 
     * @param userIds 需要删除的用户信息 针对系统操作用户ID
     * @return 结果
     */
    @Override
    public int deleteUserInfoByIds(String[] userIds)
    {
        return userInfoMapper.deleteUserInfoByIds(userIds);
    }

    /**
     * 删除用户信息 针对系统操作用户信息
     * 
     * @param userId 用户信息 针对系统操作用户ID
     * @return 结果
     */
    @Override
    public int deleteUserInfoById(String userId)
    {
        return userInfoMapper.deleteUserInfoById(userId);
    }
}
