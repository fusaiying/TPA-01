package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseFeeitem;
import com.paic.ehis.base.service.IBaseFeeitemService;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 费用项信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/feeitem")
public class BaseFeeitemController extends BaseController
{
    @Autowired
    private IBaseFeeitemService baseFeeitemService;

    /**
     * 查询费用项信息 列表
     */    //@GetMapping("/list")
    //@PreAuthorize("@ss.hasPermi('system:feeitem:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseFeeitem baseFeeitem)
    {
        startPage(baseFeeitem);
        List<BaseFeeitem> list = baseFeeitemService.selectBaseFeeitemList(baseFeeitem);
        return getDataTable(list);
    }

    /**
     * 导出费用项信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:feeitem:export')")
    @Log(title = "费用项信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,BaseFeeitem baseFeeitem) throws IOException
    {
//        String feeitemname = URLDecoder.decode(baseFeeitem.getFeeitemname(),"utf-8");
//        baseFeeitem.setFeeitemname(feeitemname);
        List<BaseFeeitem> list = baseFeeitemService.selectBaseFeeitemList(baseFeeitem);
            ExcelUtil<BaseFeeitem> util = new ExcelUtil<BaseFeeitem>(BaseFeeitem.class);
            util.exportExcel(response, list, "feeitem");
    }

    /**
     * 获取费用项信息 详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:feeitem:query')")
    @PostMapping(value = "/{feeitemcode}")
    public AjaxResult getInfo(@PathVariable("feeitemcode") String feeitemcode)
    {
        return AjaxResult.success(baseFeeitemService.selectBaseFeeitemByCode(feeitemcode));
    }

    /**
     * 新增费用项信息 
     */
    //@PreAuthorize("@ss.hasPermi('system:feeitem:add')")
    @Log(title = "费用项信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseFeeitem baseFeeitem)
    {
        BaseFeeitem baseFeeitemByCode= baseFeeitemService.selectBaseFeeitemByCode(baseFeeitem.getFeeitemcode());
        if (StringUtils.isNotNull(baseFeeitemByCode)){
            return AjaxResult.success(0);
        }
        BaseFeeitem baseFeeitemByName = baseFeeitemService.selectBaseFeeitemByName(baseFeeitem.getFeeitemname());
        if (StringUtils.isNotNull(baseFeeitemByName)){
            return AjaxResult.success(0);
        }
        return AjaxResult.success(baseFeeitemService.insertBaseFeeitem(baseFeeitem));
    }

    /**
     * 修改费用项信息 
     */
    //@PreAuthorize("@ss.hasPermi('system:feeitem:edit')")
    @Log(title = "费用项信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseFeeitem baseFeeitem)
    {
        BaseFeeitem feeitem = baseFeeitemService.selectBaseFeeitemByCode(baseFeeitem.getFeeitemcode());
        if (!feeitem.getFeeitemname().equals(baseFeeitem.getFeeitemname())){
            BaseFeeitem baseFeeitemByName = baseFeeitemService.selectBaseFeeitemByName(baseFeeitem.getFeeitemname());
            if (StringUtils.isNotNull(baseFeeitemByName)) {
               return AjaxResult.success( 0);
            }
        }
        return AjaxResult.success(baseFeeitemService.updateBaseFeeitem(baseFeeitem));
    }

    /**
     * 删除费用项信息 
     */
    //@PreAuthorize("@ss.hasPermi('system:feeitem:remove')")
    @Log(title = "费用项信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feeitemCodes}")
    public AjaxResult remove(@PathVariable String[] feeitemCodes)
    {
        return toAjax(baseFeeitemService.deleteBaseFeeitemByIds(feeitemCodes));
    }
}
