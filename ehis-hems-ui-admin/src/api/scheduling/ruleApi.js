import request from '@/utils/request'


/*抽检规则  */
export function listInfo(data) {
  return request({
    url: '/claimmgt/checkrule/selectClaimCaseCheckRule',
    method: 'post',
    data: data
  })
}

// 新增
export function addInfo(data) {
  return request({
    url: '/claimmgt/checkrule/addRule',
    method: 'post',
    data
  })
}

// editRule
export function editInfo(data) {
  return request({
    url: '/claimmgt/checkrule/editRule',
    method: 'post',
    data
  })
}
