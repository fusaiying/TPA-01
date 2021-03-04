import request from '@/utils/request'

// 初始化查询：
export function listFirst(data) {
  return request({
    url: '/claimmgt/standing/listFirst',
    method: 'get',
    params:data
  })
}
// 查询：
export function listNew(data) {
  return request({
    url: '/claimmgt/standing/listNew',
    method: 'post',
    data:data
  })
}

// 修改：
export function editStanding(data) {
  return request({
    url: '/claimmgt/standing/update',
    method: 'put',
    data:data
  })
}
//根据部门得到操作人
export function getUser(data) {
  return request({
    url: '/claimmgt/user/getusername',
    method: 'get',
    params:data
  })
}
//获取部门  以前用的
export function getDept(parentId) {
  return request({
    url: '/claimmgt/dept/getdepts',
    method: 'get',
    params:parentId
  })
}
/*//获取部门
export function getDept() {
  return request({
    url: '/system/organInfo/getList',
    method: 'get',
  })
}*/
//根据部门Id获取部门
export function getDeptById(deptId) {
  return request({
    url: 'claimmgt/dept/'+deptId,
    method: 'get',
  })
}

//获取当前机构及下属信息 支持模糊查询
export function getOrganList(data) {
  return request({
    url: 'system/organInfo/getOrganList',
    method: 'post',
    data:data
  })
}
//获取当前用户
export function getUserInfo() {
  return request({
    url: 'system/user/getUserInfo',
    method: 'get',
  })
}
//通过机构编码获取用户 支持模糊查询/
export function getUsersByOrganCode(data) {
  return request({
    url: 'system/user/getUsersByOrganCode',
    method: 'post',
    data:data
  })
}
