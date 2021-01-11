import request from '@/utils/request'
// 保存抽检规则列表
export function saveSamplingRules(data) {
  return request({
    url: 'claim/claim/spotCheck/saveRule',
    method: 'post',
    data
  })
}

// 获取抽检规则列表
export function getSamplingRules(data) {
  return request({
    url: 'claim/claim/spotCheck/getCheckList',
    method: 'post',
    data
  })
}
// 获取抽检结果列表
export function getSamplingResults(data) {
  return request({
    url: 'claim/claim/spotCheck/getCheckResultList',
    method: 'post',
    data
  })
}

// 删除抽检结果列表
export function delSamplingResults(data) {
  return request({
    url: 'claim/claim/spotCheck/deleteCheckResult',
    method: 'post',
    data
  })
}

// 删除抽检规则
export function delSamplingRule(data) {
  return request({
    url: 'claim/claim/spotCheck/deleteRule',
    method: 'post',
    data
  })
}

// 抽检
export function updateCheck(data) {
  return request({
    url: 'claim/claim/spotCheck/checkCase',
    method: 'post',
    data
  })
}

// 抽检
export function startCheck(data) {
  return request({
    url: 'claim/claim/spotCheck/startCheck',
    method: 'post',
    data
  })
}

export function getGroupList(data) {
  return request({
    url: '/claim/claim/claimGroupProvide/queryOperationgroupDetailInfo',
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

// 抽检历史
export function checkHistory(data) {
  return request({
    url: '/claim/claim/spotCheck/getCheckHistoryList',
    method: 'post',
    data
  })
}

// 查询当前操作人信息
export function queryUser(data) {
  return request({
    url: '/claim/claim/claimGroupProvide/queryOperationgroupDetailInfo',
    method: 'post',
    data
  })
}
// 查询作业组
export function queryGroup(data) {
  return request({
    url: '/claim/claim/claimGroupProvide/queryGroupReturnOperationgroupDetail',
    method: 'post',
    data
  })
}

