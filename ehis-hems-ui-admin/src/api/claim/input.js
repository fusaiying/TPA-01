import request from '@/utils/request'

// 处理中工作池：
export function processingList(data) {
  return request({
    url: '/system/bill/processingList',
    method: 'get',
    params:data
  })
}
//已处理工作池
export function accomplishList(data) {
  return request({
    url: '/system/bill/accomplishList',
    method: 'get',
    params:data
  })
}


