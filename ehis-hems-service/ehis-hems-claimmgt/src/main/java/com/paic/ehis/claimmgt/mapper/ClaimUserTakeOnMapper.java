package com.paic.ehis.claimmgt.mapper;

import com.paic.ehis.claimmgt.domain.ClaimUserTakeOn;
import com.paic.ehis.claimmgt.domain.dto.UserTakeOnDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 理赔用户工作承接Mapper接口
 *
 * @author sino
 * @date 2021-01-22
 */
public interface ClaimUserTakeOnMapper
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
    public int updateSysUser(ClaimUserTakeOn claimUserTakeOn);

    /**
     * 删除理赔用户工作承接
     *
     * @param takeOnId 理赔用户工作承接ID
     * @return 结果
     */
    public int deleteClaimUserTakeOnById(String takeOnId);

    /**
     * 批量删除理赔用户工作承接
     *
     * @param takeOnIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimUserTakeOnByIds(String[] takeOnIds);

    /**
     * 获取角色集合
     * @param userName
     * @return
     */
    public List<String> getRoleCodeList(String userName);

    /**
     * 获取承接人
     * @param roleCodeList
     * @return
     */
    public List<String> getTakeOnUserName(@Param("userName") String userName,@Param("roleCodeList") List<String> roleCodeList);
}
