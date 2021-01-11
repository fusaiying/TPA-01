package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.BaseFeeitem;

/**
 * 费用项信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface BaseFeeitemMapper 
{
    /**
     * 查询费用项信息 
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 费用项信息 
     */
    public BaseFeeitem selectBaseFeeitemById(String feeitemCode);

    /**
     * 查询费用项信息 列表
     * 
     * @param baseFeeitem 费用项信息 
     * @return 费用项信息 集合
     */
    public List<BaseFeeitem> selectBaseFeeitemList(BaseFeeitem baseFeeitem);

    /**
     * 新增费用项信息 
     * 
     * @param baseFeeitem 费用项信息 
     * @return 结果
     */
    public int insertBaseFeeitem(BaseFeeitem baseFeeitem);

    /**
     * 修改费用项信息 
     * 
     * @param baseFeeitem 费用项信息 
     * @return 结果
     */
    public int updateBaseFeeitem(BaseFeeitem baseFeeitem);

    /**
     * 删除费用项信息 
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 结果
     */
    public int deleteBaseFeeitemById(String feeitemCode);

    /**
     * 批量删除费用项信息 
     * 
     * @param feeitemCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseFeeitemByIds(String[] feeitemCodes);
}
