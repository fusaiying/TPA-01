import request from '@/utils/request'
let sapi = 'claim'
/** 案件流量详情列表 **/
export function getDetailList(data) {
  return request({
    url: `${sapi}/personalWorkbench/getCaseFlowDetailsList`,
    method: 'post',
    data
  })
}
/** 折线图列表 **/
export function lineChart(data) {
  return request({
    url: `${sapi}/personalWorkbench/getCirculationOfCasesList`,
    method: 'post',
    data
  })
}
/** 作业组下拉列表 **/
export function getWorkGroupList(data) {
  return request({
    url: `${sapi}/claim/claimGroupProvide/queryOperationgroupInfo`,
    method: 'post',
    data
  })
}
/** 查询当前用户的信息及权限 **/
export function getPersonalMessage(data) {
  return request({
    url: `${sapi}/claim/claimGroupProvide/queryOperationgroupDetailInfo`,
    method: 'post',
    data
  })
}
/** 今天待处理量 **/
export function getDetail(data) {
  return request({
    url: `${sapi}/personalWorkbench/getDealWithTodayList`,
    method: 'post',
    data
  })
}