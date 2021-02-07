package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 预约
 */
@RestController
@RequestMapping("/reservation")
public class CustomServiceReservationController extends BaseController {

    @Autowired
    private IReservationAcceptVoService iReservationAcceptVoService;

//    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(AcceptDTO acceptDTO) {
        startPage();
        List<ReservationAcceptVo> list = iReservationAcceptVoService.selectReservationAcceptVoList(acceptDTO);
        return getDataTable(list);
    }

//    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPersonalPool")
    public TableDataInfo listAndPersonalPool(AcceptDTO acceptDTO) {
        startPage();
        List<ReservationAcceptVo> list = iReservationAcceptVoService.selectReservationAcceptVoList2(acceptDTO);
        return getDataTable(list);
    }


}
