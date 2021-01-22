import request from '@/utils/request'



// 本次问题件处理
export function updateProblem(data) {
  return request({
    url:  '/system/case/updatecaseStatus',
    method: 'put',
    data
  })
}

export function baseInfo(query) {
  return request({
    url: '/system/insured/list',
    method: 'get',
    params: query
  })
}

//查询 历史问题附件信息
export function historicalProblem(query) {
  return request({
    url: '/system/problem/selectHistoricalProblem',
    method: 'get',
    params: query
  })
}

//查询 问题件工作池  (待处理)
export function PendingData(query) {
  return request({
    url: '/system/problem/list2',
    method: 'get',
    params: query
  })
}

//查询 问题件工作池  (已处理)
export function processedData(query) {
  return request({
    url: '/system/problem/list1',
    method: 'get',
    params: query
  })
}


//案件调度修改操作人
export function dispatchUpdate(data) {
  return request({
    url: '/system/case/Dispatch',
    method: 'post',
    data: data
  })
}

//获取案件调度操作人  ，暂时查询所有用户
export function getDspatchUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

//查询出单信息
export function getIssuingcompanyList(query) {
  return request({
    url: '/provider/issuingcompany/list',
    method: 'get',
    params: query
  })
}

