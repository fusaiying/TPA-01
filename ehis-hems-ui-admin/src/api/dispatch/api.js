import request from '@/utils/request'

//查询案件调度工作池
export function selectCaseDispatchList(query) {
  return request({
    url: '/claimflow/case/selectCaseDispatchList',
    method: 'post',
    data: query
  })
}

//案件调度修改操作人
export function dispatchUpdate(data) {
  return request({
    url: '/claimflow/case/Dispatch',
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
    method: 'post',
    data: query
  })
}
/*获取用户角色  */
export function roleInfo(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'get',
  })
}

/*获取登录用户信息  */
export function logInfo() {
  return request({
    url: '/system/user/getInfo',
    method: 'get',
  })
}

export function getTakeOnUserName(userName) {
  return request({
    url: '/claimmgt/on/getTakeOnUserName/'+userName,
    method: 'get',
  })
}
// 工作流查看
export function selectWorkflow(data) {
  return request({
    url: '/claimflow/caseRecord/selectWorkflow',
    method: 'post',
    data
  })
}// 工作流查看
export function selectWorkflowByRptNo(data) {
  return request({
    url: '/claimflow/caseRecord/selectWorkflowByRptNo',
    method: 'post',
    data
  })
}

