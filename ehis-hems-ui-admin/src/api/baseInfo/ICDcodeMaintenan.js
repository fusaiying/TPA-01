import request from '@/utils/request'
// 获取列表
export function getICDList(query) {
  return request({
    url: '/provider/icd10/list',
    method: 'post',
    data: query
  })
}

// 新增页面
export function addICDCode(data) {
  return request({
    url: '/provider/icd10',
    method: 'post',
    data: data
  })
}

// 编辑页面
export function updateICDCode(data) {
  return request({
    url: '/provider/icd10',
    method: 'put',
    data: data
  })
}

// 删除页面
export function deleteICDCode(icdcode) {
  return request({
    url: '/provider/icd10/' + icdcode,
    method: 'delete'
  })
}

//单个删除
export function removeOneICDCode(data) {
    return request({
      url: '/provider/icd10/removeOne',
      method: 'post',
      data: data
    })
}

