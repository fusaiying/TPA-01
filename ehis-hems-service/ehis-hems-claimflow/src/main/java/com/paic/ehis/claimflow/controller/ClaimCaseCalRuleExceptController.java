package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseCalRuleExcept;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryTwoVO;
import com.paic.ehis.claimflow.service.IClaimCaseCalRuleExceptService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件赔付规则例外Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/except")
public class ClaimCaseCalRuleExceptController extends BaseController
{
    @Autowired
    private IClaimCaseCalRuleExceptService claimCaseCalRuleExceptService;


    /**
     * 查询理算明细不使用规则列表
     */
//    @PreAuthorize("@ss.hasPermi('system:rule:list')")
    @GetMapping("/listByRptNoTwo")
    public TableDataInfo listByRptNoTwo(String rptNo)
    {
        startPage();
        List<TheAdjustmentSubsidiaryTwoVO> list = claimCaseCalRuleExceptService.selectClaimCaseCalRuleListByRptNoTwo(rptNo);
        return getDataTable(list);
    }

    //撤销按钮-就是把相对应得数据变为无效
//    @PreAuthorize("@ss.hasPermi('system:rule:edit')")
    @Log(title = "不参与理算规则", businessType = BusinessType.UPDATE)
    @PostMapping("/editAdjustmentSubsidiaryYes")
    public AjaxResult editAdjustmentSubsidiaryYes(ClaimCaseCalRuleExcept claimCaseCalRuleExcept)
    {
        return toAjax(claimCaseCalRuleExceptService.updateClaimCaseCalRuleExcept(claimCaseCalRuleExcept));
    }
}
