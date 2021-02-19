import request from '@/utils/request'


/*案件分配规则  */
export function roleAll(query) {
  return request({
    url: '/system/role/list',
    method: 'get',
    params: query
  })
}


/*案件分配规则  */
export function listInfo(query) {
  return request({
    url: '/claimmgt/dist/selectClaimCaseDist',
    method: 'get',
    params: query
  })
}

//修改
export function editInfo(data) {
  return request({
    url: '/claimmgt/dist/updateClaimCaseDistOne',
    method: 'put',
    data
  })
}

//案件分配一键均分
export function editInfoAverage(data) {
  return request({
    url: '/claimmgt/dist/updateClaimCaseAverage',
    method: 'put',
    data
  })
}
