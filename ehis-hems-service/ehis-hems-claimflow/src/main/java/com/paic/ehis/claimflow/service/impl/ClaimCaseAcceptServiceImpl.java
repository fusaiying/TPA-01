package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseAccept;
import com.paic.ehis.claimflow.domain.ClaimCaseApplyType;
import com.paic.ehis.claimflow.domain.dto.CaseRegisterAndAppleTypeDTO;
import com.paic.ehis.claimflow.mapper.ClaimCaseAcceptMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseApplyTypeMapper;
import com.paic.ehis.claimflow.service.IClaimCaseAcceptService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件受理信息 Service业务层处理
 *
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseAcceptServiceImpl implements IClaimCaseAcceptService {
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
    public CaseRegisterAndAppleTypeDTO selectClaimCaseAcceptById(String rptNo) {
        CaseRegisterAndAppleTypeDTO caseRegisterAndAppleTypeDTO = new CaseRegisterAndAppleTypeDTO();
        ClaimCaseAccept claimCaseAccept1 = claimCaseAcceptMapper.selectClaimCaseAcceptById(rptNo);//查询案件受理信息
        if (claimCaseAccept1 !=null){
        caseRegisterAndAppleTypeDTO.setClaimCaseAccept(claimCaseAccept1);
        }
        List<ClaimCaseApplyType> claimCaseApplyTypes = claimCaseApplyTypeMapper.selectClaimCaseApplyTypeByRptNo(rptNo);
        if (claimCaseApplyTypes.size()!=0){
        List<String> applyTypes = new ArrayList<>();
        for (ClaimCaseApplyType claimCaseApplyTypesList : claimCaseApplyTypes) {
            applyTypes.add(claimCaseApplyTypesList.getApplyType());
        }
        caseRegisterAndAppleTypeDTO.setApplyTypes(applyTypes);//申请原因
        }
        return caseRegisterAndAppleTypeDTO;
    }

    /**
     * 查询案件受理信息 列表
     *
     * @param claimCaseAccept 案件受理信息
     * @return 案件受理信息
     */
    @Override
    public List<ClaimCaseAccept> selectClaimCaseAcceptList(ClaimCaseAccept claimCaseAccept) {
        return claimCaseAcceptMapper.selectClaimCaseAcceptList(claimCaseAccept);
    }

    /**
     * 新增案件受理信息
     *
     * @param caseRegisterAndAppleTypeDTO 案件受理信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseAccept(CaseRegisterAndAppleTypeDTO caseRegisterAndAppleTypeDTO) {
        ClaimCaseAccept claimCaseAccept = caseRegisterAndAppleTypeDTO.getClaimCaseAccept();//得到案件信息

        claimCaseAccept.setCreateBy(SecurityUtils.getUsername());
        claimCaseAccept.setCreateTime(DateUtils.getNowDate());
        claimCaseAccept.setUpdateBy(SecurityUtils.getUsername());
        claimCaseAccept.setUpdateTime(DateUtils.getNowDate());
        claimCaseAccept.setStatus("Y");

        List<String> applyTypesList = caseRegisterAndAppleTypeDTO.getApplyTypes();//得到类型码集合

        List<ClaimCaseApplyType> claimCaseApplyTypesList = claimCaseApplyTypeMapper.selectClaimCaseApplyTypeByRptNo(claimCaseAccept.getRptNo());//查询是否已经保存过案件申请类别

        if (claimCaseApplyTypesList!=null && claimCaseApplyTypesList.size()!=0) {
            //不是第一次提交
            for (ClaimCaseApplyType claimCaseApplyTypesLists : claimCaseApplyTypesList) {
                //全部变为无效
                ClaimCaseApplyType claimCaseApplyType1 = new ClaimCaseApplyType();
                claimCaseApplyType1.setApplyId(claimCaseApplyTypesLists.getApplyId());
                claimCaseApplyType1.setStatus("N");
                claimCaseApplyTypeMapper.updateClaimCaseApplyType(claimCaseApplyType1);
            }
        }
        //是第一次
            for (String applyTypes : applyTypesList) {
                ClaimCaseApplyType claimCaseApplyType = new ClaimCaseApplyType();//新的案件申请类别表对象
                claimCaseApplyType.setCreateBy(SecurityUtils.getUsername());
                claimCaseApplyType.setCreateTime(DateUtils.getNowDate());
                claimCaseApplyType.setUpdateBy(SecurityUtils.getUsername());
                claimCaseApplyType.setUpdateTime(DateUtils.getNowDate());
                claimCaseApplyType.setRptNo(claimCaseAccept.getRptNo());
                claimCaseApplyType.setStatus("Y");
                claimCaseApplyType.setApplyType(applyTypes);
                claimCaseApplyTypeMapper.insertClaimCaseApplyType(claimCaseApplyType);
            }

        List<ClaimCaseAccept> claimCaseAccepts = claimCaseAcceptMapper.selectClaimCaseAcceptByIdOne(claimCaseAccept.getRptNo());//查询是否已经存在案件受理信息表
        if (claimCaseAccepts!=null && claimCaseAccepts.size()!=0) {//不为空的情况-已经存在
            for (ClaimCaseAccept claimCaseAccepts1 : claimCaseAccepts) {
                ClaimCaseAccept claimCaseAccept1 = new ClaimCaseAccept();
                claimCaseAccept1.setAcceptId(claimCaseAccepts1.getAcceptId());
                claimCaseAccept1.setStatus("N");
                claimCaseAcceptMapper.updateClaimCaseAccept(claimCaseAccept1);
            }
        }
        return claimCaseAcceptMapper.insertClaimCaseAccept(claimCaseAccept);

    }

    /**
     * 修改案件受理信息
     *
     * @param claimCaseAccept 案件受理信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseAccept(ClaimCaseAccept claimCaseAccept) {
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
    public int deleteClaimCaseAcceptByIds(String[] rptNos) {
        return claimCaseAcceptMapper.deleteClaimCaseAcceptByIds(rptNos);
    }

    /**
     * 删除案件受理信息 信息
     *
     * @param rptNo 案件受理信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAcceptById(String rptNo) {
        return claimCaseAcceptMapper.deleteClaimCaseAcceptById(rptNo);
    }
}
