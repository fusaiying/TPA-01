package com.paic.ehis.system.mapper;

import com.paic.ehis.system.domain.ClaimProductFeeitem;

import java.util.List;

/**
 * 费用项信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface ClaimProductFeeitemMapper 
{
    /**
     * 查询费用项信息 
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 费用项信息 
     */
    public ClaimProductFeeitem selectClaimProductFeeitemById(String feeitemCode);

    /**
     * 查询费用项信息 列表
     * 
     * @param claimProductFeeitem 费用项信息 
     * @return 费用项信息 集合
     */
    public List<ClaimProductFeeitem> selectClaimProductFeeitemList(ClaimProductFeeitem claimProductFeeitem);

    /**
     * 新增费用项信息 
     * 
     * @param claimProductFeeitem 费用项信息 
     * @return 结果
     */
    public int insertClaimProductFeeitem(ClaimProductFeeitem claimProductFeeitem);

    /**
     * 修改费用项信息 
     * 
     * @param claimProductFeeitem 费用项信息 
     * @return 结果
     */
    public int updateClaimProductFeeitem(ClaimProductFeeitem claimProductFeeitem);

    /**
     * 删除费用项信息 
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 结果
     */
    public int deleteClaimProductFeeitemById(String feeitemCode);

    /**
     * 批量删除费用项信息 
     * 
     * @param feeitemCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimProductFeeitemByIds(String[] feeitemCodes);
}
