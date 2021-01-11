import request from '@/utils/request'

// 查询出单公司信息列表
export function listIssuingcompany(query) {
  return request({
    url: '/system/issuingcompany/list',
    method: 'get',
    params: query
  })
}

// 查询出单公司信息详细
export function getIssuingcompany(companycode) {
  return request({
    url: '/system/issuingcompany/' + companycode,
    method: 'get'
  })
}

// 新增出单公司信息
export function addIssuingcompany(data) {
  return request({
    url: '/system/issuingcompany',
    method: 'post',
    data: data
  })
}

// 修改出单公司信息
export function updateIssuingcompany(data) {
  return request({
    url: '/system/issuingcompany',
    method: 'put',
    data: data
  })
}

// 删除出单公司信息
export function delIssuingcompany(companycode) {
  return request({
    url: '/system/issuingcompany/' + companycode,
    method: 'delete'
  })
}
//  查询出单公司开票信息
export function getInvoice(companycode) {
  return request({
    url: '/system/invoice/' + companycode,
    method: 'get'
  })
}
// 新增出单公司开票信息
export function addInvoice(data) {
  return request({
    url: '/system/invoice',
    method: 'post',
    data: data
  })
}
// 修改出单公司开票信息
export function updateInvoice(data) {
  return request({
    url: '/system/invoice',
    method: 'put',
    data: data
  })
}
  // 查询出单公司规则列表
export function listRule(query) {
    return request({
      url: '/system/rule/list',
      method: 'get',
      params: query
    })
  }

  // 新增和修改出单公司规则
  export function getRule(ruleno) {
    return request({
      url: '/system/rule/query',
      method: 'post',
      params: ruleno
    })

}
// 查询出单公司产品关联详细
export function getRiskrela(companycode) {
  return request({
    url: '/system/riskrela/' + companycode,
    method: 'get'
  })
}
