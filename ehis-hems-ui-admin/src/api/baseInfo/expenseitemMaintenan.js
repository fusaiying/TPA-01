import request from '@/utils/request'

// 查询费用项信息列表
export function listFeeitem(query) {
  return request({
    url: '/provider/feeitem/list',
    method: 'post',
    data: query
  })
}

// 查询费用项信息详细
export function getFeeitem(feeitemcode) {
  return request({
    url: '/provider/feeitem/' + feeitemcode,
    method: 'get'
  })
}

// 新增费用项信息
export function addFeeitem(data) {
  return request({
    url: '/provider/feeitem',
    method: 'post',
    data: data
  })
}

// 修改费用项信息
export function updateFeeitem(data) {
  return request({
    url: '/system/feeitem',
    method: 'put',
    data: data
  })
}

// 删除费用项信息
export function delFeeitem(feeitemcode) {
  return request({
    url: '/provider/feeitem/' + feeitemcode,
    method: 'delete'
  })
}
