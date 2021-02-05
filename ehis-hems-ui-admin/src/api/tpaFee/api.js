import request from '@/utils/request'

// 查询出单公司信息列表————下拉框
export function companyList(query) {
  return request({
    url: '/system/issuingcompany/list',
    method: 'post',
    data: query
  })
}

//险种
export function riskList(query) {
  return request({
    url: '/system/rule/riskList',
    method: 'get',
    params: query
  })
}

//服务费结算列表
export function listInfo(query) {
  return request({
    url: '/system/tpaTask/tpaList',
    method: 'get',
    params: query
  })
}



// 任务发起
export function tpaTaskInitiated(query) {
  return request({
    url: '/system/tpaTask/tpaTaskInitiated',
    method: 'get',
    params: query
  })
}

// 查询子页面载入
export function selectFinanceTpaSettleTaskViewDetail(query) {
  return request({
    url: '/system/tpaTask/taskViewDetail',
    method: 'get',
    params: query
})
}

// 确认阶段删除结算任务
export function updateTask(data) {
  return request({
    url: '/system/task',
    method: 'put',
    data: data
  })
}

// 确认TPA服务费结算
export function confirm(query) {
  return request({
    url: '/system/tpaTask/confirm',
    method: 'get',
    params: query
  })
}

// 导出TPA服务费结算
export function exportInitiate(query) {
  return request({
    url: '/system/tpaTask/exportInitiate',
    method: 'post',
 // #  params: query  此处？传参&拼接
})
}
