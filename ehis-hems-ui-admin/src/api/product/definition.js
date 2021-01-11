import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sinoutils";

// 查询产品列表
export function listProduct(query) {
  return request({
    url: '/product/definition/list',
    method: 'get',
    params: query
  })
}

// 查询服务类型
export function getServiceType(query) {
  return request({
    url: '/product/definition/getServiceType',
    method: 'get',
    params: query
  })
}

// 查询产品类别
export function getProductClass(query) {
  return request({
    url: '/product/definition/getProductClass',
    method: 'get',
    params: query
  })
}

// 查询产品细类
export function getProductSubClass(query) {
  return request({
    url: '/product/definition/getProductSubClass',
    method: 'get',
    params: query
  })
}

// 删除产品
export function delProductInfo(productids) {
  return request({
    url: '/product/definition/'+productids,
    method: 'delete'
  })
}

// 查询发布产品列表
export function getProductPublishList(){
  return request({
    url: '/product/definition/getProductPublishList',
    method: `get`,
  })
}
