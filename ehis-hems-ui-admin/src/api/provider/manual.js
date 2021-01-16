import request from '@/utils/request'

// 查询服务手册列表
export function listInfo(query) {
  return request({
    url: '/provider/manual/list',
    method: 'get',
    params: query
  })
}

// 查询服务手册详细
export function getInfo(manualCode) {
  return request({
    url: '/provider/manual/' + manualCode,
    method: 'get'
  })
}

// 新增服务手册
export function addInfo(data) {
  return request({
    url: '/provider/manual',
    method: 'post',
    data: data
  })
}

// 修改服务手册
export function updateInfo(data) {
  return request({
    url: '/provider/manual',
    method: 'put',
    data: data
  })
}

// 删除服务手册
export function delInfo(manualCode) {
  return request({
    url: '/provider/manual/' + manualCode,
    method: 'delete'
  })
}
