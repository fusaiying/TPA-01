package com.paic.ehis.claimmgt.service.impl;

import com.paic.ehis.claimmgt.domain.ClaimUserTakeOn;
import com.paic.ehis.claimmgt.domain.dto.UserTakeOnDTO;
import com.paic.ehis.claimmgt.mapper.ClaimUserTakeOnMapper;
import com.paic.ehis.claimmgt.service.IClaimUserTakeOnService;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 理赔用户工作承接Service业务层处理
 *
 * @author sino
 * @date 2021-01-22
 */
@Service
public class ClaimUserTakeOnServiceImpl implements IClaimUserTakeOnService
{
    @Autowired
    private ClaimUserTakeOnMapper claimUserTakeOnMapper;

    /**
     * 查询理赔用户工作承接
     *
     * @param takeOnId 理赔用户工作承接ID
     * @return 理赔用户工作承接
     */
    @Override
    public ClaimUserTakeOn selectClaimUserTakeOnById(String takeOnId)
    {
        return claimUserTakeOnMapper.selectClaimUserTakeOnById(takeOnId);
    }
    /**
     * 查询理赔用户工作承接列表
     *
     * @param claimUserTakeOn 理赔用户工作承接
     * @return 理赔用户工作承接
     */
    @Override
    public List<ClaimUserTakeOn> selectClaimUserTakeOnList(ClaimUserTakeOn claimUserTakeOn)
    {

        claimUserTakeOn.setStatus("Y");
        return claimUserTakeOnMapper.selectClaimUserTakeOnList(claimUserTakeOn);
    }

    /**
     * 查询理赔用户工作承接列表
     *
     * @param userTakeOnDTO 理赔用户工作承接
     * @return 理赔用户工作承接
     */
    @Override
    public List<UserTakeOnDTO> selectClaimUserTakeOnListOne(UserTakeOnDTO userTakeOnDTO)
    {
        return claimUserTakeOnMapper.selectClaimUserTakeOnListOne(userTakeOnDTO);
    }

    /**
     * 新增理赔用户工作承接
     *
     * @param claimUserTakeOn 理赔用户工作承接
     * @return 结果
     */
    @Override
    public int insertClaimUserTakeOn(ClaimUserTakeOn claimUserTakeOn)
    {
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();

        claimUserTakeOn.setCreateBy(username);
        claimUserTakeOn.setCreateTime(nowDate);
        claimUserTakeOn.setUpdateBy(username);
        claimUserTakeOn.setUpdateTime(nowDate);
        claimUserTakeOn.setStatus("01".equals(claimUserTakeOn.getStatus()) ? "Y" : "N");
        return claimUserTakeOnMapper.insertClaimUserTakeOn(claimUserTakeOn);
    }

    /**
     * 修改理赔用户工作承接
     *
     * @param claimUserTakeOn 理赔用户工作承接
     * @return 结果
     */
    @Override
    public int updateClaimUserTakeOn(ClaimUserTakeOn claimUserTakeOn)
    {
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();

        claimUserTakeOn.setUpdateBy(username);
        claimUserTakeOn.setUpdateTime(nowDate);
        claimUserTakeOn.setStatus("01".equals(claimUserTakeOn.getStatus()) ? "Y" : "N");
        return claimUserTakeOnMapper.updateClaimUserTakeOn(claimUserTakeOn);
    }

    /**
     * 批量删除理赔用户工作承接
     *
     * @param takeOnIds 需要删除的理赔用户工作承接ID
     * @return 结果
     */
    @Override
    public int deleteClaimUserTakeOnByIds(String[] takeOnIds)
    {
        return claimUserTakeOnMapper.deleteClaimUserTakeOnByIds(takeOnIds);
    }

    /**
     * 删除理赔用户工作承接信息
     *
     * @param takeOnId 理赔用户工作承接ID
     * @return 结果
     */
    @Override
    public int deleteClaimUserTakeOnById(String takeOnId)
    {
        return claimUserTakeOnMapper.deleteClaimUserTakeOnById(takeOnId);
    }

    /**
     * 获取承接人
     * @param userName
     * @return
     */
    @Override
    public List<String> getTakeOnUserName(String userName) {
        if(!StringUtils.isNotEmpty(userName)){
            return null;
        }
        List<String> roleCodeList = claimUserTakeOnMapper.getRoleCodeList(userName);

        if(roleCodeList == null || roleCodeList.size() <= 0){
            return null;
        }
        return claimUserTakeOnMapper.getTakeOnUserName(userName,roleCodeList);
    }

}
