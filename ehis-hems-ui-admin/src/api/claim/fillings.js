import request from '@/utils/request'

// 补材公共池查询接口
export function getList(data) {
  return request({
    url: 'claim/supplementaryMaterialTreatment/getList',
    method: 'post',
    data
  })
}
// 审物理件工作池接口
export function getCommonList(data) {
  return request({
    url: 'claim/applicationPhysicalParts/getList',
    method: 'post',
    data
  })
}