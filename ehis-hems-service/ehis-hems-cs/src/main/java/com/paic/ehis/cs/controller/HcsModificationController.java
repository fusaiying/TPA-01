package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.HcsModification;
import com.paic.ehis.cs.service.IHcsModificationService;
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
 * HCS预约修改 Controller
 * 
 * @author sino
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/Hms")
public class HcsModificationController extends BaseController
{
    @Autowired
    private IHcsModificationService hcsModificationService;


    /**
     * 查询HCS预约修改 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:modification:list')")
    @GetMapping("/HmsSearch")
    public TableDataInfo list(HcsModification hcsModification)
    {
        startPage();
        List<HcsModification> list = hcsModificationService.selectHcsModificationList(hcsModification);
        return getDataTable(list);
    }

    /**
     * 导出HCS预约修改 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:modification:export')")
    @Log(title = "HCS预约修改 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HcsModification hcsModification) throws IOException
    {
        List<HcsModification> list = hcsModificationService.selectHcsModificationList(hcsModification);
        ExcelUtil<HcsModification> util = new ExcelUtil<HcsModification>(HcsModification.class);
        util.exportExcel(response, list, "modification");
    }

    /**
     * 获取HCS预约修改 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:modification:query')")
    @GetMapping(value = "/{workOrderNo}")
    public AjaxResult getInfo(@PathVariable("workOrderNo") String workOrderNo)
    {
        return AjaxResult.success(hcsModificationService.selectHcsModificationById(workOrderNo));
    }

    /**
     * 新增HCS预约修改 
     */
//    @PreAuthorize("@ss.hasPermi('system:modification:add')")
    @Log(title = "HCS预约修改 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HcsModification hcsModification)
    {
        return toAjax(hcsModificationService.insertHcsModification(hcsModification));
    }

    /**
     * 修改HCS预约修改 
     */
//    @PreAuthorize("@ss.hasPermi('system:modification:edit')")
    @Log(title = "HCS预约修改 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HcsModification hcsModification)
    {
        return toAjax(hcsModificationService.updateHcsModification(hcsModification));
    }

    /**
     * 删除HCS预约修改 
     */
//    @PreAuthorize("@ss.hasPermi('system:modification:remove')")
    @Log(title = "HCS预约修改 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workOrderNos}")
    public AjaxResult remove(@PathVariable String[] workOrderNos)
    {
        return toAjax(hcsModificationService.deleteHcsModificationByIds(workOrderNos));
    }
}
