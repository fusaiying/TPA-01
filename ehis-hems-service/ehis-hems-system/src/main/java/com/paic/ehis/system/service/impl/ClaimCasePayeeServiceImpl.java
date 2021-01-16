package com.paic.ehis.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.paic.ehis.system.domain.ClaimCasePayee;
import com.paic.ehis.system.mapper.ClaimCasePayeeMapper;
import com.paic.ehis.system.service.IClaimCasePayeeService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件领款人信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCasePayeeServiceImpl implements IClaimCasePayeeService
{
    @Autowired
    private ClaimCasePayeeMapper claimCasePayeeMapper;

    /**
     * 查询案件领款人信息
     * 
     * @param payeeId 案件领款人信息ID
     * @return 案件领款人信息
     */
    @Override
    public ClaimCasePayee selectClaimCasePayeeById(Long payeeId)
    {
        return claimCasePayeeMapper.selectClaimCasePayeeById(payeeId);
    }

    /**
     * 查询案件领款人信息列表
     * 
     * @param claimCasePayee 案件领款人信息
     * @return 案件领款人信息
     */
    @Override
    public List<ClaimCasePayee> selectClaimCasePayeeList(ClaimCasePayee claimCasePayee)
    {
        return claimCasePayeeMapper.selectClaimCasePayeeList(claimCasePayee);
    }

    /**
     * 新增案件领款人信息
     * 
     * @param claimCasePayee 案件领款人信息
     * @return 结果
     */
    @Override
    public int insertClaimCasePayee(ClaimCasePayee claimCasePayee)
    {
        claimCasePayee.setPayeeRatio(claimCasePayee.getPayeeRatio().divide(BigDecimal.valueOf(100),2));
        claimCasePayee.setStatus("Y");
        claimCasePayee.setUpdateTime(DateUtils.getNowDate());
        claimCasePayee.setUpdateBy(SecurityUtils.getUsername());
        claimCasePayee.setCreateTime(DateUtils.getNowDate());
        claimCasePayee.setCreateBy(SecurityUtils.getUsername());
        return claimCasePayeeMapper.insertClaimCasePayee(claimCasePayee);
    }

    /**
     * 修改案件领款人信息
     * 
     * @param claimCasePayee 案件领款人信息
     * @return 结果
     */
    @Override
    public int updateClaimCasePayee(ClaimCasePayee claimCasePayee)
    {
        claimCasePayee.setUpdateTime(DateUtils.getNowDate());
        claimCasePayee.setUpdateBy(SecurityUtils.getUsername());
        return claimCasePayeeMapper.updateClaimCasePayee(claimCasePayee);
    }

    /**
     * 批量删除案件领款人信息
     * 
     * @param payeeIds 需要删除的案件领款人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCasePayeeByIds(Long[] payeeIds)
    {
        return claimCasePayeeMapper.deleteClaimCasePayeeByIds(payeeIds);
    }

    /**
     * 删除案件领款人信息信息
     * 
     * @param rptNo 案件领款人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCasePayeeByRptNo(String rptNo)
    {
        ClaimCasePayee claimCasePayee = new ClaimCasePayee();
        claimCasePayee.setRptNo(rptNo);
        claimCasePayee.setStatus("N");
        return claimCasePayeeMapper.updateClaimCasePayee(claimCasePayee);
    }

    public ClaimCasePayee selectClaimCasePayeeselectOne(ClaimCasePayee claimCasePayee){
        String rptNo = claimCasePayee.getRptNo();
        return claimCasePayeeMapper.selectClaimCasePayeeselectOne(rptNo);
    }

}
