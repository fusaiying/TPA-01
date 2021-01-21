import request from '@/utils/request'

// 查询撤件重开
export function selectRestartCase(data) {
  return request({
    url: 'claim/claim/reopen/selectRestartCase',
    method: 'post',
    data
  })
}
// 查询补材号
export function getSupplementaryno(data) {
  return request({
    url: 'claim/claim/reopen/selectSupplementaryno',
    method: 'post',
    data
  })
}
// 查询材料、退件、影像件信息
export function getSupplementInfo(data) {
  return request({
    url: 'claim/supplementUpload/getList',
    method: 'post',
    data
  })
}
// 确认重开处理
export function insertRestartCase(data) {
  return request({
    url: 'claim/claim/reopen/insertRestartCase',
    method: 'post',
    data
  })
}
// 保存退件信息
export function addreturnInformation(data) {
  return request({
    url: 'claim/supplementUpload/addreturnInformation',
    method: 'post',
    data
  })
}
// 保存处理结论
export function addSupplementaryMaterial(data) {
  return request({
    url: 'claim/supplementUpload/addSupplementaryMaterial',
    method: 'post',
    data
  })
}
// 查询已有材料
export function gethadMaterial(data) {
  return request({
    url: 'claim/claim/reopen/gethadMaterial',
    method: 'post',
    data
  })
}