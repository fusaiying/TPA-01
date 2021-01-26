import request from '@/utils/request'

//追讨工作池
export function initDebt(data) {
  return request({
    url: '/system/debt/initDebt',
    method: 'get',
    params: data
  })
}

//收款明细
export function initReceipt(data) {
  return request({
    url: '/system/receipt/initReceipt',
    method: 'get',
    params: data
  })
}
