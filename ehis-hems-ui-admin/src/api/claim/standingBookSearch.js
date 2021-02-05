import request from '@/utils/request'

// 初始化查询：
export function listFirst(data) {
  return request({
    url: '/system/standing/listFirst',
    method: 'get',
    params:data
  })
}
// 查询：
export function listNew(data) {
  return request({
    url: '/system/standing/listNew',
    method: 'get',
    params:data
  })
}

// 修改：
export function editStanding(data) {
  return request({
    url: '/system/standing/update',
    method: 'put',
    data:data
  })
}
//根据部门得到操作人
export function getUser(data) {
  return request({
    url: '/system/user/getusername',
    method: 'get',
    params:data
  })
}
//获取部门
export function getDept(parentId) {
  return request({
    url: '/system/dept/getdepts',
    method: 'get',
    params:parentId
  })
}
//根据部门Id获取部门
export function getDeptById(deptId) {
  return request({
    url: 'system/dept/'+deptId,
    method: 'get',
  })
}
