import request from '@/utils/request'

// 查询产品定义列表
export function listCustomer(query) {
  return request({
    url: '/query/customerInfo/list',
    method: 'get',
    params: query
  })
}
