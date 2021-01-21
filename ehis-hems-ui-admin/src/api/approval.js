import request from '@/utils/request'
let sapi = 'claim'
/** 结案补财申请列表 **/
export function getapprovalList(data) {
  return request({
    url: `${sapi}/closingSupplement/getList`,
    method: 'post',
    data
  })
}
/** 结案补财申请列表 **/
export function addClosingSupplement(data) {
  return request({
    url: `${sapi}/closingSupplement/addClosingSupplement`,
    method: 'post',
    data
  })
}
/** 结案补财申请列表 **/
export function getMater(data) {
  return request({
    url: `${sapi}/closingSupplement/getMater`,
    method: 'post',
    data
  })
}