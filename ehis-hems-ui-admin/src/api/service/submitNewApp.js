import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sinoutils";

// 查询客户列表
export function queryCustomerList(query) {
  return request({
    url: '/system/order/queryCustomerList',
    method: 'get',
    params: query
  })
}

// 查询服务产品列表
export function queryProductList(query) {
  return request({
    url: '/system/order/queryProductList',
    method: 'get',
    params: query
  })
}

// 查询供应商
export function getServiceCom(query) {
  return request({
    url: '/system/order/getServiceCom',
    method: 'get',
    params: query
  })
}

// 查询供应商网点
export function getServiceWeb(query) {
  return request({
    url: '/system/order/getServiceWeb',
    method: 'get',
    params: query
  })
}

// 获取客户信息
export function getCustomerInfo(customerid) {
  return request({
    url: '/system/order/getCustomerInfo',
    method: 'post',
    data: customerid
  })
}

// 获取客户服务信息
export function getCustomerServiceInfo(customerid,productcode) {
  return request({
    url: '/system/order/getCustomerServiceInfo',
    method: 'post',
    data: {customerid:customerid,productcode:productcode}
  })
}

// 获取既往受益人
export function getUserCustomerList(query) {
  return request({
    url: '/system/order/getUserCustomerList',
    method: 'get',
    params: query
  })
}

// 提交申请
export function saveOrderInfo(customerid,productcode,userInfo,orderInfo) {
  return request({
    url: '/system/order/saveOrderInfo',
    method: 'post',
    data: {customerService:{customerid:customerid,productcode:productcode},serviceOrderApplication:userInfo,serviceOrder:orderInfo}
  })
}
