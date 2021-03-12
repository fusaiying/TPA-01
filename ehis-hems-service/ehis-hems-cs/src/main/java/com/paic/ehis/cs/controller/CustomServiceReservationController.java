package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationDealVo;
import com.paic.ehis.cs.service.IEditInfoService;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import com.paic.ehis.cs.service.IWorkHandleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IEditInfoService iEditInfoService;
    @Autowired
    private IWorkHandleInfoService iWorkHandleInfoService;


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


//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "增加 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceAdd")
    public AjaxResult reservationAdd(@Validated @RequestBody ReservationAcceptVo reservationAcceptVo)
    {reservationAcceptVo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        reservationAcceptVo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        reservationAcceptVo.setBusinessType("02");
        String workOrderNo="9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6);
        reservationAcceptVo.setWorkOrderNo(workOrderNo);
        return iReservationAcceptVoService.insertServiceInfo(reservationAcceptVo) > 0 ? AjaxResult.success(workOrderNo) : AjaxResult.error();

    }

//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "修改 ", businessType = BusinessType.UPDATE)
    @PutMapping("/updateReservationAcceptVo")
    public AjaxResult updateReservationAcceptVo(@Validated @RequestBody ReservationAcceptVo reservationAcceptVo)
    {
        return toAjax(iReservationAcceptVoService.updateReservationAcceptVo(reservationAcceptVo));
    }


    /**
     * 预约取消页面提交按钮
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "提交 ", businessType = BusinessType.INSERT)
    @PutMapping("/orderCancelSubmit")
    public AjaxResult orderCancelSubmit (@Validated @RequestBody ReservationAcceptVo reservationAcceptVo)
    {
        return toAjax(iEditInfoService.orderCancelSubmit(reservationAcceptVo));
    }

    /**
     *预约处理页面  服务处理
     */
//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "增加 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceProcessing")
    public AjaxResult serviceProcessing(@Validated @RequestBody ReservationDealVo reservationDealVo) {
        if (reservationDealVo.getSign().equals("01")) {
            return toAjax(iWorkHandleInfoService.insertResevationDeal(reservationDealVo));
        } else {
            return toAjax(iWorkHandleInfoService.insertResevationSaveDeal(reservationDealVo));
        }
    }

}
