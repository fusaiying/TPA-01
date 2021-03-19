import request from '@/utils/request'

// 查询service_balance_info(服务结算基本信息)列表
export function listBalance2(query) {
  return request({
    url: '/order/balance/list2',
    method: 'post',
    data: query
  })
}

// 查询service_balance_info(服务结算基本信息)列表默认
export function listBalance2Default(query) {
  return request({
    url: '/order/balance/list2Default',
    method: 'get',
    params: query
  })
}

// 删除service_balance_info(服务结算基本信息)
export function updateBalance(data) {
  return request({
    url: '/order/balance/update',
    method: 'post',
    data: data
  })
}

// 新增service_balance_info(服务结算基本信息)-编辑
export function addBalance(data) {
  return request({
    url: '/order/balance',
    method: 'post',
    data: data
  })
}

//获取按结算明细列表-编辑
export function listBalanceDetail(query) {
  return request({
    url: '/order/balanceDetail/detailList',
    method: 'post',
    data: query
  })
}

//获取按结算明细列表-明细
export function listBalanceDetail_1(query) {
  return request({
    url: '/order/balanceDetail/list',
    method: 'post',
    data: query
  })
}

//获取按结算明细列表-明细
export function listBalanceDetail_2(query) {
  return request({
    url: '/order/balanceDetail/list2',
    method: 'post',
    data: query
  })
}
//结算清单
export function listBalanceDetail_3(query) {
  return request({
    url: '/order/balanceDetail/list3',
    method: 'get',
    params: query
  })
}

//获取按结算审核日志列表
export function listBalanceExamLog(query) {
  return request({
    url: '/order/balanceExamLog/list',
    method: 'post',
    data: query
  })
}

// 查询service_balance_invoice(服务结算发票信息)列表
export function listBalanceInvoice(query) {
  return request({
    url: '/order/balanceInvoice/list',
    method: 'post',
    data: query
  })
}

// 新增service_balance_invoice(服务结算发票信息)-编辑
export function addBalanceInvoice(data) {
  return request({
    url: '/order/balanceInvoice',
    method: 'post',
    data: data
  })
}

// 修改/删除service_balance_invoice(服务结算发票信息)
export function updateBalanceInvoice(data) {
  return request({
    url: '/order/balanceInvoice',
    method: 'put',
    data: data
  })
}

// 查询service_balance_file_info(服务结算文件信息)列表
export function listBalanceFile(query) {
  return request({
    url: '/order/balanceFile/list',
    method: 'post',
    data: query
  })
}

// 修改/删除service_balance_file_info(服务结算文件信息)列表
export function updateBalanceFile(data) {
  return request({
    url: '/order/balanceFile',
    method: 'put',
    data: data
  })
}
