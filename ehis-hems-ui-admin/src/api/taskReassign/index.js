import request from '@/utils/request'

// 查询list
export function getList(data) {
  return request({
    url: 'claim/claim/task/getTaskList',
    method: 'post',
    data
  })
}

// 改派
export function taskReassign(data) {
  return request({
    url: 'claim/claim/task/updateCase',
    method: 'post',
    data
  })
}

/** 操作人列表 **/
export function getOperatorList(data) {
  return request({
    url: '/claim/claim/claimGroup/queryOperationgroupUsers',
    method: 'post',
    data
  })
}

// 查询所有作业组信息
export function queryOperationgroupInfo(data) {
  return request({
    url: '/claim/claim/claimGroupProvide/queryOperationgroupInfo',
    method: 'post',
    data
  })
}

// 查询用户所在作业组信息
export function queryOperationgroupDetailInfo(data) {
  return request({
    url: '/claim/claim/claimGroupProvide/queryOperationgroupDetailInfo',
    method: 'post',
    data
  })
}

/** 作业组管理 查询保险公司下的所属机构 **/
export function queryBSMangecom(data) {
  return request({
    url: 'claim/claim/claimGroup/queryBSManagecom',
    method: 'post',
    data
  })
}
/** 作业组管理 查TPA下的所属机构 **/
export function queryTPAMangecom(data) {
  return request({
    url: 'claim/claim/claimGroup/queryTPAManagecom',
    method: 'post',
    data
  })
}

/** 作业组管理 根据机构查作业组 **/
export function queryMangecomsGroup(data) {
  return request({
    url: 'claim/claim/claimGroupProvide/queryGroupReturnOperationgroupDetail',
    method: 'post',
    data
  })
}