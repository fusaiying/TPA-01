package com.paic.ehis.system.mapper;

import com.paic.ehis.system.domain.ClaimProductDutyDetail;

import java.util.List;

/**
 * 责任明细信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface ClaimProductDutyDetailMapper 
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
     * 删除责任明细信息 
     * 
     * @param dutyDetailCode 责任明细信息 ID
     * @return 结果
     */
    public int deleteClaimProductDutyDetailById(String dutyDetailCode);

    /**
     * 批量删除责任明细信息 
     * 
     * @param dutyDetailCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimProductDutyDetailByIds(String[] dutyDetailCodes);
}
