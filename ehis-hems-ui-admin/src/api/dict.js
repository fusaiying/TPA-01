import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'system/api/dict',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'system/api/dict/' + id,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: 'system/api/dict',
    method: 'put',
    data
  })
}

export function downloadDict(params) {
  return request({
    url: 'system/api/dict/download',
    method: 'get',
    params,
    responseType: 'blob'
  })
}
