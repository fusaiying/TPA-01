import request from '@/utils/request'

// 查询产品定义列表
export function listInformation(query) {
  return request({
    url: '/product/definition/list',
    method: 'get',
    params: query
  })
}

// 跳转页面
export function openView(url) {
  return request({
    url: '/system/definition/'+url,
    method: 'get',
  })
}

// 查询产品定义详细
export function getInformation(productid) {
  return request({
    url: '/system/information/' + productid,
    method: 'get'
  })
}

// 新增产品定义
export function addInformation(data) {
  return request({
    url: '/system/information',
    method: 'post',
    data: data
  })
}

// 修改产品定义
export function updateInformation(data) {
  return request({
    url: '/system/information',
    method: 'put',
    data: data
  })
}

// 删除产品定义
export function delInformation(productid) {
  return request({
    url: '/system/information/' + productid,
    method: 'delete'
  })
}
