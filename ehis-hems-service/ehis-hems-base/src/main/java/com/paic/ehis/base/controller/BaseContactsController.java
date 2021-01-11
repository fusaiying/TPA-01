package com.paic.ehis.base.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.base.service.IBaseContactsService;
import com.paic.ehis.base.domain.BaseContacts;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
 * base_contacts（联系人信息）Controller
 * 
 * @author sino
 * @date 2020-12-25
 */
@RestController
@RequestMapping("/contacts")
public class BaseContactsController extends BaseController
{
    @Autowired
    private IBaseContactsService baseContactsService;

    /**
     * 查询base_contacts（联系人信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseContacts baseContacts)
    {
        startPage();
        List<BaseContacts> list = baseContactsService.selectBaseContactsList(baseContacts);
        return getDataTable(list);
    }

    /**
     * 导出base_contacts（联系人信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:export')")
    @Log(title = "base_contacts（联系人信息）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseContacts baseContacts) throws IOException
    {
        List<BaseContacts> list = baseContactsService.selectBaseContactsList(baseContacts);
        ExcelUtil<BaseContacts> util = new ExcelUtil<BaseContacts>(BaseContacts.class);
        util.exportExcel(response, list, "contacts");
    }

    /**
     * 获取base_contacts（联系人信息）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:query')")
    @GetMapping(value = "/{serialno}")
    public AjaxResult getInfo(@PathVariable("serialno") String serialno)
    {
        return AjaxResult.success(baseContactsService.selectBaseContactsById(serialno));
    }

    /**
     * 新增base_contacts（联系人信息）
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:add')")
    @Log(title = "base_contacts（联系人信息）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseContacts baseContacts)
    {
        return toAjax(baseContactsService.insertBaseContacts(baseContacts));
    }

    /**
     * 修改base_contacts（联系人信息）
     */
   /* @PreAuthorize("@ss.hasPermi('system:contacts:edit')")
    @Log(title = "base_contacts（联系人信息）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseContacts baseContacts)
    {
        return toAjax(baseContactsService.updateBaseContacts(baseContacts));
    }*/

    /**
     * 删除base_contacts（联系人信息）
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:remove')")
    @Log(title = "base_contacts（联系人信息）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialnos}")
    public AjaxResult remove(@PathVariable String[] serialnos)
    {
        return toAjax(baseContactsService.deleteBaseContactsByIds(serialnos));
    }
}
