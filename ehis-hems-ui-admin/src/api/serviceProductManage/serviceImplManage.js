import request from '@/utils/request'

/**
 * 供应商查询
 */
export function getSupplierInfo() {
  return request({
    url: '/order/info/getSupplierInfo',
    method: 'post',
  })
}
/**
 * 产品查询
 */
export function getProductInfo() {
  return request({
    url: '/order/info/getProductInfo',
    method: 'post',
  })
}
/**
 * 首页列表
 */
export function getList(data) {
  return request({
    url: '/order/info/list',
    method: 'post',
    data: data
  })
}

/**
 * 首页列表默认查询
 */
export function list3Months(data) {
  return request({
    url: '/order/info/list3Months',
    method: 'get',
    params: data
  })
}
/**
 * 工单详细信息查询
 */
export function queryinfo(data) {
  return request({
    url: '/order/info/queryinfo',
    method: 'post',
    data
  })
}
/**
 * 医院科室查询
 */
export function getHosptialInfo() {
  return request({
    url: '/order/info/getHosptialInfo',
    method: 'post'
  })
}
/**
 * 客户信息查询
 */
export function getCustomerInfo(data) {
  return request({
    url: '/order/info/getCustomerInfo',
    method: 'post',
    data: data
  })
}
/**
 * 查询服务-供应商-联系人联动关系
 */
export function getAllProSuppInfo(data) {
  return request({
    url: '/order/info/getAllProSuppInfo',
    method: 'post',
    data: data
  })
}
/**
 * 分配
 */
export function allocation(data) {
  return request({
    url: '/order/info',
    method: 'put',
    data: data
  })
}
/**
 * 评价列表查询
 */
export function getEvaluateInfo(data) {
  return request({
    url: '/order/info/getEvaluateInfo',
    method: 'post',
    data: data
  })
}
/**
 * 工单取消
 */
export function cancalOrder(data) {
  return request({
    url: '/order/info/cancalOrder',
    method: 'post',
    data: data
  })
}









