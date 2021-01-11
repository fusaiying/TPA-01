package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.HmpFileInfo;
import com.paic.ehis.system.service.IHmpFileInfoService;
import com.paic.ehis.common.core.utils.file.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 文件信息Controller
 * 
 * @author sino
 * @date 2020-10-16
 */
@RestController
@RequestMapping("/fileInfo")
public class HmpFileInfoController extends BaseController
{
    @Autowired
    private IHmpFileInfoService hmpFileInfoService;

    /**
     * 查询文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpFileInfo hmpFileInfo)
    {
        startPage();
        List<HmpFileInfo> list = hmpFileInfoService.selectHmpFileInfoList(hmpFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:export')")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpFileInfo hmpFileInfo) throws IOException
    {
        List<HmpFileInfo> list = hmpFileInfoService.selectHmpFileInfoList(hmpFileInfo);
        ExcelUtil<HmpFileInfo> util = new ExcelUtil<HmpFileInfo>(HmpFileInfo.class);
        util.exportExcel(response, list, "文件管理");
    }

    /**
     * 导出文件信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:download')")
    @Log(title = "下载文件", businessType = BusinessType.EXPORT)
    @PostMapping("/download")
    public void download(HttpServletResponse response,@RequestBody HmpFileInfo hmpFileInfo) throws IOException
    {
        if(StringUtils.isEmpty(hmpFileInfo.getFilecode())){
            return;
        }
        HmpFileInfo dbFileInfo = hmpFileInfoService.selectHmpFileInfoById(hmpFileInfo.getFilecode());
        if(FileUtils.isValidFilename(dbFileInfo.getFilename())){
            FileUtils.writeBytes(dbFileInfo.getFilepath(),response.getOutputStream());
        }
    }

    /**
     * 获取文件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:query')")
    @GetMapping(value = "/{fileCode}")
    public AjaxResult getInfo(@PathVariable("fileCode") String fileCode)
    {
        return AjaxResult.success(hmpFileInfoService.selectHmpFileInfoById(fileCode));
    }

    /**
     * 新增文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:add')")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpFileInfo hmpFileInfo)
    {
        return toAjax(hmpFileInfoService.insertHmpFileInfo(hmpFileInfo));
    }

    /**
     * 修改文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:edit')")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpFileInfo hmpFileInfo)
    {
        return toAjax(hmpFileInfoService.updateHmpFileInfo(hmpFileInfo));
    }

    /**
     * 删除文件信息
     */
    @PreAuthorize("@ss.hasPermi('system:fileInfo:remove')")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileCodes}")
    public AjaxResult remove(@PathVariable String[] fileCodes)
    {
        boolean fileFlag = false;
        int deleteFlag = 0;
        for (String fileCode : fileCodes) {
            HmpFileInfo fileInputDownload = hmpFileInfoService.selectHmpFileInfoById(fileCode);
            if(fileInputDownload != null){
                String filepath = fileInputDownload.getFilepath();
                fileFlag = FileUtils.deleteFile(filepath);
                if(fileFlag){
                    deleteFlag = deleteFlag + hmpFileInfoService.deleteHmpFileInfoById(fileCode);
                }
            }
        }
        return toAjax(deleteFlag);
    }
}
