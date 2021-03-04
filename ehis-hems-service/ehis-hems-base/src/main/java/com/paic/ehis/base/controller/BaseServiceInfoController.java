package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseServiceApplyorimpl;
import com.paic.ehis.base.domain.BaseServiceInfo;
import com.paic.ehis.base.domain.BaseServiceProcess;
import com.paic.ehis.base.domain.vo.ServiceProcess;
import com.paic.ehis.base.service.IBaseServiceApplyorimplService;
import com.paic.ehis.base.service.IBaseServiceCategoryKindService;
import com.paic.ehis.base.service.IBaseServiceInfoService;
import com.paic.ehis.base.service.IBaseServiceProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_service_info（服务项目）Controller
 * 
 * @author sino
 * @date 2020-12-28
 */
@RestController
@RequestMapping("/projectInfo")
public class BaseServiceInfoController extends BaseController
{
    @Autowired
    private IBaseServiceInfoService baseServiceInfoService;
    @Autowired
    private IBaseServiceCategoryKindService categoryKindService;
    @Autowired
    private IBaseServiceProcessService serviceProcessService;
    @Autowired
    private IBaseServiceApplyorimplService applyorimplService;

    /**
     * 查询base_service_info（服务项目）列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseServiceInfo baseServiceInfo)
    {
        startPage(baseServiceInfo);
        List<BaseServiceInfo> list = baseServiceInfoService.selectBaseServiceInfoList(baseServiceInfo);
        return getDataTable(list);
    }

    /**
     * 导出base_service_info（服务项目）列表
     */
    @Log(title = "base_service_info（服务项目）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseServiceInfo baseServiceInfo) throws IOException
    {
        List<BaseServiceInfo> list = baseServiceInfoService.selectBaseServiceInfoList(baseServiceInfo);
        ExcelUtil<BaseServiceInfo> util = new ExcelUtil<BaseServiceInfo>(BaseServiceInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取base_service_info（服务项目）详细信息
     */
    @GetMapping(value = "/{servicecode}")
    public AjaxResult getInfo(@PathVariable("servicecode") String servicecode)
    {
        return AjaxResult.success(baseServiceInfoService.selectBaseServiceInfoById(servicecode));
    }

    /**
     * 新增base_service_info（服务项目）
     */
    @Log(title = "base_service_info（服务项目）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BaseServiceInfo baseServiceInfo)
    {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        if ("".equals(baseServiceInfo.getServiceCode()) || baseServiceInfo.getServiceCode() == null) {
            // 服务项目名称判重
            String servicename = baseServiceInfo.getServiceName();
            List<BaseServiceInfo> serviceInfoList = baseServiceInfoService.selectServiceByName(servicename);
            if (serviceInfoList.size() > 0) {
                return AjaxResult.error("服务项目名称已存在，请确认后重新录入！");
            }
            // 别名判重
            String alias = baseServiceInfo.getAlias();
            List<BaseServiceInfo> aliasList = baseServiceInfoService.selectBaseServiceInfoByAlias(alias);
            if (aliasList.size() > 0){
                return AjaxResult.error("别名已存在，请确认后重新录入！");
            }
            // 原名称判重
            String originalName = baseServiceInfo.getOriginalName();
            List<BaseServiceInfo> orNameList = baseServiceInfoService.selectBaseServiceInfoByorName(originalName);
            if (orNameList.size() > 0){
                return AjaxResult.error("原名称已存在，请确认后重新录入！");
            }
            // 生成服务项目编码
            String projectId = baseServiceInfo.getServertypeCode();
            String maxno = selectServerMaxNo(baseServiceInfo.getServertypeCode());
            if ("".equals(maxno) || maxno == null){
                projectId = projectId + "01";
            } else {
                int max = Integer.parseInt(maxno);
                projectId = projectId + (String.format("%02d",++max));
            }
            baseServiceInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("projectSer",12,20));
            baseServiceInfo.setServiceCode(projectId);
            baseServiceInfo.setState("Y");
            baseServiceInfo.setCreateBy(username);
            baseServiceInfoService.insertBaseServiceInfo(baseServiceInfo);
        } else {
            String servicecode = baseServiceInfo.getServiceCode();
            String servicename = baseServiceInfo.getServiceName();
            // 服务名称判重
            List<BaseServiceInfo> serviceInfoList = baseServiceInfoService.selectServiceByName(servicename);
            for (int i = 0; i < serviceInfoList.size(); i++){
                if (!serviceInfoList.get(i).getServiceCode().equals(servicecode)){
                    return AjaxResult.error("当前修改的[服务项目名称]在系统中已存在，请再次确认后重新录入!");
                }
            }
            // 别名判重
            String alias = baseServiceInfo.getAlias();
            List<BaseServiceInfo> aliasList = baseServiceInfoService.selectBaseServiceInfoByAlias(alias);
            if (aliasList.size() > 0){
                for (int i = 0; i < aliasList.size(); i++) {
                    if (!aliasList.get(i).getServiceCode().equals(servicecode)){
                        return AjaxResult.error("当前修改的[别名]在系统中已存在，请再次确认后重新录入！");
                    }
                }
            }
            // 原名称判重
            String originalName = baseServiceInfo.getOriginalName();
            List<BaseServiceInfo> orNameList = baseServiceInfoService.selectBaseServiceInfoByorName(originalName);
            if (orNameList.size() > 0){
                for (int i = 0; i < orNameList.size(); i++) {
                    if (!orNameList.get(i).getServiceCode().equals(servicecode)){
                        return AjaxResult.error("当前修改的[原名称]在系统中已存在，请再次确认后重新录入！");
                    }
                }
            }
            String servertypeCode = baseServiceInfo.getServertypeCode();
            System.out.println("serverTypeCode:"+servertypeCode);
            String subServerType = servicecode.substring(0, 7); //C1001之类
            System.out.println("subServerType:"+subServerType);
            // 种类改变
            if (!servertypeCode.equals(subServerType)){
                // 生成新的服务项目编码
                String maxno = selectServerMaxNo(servertypeCode);
                if ("".equals(maxno) || maxno ==null){
                    maxno = "01";
                } else {
                    maxno = String.format("%02d",Integer.parseInt(maxno) + 1);
                }
                String newServiceCode = servertypeCode + maxno;
                // 流程配置 根据流水号更改成新的服务项目编码
                int row1 = serviceProcessService.updateProcessNewServiceCode(servicecode,newServiceCode);
                // 字段 根据流水号更改成新的服务项目编码
                int row2 = applyorimplService.updateFieldNewServiceCode(servicecode, newServiceCode);
                // 替换服务项目信息表里的服务项目编码
                baseServiceInfo.setServiceCode(newServiceCode);
            }
            baseServiceInfo.setUpdateBy(username);
            baseServiceInfoService.updateBaseServiceInfo(baseServiceInfo);
        }
        return AjaxResult.success(baseServiceInfo);
    }

    /**
     * 修改base_service_info（服务项目）
     */
    @Log(title = "base_service_info（服务项目）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseServiceInfo baseServiceInfo)
    {
        return toAjax(baseServiceInfoService.updateBaseServiceInfo(baseServiceInfo));
    }

    /**
     * 删除base_service_info（服务项目）
     * 将服务项目的状态置为 03
     */
    @Log(title = "base_service_info（服务项目）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servicecode}")
    public AjaxResult remove(@PathVariable String servicecode)
    {
        int processRow = serviceProcessService.updateBaseServiceProcessById(servicecode);
        logger.info("删除-更新流程配置表行数："+processRow);
        int fieldRow = applyorimplService.updateBaseServiceApplyorimplById(servicecode);
        logger.info("删除-更新字段配置表行数："+fieldRow);
        // 改变状态
        return toAjax(baseServiceInfoService.updateBaseServiceInfoById(servicecode));
    }

    /**
     * 查询所有类别
     *
     * @return
     */
    @GetMapping("/getCategoryType")
    public AjaxResult getCategoryType(){
        return AjaxResult.success(categoryKindService.selectCategoryType());
    }

    /**
     * 查询类别下的种类
     *
     * @param category 类别编码
     * @return
     */
    @GetMapping("getServerType")
    public AjaxResult getServerType(String category){
        return AjaxResult.success(categoryKindService.selectServerType(category));
    }

    /**
     * 查询服务项目配置信息
     *
     * @param servicecode
     * @return
     */
    @GetMapping("/getServiceProcess")
    public AjaxResult getServiceProcess(String servicecode){
        return AjaxResult.success(serviceProcessService.getServiceProcess(servicecode));
    }

    /**
     * 保存服务项目流程配置
     *
     * @param
     * @return
     */
    @PostMapping("/saveServiceProcess")
    public AjaxResult saveServiceProcess(@RequestBody ServiceProcess serviceProcess){
        // 修改表中已存服务项目配置字段 状态置为N
        String servicecode = serviceProcess.getServicecode();
        serviceProcessService.updateBaseServiceProcessById(servicecode);
        applyorimplService.updateBaseServiceApplyorimplById(servicecode);
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        // 更新服务项目基本信息 状态变成02
        String status = serviceProcess.getStatus();
        BaseServiceInfo baseServiceInfo = new BaseServiceInfo();
        baseServiceInfo.setServiceCode(servicecode);
        baseServiceInfo.setStatus(status);
        baseServiceInfo.setUpdateBy(username);
        baseServiceInfoService.updateBaseServiceInfoByCodeAndState(baseServiceInfo);
        // 拆分流程和字段
        List<BaseServiceProcess> processList = serviceProcess.getProcessList();
        List<BaseServiceApplyorimpl> fieldList = serviceProcess.getFieldList();

        for (BaseServiceProcess process : processList){
            process.setSerialNo(PubFun.createMySqlMaxNoUseCache("projectProcessSer",12,20));
            process.setServiceCode(servicecode);
            process.setCreateBy(username);
            // 存储服务配置
            serviceProcessService.insertBaseServiceProcess(process);
        }
        for (BaseServiceApplyorimpl field : fieldList){
            field.setSerialNo(PubFun.createMySqlMaxNoUseCache("projectFieldSer",12,20));
            field.setCreateBy(username);
            // 存储字段
            applyorimplService.insertBaseServiceApplyorimpl(field);
        }

        return AjaxResult.success("保存成功！");
    }

    /**
     * 获取二级编码下的当前最大编码
     *
     * @param serverCode
     * @return
     */
    public String selectServerMaxNo(String serverCode){
        String maxNo = baseServiceInfoService.selectServerMaxNo(serverCode);
        return maxNo;
    }

    /**
    *@Description:  查询所有服务项目
    *@Author: houjiawei
    *@date: 2021/1/11 15:11
    */
    @GetMapping("/allList")
    public TableDataInfo allList(BaseServiceInfo baseServiceInfo)
    {
        List<BaseServiceInfo> list = baseServiceInfoService.selectBaseServiceInfoAllList(baseServiceInfo);
        return getDataTable(list);
    }
}
