import request from '@/utils/request'


/*用户调度  */
export function listInfo(query) {
  return request({
    url: '/claimmgt/on/list',
    method: 'get',
    params: query
  })
}

// 新增
export function addInfo(data) {
  return request({
    url: '/claimmgt/on/add',
    method: 'post',
    data
  })
}

// 修改
export function editInfo(data) {
  return request({
    url: '/claimmgt/on/update',
    method: 'put',
    data
  })
}
