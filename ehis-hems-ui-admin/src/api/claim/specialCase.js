import request from '@/utils/request'

// 查新所有特案报备规则
export function getAllSpecialRules(data) {
  return request({
    url: 'claim/claim/rule/getAllSpecialRules',
    method: 'post',
    data
  })
}
// 查询所有特案报备规则
export function addSpecialRules(data) {
  return request({
    url: 'claim/claim/rule/addSpecialRules',
    method: 'post',
    data
  })
}

// 查询报备处理
export function getSpecialHandlList(data) {
  return request({
    url: 'claim/claim/rule/getSpecialHandlList',
    method: 'post',
    data
  })
}

// 已处理-查看按钮
export function getByClaimnoComplete(data) {
  return request({
    url: 'claim/claim/rule/getByClaimnoComplete',
    method: 'post',
    data
  })
}

// 添加报备结论
export function addClaimConclusion(data) {
  return request({
    url: 'claim/claim/rule/addClaimConclusion',
    method: 'post',
    data
  })
}

// 查询所有险种名称
export function getInsuranceName(data) {
  return request({
    url: 'claim/claim/rule/getInsuranceName',
    method: 'post',
    data
  })
}
// 查询用户
export function getDealUserName(data) {
  return request({
    url: 'claim/claim/rule/getDealUserName',
    method: 'post',
    data
  })
}
// 查询上报公司
export function getAllBranch(data) {
  return request({
    url: 'claim/claim/rule/getAllBranch',
    method: 'post',
    data
  })
}


// 模糊查询
export function fuzzyQuery(data) {
  return request({
    url: 'claim/claim/rule/fuzzyQuery',
    method: 'post',
    data
  })
}

// 删除规则
export function deleteSpecialRule(data) {
  return request({
    url: 'claim/claim/rule/deleteSpecialRule',
    method: 'post',
    data
  })
}
