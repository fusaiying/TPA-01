package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.AttachmentInfo;
import com.paic.ehis.cs.domain.QualityInspectionHandle;
import com.paic.ehis.cs.domain.QualityInspectionItem;
import com.paic.ehis.cs.domain.dto.QualityDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.QualityAcceptVo;
import com.paic.ehis.cs.domain.vo.QualityVo;
import com.paic.ehis.cs.service.*;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.system.api.ClaimFlowService;
import com.paic.ehis.system.api.domain.dto.ClaimFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客服公共信息处理
 * by Louis
 */
@RestController
@RequestMapping("/csCommon")
public class CustomServiceCommonController extends BaseController {

    @Autowired
    private ClaimFlowService claimFlowService;

    @PostMapping("/internal/selectPolicyList")
    public TableDataInfo selectPolicyList(@RequestBody ClaimFlowDTO claimFlowDTO)
    {
        logger.info("查询保单列表入参：{}",claimFlowDTO);
        TableDataInfo tableDataInfo = claimFlowService.selectPolicyList(claimFlowDTO);
        return tableDataInfo;
    }
    @PostMapping("/internal/getPolicyInfo")
    public AjaxResult getPolicyInfoBy(@RequestBody ClaimFlowDTO claimFlowDTO)
    {
        return claimFlowService.getPolicyInfoBy(claimFlowDTO);
    }


}
