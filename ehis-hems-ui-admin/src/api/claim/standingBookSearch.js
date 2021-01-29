import request from '@/utils/request'

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
