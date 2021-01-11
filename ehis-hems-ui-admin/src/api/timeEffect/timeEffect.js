import request from '@/utils/request'
const sapi = 'claim'
/** 操作人列表 **/
export function getOperatorList(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryOperationgroupUsers`,
    method: 'post',
    data
  })
}
/** 主页面列表 **/
export function getClaimList(data) {
  return request({
    url: `${sapi}/claim/limitation/getClaimList`,
    method: 'post',
    data
  })
}
/** 饼状图数据 **/
export function getPieInfo(data) {
  return request({
    url: `${sapi}/claim/limitation/getPieInfo`,
    method: 'post',
    data
  })
}

// 查询操作用户下作业组信息和作业组权限信息
export function getGroupList(data) {
  return request({
    url: '/claim/claim/claimGroupProvide/queryOperationgroupDetailInfo',
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
// 赔案清单下载
export function exportFile(data) {
  return request({
    url: '/claim/claim/case/exportFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
