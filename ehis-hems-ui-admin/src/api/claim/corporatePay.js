import request from '@/utils/request'
import data from "../../views/system/dict/data";

// 转账失败查询
export function transferFailedList(data) {
  return request({
    url: 'finance/payInfo/TransferFailedList',
    method: 'post',
    data:data
  })
}

// 对公支付初始化查询
export function initList(data) {
  return request({
    url: 'finance/pay/initList',
    method: 'get',
    params:data
  })
}
// 对公支付查询按钮
export function list(data) {
  return request({
    url: 'finance/pay/list',
    method: 'post',
    data: data
  })
}

// 开始对公支付
export function startPay(batchNo) {
  return request({
    url: '/finance/pay/'+batchNo,
    method: 'get'
  })
}

// 回退
export function rollback(rptNo) {
  return request({
    url: 'finance/pay/rollback',
    method: 'get',
    params: rptNo
  })
}
// 案件借款
export function borrowingCase(data) {
  return request({
    url: '/finance/pay/borrowingCase',
    method: 'post',
    data: data
  })
}
// 确认支付
export function confirmPayment(data) {
  return request({
    url: '/finance/pay/confirmPayment',
    method: 'post',
    data: data
  })
}


// 外币主页初始化
export function initForeignList(data) {
  return request({
    url: 'finance/pay/initForeignList',
    method: 'get',
    params: data
  })
}
//外币查询按钮
export function foreignList(data) {
  return request({
    url: 'finance/pay/foreignList',
    method: 'post',
    data: data
  })
}
//外币查询按钮
export function startForeignPay(foreignBatchNo) {
  return request({
    url: '/finance/pay/foreignBatchNo?batchNo='+foreignBatchNo,
    method: 'get'
  })
}

