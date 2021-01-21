import request from '@/utils/request'

// 查询产品包审核列表
export function listExamine(query) {
  return request({
    url: '/product/examine/list',
    method: 'get',
    params: query
  })
}

// 查询产品包审核详细
export function getExamine(productpackageno) {
  return request({
    url: '/product/examine/' + productpackageno,
    method: 'get'
  })
}

// 新增产品包审核
export function addExamine(data) {
  return request({
    url: '/examine/examine',
    method: 'post',
    data: data
  })
}

// 修改产品包审核
export function updateExamine(data) {
  return request({
    url: '/examine/examine',
    method: 'put',
    data: data
  })
}

// 删除产品包审核
export function delExamine(productpackageno) {
  return request({
    url: '/examine/examine/' + productpackageno,
    method: 'delete'
  })
}
