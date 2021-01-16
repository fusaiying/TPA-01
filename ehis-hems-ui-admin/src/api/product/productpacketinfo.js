import request from '@/utils/request'

// 查询产品包信息列表
export function listProductpacketinfo(query) {
  return request({
    url: '/product/productpacketinfo/list',
    method: 'get',
    params: query
  })
}

// 查询产品包信息详细
export function getProductpacketinfo(productpackageno) {
  return request({
    url: '/product/productpacketinfo/' + productpackageno,
    method: 'get'
  })
}

// 新增产品包信息
export function addProductpacketinfo(data) {
  return request({
    url: '/product/productpacketinfo',
    method: 'post',
    data: data
  })
}

// 修改产品包信息
export function updateProductpacketinfo(data) {
  return request({
    url: '/product/productpacketinfo',
    method: 'put',
    data: data
  })
}

// 删除产品包信息
export function delProductpacketinfo(productpackageno) {
  return request({
    url: '/product/productpacketinfo/' + productpackageno,
    method: 'delete'
  })
}
