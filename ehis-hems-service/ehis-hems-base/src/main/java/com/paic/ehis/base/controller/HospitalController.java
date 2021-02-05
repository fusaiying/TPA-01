package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.base.domain.vo.HSDTO;
import com.paic.ehis.base.domain.vo.HospitalDTO;
import com.paic.ehis.base.domain.vo.HospitalVO;
import com.paic.ehis.base.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController extends BaseController {

    @Autowired
    private HospitalService hospitalService;

    /**
     * 查询医院
     */
    //@PreAuthorize("@ss.hasPermi('provider:hospital:list')")
    @PostMapping("/list")
    /*public TableDataInfo list(HospitalDTO hospitalDTO)
    {
        startPage(hospitalDTO);
        List<HospitalVO> list=hospitalService.selectHospitalVOList(hospitalDTO);
        return getDataTable(list);
    }*/

    public AjaxResult selectHospitalVOList(@RequestBody HospitalDTO hospitalDTO){
        List<HospitalVO> list=hospitalService.selectHospitalVOList(hospitalDTO);
        return AjaxResult.success(list);
    }

    /**
     * 查询医院
     */
    //@PreAuthorize("@ss.hasPermi('provider:hospital:list')")
    @PostMapping("/hlist")
   /* public TableDataInfo list(HSDTO hSDTO)
    {
        startPage(hSDTO);
        List<HSVO> list=hospitalService.selectHSVOList(hSDTO);
        return getDataTable(list);
    }*/
    public AjaxResult selectHSVOList(@RequestBody HSDTO hSDTO){
        return AjaxResult.success(hospitalService.selectHSVOList(hSDTO));
    }



}
