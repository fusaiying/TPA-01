package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCasePayee;
import com.paic.ehis.claimflow.mapper.ClaimCasePayeeMapper;
import com.paic.ehis.claimflow.service.IClaimCasePayeeService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

        claimCasePayee.setPayeeRatio(claimCasePayee.getPayeeRatio().divide(new BigDecimal(String.valueOf(100)),2, BigDecimal.ROUND_HALF_UP));
        claimCasePayee.setStatus("Y");
        claimCasePayee.setUpdateTime(DateUtils.getNowDate());
        claimCasePayee.setUpdateBy(SecurityUtils.getUsername());
        claimCasePayee.setCreateTime(DateUtils.getNowDate());
        claimCasePayee.setCreateBy(SecurityUtils.getUsername());
        return claimCasePayeeMapper.insertClaimCasePayee(claimCasePayee);
    }

    /**
     * 校验案件领款人领款比例
     * @param rptNo
     * @return
     */
    @Override
    public int CheckThePaymentRatio(String rptNo){
        return 0==claimCasePayeeMapper.CheckClaimCasePayeeByRptNo(rptNo).compareTo(new BigDecimal(String.valueOf(1)))?1:0;
    }

    /**
     * 修改案件领款人信息
     * 
     * @param claimCasePayee 案件领款人信息+
     * @return 结果
     */
    @Override
    public int updateClaimCasePayee(ClaimCasePayee claimCasePayee)
    {
        claimCasePayee.setPayeeRatio(claimCasePayee.getPayeeRatio().divide(new BigDecimal(String.valueOf(100)),2, BigDecimal.ROUND_HALF_UP));
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
     * @param payeeId 案件领款人信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCasePayeeByRptNo(Long payeeId)
    {
        ClaimCasePayee claimCasePayee = new ClaimCasePayee();
        claimCasePayee.setPayeeId(payeeId);
        claimCasePayee.setStatus("N");
        claimCasePayee.setUpdateBy(SecurityUtils.getUsername());
        claimCasePayee.setUpdateTime(DateUtils.getNowDate());
        return claimCasePayeeMapper.updateClaimCasePayee(claimCasePayee);
    }

    /**
     * 获取案件领款人信息-最近的一条
     * @param claimCasePayee
     * @return
     */
    @Override
    public ClaimCasePayee selectClaimCasePayeeselectOne(ClaimCasePayee claimCasePayee){
        String rptNo = claimCasePayee.getRptNo();
        return claimCasePayeeMapper.selectClaimCasePayeeselectOne(rptNo);
    }

    /**
     * 获取案件领款人信息
     * @param rptNo
     * @return
     */
    @Override
    public List<ClaimCasePayee> selectClaimCasePayeeByRptNo(String rptNo){

        List<ClaimCasePayee> claimCasePayees = claimCasePayeeMapper.selectClaimCasePayeeByRptNo(rptNo);
        for (ClaimCasePayee claimCasePayee : claimCasePayees) {
            claimCasePayee.setPayeeRatio(claimCasePayee.getPayeeRatio().multiply(new BigDecimal(String.valueOf(100))));
        }
        return claimCasePayees;
    }


}
