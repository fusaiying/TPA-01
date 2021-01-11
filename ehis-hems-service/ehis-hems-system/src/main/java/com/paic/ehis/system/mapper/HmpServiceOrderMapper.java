package com.paic.ehis.system.mapper;

import java.util.List;

import com.paic.ehis.system.api.domain.SysUser;
import com.paic.ehis.system.domain.HmpOrderUser;
import com.paic.ehis.system.domain.HmpServiceOrder;
import com.paic.ehis.system.domain.HmpServiceOrderApplication;
import com.paic.ehis.system.domain.SysDictData;

/**
 * 服务工单Mapper接口
 *
 * @author sino
 * @date 2020-11-17
 */
public interface HmpServiceOrderMapper
{
    /**
     * 查询服务工单
     *
     * @param ordercode 服务工单ID
     * @return 服务工单
     */
    public HmpServiceOrder selectHmpServiceOrderById(String ordercode);

    /**
     * 查询服务工单列表
     *
     * @param hmpServiceOrder 服务工单
     * @return 服务工单集合
     */
    public List<HmpServiceOrder> selectHmpServiceOrderList(HmpServiceOrder hmpServiceOrder);

    /**
     * 新增服务工单
     *
     * @param hmpServiceOrder 服务工单
     * @return 结果
     */
    public int insertHmpServiceOrder(HmpServiceOrder hmpServiceOrder);

    /**
     * 修改服务工单
     *
     * @param hmpServiceOrder 服务工单
     * @return 结果
     */
    public int updateHmpServiceOrder(HmpServiceOrder hmpServiceOrder);

    /**
     * 删除服务工单
     *
     * @param ordercode 服务工单ID
     * @return 结果
     */
    public int deleteHmpServiceOrderById(String ordercode);

    /**
     * 批量删除服务工单
     *
     * @param ordercodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpServiceOrderByIds(String[] ordercodes);

    /**
     * 根据产品查询供应商
     *
     * @param productcode 产品
     * @return 结果
     */
    public List<SysDictData> selectServiceComByProduct(String productcode);

    /**
     * 根据供应商查询网点
     *
     * @param serviceCom 供应商
     * @return 结果
     */
    public List<SysDictData> selectServiceWebByCom(String serviceCom);

    public HmpServiceOrder selectHmpServiceOrderByCode(String ordercode);

    public int updateHmpServiceOrderStatus(HmpServiceOrder hmpServiceOrder);

    /**
     * 查询所有未分配服务工单信息
     *
     * @param orderInfo
     * @return
     */
    public List<HmpServiceOrder> selectServiceOrderList(HmpServiceOrder orderInfo);

    /**
     * 查询当前登录用户的服务任务
     *
     * @param sysUser
     * @return
     */
    public List<HmpServiceOrder> selectServiceOrderListByOnwer(SysUser sysUser);

    /**
     * 查询当前登录用户ID
     *
     * @param ownerName
     * @return
     */
    public String getOwnerIdByName(String ownerName);

    /**
     * 修改服务工单-处理人表
     *
     * @param orderUser
     * @return
     */
    public int updateHmpOrderUser(HmpOrderUser orderUser);

    /**
     * 新增服务工单-处理人表数据
     *
     * @param orderUser
     * @return
     */
    public int insertHmpOrderUser(HmpOrderUser orderUser);

    /**
     * 根据工单编号查询服务信息
     *
     * @param ordercode
     * @return
     */
    public HmpServiceOrder getServiceOrderByOrderCode(String ordercode);

    /**
     * 根据工单编号查询使用人信息
     *
     * @param ordercode
     * @return
     */
    public HmpServiceOrderApplication getServicePersonByOrderCode(String ordercode);

    /**
     * 查询服务工单-处理人表
     *
     * @param orderUser
     * @return
     */
    public List<HmpOrderUser> selectOrderUser(HmpOrderUser orderUser);
}
