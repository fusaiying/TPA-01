package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimProductDuty;

/**
 * 责任信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface ClaimProductDutyMapper 
{
    /**
     * 查询责任信息 
     * 
     * @param dutyCode 责任信息 ID
     * @return 责任信息 
     */
    public ClaimProductDuty selectClaimProductDutyById(String dutyCode,String planCode);

    /**
     * 查询责任信息 列表
     * 
     * @param claimProductDuty 责任信息 
     * @return 责任信息 集合
     */
    public List<ClaimProductDuty> selectClaimProductDutyList(ClaimProductDuty claimProductDuty);

    /**
     * 新增责任信息 
     * 
     * @param claimProductDuty 责任信息 
     * @return 结果
     */
    public int insertClaimProductDuty(ClaimProductDuty claimProductDuty);

    /**
     * 修改责任信息 
     * 
     * @param claimProductDuty 责任信息 
     * @return 结果
     */
    public int updateClaimProductDuty(ClaimProductDuty claimProductDuty);

    /**
     * 删除责任信息 
     * 
     * @param dutyCode 责任信息 ID
     * @return 结果
     */
    public int deleteClaimProductDutyById(String dutyCode);

    /**
     * 批量删除责任信息 
     * 
     * @param dutyCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimProductDutyByIds(String[] dutyCodes);
}
