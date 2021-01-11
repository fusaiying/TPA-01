package com.paic.ehis.system.controller;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.api.domain.SysUser;
import com.paic.ehis.system.domain.HmpPersonService;
import com.paic.ehis.system.domain.HmpPersonSrevTouser;
import com.paic.ehis.system.domain.PersonInfo;
import com.paic.ehis.system.service.IHmpPersonServiceService;
import com.paic.ehis.system.service.IHmpPersonSrevTouserService;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 就诊人服务信息Controller
 * 
 * @author sino
 * @date 2020-11-11
 */
@RestController
@RequestMapping("/personservice")
public class HmpPersonServiceController extends BaseController
{
    @Autowired
    private IHmpPersonServiceService hmpPersonServiceService;

    @Autowired
    private IHmpPersonSrevTouserService hmpPersonSrevTouserService;

    /**
     * 查询就诊人服务信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpPersonService hmpPersonService)
    {
        startPage();
        List<HmpPersonService> list = hmpPersonServiceService.selectHmpPersonServiceList(hmpPersonService);
        return getDataTable(list);
    }

    /**
     * 查询我的就诊人服务信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:ownerlist')")
    @GetMapping("/ownerlist")
    public TableDataInfo ownerList(SysUser sysUser)
    {
        startPage();
        String username = SecurityUtils.getUsername();
        sysUser.setUserName(username);
        List<HmpPersonService> list = hmpPersonServiceService.selectHmpPersonServiceListByOnwer(sysUser);
        return getDataTable(list);
    }

    /**
     * 导出就诊人服务信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:export')")
    @Log(title = "就诊人服务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpPersonService hmpPersonService) throws IOException
    {
        List<HmpPersonService> list = hmpPersonServiceService.selectHmpPersonServiceList(hmpPersonService);
        ExcelUtil<HmpPersonService> util = new ExcelUtil<HmpPersonService>(HmpPersonService.class);
        util.exportExcel(response, list, "personservice");
    }

    /**
     * 获取就诊人服务信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:query')")
    @GetMapping(value = "/{personcode}")
    public AjaxResult getInfo(@PathVariable("personcode") String personcode)
    {
        return AjaxResult.success(hmpPersonServiceService.selectHmpPersonServiceById(personcode));
    }

    /**
     * 新增就诊人服务信息
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:add')")
    @Log(title = "就诊人服务信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpPersonService hmpPersonService)
    {
        return toAjax(hmpPersonServiceService.insertHmpPersonService(hmpPersonService));
    }

    /**
     * 修改就诊人服务信息
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:edit')")
    @Log(title = "就诊人服务信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpPersonService hmpPersonService)
    {
        return toAjax(hmpPersonServiceService.updateHmpPersonService(hmpPersonService));
    }

    /**
     * 删除就诊人服务信息
     */
    @PreAuthorize("@ss.hasPermi('system:personservice:remove')")
    @Log(title = "就诊人服务信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{personcodes}")
    public AjaxResult remove(@PathVariable String[] personcodes)
    {
        return toAjax(hmpPersonServiceService.deleteHmpPersonServiceByIds(personcodes));
    }

    /**
     * 查询就诊人服务信息
     */
    @PostMapping("/queryPersonService")
    public AjaxResult queryPersonService(@RequestBody String personcode)
    {
        if (!StringUtils.isEmpty(personcode))
        {
            HmpPersonService personService = hmpPersonServiceService.selectHmpPersonServiceAndChineseById(personcode);
            return AjaxResult.success(personService);
        }else {
            return AjaxResult.error("就诊人编码为空！");
        }
    }

    /**
     * 任务指派
     */
    @PostMapping("/tasksend")
    public AjaxResult taskSend(@RequestBody PersonInfo personInfo)
    {
        HmpPersonService personService = personInfo.getPersonService();
        SysUser user = personInfo.getUser();
        if(!StringUtils.isEmpty(personService.getPersoncode()) && !StringUtils.isEmpty(user.getUserName())){
            String operator = SecurityUtils.getUsername();

            HmpPersonSrevTouser personSrevTouser = new HmpPersonSrevTouser();
            personSrevTouser.setPersoncode(personService.getPersoncode());

            //查询数据库是否该任务已经指派给其他人,有则删掉
            List<HmpPersonSrevTouser> tousers = hmpPersonSrevTouserService.selectHmpPersonSrevTouserList(personSrevTouser);

            if(tousers.size() > 0){
                for (HmpPersonSrevTouser touser : tousers) {
                    hmpPersonSrevTouserService.deleteHmpPersonSrevTouserById(touser.getPersoncode());
                }
            }

            personSrevTouser.setUsercode(user.getUserName());
            personSrevTouser.setStatus("01");
            personSrevTouser.setOperator(operator);
            personSrevTouser.setMakedate(new Date());
            personSrevTouser.setMaketime(PubFun.getCurrentTime());
            personSrevTouser.setModifydate(new Date());
            personSrevTouser.setModifytime(PubFun.getCurrentTime());

            return toAjax(hmpPersonSrevTouserService.insertHmpPersonSrevTouser(personSrevTouser));
        }else {
            return AjaxResult.error("就诊人编码或用户编码为空！");
        }
    }
}
