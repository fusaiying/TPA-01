package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.service.IPersonInfoService;
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
 * 人员信息 Controller
 * 
 * @author sino
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/info")
public class PersonInfoController extends BaseController
{
    @Autowired
    private IPersonInfoService personInfoService;

    /**
     * 查询人员信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonInfo personInfo)
    {
        startPage();
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        return getDataTable(list);
    }

    /**
     * 导出人员信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "人员信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonInfo personInfo) throws IOException
    {
        List<PersonInfo> list = personInfoService.selectPersonInfoList(personInfo);
        ExcelUtil<PersonInfo> util = new ExcelUtil<PersonInfo>(PersonInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取人员信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{personId}")
    public AjaxResult getInfo(@PathVariable("personId") String personId)
    {
        return AjaxResult.success(personInfoService.selectPersonInfoById(personId));
    }

    /**
     * 新增人员信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "人员信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonInfo personInfo)
    {
        return toAjax(personInfoService.insertPersonInfo(personInfo));
    }

    /**
     * 修改人员信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "人员信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonInfo personInfo)
    {
        return toAjax(personInfoService.updatePersonInfo(personInfo));
    }

    /**
     * 删除人员信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "人员信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{personIds}")
    public AjaxResult remove(@PathVariable String[] personIds)
    {
        return toAjax(personInfoService.deletePersonInfoByIds(personIds));
    }
}
