package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.EditDetail;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.service.IEditDetailService;
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
 * 修改明细 Controller
 * 
 * @author sino
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/detail")
public class EditDetailController extends BaseController
{
    @Autowired
    private IEditDetailService editDetailService;

    /**
     * 查询修改明细 列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(EditDetail editDetail)
    {
        startPage();
        List<EditDetail> list = editDetailService.selectEditDetailList(editDetail);
        return getDataTable(list);
    }


    @GetMapping("/edit")
    public TableDataInfo getEdit(FlowLog flowLog){
         List<EditDetail> list =editDetailService.selectEdit(flowLog);
        return getDataTable(list);
    }

    /**
     * 导出修改明细 列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "修改明细 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EditDetail editDetail) throws IOException
    {
        List<EditDetail> list = editDetailService.selectEditDetailList(editDetail);
        ExcelUtil<EditDetail> util = new ExcelUtil<EditDetail>(EditDetail.class);
        util.exportExcel(response, list, "detail");
    }


    /**
     * 新增修改明细 
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "修改明细 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EditDetail editDetail)
    {
        return toAjax(editDetailService.insertEditDetail(editDetail));
    }

    /**
     * 修改修改明细 
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "修改明细 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EditDetail editDetail)
    {
        return toAjax(editDetailService.updateEditDetail(editDetail));
    }
}
