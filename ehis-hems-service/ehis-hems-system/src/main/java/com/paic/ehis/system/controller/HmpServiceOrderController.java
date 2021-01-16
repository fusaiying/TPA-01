package com.paic.ehis.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.*;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.api.domain.SysUser;
import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.service.IHmpBenefitCustomerService;
import com.paic.ehis.system.service.IHmpBenefitCustomerServiceService;
import com.paic.ehis.system.service.IHmpServiceOrderApplicationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.service.IHmpServiceOrderService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 服务工单Controller
 *
 * @author sino
 * @date 2020-11-17
 */
@RestController
@RequestMapping("/order")
public class HmpServiceOrderController extends BaseController
{
    @Autowired
    private IHmpServiceOrderService hmpServiceOrderService;

    @Autowired
    private IHmpServiceOrderApplicationService hmpServiceOrderApplicationService;

    @Autowired
    private IHmpBenefitCustomerService hmpBenefitCustomerService;

    @Autowired
    private IHmpBenefitCustomerServiceService hmpCustomerProductService;

//    /**
//     * 查询服务工单列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:order:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(HmpServiceOrder hmpServiceOrder)
//    {
//        startPage();
//        List<HmpServiceOrder> list = hmpServiceOrderService.selectHmpServiceOrderList(hmpServiceOrder);
//        return getDataTable(list);
//    }

    /**
     * 查询所有未分配的服务工单信息
     * @param serviceOrderInfo
     * @return
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody HmpServiceOrder serviceOrderInfo){
        startPage();
        List<HmpServiceOrder> orderInfoList = hmpServiceOrderService.selectServiceOrderList(serviceOrderInfo);
        return getDataTable(orderInfoList);
    }

    /**
     * 导出服务工单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "服务工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpServiceOrder hmpServiceOrder) throws IOException
    {
        List<HmpServiceOrder> list = hmpServiceOrderService.selectHmpServiceOrderList(hmpServiceOrder);
        ExcelUtil<HmpServiceOrder> util = new ExcelUtil<HmpServiceOrder>(HmpServiceOrder.class);
        util.exportExcel(response, list, "order");
    }

    /**
     * 获取服务工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{ordercode}")
    public AjaxResult getInfo(@PathVariable("ordercode") String ordercode)
    {
        return AjaxResult.success(hmpServiceOrderService.selectHmpServiceOrderById(ordercode));
    }

    /**
     * 新增服务工单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "服务工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpServiceOrder hmpServiceOrder)
    {
        return toAjax(hmpServiceOrderService.insertHmpServiceOrder(hmpServiceOrder));
    }

    /**
     * 修改服务工单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "服务工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpServiceOrder hmpServiceOrder)
    {
        return toAjax(hmpServiceOrderService.updateHmpServiceOrder(hmpServiceOrder));
    }

    /**
     * 删除服务工单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "服务工单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ordercodes}")
    public AjaxResult remove(@PathVariable String[] ordercodes)
    {
        return toAjax(hmpServiceOrderService.deleteHmpServiceOrderByIds(ordercodes));
    }

    /**
     * 查询客户列表
     */
    @GetMapping("/queryCustomerList")
    public TableDataInfo queryCustomerList(HmpBenefitCustomer hmpBenefitCustomer)
    {
        startPage();
        List<HmpBenefitCustomer> list = hmpBenefitCustomerService.selectHmpBenefitCustomerListUseChinese(hmpBenefitCustomer);
        return getDataTable(list);
    }

    /**
     * 查询服务产品列表
     */
    @GetMapping("/queryProductList")
    public TableDataInfo queryProductList(HmpBenefitCustomerService hmpBenefitCustomerService)
    {
        startPage();
        List<HmpBenefitCustomerService> list = hmpCustomerProductService.selectHmpBenefitCustomerServiceListUseChinese(hmpBenefitCustomerService);
        return getDataTable(list);
    }

    /**
     * 根据产品查询供应商
     */
    @GetMapping("/getServiceCom")
    public AjaxResult getServiceCom(String productcode)
    {
        return AjaxResult.success(hmpServiceOrderService.selectServiceComByProduct(productcode));
    }

    /**
     * 根据供应商查询网点
     */
    @GetMapping("/getServiceWeb")
    public AjaxResult getServiceWeb(String serviceCom)
    {
        return AjaxResult.success(hmpServiceOrderService.selectServiceWebByCom(serviceCom));
    }

    /**
     * 查询客户信息
     */
    @PostMapping("/getCustomerInfo")
    public AjaxResult getCustomerInfo(@RequestBody String customerid)
    {
        if (!StringUtils.isEmpty(customerid))
        {
            HmpBenefitCustomer customer = hmpBenefitCustomerService.selectHmpBenefitCustomerById(customerid);
            return AjaxResult.success(customer);
        }else {
            return AjaxResult.error("客户编码为空！");
        }
    }

