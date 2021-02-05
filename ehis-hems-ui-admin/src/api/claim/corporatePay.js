import request from '@/utils/request'

// 转账失败查询
export function transferFailedList(data) {
  return request({
    url: 'system/payInfo/TransferFailedList',
    method: 'get',
    params:data
  })
}

// 对公支付初始化查询
export function initList(data) {
  return request({
    url: 'system/pay/initList',
    method: 'get',
    params:data
  })
}
// 对公支付查询按钮
export function list(data) {
  return request({
    url: 'system/pay/list',
    method: 'post',
    data: data
  })
}
// 回退
export function rollback(rptNo) {
  return request({
    url: 'system/pay/rollback',
    method: 'get',
    params: rptNo
  })
}

