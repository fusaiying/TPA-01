package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.api.domain.SysUser;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.system.domain.HmpOrderUser;
import com.paic.ehis.system.domain.HmpServiceOrderApplication;
import com.paic.ehis.system.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpServiceOrderMapper;
import com.paic.ehis.system.domain.HmpServiceOrder;
import com.paic.ehis.system.service.IHmpServiceOrderService;

/**
 * 服务工单Service业务层处理
 *
 * @author sino
 * @date 2020-11-17
 */
@Service
public class HmpServiceOrderServiceImpl implements IHmpServiceOrderService
{
    @Autowired
    private HmpServiceOrderMapper hmpServiceOrderMapper;

    /**
     * 查询服务工单
     *
     * @param ordercode 服务工单ID
     * @return 服务工单
     */
    @Override
    public HmpServiceOrder selectHmpServiceOrderById(String ordercode)
    {
        return hmpServiceOrderMapper.selectHmpServiceOrderById(ordercode);
    }

    /**
     * 查询服务工单列表
     *
     * @param hmpServiceOrder 服务工单
     * @return 服务工单
     */
    @Override
    public List<HmpServiceOrder> selectHmpServiceOrderList(HmpServiceOrder hmpServiceOrder)
    {
        return hmpServiceOrderMapper.selectHmpServiceOrderList(hmpServiceOrder);
    }

    /**
     * 新增服务工单
     *
     * @param hmpServiceOrder 服务工单
     * @return 结果
     */
    @Override
    public int insertHmpServiceOrder(HmpServiceOrder hmpServiceOrder)
    {
        hmpServiceOrder.setCreateTime(DateUtils.getNowDate());
        return hmpServiceOrderMapper.insertHmpServiceOrder(hmpServiceOrder);
    }

    /**
     * 修改服务工单
     *
     * @param hmpServiceOrder 服务工单
     * @return 结果
     */
    @Override
    public int updateHmpServiceOrder(HmpServiceOrder hmpServiceOrder)
    {
        hmpServiceOrder.setUpdateTime(DateUtils.getNowDate());
        return hmpServiceOrderMapper.updateHmpServiceOrder(hmpServiceOrder);
    }

    /**
     * 批量删除服务工单
     *
     * @param ordercodes 需要删除的服务工单ID
     * @return 结果
     */
    @Override
    public int deleteHmpServiceOrderByIds(String[] ordercodes)
    {
        return hmpServiceOrderMapper.deleteHmpServiceOrderByIds(ordercodes);
    }

    /**
     * 删除服务工单信息
     *
     * @param ordercode 服务工单ID
     * @return 结果
     */
    @Override
    public int deleteHmpServiceOrderById(String ordercode)
    {
        return hmpServiceOrderMapper.deleteHmpServiceOrderById(ordercode);
    }

    /**
     * 根据产品查询供应商
     *
     * @param productcode 产品
     * @return 结果
     */
    @Override
    public List<SysDictData> selectServiceComByProduct(String productcode){
        return hmpServiceOrderMapper.selectServiceComByProduct(productcode);
    }

    /**
     * 根据供应商查询网点
     *
     * @param serviceCom 供应商
     * @return 结果
     */
    @Override
    public List<SysDictData> selectServiceWebByCom(String serviceCom){
        return hmpServiceOrderMapper.selectServiceWebByCom(serviceCom);
    }

    @Override
    public HmpServiceOrder selectHmpServiceOrderByCode(String ordercode) {
        return hmpServiceOrderMapper.selectHmpServiceOrderByCode(ordercode);
    }

    @Override
    public int updateHmpServiceOrderStatus(HmpServiceOrder hmpServiceOrder) {
        return hmpServiceOrderMapper.updateHmpServiceOrderStatus(hmpServiceOrder);
    }

    /**
     * 查询所有未分配服务工单信息
     *
     * @param orderInfo
     * @return
     */
    @Override
    public List<HmpServiceOrder> selectServiceOrderList(HmpServiceOrder orderInfo) {
        return hmpServiceOrderMapper.selectServiceOrderList(orderInfo);
    }

    /**
     * 查询当前登录用户的服务任务
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<HmpServiceOrder> selectServiceOrderListByOnwer(SysUser sysUser) {
        return hmpServiceOrderMapper.selectServiceOrderListByOnwer(sysUser);
    }

    /**
     * 查询用户ID
     *
     * @param ownerName
     * @return
     */
    @Override
    public String getOwnerIdByName(String ownerName){
        return hmpServiceOrderMapper.getOwnerIdByName(ownerName);
    }

    /**
     * 修改服务工单—处理人表
     *
     * @param orderUser
     * @return
     */
    @Override
    public int updateHmpOrderUser(HmpOrderUser orderUser) {
        return hmpServiceOrderMapper.updateHmpOrderUser(orderUser);
    }

    /**
     * 新增服务工单-处理人表数据
     *
     * @param orderUser
     * @return
     */
    @Override
    public int insertHmpOrderUser(HmpOrderUser orderUser) {
        return hmpServiceOrderMapper.insertHmpOrderUser(orderUser);
    }

    /**
     * 根据工单编号查询服务信息
     *
     * @param ordercode
     * @return
     */
    @Override
    public HmpServiceOrder getServiceOrderByOrderCode(String ordercode) {
        return hmpServiceOrderMapper.getServiceOrderByOrderCode(ordercode);
    }

    /**
     * 根据工单编号查询使用人信息
     *
     * @param ordercode
     * @return
     */
    @Override
    public HmpServiceOrderApplication getServicePersonByOrderCode(String ordercode) {
        return hmpServiceOrderMapper.getServicePersonByOrderCode(ordercode);
    }

    /**
     * 查询服务工单-处理人表
     *
     * @param orderUser
     * @return
     */
    @Override
    public List<HmpOrderUser> selectOrderUser(HmpOrderUser orderUser) {
        return hmpServiceOrderMapper.selectOrderUser(orderUser);
    }
}
