import request from '@/utils/request'
let sapi = 'claim'
/** 案件流量详情列表 **/
export function getCaseList(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/findClaimInformationList`,
    method: 'post',
    data
  })
}
// 调查信息列表
export function getMessageList(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/queryClaimInfo`,
    method: 'post',
    data
  })
}
// 垫付信息列表
export function getAdvancePayList(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/getAdvancePayList`,
    method: 'post',
    data
  })
}
// 调查信息列表
export function getInvestigateList(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/getInvestigateList`,
    method: 'post',
    data
  })
}
// 赔案信息列表
export function getClaimInformationList(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/findClaimInformationList`,
    method: 'post',
    data
  })
}
// 保单信息下载
export function getClaimInformationListtt(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/getClaimInformationList`,
    method: 'post',
    data
  })
}
// 通过赔案号查被保人号
export function findInsurednoByClaimno(data) {
  return request({
    url: `${sapi}/ClaimComprehensive/findInsurednoByClaimno`,
    method: 'post',
    data
  })
}
// 理赔时查询调查信息
export function getClaimInvestigateList(data) {
  return request({
    url: 'claim/ClaimComprehensive/getClaimInvestigateList',
    method: 'post',
    data
  })
}
// 提调信息
export function getInvestigateInfo(data) {
  return request({
    url: 'claim/ClaimComprehensive/getInvestigateInfo',
    method: 'post',
    data
  })
}
// 提调信息
export function getInvestigateDetail(data) {
  return request({
    url: 'claim/ClaimComprehensive/getInvestigateDetail',
    method: 'post',
    data
  })
}
// 调查信息
export function getClaimInvestigateList2(data) {
  return request({
    url: 'claim/ClaimComprehensive/getInvestigateList2',
    method: 'post',
    data
  })
}
export function isMySystem4ClaimNo(data) {
  return request({
    url: `claim/ClaimComprehensive/isMySystem4ClaimNo?claimno=${data}`,
    method: 'get',
  })
}
export function getCustomerNo(data) {
  return request({
    url: `claim/ClaimComprehensive/getCustomerNo`,
    method: 'post',
    data
  })
}
