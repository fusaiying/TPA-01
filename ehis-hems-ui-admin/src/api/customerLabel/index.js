import request from '@/utils/request'
// 查询客户列表
export function getCustomerLableList(data) {
  return request({
    url: 'claim/claim/customerLabel/getCustomerLabelList',
    method: 'post',
    data
  })
}

// 删除客户标签
export function deleteLabel(data) {
  return request({
    url: 'claim/claim/customerLabel/deleteLabel',
    method: 'post',
    data
  })
}

// 客户标签查询
export function getCustomerLableInfo(data) {
  return request({
    url: 'claim/claim/customerLabel/getCustomerLabelInfo',
    method: 'post',
    data
  })
}

// 修改客户标签
export function updateLabel(data) {
  return request({
    url: 'claim/claim/customerLabel/updateLabel',
    method: 'post',
    data
  })
}

// 新增客户标签
export function saveLabel(data) {
  return request({
    url: 'claim/claim/customerLabel/saveLabel',
    method: 'post',
    data
  })
}
