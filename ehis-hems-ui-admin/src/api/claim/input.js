import request from '@/utils/request'

// 处理中工作池：
export function processingList(data) {
  return request({
    url: '/claimflow/bill/processingList',
    method: 'post',
    data:data
  })
}
//已处理工作池
export function accomplishList(data) {
  return request({
    url: '/claimflow/bill/accomplishList',
    method: 'post',
    data:data
  })
}


