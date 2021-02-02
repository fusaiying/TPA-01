package com.paic.ehis.base.controller;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseDocInfo;
import com.paic.ehis.base.service.IBaseDocInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * base_doctor(医生信息)Controller
 * 
 * @author sino
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/docinfo")
public class BaseDocInfoController extends BaseController
{
    @Autowired
    private IBaseDocInfoService baseDocInfoService;


    /**
     * 查询base_doctor(医生信息)列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BaseDocInfo baseDocInfo)
    {
        startPage();
        List<BaseDocInfo> list = baseDocInfoService.selectBaseDocInfoList(baseDocInfo);
        return getDataTable(list);
    }

    //查询页面需默认显示截止当前时间在一个月内且合约状态为“有效”的数据
    @GetMapping("/month")
    public TableDataInfo lista(BaseDocInfo baseDocInfo) throws Exception
    {
        startPage();
        List<BaseDocInfo> month = baseDocInfoService.selectBaseMonth(baseDocInfo);
        return getDataTable(month);
    }

    @GetMapping("/dataList")
    public AjaxResult datalist()
    {
        startPage();
        List<BaseDocInfo> listInfo = baseDocInfoService.selectSupplierList();
        return AjaxResult.success(listInfo);
    }


    /**
     * 导出base_doctor(医生信息)列表
     */
    @Log(title = "base_doctor(医生信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseDocInfo baseDocInfo) throws Exception
    {
        List<BaseDocInfo> list = baseDocInfoService.selectBaseDocInfoList(baseDocInfo);
        ExcelUtil<BaseDocInfo> util = new ExcelUtil<BaseDocInfo>(BaseDocInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取base_doctor(医生信息)详细信息
     */
    @GetMapping(value = "/{docCode}")
    public AjaxResult getInfo(@PathVariable("docCode") String docCode)
    {
        return AjaxResult.success(baseDocInfoService.selectBaseDocInfoById(docCode));
    }

    /**
     * 新增base_doctor(医生信息)
     */
    @Log(title = "base_doctor(医生信息)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BaseDocInfo baseDocInfo)
    {
        return AjaxResult.success(baseDocInfoService.insertBaseDocInfo(baseDocInfo));
    }

    /**
     * 修改base_doctor(医生信息)
     */
    @Log(title = "base_doctor(医生信息)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseDocInfo baseDocInfo)
    {
        baseDocInfoService.deleteBaseDocInfoById(baseDocInfo.getDocCode());
        return AjaxResult.success(baseDocInfoService.insertBaseDocInfo(baseDocInfo));
    }

    /**
     * 删除base_doctor(医生信息)
     */
    @Log(title = "base_doctor(医生信息)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{docCodes}")
    public AjaxResult remove(@PathVariable String[] docCodes)
    {
        return toAjax(baseDocInfoService.deleteBaseDocInfoByIds(docCodes));
    }
    //单体删除
    @DeleteMapping("/oneDoc/{docCode}")
    public AjaxResult removeOne(@PathVariable String docCode)
    {
        return toAjax(baseDocInfoService.deleteBaseDocInfoById(docCode));
    }
}
