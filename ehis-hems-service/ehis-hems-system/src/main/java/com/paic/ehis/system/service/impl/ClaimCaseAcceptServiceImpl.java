package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseAccept;
import com.paic.ehis.system.domain.ClaimCaseApplyType;
import com.paic.ehis.system.domain.dto.CaseRegisterAndAppleTypeDTO;
import com.paic.ehis.system.mapper.ClaimCaseAcceptMapper;
import com.paic.ehis.system.mapper.ClaimCaseApplyTypeMapper;
import com.paic.ehis.system.service.IClaimCaseAcceptService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件受理信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseAcceptServiceImpl implements IClaimCaseAcceptService
{
    @Autowired
    private ClaimCaseAcceptMapper claimCaseAcceptMapper;

    @Autowired
    private ClaimCaseApplyTypeMapper claimCaseApplyTypeMapper;

    /**
     * 查询案件受理信息 
     * 
     * @param rptNo 案件受理信息 ID
     * @return 案件受理信息 
     */
    @Override
    public ClaimCaseAccept selectClaimCaseAcceptById(String rptNo)
    {
        return claimCaseAcceptMapper.selectClaimCaseAcceptById(rptNo);
    }

    /**
     * 查询案件受理信息 列表
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 案件受理信息 
     */
    @Override
    public List<ClaimCaseAccept> selectClaimCaseAcceptList(ClaimCaseAccept claimCaseAccept)
    {
        return claimCaseAcceptMapper.selectClaimCaseAcceptList(claimCaseAccept);
    }

    /**
     * 新增案件受理信息 
     * 
     * @param caseRegisterAndAppleTypeDTO 案件受理信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseAccept(CaseRegisterAndAppleTypeDTO caseRegisterAndAppleTypeDTO)
    {
        int i;
        Long applyId=null;
        ClaimCaseAccept claimCaseAccept = caseRegisterAndAppleTypeDTO.getClaimCaseAccept();
        ClaimCaseAccept claimCaseAccept1 = claimCaseAcceptMapper.selectClaimCaseAcceptById(claimCaseAccept.getRptNo());//案件受理信息表

        claimCaseAccept.setCreateBy(SecurityUtils.getUsername());
        claimCaseAccept.setCreateTime(DateUtils.getNowDate());
        claimCaseAccept.setUpdateBy(SecurityUtils.getUsername());
        claimCaseAccept.setUpdateTime(DateUtils.getNowDate());
        claimCaseAccept.setStatus("Y");

        ClaimCaseApplyType claimCaseApplyType = new ClaimCaseApplyType();
        claimCaseApplyType.setCreateBy(SecurityUtils.getUsername());
        claimCaseApplyType.setCreateTime(DateUtils.getNowDate());
        claimCaseApplyType.setUpdateBy(SecurityUtils.getUsername());
        claimCaseApplyType.setUpdateTime(DateUtils.getNowDate());
        claimCaseApplyType.setRptNo(claimCaseAccept.getRptNo());
        claimCaseApplyType.setStatus("Y");

        claimCaseApplyType.setRptNo(claimCaseAccept1.getRptNo());
        List<String> applyTypes = caseRegisterAndAppleTypeDTO.getApplyTypes();
        List<ClaimCaseApplyType> claimCaseApplyTypes = claimCaseApplyTypeMapper.selectClaimCaseApplyTypeList(claimCaseApplyType);//案件申请类别表
        for (ClaimCaseApplyType claimCaseApplyTypesTwo:claimCaseApplyTypes){
            applyId = claimCaseApplyTypesTwo.getApplyId();
        }
        if (claimCaseAccept1 == null || claimCaseAccept1.equals("")){//为空-第一次保存
            for(String applyType: applyTypes) {
                claimCaseApplyType.setApplyType(applyType);
                claimCaseApplyTypeMapper.insertClaimCaseApplyType(claimCaseApplyType);
            }
            i = claimCaseAcceptMapper.insertClaimCaseAccept(claimCaseAccept);
        }else {//第二次保存
            for(String applyType: applyTypes) {
                claimCaseApplyType.setApplyType(applyType);
                claimCaseApplyType.setApplyId(applyId);
                claimCaseApplyTypeMapper.updateClaimCaseApplyType(claimCaseApplyType);
            }
            i = claimCaseAcceptMapper.updateClaimCaseAccept(claimCaseAccept);
        }
        return i;
    }

    /**
     * 修改案件受理信息 
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 结果
     */
    @Override
    public int updateClaimCaseAccept(ClaimCaseAccept claimCaseAccept)
    {
        claimCaseAccept.setUpdateTime(DateUtils.getNowDate());
        return claimCaseAcceptMapper.updateClaimCaseAccept(claimCaseAccept);
    }

    /**
     * 批量删除案件受理信息 
     * 
     * @param rptNos 需要删除的案件受理信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAcceptByIds(String[] rptNos)
    {
        return claimCaseAcceptMapper.deleteClaimCaseAcceptByIds(rptNos);
    }

    /**
     * 删除案件受理信息 信息
     * 
     * @param rptNo 案件受理信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAcceptById(String rptNo)
    {
        return claimCaseAcceptMapper.deleteClaimCaseAcceptById(rptNo);
    }
}
