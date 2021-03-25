import request from '@/utils/request'


/*案件分配规则  */
export function roleAll(data) {
  return request({
    url: '/claimmgt/dist/selectClaimUserRoleInfo',
    method: 'post',
    data: data
  })
}
/*获取用户角色  */
export function roleInfo(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'get',
  })
}

/*案件分配规则  */
export function listInfo(data) {
  return request({
    url: '/claimmgt/dist/selectClaimCaseDist',
    method: 'post',
    data: data
  })
}

//修改
export function editInfo(data) {
  return request({
    url: '/claimmgt/dist/editClaimCaseDist',
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
