package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseRegister;
import com.paic.ehis.system.mapper.ClaimCaseRegisterMapper;
import com.paic.ehis.system.service.IClaimCaseRegisterService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件申请人信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseRegisterServiceImpl implements IClaimCaseRegisterService
{
    @Autowired
    private ClaimCaseRegisterMapper claimCaseRegisterMapper;

    /**
     * 查询案件申请人信息
     * 
     * @param rptNo 案件申请人信息ID
     * @return 案件申请人信息
     */
    @Override
    public ClaimCaseRegister selectClaimCaseRegisterById(String rptNo)
    {
        return claimCaseRegisterMapper.selectClaimCaseRegisterById(rptNo);
    }

    /**
     * 查询案件申请人信息列表
     * 
     * @param claimCaseRegister 案件申请人信息
     * @return 案件申请人信息
     */
    @Override
    public List<ClaimCaseRegister> selectClaimCaseRegisterList(ClaimCaseRegister claimCaseRegister)
    {
        return claimCaseRegisterMapper.selectClaimCaseRegisterList(claimCaseRegister);
    }

    /**
     * 新增案件申请人信息
     * 
     * @param claimCaseRegister 案件申请人信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseRegister(ClaimCaseRegister claimCaseRegister)
    {
        int i;
        claimCaseRegister.setStatus("Y");
        claimCaseRegister.setCreateBy(SecurityUtils.getUsername());
        claimCaseRegister.setCreateTime(DateUtils.getNowDate());
        claimCaseRegister.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRegister.setUpdateTime(DateUtils.getNowDate());
        ClaimCaseRegister claimCaseRegister1 = claimCaseRegisterMapper.selectClaimCaseRegisterById(claimCaseRegister.getRptNo());

        if (claimCaseRegister1 == null || claimCaseRegister1.equals("")){
            i = claimCaseRegisterMapper.insertClaimCaseRegister(claimCaseRegister);
        }else {
            i= claimCaseRegisterMapper.updateClaimCaseRegister(claimCaseRegister);
        }

        return i;
    }

    /**
     * 修改案件申请人信息
     * 
     * @param claimCaseRegister 案件申请人信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseRegister(ClaimCaseRegister claimCaseRegister)
    {
        claimCaseRegister.setUpdateTime(DateUtils.getNowDate());
        return claimCaseRegisterMapper.updateClaimCaseRegister(claimCaseRegister);
    }

    /**
     * 批量删除案件申请人信息
     * 
     * @param rptNos 需要删除的案件申请人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRegisterByIds(String[] rptNos)
    {
        return claimCaseRegisterMapper.deleteClaimCaseRegisterByIds(rptNos);
    }

    /**
     * 删除案件申请人信息信息
     * 
     * @param rptNo 案件申请人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseRegisterById(String rptNo)
    {
        return claimCaseRegisterMapper.deleteClaimCaseRegisterById(rptNo);
    }
}
