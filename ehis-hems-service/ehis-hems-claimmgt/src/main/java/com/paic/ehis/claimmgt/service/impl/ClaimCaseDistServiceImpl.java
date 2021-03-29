package com.paic.ehis.claimmgt.service.impl;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimmgt.constant.MgtConstant;
import com.paic.ehis.claimmgt.domain.ClaimUserRole;
import com.paic.ehis.claimmgt.domain.ClaimUserTakeOn;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseOperationDTO;
import com.paic.ehis.claimmgt.mapper.ClaimUserTakeOnMapper;
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

    @Autowired
    private ClaimUserTakeOnMapper claimUserTakeOnMapper;

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

    /**
     * 获取理赔案件操作人
     * @param operation 流程节点
     * @param roleCode 角色编码
     * @param organCode 机构编码
     * @return
     */
    @Override
    public String getClaimCaseOperator(String operation, String roleCode, String organCode) {
        String userName = "";
        //获取当前角色及比例集合
        ClaimCaseDist claimCaseDist = new ClaimCaseDist();
        claimCaseDist.setRoleCode(roleCode);
        claimCaseDist.setUserOrganCode(organCode);
        claimCaseDist.setStatus("Y");
        List<ClaimCaseDist> claimCaseDistList = claimCaseDistMapper.selectClaimCaseDistList(claimCaseDist);

        //查看当前环节待处理案件数量
        ClaimCaseOperationDTO claimCaseOperationDTO = new ClaimCaseOperationDTO();
        claimCaseOperationDTO.setUserOrganCode(organCode);
        claimCaseOperationDTO.setOperation(operation);
        int currOperaCaseCount = claimCaseDistMapper.selectCaseCountByClaimCaseOperation(claimCaseOperationDTO);

        //随机得到处理人
        if(StringUtils.isNotEmpty(claimCaseDistList)){
           userName = getOperator(claimCaseDistList,currOperaCaseCount,claimCaseOperationDTO);
        }else{
            throw new RuntimeException("当前机构无处理该案件下一环节的人员，请联系管理员配置！");
        }

        //查看当前处理人是否设置了承接人
        ClaimUserTakeOn claimUserTakeOn = new ClaimUserTakeOn();
        claimUserTakeOn.setUserName(userName);
        claimUserTakeOn.setStatus("Y");
        List<ClaimUserTakeOn> claimUserTakeOnList = claimUserTakeOnMapper.selectClaimUserTakeOnList(claimUserTakeOn);
        //如果存在有效的承接人规则，获取第一条
        if(StringUtils.isNotEmpty(claimUserTakeOnList)){
            userName = claimUserTakeOnList.get(0).getTakeOnUserName();
        }

        return userName;
    }

    /**
     * 根据对应比例随机获取处理人
     * @param claimCaseDistList 角色比例集合
     * @return
     */
    private String getOperator(List<ClaimCaseDist> claimCaseDistList, int currOperaCaseCount , ClaimCaseOperationDTO claimCaseOperationDTO) {
        String userName = "";
        try{
            //计算总比例
            BigDecimal sumRate = BigDecimal.ZERO;
            for(ClaimCaseDist claimCaseDist : claimCaseDistList){
                sumRate = sumRate.add(claimCaseDist.getRate());
            }

            //产生随机数
            BigDecimal randomNumber = new BigDecimal(Math.random());
            randomNumber = randomNumber.setScale(6,BigDecimal.ROUND_HALF_UP);


            BigDecimal d1 = BigDecimal.ZERO;//概率区间起
            BigDecimal d2 = BigDecimal.ZERO;//概率区间止
            BigDecimal d3 = BigDecimal.ZERO;//随机人员案件分配规则占比
            BigDecimal d4 = BigDecimal.ZERO;//水机人员实际案件占比

            //根据随机数在所有理赔人员的比例区域并确定人员
            for(int i = 0; i < claimCaseDistList.size(); i++){
                d3 = claimCaseDistList.get(i).getRate().divide(sumRate,6,BigDecimal.ROUND_HALF_UP);
                d2 = d2.add(claimCaseDistList.get(i).getRate().divide(sumRate,6,BigDecimal.ROUND_HALF_UP));
                if(i > 0){
                    d1 = d1.add(claimCaseDistList.get(i-1).getRate().divide(sumRate,6,BigDecimal.ROUND_HALF_UP));
                }

                if(randomNumber.compareTo(d1) > 0 && randomNumber.compareTo(d2) <= 0){
                    userName = claimCaseDistList.get(i).getUserName();
                    break;
                }
            }

            //计算当前人员目前案件占比
            if(currOperaCaseCount > 0){
                claimCaseOperationDTO.setUserName(userName);
                int currUserCaseCount = claimCaseDistMapper.selectCaseCountByClaimCaseOperation(claimCaseOperationDTO);
                d4 = BigDecimal.valueOf(currUserCaseCount).add(BigDecimal.ONE).divide(BigDecimal.valueOf(currOperaCaseCount).add(BigDecimal.ONE),6,BigDecimal.ROUND_HALF_UP);

                if(d4.compareTo(d3) > 0){
                    userName = getOperator(claimCaseDistList,currOperaCaseCount,claimCaseOperationDTO);
                }
            }

            return userName;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}



