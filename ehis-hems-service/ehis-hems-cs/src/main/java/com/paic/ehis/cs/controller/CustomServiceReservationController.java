package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预约
 */
@RestController
@RequestMapping("/reservation")
public class CustomServiceReservationController extends BaseController {

    @Autowired
    private IReservationAcceptVoService iReservationAcceptVoService;


    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(AcceptDTO acceptDTO) {
        startPage();
        List<ReservationAcceptVo> list = iReservationAcceptVoService.selectReservationAcceptVoList(acceptDTO);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPersonalPool")
    public TableDataInfo listAndPersonalPool(AcceptDTO acceptDTO) {
        startPage();
        List<ReservationAcceptVo> list = iReservationAcceptVoService.selectReservationAcceptVoList2(acceptDTO);
        return getDataTable(list);
    }


    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "增加 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceAdd")
    public AjaxResult reservationAdd(@Validated @RequestBody ReservationAcceptVo reservationAcceptVo)
    {
        return toAjax(iReservationAcceptVoService.insertServiceInfo(reservationAcceptVo));
    }

    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "修改 ", businessType = BusinessType.UPDATE)
    @PutMapping("/updateReservationAcceptVo")
    public AjaxResult updateReservationAcceptVo(@Validated @RequestBody ReservationAcceptVo reservationAcceptVo)
    {
        return toAjax(iReservationAcceptVoService.updateReservationAcceptVo(reservationAcceptVo));
    }



}
