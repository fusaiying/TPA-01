package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 信息需求
 */
@RestController
@RequestMapping("/demand")
public class CustomServiceDemandController extends BaseController {
    @Autowired
    private IDemandAcceptVoService iDemandAcceptVoService;

    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(AcceptDTO acceptDTO) {
        startPage();
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectDemandAcceptList(acceptDTO);
        return getDataTable(list);
    }

   @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPersonalPool")
    public TableDataInfo listAndPersonalPool(AcceptDTO acceptDTO) {
        startPage();
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectDemandAcceptList2(acceptDTO);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.UPDATE)
    @PutMapping("/obtain")
    public AjaxResult edit(String workOrderNo)
    {
        return toAjax(iDemandAcceptVoService.updateStatus(workOrderNo));
    }


    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceAdd")
    public AjaxResult serviceAdd(@Validated @RequestBody DemandAcceptVo demandAcceptVo)
    {
        demandAcceptVo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        demandAcceptVo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        demandAcceptVo.setBusinessType("信息需求");
        demandAcceptVo.setWorkOrderNo("9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6));
        return toAjax(iDemandAcceptVoService.insertServiceInfo(demandAcceptVo));
    }
}
