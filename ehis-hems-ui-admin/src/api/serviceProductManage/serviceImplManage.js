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
    method: 'get',
    data
  })
}









