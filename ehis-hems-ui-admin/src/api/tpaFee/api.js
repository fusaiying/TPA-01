import request from '@/utils/request'

// 查询出单公司信息列表————下拉框
export function companyList(query) {
  return request({
    url: '/provider/issuingcompany/list',
    method: 'post',
    data: query
  })
}

//险种
export function riskList(query) {
  return request({
    url: '/provider/rule/riskList',
    method: 'get',
    params: query
  })
}

//服务费结算列表
export function listInfo(query) {
  return request({
    url: '/finance/tpaTask/tpaList',
    method: 'get',
    params: query
  })
}



// 任务发起
export function initiateTask(query) {
  return request({
    url: '/finance/tpaTask/tpaTaskInitiated',
    method: 'get',
    params: query
  })
}

// 查询子页面载入
export function taskViewDetail(query) {
  return request({
    url: '/finance/tpaTask/taskViewDetail',
    method: 'get',
    params: query
})
}

// 确认阶段删除结算任务
export function updateTask(data) {
  return request({
    url: '/finance/tpaTask',
    method: 'put',
    data: data
  })
}

// 确认TPA服务费结算
export function updateConfirm(settleTaskNo) {
  return request({
    url: '/finance/tpaTask/' + settleTaskNo,
    method: 'put',
    data: settleTaskNo
  })
}

// 导出TPA服务费结算
export function exportInitiate(query) {
  return request({
    url: '/finance/tpaTask/exportInitiate',
    method: 'post',
 // #  params: query  此处？传参&拼接
})
}


//tpa 收款明细
export function receiptColList(query) {
  return request({
    url: '/finance/tpaTask/tpaReceiptColList',
    method: 'get',
    params: query
  })
}
