package com.paic.ehis.order.service;

import com.paic.ehis.order.domain.HmpOrdOwnInfo;

import java.util.List;

/**
 * 个人订单Service接口
 * 
 * @author sino
 * @date 2020-09-24
 */
public interface IHmpOrdOwnInfoService
{
    /**
     * 查询个人订单
     * 
     * @param personalorderno 个人订单ID
     * @return 个人订单
     */
    public HmpOrdOwnInfo selectHmpOrdOwnInfoById(String personalorderno);

    /**
     * 查询个人订单列表
     * 
     * @param hmpordowninfo 个人订单
     * @return 个人订单集合
     */
    public List<HmpOrdOwnInfo> selectHmpOrdOwnInfoList(HmpOrdOwnInfo hmpordowninfo);

    /**
     * 新增个人订单
     * 
     * @param hmpordowninfo 个人订单
     * @return 结果
     */
    public int insertHmpOrdOwnInfo(HmpOrdOwnInfo hmpordowninfo);

    /**
     * 修改个人订单
     * 
     * @param hmpordowninfo 个人订单
     * @return 结果
     */
    public int updateHmpOrdOwnInfo(HmpOrdOwnInfo hmpordowninfo);

    /**
     * 批量删除个人订单
     * 
     * @param appnos 需要删除的个人订单ID
     * @return 结果
     */
    public int deleteHmpOrdOwnInfoByIds(String[] appnos);

    /**
     * 删除个人订单信息
     * 
     * @param appno 个人订单ID
     * @return 结果
     */
    public int deleteHmpOrdOwnInfoById(String appno);
}
