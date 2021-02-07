package com.paic.ehis.claimmgt.controller;

import com.paic.ehis.claimmgt.domain.vo.DeptVo1;
import com.paic.ehis.claimmgt.mapper.SysDeptMapper;
import com.paic.ehis.claimmgt.service.ISysDeptService;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class SysDeptController {
    @Autowired
    private ISysDeptService deptService;


    @GetMapping("/getdepts")
    public DeptVo1 selectDepts(SysDept dept){
        return deptService.selectDepts(dept);
    }


    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable String deptId)
    {
        return AjaxResult.success(deptService.selectDeptById(Long.valueOf(deptId)));
    }
}
