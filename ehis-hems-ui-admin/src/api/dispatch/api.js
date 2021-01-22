import request from '@/utils/request'

//查询案件调度工作池
export function selectCaseDispatchList(query) {
  return request({
    url: '/system/case/selectCaseDispatchList',
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

