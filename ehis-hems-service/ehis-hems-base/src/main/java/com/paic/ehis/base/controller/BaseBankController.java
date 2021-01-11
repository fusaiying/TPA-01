package com.paic.ehis.base.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.base.service.IBaseBankService;
import com.paic.ehis.base.domain.BaseBank;
import com.paic.ehis.base.domain.vo.BaseBankVo;
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
 * base_bank（银行信息）Controller
 * 
 * @author sino
 * @date 2020-12-28
 */
@RestController
@RequestMapping("/bank")
public class BaseBankController extends BaseController
{
    @Autowired
    private IBaseBankService baseBankService;

    /**
     * 查询base_bank（银行信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:bank:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseBank baseBank)
    {
        startPage();
        List<BaseBank> list = baseBankService.selectBaseBankList(baseBank);
        return getDataTable(list);
    }

    /**
     * 导出base_bank（银行信息）列表
     */
    @PreAuthorize("@ss.hasPermi('system:bank:export')")
    @Log(title = "base_bank（银行信息）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseBank baseBank) throws IOException
    {
        List<BaseBank> list = baseBankService.selectBaseBankList(baseBank);
        ExcelUtil<BaseBank> util = new ExcelUtil<BaseBank>(BaseBank.class);
        util.exportExcel(response, list, "bank");
    }

    /**
     * 获取base_bank（银行信息）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseBankService.selectBaseBankById(id));
    }

    /**
     * 新增base_bank（银行信息）
     */
    @PreAuthorize("@ss.hasPermi('system:bank:add')")
    @Log(title = "base_bank（银行信息）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseBankVo baseBank)
    {
        return toAjax(baseBankService.insertBaseBank(baseBank));
    }

    /**
     * 修改base_bank（银行信息）
     */
    @PreAuthorize("@ss.hasPermi('system:bank:edit')")
    @Log(title = "base_bank（银行信息）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseBank baseBank)
    {
        return toAjax(baseBankService.updateBaseBank(baseBank));
    }

    /**
     * 删除base_bank（银行信息）
     */
    @PreAuthorize("@ss.hasPermi('system:bank:remove')")
    @Log(title = "base_bank（银行信息）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseBankService.deleteBaseBankByIds(ids));
    }
}
