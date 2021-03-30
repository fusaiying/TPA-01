package com.paic.ehis.claimmgt.controller;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
import com.paic.ehis.claimmgt.domain.ClaimUserRole;
import com.paic.ehis.claimmgt.service.IClaimCaseDistService;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.dto.MenuIdDTO;
import com.paic.ehis.system.api.domain.dto.RoleUserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 案件分配规则Controller
 *
 * @author sino
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/dist")
public class ClaimCaseDistController extends BaseController
{
    @Autowired
    private IClaimCaseDistService claimCaseDistService;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询案件分配规则列表
     */
    //@PreAuthorize("@ss.hasPermi('system:dist:list')")
    @PostMapping("/selectClaimCaseDist")
    public TableDataInfo selectClaimCaseDist(@RequestBody ClaimUserRole claimUserRole)
    {
        MenuIdDTO menuIdDTO = new MenuIdDTO();
        menuIdDTO.setMenuId(Long.valueOf(claimUserRole.getMappingValue()));
        menuIdDTO.setPageNum(claimUserRole.getPageNum());
        menuIdDTO.setPageSize(claimUserRole.getPageSize());
        TableDataInfo tableDataInfo = remoteUserService.getUsersByMenuId(menuIdDTO);

        Object data = tableDataInfo.getRows();
        List<RoleUserInfoDTO> roleUserInfoDTOList = new ArrayList<>();
        if(data != null){
            String jsonRoleUserInfoDTOListStr = JSON.toJSONString(data);
            roleUserInfoDTOList = JSON.parseArray(jsonRoleUserInfoDTOListStr,RoleUserInfoDTO.class);
        }

        List<ClaimUserRole> claimUserRoleList = claimCaseDistService.selectClaimUserRole(claimUserRole);
        if(StringUtils.isEmpty(claimUserRoleList) || claimUserRoleList.size() > 1){
            return getDataTableErr("角色信息查询错误！");
        }
        claimUserRole = claimUserRoleList.get(0);

        List<RoleUserInfoDTO> termRoleUserInfoDTOList = new ArrayList<>();
        for (RoleUserInfoDTO roleUserInfoDTO:roleUserInfoDTOList) {
            roleUserInfoDTO.setRoleCode(claimUserRole.getRoleCode());

            ClaimCaseDist claimCaseDist = new ClaimCaseDist();
            claimCaseDist.setRoleCode(claimUserRole.getRoleCode());
            claimCaseDist.setUserName(roleUserInfoDTO.getUserName());
            claimCaseDist.setUserOrganCode(roleUserInfoDTO.getOrangeCode());
            List<ClaimCaseDist> claimCaseDistList = claimCaseDistService.getClaimCaseDistInfo(claimCaseDist);
            //存在则获取，因只会存在一条数据所以获取第一条
            if(StringUtils.isNotEmpty(claimCaseDistList)){
                ClaimCaseDist vo = claimCaseDistList.get(0);
                roleUserInfoDTO.setDistId(vo.getDistId());
                roleUserInfoDTO.setDistId(vo.getDistId());
                roleUserInfoDTO.setStatus(vo.getStatus());
                roleUserInfoDTO.setRate(vo.getRate());
                roleUserInfoDTO.setStatus(vo.getStatus());
                roleUserInfoDTO.setIsEqually(claimUserRole.getIsEqually());
            }
            termRoleUserInfoDTOList.add(roleUserInfoDTO);
        }
        tableDataInfo.setRows(termRoleUserInfoDTOList);

        return tableDataInfo;
    }

    /**
     * 查询理赔角色信息
     * @param claimUserRole
     * @return
     */
    @PostMapping("/selectClaimUserRoleInfo")
    public AjaxResult selectClaimUserRoleInfo(@RequestBody ClaimUserRole claimUserRole){
        return AjaxResult.success(claimCaseDistService.selectClaimUserRole(claimUserRole));
    }

    /**
     * 编辑分配规则
     * @param claimCaseDist
     * @return
     */
    @PostMapping("/editClaimCaseDist")
    public AjaxResult editClaimCaseDist(@RequestBody ClaimCaseDist claimCaseDist){
        try{
            claimCaseDistService.editClaimCaseDist(claimCaseDist);
        }catch (Exception e){
            return AjaxResult.error("操作失败，原因：{}",e.getMessage());
        }

        return AjaxResult.success();
    }

    /**
     * 编辑理赔角色
     * @param claimUserRole
     * @return
     */
    @PostMapping("/editClaimUserRole")
    public AjaxResult editClaimUserRole(@RequestBody ClaimUserRole claimUserRole){
        try{
            claimCaseDistService.editClaimUserRole(claimUserRole);
        }catch (Exception e){
            return AjaxResult.error("操作失败，原因：{}",e.getMessage());
        }

        return AjaxResult.success();
    }

    /**
     * 获取理赔案件操作人
     * @param operation 流程节点
     * @param roleCode 角色编码
     * @param organCode 机构编码
     * @return
     */
    @GetMapping("/getClaimCaseOperator")
    public AjaxResult getClaimCaseOperator(@RequestParam("operation") String operation,@RequestParam("roleCode") String roleCode,@RequestParam("organCode") String organCode){
        String userName = "";
        try{
            userName = claimCaseDistService.getClaimCaseOperator(operation,roleCode,organCode);
        }catch (Exception e){
            return AjaxResult.error("获取理赔案件操作人失败，原因："+e.getMessage());
        }

        return AjaxResult.success(userName);
    }
}
