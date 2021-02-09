package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.service.IComplaintAcceptVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投诉
 */
@RestController
@RequestMapping("/complaint")
public class CustomServiceComplaintController extends BaseController {

    @Autowired
    private IComplaintAcceptVoService iComplaintAcceptVoService;

    /**
     * 投诉页面待处理
     * @param acceptDTO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/complaint/selectComplaintAcceptVoList")
    public TableDataInfo selectComplaintAcceptVoList(AcceptDTO acceptDTO) {
        startPage();
        List<DemandAcceptVo> list = iComplaintAcceptVoService.selectComplaintAcceptVoList(acceptDTO);
        return getDataTable(list);
    }

    @GetMapping("/accept")
    public AjaxResult selectComplaintAcceptVo(@RequestBody String workOrderNo){
        ComplaintAcceptVo complaintAcceptVo=iComplaintAcceptVoService.selectComplaintAcceptVo(workOrderNo);
        return AjaxResult.success(complaintAcceptVo);
    }

    /**
     * 投诉页面已处理
     * @param acceptDTO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/complaint/selectComplaintAcceptVoListOne")
    public TableDataInfo selectComplaintAcceptVoListOne(AcceptDTO acceptDTO) {
        startPage();
        List<DemandAcceptVo> list = iComplaintAcceptVoService.selectComplaintAcceptVoListOne(acceptDTO);
        return getDataTable(list);
    }

    /**
     * 投诉新增页面
     * @param complaintAcceptVo
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "增加 ", businessType = BusinessType.INSERT)
    @PutMapping("/serviceAdd")
    public AjaxResult serviceAdd(@Validated @RequestBody ComplaintAcceptVo complaintAcceptVo)
    {
        complaintAcceptVo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        complaintAcceptVo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        complaintAcceptVo.setBusinessType("信息需求");
        complaintAcceptVo.setWorkOrderNo("9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6));
        return toAjax(iComplaintAcceptVoService.insertComplaintInfo(complaintAcceptVo));
    }

    @PreAuthorize("@ss.hasPermi('system:customService::edit')")
    @Log(title = "修改 ", businessType = BusinessType.UPDATE)
    @PutMapping("/updateComplaintAcceptVo")
    public AjaxResult updateComplaintAcceptVo(@Validated @RequestBody ComplaintAcceptVo complaintAcceptVo)
    {
        return toAjax(iComplaintAcceptVoService.updateComplaintAcceptVo(complaintAcceptVo));
    }
}
