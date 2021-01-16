import request from '@/utils/request'

// 查询个人订单列表
export function listPersonal(query) {
  return request({
    url: '/order/personal/list',
    method: 'get',
    params: query
  })
}

// 查询个人订单详细
export function getPersonal(personalorderno) {
  return request({
    url: '/order/personal/' + personalorderno,
    method: 'get'
  })
}

// 新增个人订单
export function addPersonal(data) {
  return request({
    url: '/order/personal',
    method: 'post',
    data: data
  })
}

// 修改个人订单
export function updatePersonal(data) {
  return request({
    url: '/order/personal',
    method: 'put',
    data: data
  })
}

// 删除个人订单
export function delPersonal(appno) {
  return request({
    url: '/order/personal/' + appno,
    method: 'delete'
  })
}
