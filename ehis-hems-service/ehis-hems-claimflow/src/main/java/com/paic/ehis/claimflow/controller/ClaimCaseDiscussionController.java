package com.paic.ehis.claimflow.controller;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.core.web.page.TableSupport;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.claimflow.domain.ClaimCaseDiscussion;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDiscussionDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseDiscussionVO;
import com.paic.ehis.claimflow.service.IClaimCaseDiscussionService;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
   // @PreAuthorize("@ss.hasPermi('system:discussion:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseDiscussion claimCaseDiscussion)
    {
        startPage();
        List<ClaimCaseDiscussion> list = claimCaseDiscussionService.selectClaimCaseDiscussionList(claimCaseDiscussion);
        return getDataTable(list);
    }

    /**
     * 查询案件历史协谈信息列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:discussion:list')")
    @GetMapping(value = "/history/{rptNo}")
    public TableDataInfo discussionList(@PathVariable("rptNo") String rptNo)
    {
        TableSupport.setOrderByColumn("disc_id");
        TableSupport.setSort("asc");
        startSortPage();
        List<ClaimCaseDiscussion> list = claimCaseDiscussionService.selectDiscussionListByRptNo(rptNo);
        return getDataTable(list);
    }

    /**
     * 导出案件协谈信息列表
     */
   // @PreAuthorize("@ss.hasPermi('system:discussion:export')")
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
   // @PreAuthorize("@ss.hasPermi('system:discussion:query')")
    @GetMapping(value = "/{discId}")
    public AjaxResult getInfo(@PathVariable("discId") Long discId)
    {
        return AjaxResult.success(claimCaseDiscussionService.selectClaimCaseDiscussionById(discId));
    }

    /**
     * 新增案件协谈信息
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:discussion:add')")
    @Log(title = "案件协谈信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseDiscussion claimCaseDiscussion)
    {
        return toAjax(claimCaseDiscussionService.insertClaimCaseDiscussion(claimCaseDiscussion));
    }

    /**
     * 修改案件协谈信息
     */
   // @PreAuthorize("@ss.hasPermi('system:discussion:edit')")
    @Log(title = "案件协谈信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updateClaimCaseDiscussion")
    public AjaxResult edit(@RequestBody ClaimCaseDiscussion claimCaseDiscussion)
    {
        return toAjax(claimCaseDiscussionService.updateClaimCaseDiscussion(claimCaseDiscussion));
    }

    /**
     * 删除案件协谈信息
     */
   // @PreAuthorize("@ss.hasPermi('system:discussion:remove')")
    @Log(title = "案件协谈信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{discIds}")
    public AjaxResult remove(@PathVariable Long[] discIds)
    {
        return toAjax(claimCaseDiscussionService.deleteClaimCaseDiscussionByIds(discIds));
    }

    /**
     * 查询案件未处理协谈工作池
     */
   // @PreAuthorize("@ss.hasPermi('system:case:selectCaseDisListUnder')")
    @GetMapping("/selectCaseDisListUnder")
    public TableDataInfo selectCaseDisListUnder(ClaimCaseDiscussionDTO claimCaseDiscussionDTO)
    {
        startPage();
        List<ClaimCaseDiscussionVO> list = claimCaseDiscussionService.selectCaseDisListUnder(claimCaseDiscussionDTO);
        return getDataTable(list);
    }


    /**
     * 查询案件已处理协谈工作池
     */
   // @PreAuthorize("@ss.hasPermi('system:case:selectCaseDisListOver')")
    @GetMapping("/selectCaseDisListOver")
    public TableDataInfo selectCaseDisListOver(ClaimCaseDiscussionDTO claimCaseDiscussionDTO)
    {
        startPage();
        List<ClaimCaseDiscussionVO> list = claimCaseDiscussionService.selectCaseDisListOver(claimCaseDiscussionDTO);
        return getDataTable(list);
    }

    /*查询基本信息表*/

   // @PreAuthorize("@ss.hasPermi('system:insured:query')")
    @GetMapping(value = "/caseInfo/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        ClaimCaseDiscussionVO  dto = new ClaimCaseDiscussionVO();
        dto.setRptNo(rptNo);
        dto.setIsHistory("N");
        ClaimCaseDiscussionVO claimCaseDiscussionVO = claimCaseDiscussionService.selectCaseBaseInfo(dto);
        return AjaxResult.success(claimCaseDiscussionVO);
    }

    @GetMapping(value = "/caseInfo/history/{rptNo}")
    public AjaxResult getHistoryInfo(@PathVariable("rptNo") String rptNo)
    {
        ClaimCaseDiscussionVO  dto = new ClaimCaseDiscussionVO();
        dto.setRptNo(rptNo);
        dto.setIsHistory("Y");
        ClaimCaseDiscussionVO claimCaseDiscussionVO = claimCaseDiscussionService.selectCaseBaseInfo(dto);
        return AjaxResult.success(claimCaseDiscussionVO);
    }

}