    /**
     * 获取客户服务信息
     */
    @PostMapping("/getCustomerServiceInfo")
    public AjaxResult getCustomerServiceInfo(@RequestBody HmpBenefitCustomerService customerService)
    {
        List<HmpBenefitCustomerService> customerServices = hmpCustomerProductService.selectHmpBenefitCustomerServiceList(customerService);
        if(customerServices.size() <= 0){
            return AjaxResult.error("客户服务信息为空！");
        }
        if(customerServices.size() == 1){
            return AjaxResult.success(customerServices.get(0));
        }else {
            return AjaxResult.error("客户服务信息查询有误!");
        }
    }

    /**
     * 获取既往受益人
     */
    @PreAuthorize("@ss.hasPermi('system:order:getUserCustomerList')")
    @GetMapping("/getUserCustomerList")
    public TableDataInfo getUserCustomerList(HmpServiceOrder hmpServiceOrder)
    {
        startPage();
        //查询预约工单信息
        List<HmpServiceOrder> list = hmpServiceOrderService.selectHmpServiceOrderList(hmpServiceOrder);
        List<HmpServiceOrderApplication> result = new ArrayList<>();
        HmpServiceOrderApplication query = new HmpServiceOrderApplication();
        //循环工单信息，拿到客户产品服务下的所有使用人
        for (HmpServiceOrder order: list) {
            query.setOrdercode(order.getOrdercode());
            result.addAll(hmpServiceOrderApplicationService.selectHmpServiceOrderApplicationList(query));
        }
        return getDataTable(result);
    }

    /**
     * 提交申请
     */
    @PostMapping("/saveOrderInfo")
    public AjaxResult saveOrderInfo(@RequestBody ServiceOrderInfo serviceOrderInfo)
    {
        //获取参数信息
        HmpBenefitCustomerService customerService = serviceOrderInfo.getCustomerService();

        HmpServiceOrder serviceOrder = serviceOrderInfo.getServiceOrder();

        HmpServiceOrderApplication serviceOrderApplication = serviceOrderInfo.getServiceOrderApplication();

        //校验数据
        if(StringUtils.isEmpty(customerService.getCustomerid())){
            return AjaxResult.error("客户编码为空！");
        }
        List<HmpBenefitCustomerService> customerServices = hmpCustomerProductService.selectHmpBenefitCustomerServiceList(customerService);
        if(customerServices.size() <= 0){
            return AjaxResult.error("客户服务信息为空！");
        }
        //准备数据插入数据库
        String userName = SecurityUtils.getUsername();
        if(customerServices.size() == 1){

            serviceOrder.setOrdercode("OR"+ PubFun.createMySqlMaxNoUseCache("HmpServiceOrder",10,18));
            serviceOrder.setCustomerid(customerService.getCustomerid());
            serviceOrder.setProductcode(customerService.getProductcode());
            serviceOrder.setServicecode(customerServices.get(0).getServicecode());
            serviceOrder.setOrderstatus("01");
            serviceOrder.setCreateBy(userName);
            serviceOrder.setCreateTime(new Date());
            serviceOrder.setUpdateBy(userName);
            serviceOrder.setUpdateTime(new Date());

            serviceOrderApplication.setApplicationcode("SOA"+ PubFun.createMySqlMaxNoUseCache("HmpServiceOrderApplication",10,17));
            serviceOrderApplication.setOrdercode(serviceOrder.getOrdercode());
            serviceOrderApplication.setStatus("01");
            serviceOrderApplication.setCreateBy(userName);
            serviceOrderApplication.setCreateTime(new Date());
            serviceOrderApplication.setUpdateBy(userName);
            serviceOrderApplication.setUpdateTime(new Date());

            int i = hmpServiceOrderService.insertHmpServiceOrder(serviceOrder);

            if(i > 0){
                i = hmpServiceOrderApplicationService.insertHmpServiceOrderApplication(serviceOrderApplication);
            }else {
                return AjaxResult.error("插入服务预约工单信息有错误!");
            }
            if(i > 0){
                return AjaxResult.success("成功!");
            }else {
                return AjaxResult.error("插入服务使用人信息有错误!!");
            }
        }else {
            return AjaxResult.error("客户服务信息查询有误!");
        }
    }

