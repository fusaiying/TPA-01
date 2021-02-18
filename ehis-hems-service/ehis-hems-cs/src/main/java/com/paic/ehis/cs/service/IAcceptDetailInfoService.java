package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.AcceptDetailInfo;

import java.util.List;


/**
 * 受理详情 Service接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface IAcceptDetailInfoService 
{
    /**
     * 查询受理详情 
     * 
     * @param workOrderNo 受理详情 ID
     * @return 受理详情 
     */
    public AcceptDetailInfo selectAcceptDetailInfoById(String workOrderNo);

    /**
     * 查询受理详情 列表
     * 
     * @param acceptDetailInfo 受理详情 
     * @return 受理详情 集合
     */
    public List<AcceptDetailInfo> selectAcceptDetailInfoList(AcceptDetailInfo acceptDetailInfo);



    /**
     * 新增受理详情 
     * 
     * @param acceptDetailInfo 受理详情 
     * @return 结果
     */
    public int insertAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo);

    /**
     * 修改受理详情 
     * 
     * @param acceptDetailInfo 受理详情 
     * @return 结果
     */
    public int updateAcceptDetailInfo(AcceptDetailInfo acceptDetailInfo);

    /**
     * 批量删除受理详情 
     * 
     * @param workOrderNos 需要删除的受理详情 ID
     * @return 结果
     */
    public int deleteAcceptDetailInfoByIds(String[] workOrderNos);

    /**
     * 删除受理详情 信息
     * 
     * @param workOrderNo 受理详情 ID
     * @return 结果
     */
    public int deleteAcceptDetailInfoById(String workOrderNo);
}
