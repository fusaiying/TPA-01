import request from '@/utils/request'

// 处理中工作池：
export function processingList(data) {
  return request({
    url: '/system/bill/processingList',
    method: 'post',
    data:data
  })
}
//已处理工作池
export function accomplishList(data) {
  return request({
    url: '/system/bill/accomplishList',
    method: 'post',
    data:data
  })
}


