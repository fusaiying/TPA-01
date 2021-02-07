package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.utils.PathUtil;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.file.FileUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.order.domain.ServiceBalanceFileInfo;
import com.paic.ehis.order.service.IServiceBalanceFileInfoService;
import com.paic.ehis.order.service.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * service_balance_file_info（服务结算文件信息）Controller
 * 
 * @author sino
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/balanceFile")
public class ServiceBalanceFileInfoController extends BaseController
{
    @Autowired
    private IServiceBalanceFileInfoService serviceBalanceFileInfoService;

    /**
     * 查询service_balance_file_info（服务结算文件信息）列表
     */
//    @PreAuthorize("@ss.hasPermi('order:balanceFile:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ServiceBalanceFileInfo serviceBalanceFileInfo)
    {
        startPage();
        List<ServiceBalanceFileInfo> list = serviceBalanceFileInfoService.selectServiceBalanceFileInfoList(serviceBalanceFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出service_balance_file_info（服务结算文件信息）列表
     */
//    @PreAuthorize("@ss.hasPermi('order:balanceFile:export')")
    @Log(title = "service_balance_file_info（服务结算文件信息）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceBalanceFileInfo serviceBalanceFileInfo) throws IOException
    {
        List<ServiceBalanceFileInfo> list = serviceBalanceFileInfoService.selectServiceBalanceFileInfoList(serviceBalanceFileInfo);
        ExcelUtil<ServiceBalanceFileInfo> util = new ExcelUtil<ServiceBalanceFileInfo>(ServiceBalanceFileInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取service_balance_file_info（服务结算文件信息）详细信息
     */
//    @PreAuthorize("@ss.hasPermi('order:balanceFile:query')")
    @GetMapping(value = "/{serialNo}")
    public AjaxResult getInfo(@PathVariable("serialNo") Long serialNo)
    {
        return AjaxResult.success(serviceBalanceFileInfoService.selectServiceBalanceFileInfoById(serialNo));
    }

    /**
     * 新增service_balance_file_info（服务结算文件信息）
     */
//    @PreAuthorize("@ss.hasPermi('order:balanceFile:add')")
    @Log(title = "service_balance_file_info（服务结算文件信息）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceBalanceFileInfo serviceBalanceFileInfo)
    {
        return toAjax(serviceBalanceFileInfoService.insertServiceBalanceFileInfo(serviceBalanceFileInfo));
    }

    /**
     * 修改service_balance_file_info（服务结算文件信息）
     */
//    @PreAuthorize("@ss.hasPermi('order:balanceFile:edit')")
    @Log(title = "service_balance_file_info（服务结算文件信息）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceBalanceFileInfo serviceBalanceFileInfo)
    {
        return toAjax(serviceBalanceFileInfoService.updateServiceBalanceFileInfo(serviceBalanceFileInfo));
    }

    /**
     * 删除service_balance_file_info（服务结算文件信息）
     */
//    @PreAuthorize("@ss.hasPermi('order:balanceFile:remove')")
    @Log(title = "service_balance_file_info（服务结算文件信息）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable Long[] serialNos)
    {
        return toAjax(serviceBalanceFileInfoService.deleteServiceBalanceFileInfoByIds(serialNos));
    }

    /**
     * 服务结算文件上传
     * @param file
     * @param taskNo
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public AjaxResult importData(MultipartFile file, @RequestParam("taskNo") String taskNo) throws Exception {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        //文件名称
        String fileName = file.getOriginalFilename();
        // 文件信息
        ServiceBalanceFileInfo serviceBalanceFileInfo = new ServiceBalanceFileInfo();
        // 设置文件名称
        serviceBalanceFileInfo.setFileName(fileName);
        // 设置文件来源
        serviceBalanceFileInfo.setFileSource(taskNo);
        //有效
        serviceBalanceFileInfo.setStatus(StatusEnum.VALID.getCode());
        // 文件信息
        List<ServiceBalanceFileInfo> fileInfoList = serviceBalanceFileInfoService.selectServiceBalanceFileInfoList(serviceBalanceFileInfo);
        if (fileInfoList.size() > 0) {
            return AjaxResult.error("操作失败-`" + fileName + "`文件名已存在！");
        }
        //文件保存地址
        String url = PathUtil.projectPath.substring(6, PathUtil.projectPath.length()) + "sino_file/sino_order/balance/" + taskNo + "/";
        //保存文件
        boolean flag = FileUtils.saveFileByMultipartFile(file, url);
        if (!flag) {
            return AjaxResult.error("操作失败-请检查`" + fileName + "`文件内容是否为空！");
        }
        //文件信息入库
        serviceBalanceFileInfo.setFilePath(url + fileName);
        int i = serviceBalanceFileInfoService.insertServiceBalanceFileInfo(serviceBalanceFileInfo);
        if (i > 0) {
            return AjaxResult.success("上传文件成功！");
        } else {
            return AjaxResult.error("上传文件失败！");
        }
    }

    /**
     * 导出文件信息列表
     */
    @Log(title = "下载文件", businessType = BusinessType.EXPORT)
    @PostMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("serialNo") Long serialNo) throws IOException
    {
        if(serialNo == null) {
            return;
        }
        ServiceBalanceFileInfo serviceBalanceFileInfo = serviceBalanceFileInfoService.selectServiceBalanceFileInfoById(serialNo);

        if(FileUtils.isValidFilename(serviceBalanceFileInfo.getFileName())) {
            FileUtils.writeBytes(serviceBalanceFileInfo.getFilePath(),response.getOutputStream());
        }
    }
}
