package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.service.IAttachmentInfoService;
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
 * 附件信息 Controller
 * 
 * @author sino
 * @date 2021-02-20
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentInfoController extends BaseController
{
    @Autowired
    private IAttachmentInfoService attachmentInfoService;

    /**
     * 查询附件信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttachmentInfo attachmentInfo)
    {
        startPage();
        List<AttachmentInfo> list = attachmentInfoService.selectAttachmentInfoList(attachmentInfo);
        return getDataTable(list);
    }

    /**
     * 导出附件信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "附件信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttachmentInfo attachmentInfo) throws IOException
    {
        List<AttachmentInfo> list = attachmentInfoService.selectAttachmentInfoList(attachmentInfo);
        ExcelUtil<AttachmentInfo> util = new ExcelUtil<AttachmentInfo>(AttachmentInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取附件信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{attachmentId}")
    public AjaxResult getInfo(@PathVariable("attachmentId") String attachmentId)
    {
        return AjaxResult.success(attachmentInfoService.selectAttachmentInfoById(attachmentId));
    }

    /**
     * 新增附件信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "附件信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttachmentInfo attachmentInfo)
    {
        return toAjax(attachmentInfoService.insertAttachmentInfo(attachmentInfo));
    }

    /**
     * 修改附件信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "附件信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttachmentInfo attachmentInfo)
    {
        return toAjax(attachmentInfoService.updateAttachmentInfo(attachmentInfo));
    }

    /**
     * 删除附件信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "附件信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attachmentIds}")
    public AjaxResult remove(@PathVariable String[] attachmentIds)
    {
        return toAjax(attachmentInfoService.deleteAttachmentInfoByIds(attachmentIds));
    }
}
