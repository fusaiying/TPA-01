import request from '@/utils/request'

//发票归档
export function invoiceData(query) {
  return request({
    url: '/system/filing/selectInvoiceFile',
    method: 'get',
    params: query
  })
}

//编辑发票归档
export function updateInvoice(data) {
  return request({
    url: '/system/filing/updateInvoiceFile',
    method: 'put',
    data
  })
}
