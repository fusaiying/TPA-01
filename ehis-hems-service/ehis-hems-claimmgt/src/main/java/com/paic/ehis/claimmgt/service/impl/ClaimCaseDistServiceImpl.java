package com.paic.ehis.claimmgt.service.impl;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimmgt.constant.MgtConstant;
import com.paic.ehis.claimmgt.domain.ClaimUserRole;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
import com.paic.ehis.claimmgt.mapper.ClaimCaseDistMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseDistService;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.dto.MenuIdDTO;
import com.paic.ehis.system.api.domain.dto.RoleUserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 案件分配规则Service业务层处理
 *
 * @author sino
 * @date 2021-01-22
 */
@Service
public class ClaimCaseDistServiceImpl implements IClaimCaseDistService
{
    @Autowired
    private ClaimCaseDistMapper claimCaseDistMapper;

    @Autowired
    private RemoteUserService remoteUserService;
    /**
     * 获取分配规则信息
     * @param claimCaseDist
     * @return
     */
    @Override
    public List<ClaimCaseDist> getClaimCaseDistInfo(ClaimCaseDist claimCaseDist) {
        return claimCaseDistMapper.selectClaimCaseDistList(claimCaseDist);
    }

    /**
     * 查询理赔角色信息
     * @param claimUserRole
     * @return
     */
    @Override
    public List<ClaimUserRole> selectClaimUserRole(ClaimUserRole claimUserRole) {
        return claimCaseDistMapper.selectClaimUserRole(claimUserRole);
    }

    /**
     * 编辑分配规则
     * @param claimCaseDist
     * @return
     */
    @Override
    public int editClaimCaseDist(ClaimCaseDist claimCaseDist) {
        int i = 0;
        ClaimCaseDist checkClaimCaseDist = new ClaimCaseDist();
        checkClaimCaseDist.setRoleCode(claimCaseDist.getRoleCode());
        checkClaimCaseDist.setUserName(claimCaseDist.getUserName());
        checkClaimCaseDist.setDistId(claimCaseDist.getDistId());
        List<ClaimCaseDist> claimCaseDistList = claimCaseDistMapper.selectClaimCaseDistList(checkClaimCaseDist);
        boolean isUpdate = false;
        if(StringUtils.isNotEmpty(claimCaseDistList)){
            if(claimCaseDistList.size() > 1){
                throw new RuntimeException("分配数据查询异常！");
            }
            isUpdate = true;
        }
        //更新
        if(isUpdate){
            claimCaseDist.setUpdateBy(SecurityUtils.getUsername());
            claimCaseDist.setUpdateTime(DateUtils.getNowDate());
            i = claimCaseDistMapper.updateClaimCaseDist(claimCaseDist);
        }else {//新增
            claimCaseDist.setCreateBy(SecurityUtils.getUsername());
            claimCaseDist.setCreateTime(DateUtils.getNowDate());
            i = claimCaseDistMapper.insertClaimCaseDist(claimCaseDist);
        }

        return i;
    }

    /**
     * 编辑理赔角色
     * @param claimUserRole
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int editClaimUserRole(ClaimUserRole claimUserRole) {

        if(MgtConstant.IsEquallyYES.equals(claimUserRole.getIsEqually())){
            MenuIdDTO menuIdDTO = new MenuIdDTO();
            menuIdDTO.setMenuId(Long.valueOf(claimUserRole.getMappingValue()));
            Object data = remoteUserService.getUsersByMenuId(menuIdDTO).getRows();
            List<RoleUserInfoDTO> roleUserInfoDTOList = new ArrayList<>();
            if(data != null){
                String jsonRoleUserInfoDTOListStr = JSON.toJSONString(data);
                roleUserInfoDTOList = JSON.parseArray(jsonRoleUserInfoDTOListStr,RoleUserInfoDTO.class);
            }

            for (RoleUserInfoDTO roleUserInfo : roleUserInfoDTOList) {
                ClaimCaseDist claimCaseDist = new ClaimCaseDist();
                claimCaseDist.setRoleCode(claimUserRole.getRoleCode());
                claimCaseDist.setUserName(roleUserInfo.getUserName());
                claimCaseDist.setUserOrganCode(roleUserInfo.getOrangeCode());
                claimCaseDist.setRate(new BigDecimal("50"));
                claimCaseDist.setStatus("Y");

                editClaimCaseDist(claimCaseDist);
            }
        }else if(MgtConstant.IsEquallyNO.equals(claimUserRole.getIsEqually())){//不均分更新比例为0
            ClaimCaseDist claimCaseDist = new ClaimCaseDist();
            claimCaseDist.setRoleCode(claimUserRole.getRoleCode());
            claimCaseDist.setRate(new BigDecimal("0"));
            claimCaseDist.setUpdateBy(SecurityUtils.getUsername());
            claimCaseDist.setUpdateTime(DateUtils.getNowDate());

            claimCaseDistMapper.updateClaimCaseDist(claimCaseDist);
        }

        return claimCaseDistMapper.updateClaimUserRole(claimUserRole);
    }


}



