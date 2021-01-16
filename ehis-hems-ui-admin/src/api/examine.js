import request from '@/utils/request'
let sapi = 'claim'
/** 结案补财审批列表 **/
export function list(data) {
  return request({
    url: `${sapi}/approvalSupplementaryMaterials/getList`,
    method: 'post',
    data
  })
}
/** 结案补财详情列表 **/
export function getApprovalsupplementaryMaterial(data) {
  return request({
    url: `${sapi}/approvalSupplementaryMaterials/getApprovalsupplementaryMaterial`,
    method: 'post',
    data
  })
}
/** 结案审批 **/
export function updateApprovalsupplementaryMaterial(data) {
  return request({
    url: `${sapi}/approvalSupplementaryMaterials/updateApprovalsupplementaryMaterial`,
    method: 'post',
    data
  })
}