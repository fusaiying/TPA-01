import request from '@/utils/request'


/*抽检规则  */
export function listInfo(query) {
  return request({
    url: '/system/on/list',
    method: 'get',
    params: query
  })
}

// 新增/修改
export function editInfo(data) {
  return request({
    url: '/system/on/add',
    method: 'put',
    data
  })
}


// 删除
export function delInfo(id) {
  return request({
    url: '/system/on/delete/'+id,
    method: 'delete',
  })
}
