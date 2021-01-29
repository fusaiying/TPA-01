import request from '@/utils/request'


/*案件分配规则  */
export function listInfo(query) {
  return request({
    url: '/system/whitelist/list',
    method: 'get',
    params: query
  })
}

// 新增/修改
export function editInfo(data) {
  return request({
    url: '/system/whitelist/add',
    method: 'post',
    data
  })
}


// 删除
export function delInfo(id) {
  return request({
    url:  '/system/whitelist/remove/'+ id,
    method: 'delete',
  })
}
