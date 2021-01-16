package com.paic.ehis.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paic.ehis.system.domain.ClaimProductDuty;
import com.paic.ehis.system.domain.ClaimProductDutyDetail;
import com.paic.ehis.system.domain.ClaimProductDutyDetailInfo;
import com.paic.ehis.system.mapper.ClaimProductDutyDetailMapper;
import com.paic.ehis.system.mapper.ClaimProductDutyMapper;
import com.paic.ehis.system.service.IClaimProductDutyDetailService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 责任明细信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimProductDutyDetailServiceImpl implements IClaimProductDutyDetailService
{
    @Autowired
    private ClaimProductDutyDetailMapper claimProductDutyDetailMapper;

    @Autowired
    private ClaimProductDutyMapper claimProductDutyMapper;
    /**
     * 查询责任明细信息 
     * 
     * @param dutyDetailCode 责任明细信息 ID
     * @return 责任明细信息 
     */
    @Override
    public ClaimProductDutyDetail selectClaimProductDutyDetailById(String dutyDetailCode)
    {
        return claimProductDutyDetailMapper.selectClaimProductDutyDetailById(dutyDetailCode);
    }

    /**
     * 查询责任明细信息 列表
     * 
     * @param claimProductDutyDetail 责任明细信息 
     * @return 责任明细信息 
     */
    @Override
    public List<ClaimProductDutyDetail> selectClaimProductDutyDetailList(ClaimProductDutyDetail claimProductDutyDetail)
    {
        return claimProductDutyDetailMapper.selectClaimProductDutyDetailList(claimProductDutyDetail);
    }

    /**
     * 查询责任明细信息 列表
     *
     * @param claimProductDutyDetail 责任明细信息
     * @return 责任明细信息
     */
    @Override
    public List<ClaimProductDutyDetailInfo> selectClaimProductDutyDetailInfoList(ClaimProductDutyDetail claimProductDutyDetail)
    {
        List<ClaimProductDutyDetailInfo> detailInfoList = new ArrayList<>();
        System.out.println(claimProductDutyDetail.getPlanCode());
        List<ClaimProductDutyDetail> details = claimProductDutyDetailMapper.selectClaimProductDutyDetailList(claimProductDutyDetail);
        for (ClaimProductDutyDetail detail : details) {
            ClaimProductDutyDetailInfo detailInfo = new ClaimProductDutyDetailInfo();
            BeanUtils.copyProperties(detail,detailInfo);
            ClaimProductDuty duty = claimProductDutyMapper.selectClaimProductDutyById(detail.getDutyCode(),detail.getPlanCode());
            if (StringUtils.isNotNull(duty)) {
                detailInfo.setDutyName(duty.getDutyName());
                detailInfoList.add(detailInfo);
            }
        }
        return detailInfoList;
    }

    /**
     * 新增责任明细信息 
     * 
     * @param claimProductDutyDetail 责任明细信息 
     * @return 结果
     */
    @Override
    public int insertClaimProductDutyDetail(ClaimProductDutyDetail claimProductDutyDetail)
    {
        claimProductDutyDetail.setCreateTime(DateUtils.getNowDate());
        return claimProductDutyDetailMapper.insertClaimProductDutyDetail(claimProductDutyDetail);
    }

    /**
     * 修改责任明细信息 
     * 
     * @param claimProductDutyDetail 责任明细信息 
     * @return 结果
     */
    @Override
    public int updateClaimProductDutyDetail(ClaimProductDutyDetail claimProductDutyDetail)
    {
        claimProductDutyDetail.setUpdateTime(DateUtils.getNowDate());
        return claimProductDutyDetailMapper.updateClaimProductDutyDetail(claimProductDutyDetail);
    }

    /**
     * 批量删除责任明细信息 
     * 
     * @param dutyDetailCodes 需要删除的责任明细信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductDutyDetailByIds(String[] dutyDetailCodes)
    {
        return claimProductDutyDetailMapper.deleteClaimProductDutyDetailByIds(dutyDetailCodes);
    }

    /**
     * 删除责任明细信息 信息
     * 
     * @param dutyDetailCode 责任明细信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductDutyDetailById(String dutyDetailCode)
    {
        return claimProductDutyDetailMapper.deleteClaimProductDutyDetailById(dutyDetailCode);
    }
}