    /**
     * 查询归属当前登录用户的服务工单信息
     *
     * @param sysUser
     * @return
     */
    @GetMapping("/ownerlist")
    public TableDataInfo ownerlist(SysUser sysUser){
        startPage();
        String username = SecurityUtils.getUsername();
        sysUser.setUserName(username);
        List<HmpServiceOrder> ownerServiceOrderList = hmpServiceOrderService.selectServiceOrderListByOnwer(sysUser);
        return getDataTable(ownerServiceOrderList);
    }

    /**
     * 任务获取
     *
     * @param ordercode
     * @return
     */
    @GetMapping("/taskGetOwner")
    public AjaxResult taskGetOwner(String ordercode){
        //获取当前登录用户名称
        String ownerName = SecurityUtils.getUsername();
        //获取登录用户ID
        String ownerId = hmpServiceOrderService.getOwnerIdByName(ownerName);

        HmpOrderUser orderUser = new HmpOrderUser();
        orderUser.setSerialnum(PubFun.createMySqlMaxNoUseCache("OrderUser",10,11));
        orderUser.setOrdercode(ordercode);
        orderUser.setUserId(ownerId);
        orderUser.setStatus("01");
        orderUser.setOperator(ownerName);
        orderUser.setMakedate(new Date());
        orderUser.setMaketime(PubFun.getCurrentTime());
        return toAjax(hmpServiceOrderService.insertHmpOrderUser(orderUser));
    }

    /**
     * 任务释放
     *
     * @param ordercode
     * @return
     */
    @GetMapping("/taskRelease")
    public AjaxResult taskRelease(String ordercode){
        //获取当前登录用户名称
        String ownerName = SecurityUtils.getUsername();

        HmpOrderUser orderUser = new HmpOrderUser();
        orderUser.setOrdercode(ordercode);
        orderUser.setStatus("00");
        orderUser.setModifyoperator(ownerName);
        orderUser.setModifydate(new Date());
        orderUser.setModifytime(PubFun.getCurrentTime());
        return toAjax(hmpServiceOrderService.updateHmpOrderUser(orderUser));
    }

    /**
     * 查询服务使用人信息
     *
     * @param ordercode
     * @return
     */
    @GetMapping("/queryPersonService")
    public AjaxResult queryPersonService(String ordercode) {
        if (!StringUtils.isEmpty(ordercode)){
            HmpServiceOrderApplication servicePerson = hmpServiceOrderService.getServicePersonByOrderCode(ordercode);
            return AjaxResult.success(servicePerson);
        } else {
            return AjaxResult.error("就诊人编码为空！");
        }
    }

    /**
     * 查询服务信息
     *
     * @param ordercode
     * @return
     */
    @GetMapping("/queryServiceOrder")
    public AjaxResult queryServiceOrder(String ordercode){
        if (!StringUtils.isEmpty(ordercode)){
            HmpServiceOrder serviceOrder = hmpServiceOrderService.getServiceOrderByOrderCode(ordercode);
            return AjaxResult.success(serviceOrder);
        } else {
            return AjaxResult.error("就诊人编码为空！");
        }
    }

    /**
     * 任务指派
     *
     * @return
     */
    @PostMapping("/tasksend")
    public AjaxResult taskSend(@RequestBody OrderUserInfo orderUserInfo){
        //获取当前登录用户名
        String username = SecurityUtils.getUsername();
        HmpServiceOrder serviceOrder = orderUserInfo.getServiceOrder();
        String ordercode = serviceOrder.getOrdercode();
        SysUser user = orderUserInfo.getUser();
        Long userId = user.getUserId();
        if (!StringUtils.isEmpty(ordercode) && !StringUtils.isEmpty(userId.toString())){
            HmpOrderUser orderUser = new HmpOrderUser();
            orderUser.setOrdercode(ordercode);
            orderUser.setStatus("01");
            //查询数据库是否该任务已经指派给其他人
            List<HmpOrderUser> orderUserList = hmpServiceOrderService.selectOrderUser(orderUser);
            //有则更改状态为00
            if (orderUserList.size() > 0) {
                orderUser.setStatus("00");
                orderUser.setModifyoperator(username);
                orderUser.setModifydate(new Date());
                orderUser.setModifytime(PubFun.getCurrentTime());
                hmpServiceOrderService.updateHmpOrderUser(orderUser);
            }
            orderUser.setUserId(userId.toString());
            orderUser.setStatus("01");
            orderUser.setOperator(username);
            orderUser.setMakedate(new Date());
            orderUser.setMaketime(PubFun.getCurrentTime());
            orderUser.setSerialnum(PubFun.createMySqlMaxNoUseCache("OrderUser",10,11));
            return toAjax(hmpServiceOrderService.insertHmpOrderUser(orderUser));
        } else {
            return AjaxResult.error("工单编号或者用户编码为空！");
        }

    }
}
