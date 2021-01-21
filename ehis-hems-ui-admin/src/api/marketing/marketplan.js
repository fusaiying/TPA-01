import request from '@/utils/request'

// 查询marketplan列表
export function listMarketplan(query) {
  return request({
    url: '/marketing/marketplan/list',
    method: 'get',
    params: query
  })
}

// 查询marketplan详细
export function getMarketplan(programno) {
  return request({
    url: '/marketing/marketplan/' + programno,
    method: 'get'
  })
}

// 新增marketplan
export function addMarketplan(data) {
  return request({
    url: '/marketing/marketplan',
    method: 'post',
    data: data
  })
}

// 修改marketplan
export function updateMarketplan(data) {
  return request({
    url: '/marketing/marketplan',
    method: 'put',
    data: data
  })
}

// 删除marketplan
export function delMarketplan(programno) {
  return request({
    url: '/marketing/marketplan/' + programno,
    method: 'delete'
  })
}

