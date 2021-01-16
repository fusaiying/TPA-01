package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseDiscussion;
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
import com.paic.ehis.system.service.IClaimCaseDiscussionService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件协谈信息Controller
 * 
 * @author sino
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/discussion")
public class ClaimCaseDiscussionController extends BaseController
{
    @Autowired
    private IClaimCaseDiscussionService claimCaseDiscussionService;

    /**
     * 查询案件协谈信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:discussion:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseDiscussion claimCaseDiscussion)
    {
        startPage();
        List<ClaimCaseDiscussion> list = claimCaseDiscussionService.selectClaimCaseDiscussionList(claimCaseDiscussion);
        return getDataTable(list);
    }

    /**
     * 导出案件协谈信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:discussion:export')")
    @Log(title = "案件协谈信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseDiscussion claimCaseDiscussion) throws IOException
    {
        List<ClaimCaseDiscussion> list = claimCaseDiscussionService.selectClaimCaseDiscussionList(claimCaseDiscussion);
        ExcelUtil<ClaimCaseDiscussion> util = new ExcelUtil<ClaimCaseDiscussion>(ClaimCaseDiscussion.class);
        util.exportExcel(response, list, "discussion");
    }

    /**
     * 获取案件协谈信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:discussion:query')")
    @GetMapping(value = "/{discId}")
    public AjaxResult getInfo(@PathVariable("discId") Long discId)
    {
        return AjaxResult.success(claimCaseDiscussionService.selectClaimCaseDiscussionById(discId));
    }

    /**
     * 新增案件协谈信息
     */
    @PreAuthorize("@ss.hasPermi('system:discussion:add')")
    @Log(title = "案件协谈信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseDiscussion claimCaseDiscussion)
    {
        return toAjax(claimCaseDiscussionService.insertClaimCaseDiscussion(claimCaseDiscussion));
    }

    /**
     * 修改案件协谈信息
     */
    @PreAuthorize("@ss.hasPermi('system:discussion:edit')")
    @Log(title = "案件协谈信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseDiscussion claimCaseDiscussion)
    {
        return toAjax(claimCaseDiscussionService.updateClaimCaseDiscussion(claimCaseDiscussion));
    }

    /**
     * 删除案件协谈信息
     */
    @PreAuthorize("@ss.hasPermi('system:discussion:remove')")
    @Log(title = "案件协谈信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{discIds}")
    public AjaxResult remove(@PathVariable Long[] discIds)
    {
        return toAjax(claimCaseDiscussionService.deleteClaimCaseDiscussionByIds(discIds));
    }
}
