package com.paic.ehis.product.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.product.service.IHmpServProjectService;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.product.domain.HmpServOpera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.product.domain.HmpServProject;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 服务项目Controller
 *
 * @author sino
 * @date 2020-11-09
 */
@RestController
@RequestMapping("/project")
public class HmpServProjectController extends BaseController {
    @Autowired
    private IHmpServProjectService hmpServProjectService;


    /**
     * 查询服务项目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HmpServProject hmpServProject) {
        startPage();
        List<HmpServProject> list = hmpServProjectService.selectHmpServProjectList(hmpServProject);
        return getDataTable(list);
    }

    /**
     * 导出服务项目列表
     */
    @Log(title = "服务项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpServProject hmpServProject) throws IOException {
        List<HmpServProject> list = hmpServProjectService.selectHmpServProjectList(hmpServProject);
        ExcelUtil<HmpServProject> util = new ExcelUtil<HmpServProject>(HmpServProject.class);
        util.exportExcel(response, list, "project");
    }

    /**
     * 获取服务项目详细信息
     */
    @GetMapping(value = "/{projectcode}")
    public AjaxResult getInfo(@PathVariable("projectcode") String projectcode) {
        return AjaxResult.success(hmpServProjectService.selectHmpServProjectById(projectcode));
    }

    /**
     * 新增服务项目
     */
    @Log(title = "服务项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpServProject hmpServProject) {
        return toAjax(hmpServProjectService.insertHmpServProject(hmpServProject));
    }

    /**
     * 新增服务项目
     */
    @Log(title = "服务项目", businessType = BusinessType.INSERT)
    @PostMapping("/saveProject")
    public AjaxResult saveProject(@RequestBody List<HmpServProject> servProjects) {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        if (servProjects.size() > 0) {
            Date makedate = new Date();
            String maketime = PubFun.getCurrentTime();
            String projectcode = "";
            int result = 0;
            for (HmpServProject servProject : servProjects) {
                if (StringUtils.isEmpty(servProject.getProjectcode())) {
                    if (StringUtils.isEmpty(projectcode)) {
                        projectcode = "PJ" + PubFun.createMySqlMaxNoUseCache("ServProject", 10, 8);
                    }
                    if (StringUtils.isEmpty(servProject.getStatus())) {
                        servProject.setStatus("01");
                    }
                    servProject.setProjectcode(projectcode);
                    servProject.setOperator(username);
                    servProject.setMakedate(makedate);
                    servProject.setMaketime(maketime);
                    servProject.setModifydate(makedate);
                    servProject.setModifytime(maketime);
                    result = result + hmpServProjectService.insertHmpServProject(servProject);
                } else {
                    projectcode = servProject.getProjectcode();
                    servProject.setOperator(username);
                    servProject.setModifydate(makedate);
                    servProject.setModifytime(maketime);
//                    result = result + hmpServProjectService.updateHmpServProject(servProject);
                    result = result + hmpServProjectService.updateHmpServOperaById(servProject);
                }
            }
            return AjaxResult.success(projectcode);
        } else {
            return AjaxResult.error("没有处理的服务项目!");
        }
    }

    /**
     * 修改服务项目
     */
    @Log(title = "服务项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpServProject hmpServProject) {
        return toAjax(hmpServProjectService.updateHmpServProject(hmpServProject));
    }

    /**
     * 删除服务项目
     */
    @Log(title = "服务项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectcodes}")
    public AjaxResult remove(@PathVariable String[] projectcodes) {
        return toAjax(hmpServProjectService.deleteHmpServProjectByIds(projectcodes));
    }

    @PostMapping("/queryProjectData")
    public AjaxResult queryProjectData(@RequestBody String projectcode) {
        List<HmpServProject> projects = new ArrayList<>();
        if (!StringUtils.isEmpty(projectcode)) {
            projects = hmpServProjectService.selectHmpServProjectListByProjectCode(projectcode);
        }
        return AjaxResult.success(projects);
    }


    /**
     * 查询服务项目 用于下拉
     * 应用于供应商合约
     */
    @GetMapping("/selectOptions")
    public AjaxResult selectOptions() {
        return AjaxResult.success(hmpServProjectService.selectHmpServProjectOptions());
    }

    /**
     * 查询服务项目所有供应商
     *
     * @param hmpServProject 服务项目
     * @return 供应商列表
     */
    @GetMapping("/getServiceOrgList")
    public TableDataInfo getServiceOrgList(HmpServProject hmpServProject) {
        startPage();
        String projectcode = hmpServProject.getProjectcode();
        List<HmpServOpera> orgList = hmpServProjectService.queryServiceOrgByProjectCode(projectcode);
        return getDataTable(orgList);
    }
}
