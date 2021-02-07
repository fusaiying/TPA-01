package com.paic.ehis.claimmgt.controller;


import com.paic.ehis.claimmgt.domain.vo.ClaimCaseStandingVo1;
import com.paic.ehis.claimmgt.service.ISysUserService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @GetMapping("/getusername")
    public AjaxResult selectuserName(ClaimCaseStandingVo1 claimCaseStandingVo1){
        String deptId=claimCaseStandingVo1.getOrgancode();
        String userName=claimCaseStandingVo1.getUserName();
        SysUser user=new SysUser();
        user.setDeptId(Long.valueOf(deptId));
        user.setUserName(userName);
        List<String> list=userService.selectuserName(user);
        return AjaxResult.success(list);
    }
}
