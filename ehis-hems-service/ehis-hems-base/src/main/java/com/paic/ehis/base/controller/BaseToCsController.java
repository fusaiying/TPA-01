package com.paic.ehis.base.controller;

import com.paic.ehis.base.domain.BaseHospitalForReservationVo;
import com.paic.ehis.base.domain.dto.BaseHospitalForReservationDTO;
import com.paic.ehis.base.service.IBaseToCsService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基础信息模块提供客服模块功能
 * @date 2020-12-25
 */
@RestController
@RequestMapping("/baseToCs")
public class BaseToCsController extends BaseController
{
    @Autowired
    private IBaseToCsService baseToCsService;

    /** 提供客服预约中医疗机构查询*/
    @PostMapping("/hospital/forReservation")
    public TableDataInfo list(@RequestBody BaseHospitalForReservationDTO baseHospitalForReservationDTO)
    {
        logger.info("客服预约查询医疗机构接口入参：{}",baseHospitalForReservationDTO);
        List<BaseHospitalForReservationVo> list=baseToCsService.selectHospitalForReservation(baseHospitalForReservationDTO);
        logger.info("客服预约查询医疗机构接口结果：{}",list);
        return getDataTable(list);
    }
}
