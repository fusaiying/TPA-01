package com.paic.ehis.claimcal.service;



import com.paic.ehis.claimcal.domain.ClaimProductDutyDetail;
import com.paic.ehis.claimcal.domain.ClaimProductDutyDetailInfo;

import java.util.List;

/**
 * 责任明细信息 Service接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface IClaimProductDutyDetailService 
{
    /**
     * 查询责任明细信息 
     * 
     * @param dutyDetailCode 责任明细信息 ID
     * @return 责任明细信息 
     */
    public ClaimProductDutyDetail selectClaimProductDutyDetailById(String dutyDetailCode);

    /**
     * 查询责任明细信息 列表
     * 
     * @param claimProductDutyDetail 责任明细信息 
     * @return 责任明细信息 集合
     */
    public List<ClaimProductDutyDetail> selectClaimProductDutyDetailList(ClaimProductDutyDetail claimProductDutyDetail);

    /**
     * 查询责任明细信息 列表
     *
     * @param claimProductDutyDetail 责任明细信息
     * @return 责任明细信息 集合
     */
    public List<ClaimProductDutyDetailInfo> selectClaimProductDutyDetailInfoList(ClaimProductDutyDetail claimProductDutyDetail);

    /**
     * 新增责任明细信息 
     * 
     * @param claimProductDutyDetail 责任明细信息 
     * @return 结果
     */
    public int insertClaimProductDutyDetail(ClaimProductDutyDetail claimProductDutyDetail);

    /**
     * 修改责任明细信息 
     * 
     * @param claimProductDutyDetail 责任明细信息 
     * @return 结果
     */
    public int updateClaimProductDutyDetail(ClaimProductDutyDetail claimProductDutyDetail);

    /**
     * 批量删除责任明细信息 
     * 
     * @param dutyDetailCodes 需要删除的责任明细信息 ID
     * @return 结果
     */
    public int deleteClaimProductDutyDetailByIds(String[] dutyDetailCodes);

    /**
     * 删除责任明细信息 信息
     * 
     * @param dutyDetailCode 责任明细信息 ID
     * @return 结果
     */
    public int deleteClaimProductDutyDetailById(String dutyDetailCode);
}
