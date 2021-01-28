package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 信息需求
 */
@RestController
@RequestMapping("/demand")
public class CustomServiceDemandController extends BaseController {
    /*@Autowired
    private PoolVoService poolVoService;


    *//***************************************************//*
    //公共池：数据来源
    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(DemandQuery demandQuery)
    {
        startPage();
//        List<ClaimProduct> list = claimProductService.selectClaimProductListAndPublicPool(claimProduct);
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        PoolVo poolVo=new PoolVo();
        poolVo.setBusinessType("DemandAcceptVo");
        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        acceptDetailInfo.setWorkOrderNo(demandQuery.getWorkOrderNo());

        List<PoolVo> poolVoList=poolVoService.selectPoolList(poolVo,acceptDetailInfo);
        return getDataTable(list);
    }*/
    @Autowired
    private IDemandAcceptVoService iDemandAcceptVoService;

    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(DemandAcceptVo demandAcceptVo) {
        startPage();
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectDemandAcceptList(demandAcceptVo);
        return getDataTable(list);
    }

   @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPersonalPool")
    public TableDataInfo listAndPersonalPool(DemandAcceptVo demandAcceptVo) {
        startPage();
        List<DemandAcceptVo> list = iDemandAcceptVoService.selectDemandAcceptList2(demandAcceptVo);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "获取 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(String workOrderNo)
    {
        return toAjax(iDemandAcceptVoService.updateStatus(workOrderNo));
    }


}
