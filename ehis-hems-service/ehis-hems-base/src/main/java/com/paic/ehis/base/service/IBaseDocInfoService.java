package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseDocInfo;

import java.util.List;

/**
 * base_doctor(医生信息)Service接口
 * 
 * @author sino
 * @date 2020-12-31
 */
public interface IBaseDocInfoService 
{
    /**
     * 查询base_doctor(医生信息)
     * 
     * @param docCode base_doctor(医生信息)ID
     * @return base_doctor(医生信息)
     */
    public BaseDocInfo selectBaseDocInfoById(String docCode);

    /**
     * 查询base_doctor(医生信息)列表
     * 
     * @param baseDocInfo base_doctor(医生信息)
     * @return base_doctor(医生信息)集合
     */
    public List<BaseDocInfo> selectBaseDocInfoList(BaseDocInfo baseDocInfo) ;

    //主查询页面需默认显示截止当前时间在一个月内且合约状态为“有效”的数据
    public List<BaseDocInfo> selectBaseMonth(BaseDocInfo baseDocInfo) throws Exception;

    public List<BaseDocInfo> selectSupplierList();

    /**
     * 新增base_doctor(医生信息)
     *
     * @param baseDocInfo base_doctor(医生信息)
     * @return 结果
     */
    public BaseDocInfo insertBaseDocInfo(BaseDocInfo baseDocInfo);

    /**
     * 修改base_doctor(医生信息)
     * 
     * @param baseDocInfo base_doctor(医生信息)
     * @return 结果
     */
    public int updateBaseDocInfo(BaseDocInfo baseDocInfo);

    /**
     * 批量删除base_doctor(医生信息)
     * 
     * @param docCodes 需要删除的base_doctor(医生信息)ID
     * @return 结果
     */
    public int deleteBaseDocInfoByIds(String[] docCodes);

    /**
     * 删除base_doctor(医生信息)信息
     * 
     * @param docCode base_doctor(医生信息)ID
     * @return 结果
     */
    public int deleteBaseDocInfoById(String docCode);

}
