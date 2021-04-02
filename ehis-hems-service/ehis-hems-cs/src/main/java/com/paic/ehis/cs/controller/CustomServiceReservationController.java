package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
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
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.system.api.BaseService;
import com.paic.ehis.system.api.domain.dto.BaseHospitalForReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private BaseService baseService;


    //预约待处理
//    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPublicPool")
    public TableDataInfo listAndPublicPool(AcceptDTO acceptDTO) {
        startPage();
        List<ReservationAcceptVo> list = iReservationAcceptVoService.selectReservationAcceptVoList(acceptDTO);
        return getDataTable(list);
    }

    //预约处理中
//    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/internal/listAndPersonalPool")
    public TableDataInfo listAndPersonalPool(AcceptDTO acceptDTO) {
        startPage();
        acceptDTO.setOperationBy(SecurityUtils.getUsername());
        List<ReservationAcceptVo> list = iReservationAcceptVoService.selectReservationAcceptVoList2(acceptDTO);
        return getDataTable(list);
    }

    //个人预约修改页面反显
    @GetMapping("/internal/PersonalPool")
    public AjaxResult PersonalPool(String workOrderNo) {
        startPage();
        logger.info("工单详情查询条件：{}", workOrderNo);
        ReservationAcceptVo reservationAcceptVo = iReservationAcceptVoService.selectReservationAcceptVoList3(workOrderNo);
        logger.info("工单详情查询结果：{}", reservationAcceptVo);
        return AjaxResult.success(reservationAcceptVo);
    }

//    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "增加 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceAdd")
    @Transactional
    public AjaxResult reservationAdd(@Validated @RequestBody ReservationAcceptVo reservationAcceptVo) {
        reservationAcceptVo.setBusinessType(CodeEnum.BUSINESS_TYPE_02.getCode());
        String workOrderNo="9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6);
        reservationAcceptVo.setWorkOrderNo(workOrderNo);

        reservationAcceptVo.setAcceptBy(SecurityUtils.getUsername());
        reservationAcceptVo.setAcceptTime(DateUtils.getNowDate());
        reservationAcceptVo.setCreateBy(SecurityUtils.getUsername());
        reservationAcceptVo.setCreateTime(DateUtils.getNowDate());
        reservationAcceptVo.setUpdateTime(DateUtils.getNowDate());
        reservationAcceptVo.setUpdateBy(SecurityUtils.getUsername());

        return iReservationAcceptVoService.insertServiceInfo(reservationAcceptVo) > 0 ? AjaxResult.success(workOrderNo) : AjaxResult.error();

    }

    //预约修改提交按钮
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

    /*预约点击【处理】保存处理时间*/
    @Log(title = "处理 ", businessType = BusinessType.INSERT)
    @GetMapping("/updateClickTime")
    public AjaxResult updateClickTime(AcceptDTO acceptDTO) {
        int a = iReservationAcceptVoService.updateClickTime(acceptDTO);
        return toAjax(1);
    }


    @PostMapping("/internal/selectHospital")
    public TableDataInfo selectHospital(@RequestBody BaseHospitalForReservationDTO baseHospitalForReservationDTO) {
        TableDataInfo tableDataInfo = baseService.selectHospitalForReservation(baseHospitalForReservationDTO);
        return tableDataInfo;
    }
}
