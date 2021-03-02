import request from '@/utils/request'

//追讨工作池
export function initDebt(data) {
  return request({
    url: '/claimflow/debt/initDebt',
    method: 'post',
    data: data
  })
}

//收款明细
export function initReceipt(data) {
  return request({
    url: '/claimflow/receipt/initReceipt',
    method: 'post',
    data: data
  })
}
