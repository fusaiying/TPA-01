package com.paic.ehis.cs.service.impl;

import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.mapper.QualityInspectionAcceptMapper;
import com.paic.ehis.cs.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 工单申请信息 Service业务层处理
 *
 * @author sino
 * @date 2021-01-21
 */
@Service
public class CommonServiceImpl implements ICommonService
{
    @Autowired
    private QualityInspectionAcceptMapper qualityInspectionAcceptMapper;

    @Override
    public List<AcceptVo> getWorkOrderCountByUserId(WorkOrderQueryDTO workOrderQueryDTO) {
        List<AcceptVo> acceptVoList= qualityInspectionAcceptMapper.getWorkOrderCountByUserId(workOrderQueryDTO);
        return acceptVoList;
    }
}
